package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeScreen extends BaseScreen {

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/productTV")
    private WebElement titulo;

    @AndroidFindBy(accessibility = "View menu")
    private WebElement menu;

    @AndroidFindBy(accessibility = "Login Menu Item")
    private WebElement loginMenu;

    @AndroidFindBy(accessibility = "Logout Menu Item")
    private WebElement logoutMenu;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"About\")")
    private WebElement perfilMenu;

    @AndroidFindBy(uiAutomator = "new UiSelector().description(\"Product Image\").instance(0)")
    private WebElement primerProducto;

    @AndroidFindBy(accessibility = "View cart")
    private WebElement iconoCarrito;

    public HomeScreen(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void esperarCatalogo() {
        esperarTexto(titulo, "Products");
    }

    public boolean muestraTitulo(String texto) {
        return esperarVisible(titulo).getText().contains(texto);
    }

    public void abrirMenu() {
        tap(menu);
    }

    public void irALogin() {
        esperarCatalogo();
        abrirMenu();
        tap(loginMenu);
    }

    public void abrirPrimerProducto() {
        esperarCatalogo();
        tap(primerProducto);
    }

    public void irAPerfil() {
        esperarCatalogo();
        abrirMenu();
        tap(perfilMenu);
    }

    public void abrirProducto(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("Indicar el nombre del producto");
        }
        esperarCatalogo();
        String texto = nombre.replace("\\", "\\\\").replace("\"", "\\\"");
        // Abrir la imagen de la tarjeta que corresponde al nombre buscado.
        String selector = "new UiSelector().text(\"" + texto + "\")"
                + ".fromParent(new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/productIV\"))";
        scrollYTap(AppiumBy.androidUIAutomator(selector));
    }

    public void abrirDialogoLogout() {
        esperarCatalogo();
        abrirMenu();
        tap(logoutMenu);
    }

    public void confirmarLogout() {
        confirmarDialogo("logout");
    }

    public void cancelarLogout() {
        cancelarDialogo("logout");
    }

    public void abrirCarrito() {
        tap(iconoCarrito);
    }
}
