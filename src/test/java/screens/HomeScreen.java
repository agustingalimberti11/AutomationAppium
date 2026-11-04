package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeScreen extends BaseScreen{

    private static final By TITULO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");
    private static final By MENU = AppiumBy.accessibilityId("View menu");
    private static final By LOGIN_MENU = AppiumBy.accessibilityId("Login Menu Item");
    private static final By PERFIL_MENU = AppiumBy.androidUIAutomator(
            "new UiSelector().text(\"About\")"
    );
    private static final By PRIMER_PRODUCTO = AppiumBy.androidUIAutomator(
            "new UiSelector().description(\"Product Image\").instance(0)"
    );
    private static final By ICONO_CARRITO = AppiumBy.accessibilityId("View cart");

    public HomeScreen(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }


    public void esperarCatalogo() {
        esperarVisible(TITULO);
    }

    public boolean muestraTitulo(String texto) {
        return esperarVisible(TITULO).getText().contains(texto);
    }

    public void abrirMenu() {
        tap(MENU);
    }

    public void irALogin() {
        esperarCatalogo();
        abrirMenu();
        tap(LOGIN_MENU);
    }

    public void abrirPrimerProducto() {
        esperarCatalogo();
        tap(PRIMER_PRODUCTO);
    }

    public void irAPerfil() {
        esperarCatalogo();
        abrirMenu();
        tap(PERFIL_MENU);
    }

    public void abrirCarrito() {
        tap(ICONO_CARRITO);
    }
}
