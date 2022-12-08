import java.io.*;


public class Basket implements Serializable {
    protected String[] products;
    protected int[] productsCountList;
    protected int[] prices;

    public Basket(String[] products, int[] prices) {
        this.products = products;
        this.prices = prices;
    }

    public void addToCart(int productNum, int amount) {
        this.productsCountList[productNum] += amount;

    }

    public void printCart() {
        System.out.println(" Список товаров: ");
        for (int i = 0; i < products.length; i++) {
            System.out.printf("Покупки: " + i + 1, products[i], prices[i]);
        }

    }

    public void saveBin(File file) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        } catch (FileNotFoundException e) {
           e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
        }
    }

    public static Basket loadFromBinFile(File file) {
        Basket basket = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) in.readObject();
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return basket;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    public int[] getProductsCountList() {
        return productsCountList;
    }

    public void setProductsCountList(int[] productsCountList) {
        this.productsCountList = productsCountList;
    }

    public int[] getPrices() {
        return prices;
    }

    public void setPrices(int[] prices) {
        this.prices = prices;
    }


}


