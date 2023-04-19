package ru.kovbasa.streams.storage;

import com.google.gson.Gson;
import ru.kovbasa.streams.Basket;

import java.io.*;

public class JsonStorage implements IStorage {

    private final String fileName;

    public JsonStorage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void saveBasket(Basket basket) {
        Gson gson = new Gson();
        String json = gson.toJson(basket);

        try (PrintWriter out = new PrintWriter(this.fileName)) {
            out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Basket loadBasket() {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(this.fileName))) {
            sb.append(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Gson gson = new Gson();
        return gson.fromJson(sb.toString(), Basket.class);
    }

    @Override
    public boolean isStorageExists() {
        return new File(this.fileName).exists();
    }

}
