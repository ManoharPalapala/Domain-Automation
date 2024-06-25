package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropHandler {

    public String readDataFromPropFile(String propName) {

        File propFile = new File(
                "C:\\Users\\JOBIAK\\Desktop\\scripts\\eclipse-workspace\\domainAutomation\\src\\main\\java\\resources\\config.properties");
        Properties prop = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(propFile);
            prop.load(fis);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return prop.getProperty(propName);

    }
}
