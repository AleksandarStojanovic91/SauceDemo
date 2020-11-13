import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Base {

    public static WebDriver driver;

    public static void init(String URL){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.addArguments("window-size=1920,1080");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get(URL);
    }

    public void takeScreenshot(String name) throws IOException {
        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("src/main/resources/screenshots/"+name+".png"));
    }

    public static void login(String username,String password){
        driver.findElement(By.cssSelector("#user-name")).sendKeys(username);
        driver.findElement(By.cssSelector("#password")).sendKeys(password);
        driver.findElement(By.cssSelector("#login-button")).click();
    }
}
