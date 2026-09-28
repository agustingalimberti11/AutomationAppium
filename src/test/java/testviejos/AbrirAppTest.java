package testviejos;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.Path;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbrirAppTest {

    @Test
    void instalaLaApkYMuestraElCatalogo() throws MalformedURLException {
        Path apk = Path.of("src/test/resources/apks/mda-2.2.0-25.apk").toAbsolutePath();

        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName("Pixel_9a")
                .setAvd("Pixel_9a")
                .setUdid("emulator-554")
                .setApp(apk.toString())
                .setAppWaitActivity("*")
                .setAutoGrantPermissions(true);

        AndroidDriver driver = new AndroidDriver(
                URI.create("http://127.0.0.1:4723").toURL(),
                options
        );

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
