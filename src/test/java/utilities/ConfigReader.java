package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties; // A Properties object is created to store configuration data.

    static {
        String filePath = "configuration.properties"; // The path of the configuration file is specified.
        try {
            FileInputStream fis = new FileInputStream(filePath); // A FileInputStream is created to read the file.
            properties = new Properties(); // A new Properties object is created to store configuration data.
            properties.load(fis); // The data read with FileInputStream is loaded into the Properties object.
            fis.close(); // The file is closed.
        } catch (IOException e) {
            e.printStackTrace(); // If an error occurs, the exception is caught and printed to the console.
        }
    }

    public static String getProperty(String key) {
        // The getProperty method is created to return the value associated with the specified key.
        return properties.getProperty(key);
    }
}
