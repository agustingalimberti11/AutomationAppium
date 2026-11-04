package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginScreen extends BaseScreen{
    public LoginScreen(AndroidDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    private static final By USUARIO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    private static final By PASSWORD = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET");
    private static final By BOTON_LOGIN = AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginBtn");
    private static final By TITULO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginTV");


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
