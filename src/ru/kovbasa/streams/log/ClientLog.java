package ru.kovbasa.streams.log;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ClientLog {

    private final List<String> log;

    public ClientLog() {
        log = new ArrayList<>();
        log.add("productNum,amount");
    }

    public void log(int productNum, int amount) {
        log.add(productNum + "," + amount);
    }

    public void exportAsCSV(File txtFile) {
        try (PrintWriter out = new PrintWriter(txtFile)) {
            for (String s : log) {
                out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}