import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.LeerCapability;
import java.net.URI;
import java.time.Duration;

public class BaseTest {
    protected AndroidDriver driver;
    protected WebDriverWait wait;
    @BeforeEach
    void AbrirApp() throws Exception {
         driver = new AndroidDriver(
                 URI.create(LeerCapability.serverUrl()).toURL(),
                LeerCapability.opciones());
         wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try{
            new WebDriverWait(driver, Duration.ofSeconds(8)).until(
                    ExpectedConditions.elementToBeClickable(AppiumBy.id("android:id/button1"))
            ).click();
        }catch (Exception ignore){}
    }

    @AfterEach
    void cerrarSesion(){
        if(driver != null){
            driver.quit();
        }
    }
}
