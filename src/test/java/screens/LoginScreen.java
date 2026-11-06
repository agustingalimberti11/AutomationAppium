package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginScreen extends BaseScreen{

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/nameET")
    private WebElement USUARIO;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/passwordET")
    private WebElement PASSWORD;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/loginBtn")
    private WebElement BOTON_LOGIN;

    @AndroidFindBy(id = "com.saucelabs.mydemoapp.android:id/loginTV")
    private WebElement TITULO;

    public LoginScreen(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void esperarPantalla() {
        esperarVisible(USUARIO);
    }

    public void escribirUsuario(String usuario) {
        escribir(USUARIO, usuario);
    }

    public void escribirPassword(String password) {
        escribir(PASSWORD, password);
    }

    public void tapLogin() {
        tap(BOTON_LOGIN);
    }

    public void ingresar(String usuario, String password) {
        esperarPantalla();
        escribirUsuario(usuario);
        escribirPassword(password);
        tapLogin();
    }

    public boolean muestraTitulo(String texto) {
        return esperarVisible(TITULO).getText().contains(texto);
    }
}
