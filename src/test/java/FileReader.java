import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class FileReader {
    public Properties prop;

    public Properties gProperties() {
        InputStream inputStream;

        try {
            prop = new Properties();
            String propFileName = "config.properties";
            inputStream = new FileInputStream(propFileName);
            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
            // get the property value and print it out

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return prop;
    }

}
