package com.codecool.shop.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Parameters {

    public static Properties init(){
        Properties prop = new Properties();
        String fileName = "src/main/resources/connection.properties";

        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop.load(fis);
        } catch (FileNotFoundException ex) {
            System.out.println("file not found");
            return null;
        } catch (IOException ex) {
            System.out.println("Exception " + ex);
            return null;

        }

        return prop;
    }

    public static String getDBMethod() {
        Properties properties = init();
        assert properties != null;
        return properties.getProperty("dao");
    }

    public static String getURL() {
        Properties properties = init();
        assert properties != null;
        return properties.getProperty("url");
    }

    public static String getDBName() {
        Properties properties = init();
        assert properties != null;
        return properties.getProperty("database");
    }

    public static String getUser() {
        Properties properties = init();
        assert properties != null;
        return properties.getProperty("user");
    }

    public static String getPW() {
        Properties properties = init();
        assert properties != null;
        return properties.getProperty("password");
    }



    /*try (
    FileInputStream fis = new FileInputStream(fileName)) {
        prop.load(fis);
    } catch (
    FileNotFoundException ex) {
    ... // FileNotFoundException catch is optional and can be collapsed
    } catch (
    IOException ex) {
    }
System.out.println(prop.getProperty("app.name"));
System.out.println(prop.getProperty("app.version"));
*/

}
