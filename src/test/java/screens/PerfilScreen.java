package screens;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PerfilScreen extends BaseScreen {

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/versionTV")
    private WebElement version;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/webTV")
    private WebElement web;

    public PerfilScreen(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public boolean estaVisible() {
        return esperarVisible(version).isDisplayed();
    }

    public String textoVersion() {
        return esperarVisible(version).getText();
    }

    public String textoWeb() {
        return esperarVisible(web).getText();
    }
}
