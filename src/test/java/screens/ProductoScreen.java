package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductoScreen extends BaseScreen {

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement titulo;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/priceTV")
    private WebElement precio;

    @AndroidFindBy(accessibility = "View cart")
    private WebElement iconoCarrito;

    public ProductoScreen(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void esperarProducto(String nombre) {
        esperarTexto(titulo, nombre);
    }

    public String textoTitulo() {
        return esperarVisible(titulo).getText();
    }

    public String textoPrecio() {
        return esperarVisible(precio).getText();
    }

    public void agregarAlCarrito() {
        scrollYTap(AppiumBy.accessibilityId("Tap to add product to cart"));
    }

    public void volverAlCatalogo() {
        driver.navigate().back();
    }

    public void abrirCarrito() {
        tap(iconoCarrito);
    }
}
