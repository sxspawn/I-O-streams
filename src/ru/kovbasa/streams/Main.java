package ru.kovbasa.streams;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Программа \"Потребительская корзина!\"\n");
        Basket basket;
        File file = new File("basket.bin");
        if (file.exists()) {
            basket = Basket.loadFromBinFile(file);
            assert basket != null;
            basket.printCart();
        } else {
            String[] products = {"Хлеб", "Яблоки", "Молоко", "Кефир", "Селедка"};
            int[] prices = {46, 93, 78, 92, 154};
            basket = new Basket(products, prices);
        }

        basket.printProducts();
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите номер товара и его количество через пробел.\n" + "Для завершения программы и подсчета корзины введите: \"0\" и нажмите Enter.");
            String input = scanner.nextLine();
            if ("0".equals(input)) {
                System.out.println("Программа завершена!");
                break;
            }

            try {
                String[] parts = input.split(" ", 2);
                int productNumber = Integer.parseInt(parts[0]) - 1;
                int productQuantity = Integer.parseInt(parts[1]);
                basket.addToCart(productNumber, productQuantity);
                basket.saveBin(file);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Неправильный формат ввода! \n");
            }
        }

        basket.printCart();
    }
}