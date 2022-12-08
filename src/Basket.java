import java.io.*;
import java.util.Arrays;

public class Basket {
    protected String[] products;
    protected int [] productsCountList;
    protected int [] prices;

    public Basket (String[]products,int [] prices) {
       this.products=products;
       this.prices=prices;
    }

    public void addToCart(int productNum, int amount){
        this.productsCountList[productNum]+=amount;

    }

    public void printCart(){
        System.out.println(" Список товаров: ");
        for(int i =0;i< products.length;i++){
            System.out.printf("Покупки: " + i+1,products[i],prices[i]);
        }

    }
    public void saveTxt(File textFile) {
        try (BufferedWriter out = new BufferedWriter(new PrintWriter(textFile))) {
            for (String s : products) {
                out.append(s + " ");
                out.newLine();
            }
            for (int y : prices) {
                out.append(y + " ");
                out.newLine();
            }
            for (int i : productsCountList) {
                out.append(i + " ");
                out.newLine();
                out.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static Basket loadFromTxtFile(File textFile)  {
            String[] products;
             int [] productsCountList;
             int [] prices;
            StringBuilder stringBuilder=new StringBuilder();
            try (BufferedReader bufferedReader=new BufferedReader(new FileReader(textFile))){
                String stripe;
                 while ((stripe= bufferedReader.readLine())!=null){
                     stringBuilder.append(stripe);
                     stringBuilder.append("/n");
                 }
                } catch (FileNotFoundException e) {
                e.printStackTrace();
             } catch (IOException e) {
                e.printStackTrace();
    }
          Object[] o = stringBuilder.toString().lines().toArray();
          products = o[0].toString().split(" ");
          prices = Arrays.stream(o[1].toString().split(" ")).mapToInt(n -> Integer.parseInt(n)).toArray();
           productsCountList = Arrays.stream(o[2].toString().split(" ")).mapToInt(n -> Integer.parseInt(n)).toArray();

            Basket basket = new Basket(products, prices);
            basket.setProductsCountList(productsCountList);
            return basket;
        }

   public void setProducts(String[] products) {
        this.products=products;
    }

    public int[] getProductsCountList() {
        return productsCountList;
    }

    public void setProductsCountList(int[]productsCountList) {
        this.productsCountList = productsCountList;
    }

    public int[] getPrices() {
        return prices;
    }

    public void setPrices(int[] prices) {
        this.prices = prices;
    }


}


