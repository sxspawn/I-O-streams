package ru.kovbasa.streams;

import ru.kovbasa.streams.log.ClientLog;
import ru.kovbasa.streams.storage.IStorage;
import ru.kovbasa.streams.storage.JsonStorage;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ClientLog log = new ClientLog();
        IStorage storage;
        storage = new JsonStorage("basket.json");

        System.out.println("Программа \"Потребительская корзина!\"\n");

        Basket basket;
        if (storage.isStorageExists()) {
            basket = storage.loadBasket();
            basket.printCart();
        } else {
            String[] products = {"Хлеб", "Яблоки", "Молоко", "Кефир", "Селедка"};
            int[] prices = {46, 93, 78, 92, 154};
            basket = new Basket(products, prices);
        }

        basket.printProducts();

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
                log.log(productNumber, productQuantity);
                storage.saveBasket(basket);
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Неправильный формат ввода");
            }
        }

        System.out.println();
        basket.printCart();

        log.exportAsCSV(new File("log.csv"));
    }
}