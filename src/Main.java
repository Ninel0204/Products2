
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] products = new String[]{"Молоко", "Хлеб", "Гречневая крупа"};
        int[] prices = new int[]{50, 14, 80};
        int[] x = new int[3];

        int sumol = 0;
        int productNumber = 0;
        int productCount = 0;
        int sumProducts = 0;

        System.out.println("Список возможных товаров для покупки");
        for (int i = 0; i < products.length; i++) {
            System.out.println(i + 1 + ". " + products[i] + "  " + prices[i] + " руб/шт");
        }

        File basketFile = new File("basket.txt");
        Basket basket = null;
        if (!basketFile.exists()) {
            try {
                Files.copy(Path.of("template.txt"), Path.of(String.valueOf(basketFile)));
                basket = Basket.loadFromTxtFile(basketFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            basket = Basket.loadFromTxtFile(basketFile);
        }
        basket.printCart();

        while (true) {

            System.out.println("Выберите товар и количество или введите end");
            String input = scanner.nextLine();
            if (input.equals("end")) {
                break;
            }

            String[] number = input.split(" ");
            if (number.length != 2) {
                System.out.println("В параметре необходимо указать товар и количество");
                continue;
            }
            try {
                productNumber = Integer.parseInt(number[0]);
                productCount = Integer.parseInt(number[1]);
                x[productNumber] += productCount;
                basket.addToCart(productNumber, productCount);
                basket.saveTxt(basketFile);
                if (productCount < 0) {
                    System.out.println("В параметре необходимо указать количество продукта положительное");
                }
            } catch (NumberFormatException a) {
                System.out.println("В параметре необходимо указать число или end");

            }
            System.out.println("Ваша корзина: ");

            for (int i = 0; i < x.length; i++) {

                if (x[i] != 0) {
                    sumol = x[i] * prices[i];
                    System.out.println(products[i] + " " + x[i] + " шт " + prices[i] + " руб/шт " + sumol + " руб в сумме");
                    sumProducts += sumol;
                }
            }
            System.out.println("Итого " + sumProducts + " руб ");

        }


    }
}









