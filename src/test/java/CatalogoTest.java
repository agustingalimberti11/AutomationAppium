import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CatalogoTest extends BaseTest{

    @Test
    void catalogoMuestraTituloProducts() throws Exception {
            WebElement titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV")
            ));
            assertTrue(titulo.getText().contains("Products"));
    }
}
