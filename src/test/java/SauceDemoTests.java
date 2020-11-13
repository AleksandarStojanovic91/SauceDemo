import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListener.class)
public class SauceDemoTests extends Base{
    String URL = "https://www.saucedemo.com/";
    String username = "standard_user";
    String password = "secret_sauce";

    @BeforeMethod
    public void setup(){
        init(URL);
    }
    @Test
    public void e2eTest(){
        login(username,password);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.saucedemo.com/inventory.html");

        String naziv = driver.findElement(By.cssSelector(".inventory_item:nth-child(1) .inventory_item_name")).getText();
        String cena = driver.findElement(By.cssSelector(".inventory_item:nth-child(1)  .inventory_item_price")).getText();

        driver.findElement(By.cssSelector(".inventory_item:nth-child(1)>div>button")).click();
        driver.findElement(By.cssSelector("#shopping_cart_container")).click();

        String nazivUKorpi = driver.findElement(By.cssSelector(".inventory_item_name")).getText();
        String cenaUKorpi = driver.findElement(By.cssSelector(".inventory_item_price")).getText();

        Assert.assertEquals(naziv,nazivUKorpi);
        Assert.assertEquals(cena,"$"+cenaUKorpi);
        Assert.assertEquals(Double.valueOf(cenaUKorpi),Double.valueOf(cena.substring(1,6)));
        Assert.assertEquals(cena,"$"+cenaUKorpi);

        driver.findElement(By.cssSelector(".btn_action.checkout_button")).click();

        driver.findElement(By.cssSelector("#postal-code")).sendKeys("11000");
        driver.findElement(By.cssSelector(".btn_primary.cart_button")).click();
        driver.findElement(By.cssSelector("#first-name")).sendKeys("NekoIme");
        driver.findElement(By.cssSelector("#last-name")).sendKeys("NekoPrezime");


        driver.findElement(By.cssSelector(".btn_action.cart_button")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector(".pony_express")).isDisplayed());
    }
    @AfterMethod
    public void tearDown(){
        driver.close()
    }
}
