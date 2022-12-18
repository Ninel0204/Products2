import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;


    public class Main {


        public static void main(String[] args) {
            File setupFile = new File("shop.xml");
            Setup setup = new Setup(setupFile);
            ClientLog clientLog = new ClientLog();
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

            File file = new File(setup.getLFileName());
            Basket basket = null;
            if (!file.exists()) {
                try {
                    if (setup.getLFormat().equalsIgnoreCase("json")) {
                        Files.copy(Path.of("template.json"), Path.of(String.valueOf(file)));
                        basket = Basket.Json1(file);
                    } else {
                        Files.copy(Path.of("template.txt"), Path.of(String.valueOf(file)));
                        basket = Basket.loadFromTxtFile(file);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                if (setup.getLFormat().equalsIgnoreCase("json")) {
                    if (setup.getLEnabled().equalsIgnoreCase("true")) {
                        basket = Basket.Json1(file);
                    } else {
                        basket = Basket.Json1(new File("template.json"));
                    }
                } else {
                    if (setup.getLEnabled().equalsIgnoreCase("true")) {
                        basket = Basket.loadFromTxtFile(file);
                    } else {
                        basket = Basket.loadFromTxtFile(new File("template.txt"));
                    }
                }
            }

            while (true) {

                System.out.println("Выберите товар и количество или введите end");
                String input = scanner.nextLine();
                if (input.equals("end")) {
                    if (setup.getLEnabled().equalsIgnoreCase("true")) {
                        File logFile = new File(setup.getLFileName());
                        clientLog.exportAsCSV(logFile);
                    }
                    break;
                }

                String[] number = input.split(" ");
                if (number.length != 2) {
                    System.out.println("В параметре необходимо указать товар и количество");
                    continue;
                }

                clientLog.log1(number[0], number[1]);
                try {
                    productNumber = Integer.parseInt(number[0]);
                    productCount = Integer.parseInt(number[1]);
                    x[productNumber] += productCount;
                    basket.addToCart(productNumber, productCount);
                    if (setup.getSFormat().equalsIgnoreCase("json")) {
                        if (setup.getSEnabled().equalsIgnoreCase("true")) {
                            basket.Json(new File(setup.getSFileName()));
                        }
                    } else {
                        if (setup.getSEnabled().equalsIgnoreCase("true")) {
                            basket.saveTxt(new File(setup.getSFileName()));                                                               //2 2
                        }
                    }
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

