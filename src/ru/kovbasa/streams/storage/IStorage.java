package ru.kovbasa.streams.storage;

import ru.kovbasa.streams.Basket;

public interface IStorage {

    void saveBasket(Basket basket);

    Basket loadBasket();

    boolean isStorageExists();
}
