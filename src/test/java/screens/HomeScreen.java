package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeScreen extends BaseScreen{

    @AndroidFindBy(id="com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement TITULO;

    @AndroidFindBy(accessibility = "View menu")
    private WebElement MENU;

    @AndroidFindBy(accessibility = "Login Menu Item")
    private WebElement LOGIN_MENU;

    @AndroidFindBy(accessibility = "View cart")
    private WebElement ICONO_CARRITO;

    @AndroidFindBy(uiAutomator = "\"new UiSelector().text(\\\"About\\\")")
    private WebElement PERFIL_MENU;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Product Image\").instance(0)")
    private WebElement PRIMER_PRODUCTO;

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
