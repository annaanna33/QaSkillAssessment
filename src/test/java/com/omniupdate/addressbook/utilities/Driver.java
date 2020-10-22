package com.omniupdate.addressbook.utilities;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class Driver {

    //singleton design pattern to run only one instance of driver

    private Driver() {
    }

    private static ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();


    public static WebDriver getDriver() {
        if (driverPool.get() == null) {

            String browser = System.getProperty("browser") != null ? System.getProperty("browser") : ConfigurationReader.getProperty("browser");

            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                    driverPool.set(new ChromeDriver());
                    break;
                case "chrome-headless":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
                    driverPool.set(new ChromeDriver(new ChromeOptions().setHeadless(true)));
                    break;
                case "firefox":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/geckodriver.exe");
                    driverPool.set(new FirefoxDriver());
                    break;
                case "firefox-headless":
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/geckodriver.exe");
                    driverPool.set(new FirefoxDriver(new FirefoxOptions().setHeadless(true)));
                    break;
                case "ie":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Internet Explorer");
                    System.setProperty("webdriver.ie.driver", "src/test/resources/drivers/IEDriverServer.exe");
                    driverPool.set(new InternetExplorerDriver());
                    break;
                case "edge":
                    if (!System.getProperty("os.name").toLowerCase().contains("windows"))
                        throw new WebDriverException("Your OS doesn't support Edge");
                    System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
                    driverPool.set(new EdgeDriver());
                    break;


            }
        }
        return driverPool.get();
    }

    public static void closeDriver() {
        if (driverPool.get() != null) {
            driverPool.get().quit();
            driverPool.remove();
        }
    }



}
