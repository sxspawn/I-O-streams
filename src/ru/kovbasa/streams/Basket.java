package ru.kovbasa.streams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Basket {

    public String[] products;
    public int[] prices;
    public int[] productsQuantity;

    public Basket(String[] products, int[] prices) {
        this(products, prices, new int[products.length]);
    }

    public Basket(String[] products, int[] prices, int[] productsQuantity) {
        this.products = products;
        this.prices = prices;
        this.productsQuantity = productsQuantity;
    }

    public void addToCart(int productNum, int amount) {
        productsQuantity[productNum] += amount;
    }

    public void printCart() {
        int sumCart = 0;
        System.out.println("\nВаша корзина: ");
        for (int i = 0; i < productsQuantity.length; i++) {
            if (productsQuantity[i] > 0) {
                System.out.println(products[i] + " - " + prices[i] + " руб.шт. * " + productsQuantity[i] + " шт. = " + (prices[i] * productsQuantity[i]) + " руб.");
            }
            sumCart += prices[i] * productsQuantity[i];
        }
        System.out.println("Общая сумма: " + sumCart + " руб.\r\n");
    }

    public void printProducts() {
        System.out.println("Список товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " = " + prices[i] + " руб.");
        }
    }

    public void saveTxt(File file) {
        PrintWriter out = null;
        try {
            out = new PrintWriter(file);
            out.println(Arrays.toString(products));
            out.println(Arrays.toString(prices));
            out.println(Arrays.toString(productsQuantity));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    public static Basket loadFromTxtFile(File file) {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(file));

            String productsStr = prepareString(in.readLine());
            String pricesStr = prepareString(in.readLine());
            String productsQuantityStr = prepareString(in.readLine());

            String[] products = Arrays.stream(productsStr.split(",")).map(String::trim).toArray(String[]::new);
            int[] prices = Arrays.stream(pricesStr.split(",")).mapToInt(s -> Integer.parseInt(s.trim())).toArray();
            int[] productsQuantity = Arrays.stream(productsQuantityStr.split(",")).mapToInt(s -> Integer.parseInt(s.trim())).toArray();

            return new Basket(products, prices, productsQuantity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
            }
        }

        return null;
    }

    private static String prepareString(String str) {
        if (str != null) {
            str = str.trim();
            if (!str.isEmpty() && str.charAt(0) == '[' && str.charAt(str.length() - 1) == ']') {
                str = str.substring(1, str.length() - 1);
            }
            return str;
        } else {
            return "";
        }
    }
}