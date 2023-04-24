package ru.kovbasa.streams.settings;

public class Settings {

    private boolean loadEnabled;
    private String loadFileName;
    private String loadFormat;

    private boolean saveEnabled;
    private String saveFileName;
    private String saveFormat;

    private boolean logEnabled;
    private String logFileName;

    public Settings() {
        this.loadEnabled = false;
        this.loadFileName = "basket.json";
        this.loadFormat = "json";

        this.saveEnabled = true;
        this.saveFileName = "basket.json";
        this.saveFormat = "json";

        this.logEnabled = true;
        this.logFileName = "client.csv";
    }

    public boolean isLoadEnabled() {
        return loadEnabled;
    }

    public void setLoadEnabled(boolean loadEnabled) {
        this.loadEnabled = loadEnabled;
    }

    public String getLoadFileName() {
        return loadFileName;
    }

    public void setLoadFileName(String loadFileName) {
        this.loadFileName = loadFileName;
    }

    public String getLoadFormat() {
        return loadFormat;
    }

    public void setLoadFormat(String loadFormat) {
        this.loadFormat = loadFormat;
    }

    public boolean isSaveEnabled() {
        return saveEnabled;
    }

    public void setSaveEnabled(boolean saveEnabled) {
        this.saveEnabled = saveEnabled;
    }

    public String getSaveFileName() {
        return saveFileName;
    }

    public void setSaveFileName(String saveFileName) {
        this.saveFileName = saveFileName;
    }

    public String getSaveFormat() {
        return saveFormat;
    }

    public void setSaveFormat(String saveFormat) {
        this.saveFormat = saveFormat;
    }

    public boolean isLogEnabled() {
        return logEnabled;
    }

    public void setLogEnabled(boolean logEnabled) {
        this.logEnabled = logEnabled;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

}
