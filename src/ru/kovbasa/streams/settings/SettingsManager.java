package ru.kovbasa.streams.settings;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class SettingsManager {

    private static final String SETTINGS_FILE = "shop.xml";

    public static Settings loadSettings() {
        Settings settings = new Settings();

        try {
            File file = new File(SETTINGS_FILE);

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);

            doc.getDocumentElement().normalize();

            Element root = (Element) doc.getElementsByTagName("config").item(0);

            Element loadConfig = (Element) root.getElementsByTagName("load").item(0);
            settings.setLoadEnabled(Boolean.parseBoolean(loadConfig.getElementsByTagName("enabled").item(0).getTextContent()));
            settings.setLoadFileName(loadConfig.getElementsByTagName("fileName").item(0).getTextContent());
            settings.setLoadFormat(loadConfig.getElementsByTagName("format").item(0).getTextContent());

            Element saveConfig = (Element) root.getElementsByTagName("save").item(0);
            settings.setSaveEnabled(Boolean.parseBoolean(saveConfig.getElementsByTagName("enabled").item(0).getTextContent()));
            settings.setSaveFileName(saveConfig.getElementsByTagName("fileName").item(0).getTextContent());
            settings.setSaveFormat(saveConfig.getElementsByTagName("format").item(0).getTextContent());

            Element logConfig = (Element) root.getElementsByTagName("log").item(0);
            settings.setLogEnabled(Boolean.parseBoolean(logConfig.getElementsByTagName("enabled").item(0).getTextContent()));
            settings.setLogFileName(logConfig.getElementsByTagName("fileName").item(0).getTextContent());
        } catch (Exception e) {
//            e.printStackTrace();
        }

        return settings;
    }

    public static void saveSettings(Settings settings) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element root = doc.createElement("config");
            doc.appendChild(root);

            Element loadConfig = doc.createElement("load");
            Element loadEnabled = doc.createElement("enabled");
            loadEnabled.appendChild(doc.createTextNode(String.valueOf(settings.isLoadEnabled())));
            loadConfig.appendChild(loadEnabled);
            Element loadFileName = doc.createElement("fileName");
            loadFileName.appendChild(doc.createTextNode(settings.getLoadFileName()));
            loadConfig.appendChild(loadFileName);
            Element loadFormat = doc.createElement("format");
            loadFormat.appendChild(doc.createTextNode(settings.getLoadFormat()));
            loadConfig.appendChild(loadFormat);
            root.appendChild(loadConfig);

            Element saveConfig = doc.createElement("save");
            Element saveEnabled = doc.createElement("enabled");
            saveEnabled.appendChild(doc.createTextNode(String.valueOf(settings.isSaveEnabled())));
            saveConfig.appendChild(saveEnabled);
            Element saveFileName = doc.createElement("fileName");
            saveFileName.appendChild(doc.createTextNode(settings.getSaveFileName()));
            saveConfig.appendChild(saveFileName);
            Element saveFormat = doc.createElement("format");
            saveFormat.appendChild(doc.createTextNode(settings.getSaveFormat()));
            saveConfig.appendChild(saveFormat);
            root.appendChild(saveConfig);

            Element logConfig = doc.createElement("log");
            Element logEnabled = doc.createElement("enabled");
            logEnabled.appendChild(doc.createTextNode(String.valueOf(settings.isLogEnabled())));
            logConfig.appendChild(logEnabled);
            Element logFileName = doc.createElement("fileName");
            logFileName.appendChild(doc.createTextNode(settings.getLogFileName()));
            logConfig.appendChild(logFileName);
            root.appendChild(logConfig);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(SETTINGS_FILE));

            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
