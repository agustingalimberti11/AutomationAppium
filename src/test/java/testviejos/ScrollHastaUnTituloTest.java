package testviejos;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScrollHastaUnTituloTest extends BaseTest {
    private static final By TITULO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");

    @Test
    void encontrarTextoHaciendoScroll(){
        esperarVisible(TITULO);

        WebElement onesie = scrollAbajoHasta("Sauce Labs Onesie");
        assertTrue(onesie.isDisplayed());
        assertTrue(onesie.getText().contains("Onesie"));
    }
}
