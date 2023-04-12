package ru.kovbasa.streams;

import java.io.*;
import java.util.Arrays;

public class Basket implements Serializable {

    @Serial
    private static final long serialVersionUID = 4961751090262990205L;

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
        System.out.println("Общая сумма: " + sumCart + " руб.\n");
    }

    public void printProducts() {
        System.out.println("Список товаров для покупки:");
        for (int i = 0; i < products.length; i++) {
            System.out.println((i + 1) + ". " + products[i] + " = " + prices[i] + " руб.");
        }
        System.out.println();
    }

    public void saveBin(File file) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Basket loadFromBinFile(File file) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (Basket) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return "Basket:{" + "\n" +
                "\tproducts: " + Arrays.toString(products) + "\n" +
                "\tprices: " + Arrays.toString(prices) + "\n" +
                "\tproductsQuantity: " + Arrays.toString(productsQuantity) + "\n" +
                "}";
    }
}