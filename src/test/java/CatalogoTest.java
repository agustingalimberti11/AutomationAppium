import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.LeerCapability;

import java.net.URI;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogoTest {


    @Test
    void catalogoMuestraTituloProducts() throws Exception {

        AndroidDriver driver = new AndroidDriver(
                URI.create(LeerCapability.serverUrl()).toURL(),
                LeerCapability.opciones());

        try{
            try{
                new WebDriverWait(driver, Duration.ofSeconds(8)).until(
                        ExpectedConditions.elementToBeClickable(AppiumBy.id("android:id/button1"))
                ).click();
            }catch (Exception ignore){}

            WebElement titulo = new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                    ExpectedConditions.visibilityOfElementLocated(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV"))
            );

            assertTrue(titulo.getText().contains("Products"));

        }finally{
            driver.quit();
        }
    }
}
