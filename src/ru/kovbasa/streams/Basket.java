package ru.kovbasa.streams;

import java.io.Serializable;
import java.util.Arrays;

public class Basket implements Serializable {

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

    public String[] getProducts() {
        return products;
    }

    public int[] getPrices() {
        return prices;
    }

    public int[] getProductsQuantity() {
        return productsQuantity;
    }

    public void printCart() {
        int sumCart = 0;
        System.out.println("Ваша корзина: ");
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

    @Override
    public String toString() {
        return "Basket:{" + "\n" +
                "\tproducts: " + Arrays.toString(products) + "\n" +
                "\tprices: " + Arrays.toString(prices) + "\n" +
                "\tproductsQuantity: " + Arrays.toString(productsQuantity) + "\n" +
                "}";
    }
}
