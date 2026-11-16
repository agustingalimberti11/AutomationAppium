package screens;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CarritoScreen extends BaseScreen {

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/titleTV")
    private WebElement item;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/totalPriceTV")
    private WebElement total;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/noItemTitleTV")
    private WebElement vacio;

    public CarritoScreen(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String textoItem() {
        return esperarVisible(item).getText();
    }

    public String textoTotal() {
        return esperarVisible(total).getText();
    }

    public boolean estaVacio() {
        return esperarVisible(vacio).isDisplayed();
    }
}