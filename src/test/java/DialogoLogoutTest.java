import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DialogoLogoutTest extends BaseTest {

    private static final By TITULO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");
    private static final By MENU = AppiumBy.accessibilityId("View menu");
    private static final By LOGIN_MENU = AppiumBy.accessibilityId("Login Menu Item");
    private static final By USUARIO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    private static final By PASSWORD = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET");
    private static final By BOTON_LOGIN = AppiumBy.accessibilityId("Tap to login with given credentials");
    private static final By TEXTO_DIALOG = AppiumBy.id("android:id/message");
    private static final By BOTON_LOGOUT = AppiumBy.accessibilityId("Logout Menu Item");
    private static final By CONFIRM_BOTON_LOGOUT = AppiumBy.id("android:id/button1");

    @Test
    void confirmarLogOut(){
        esperarVisible(TITULO);
        tap(MENU);
        tap(LOGIN_MENU);
        escribir(USUARIO,"bod@example.com");
        escribir(PASSWORD , "10203040");
        tap(BOTON_LOGIN);
        assertTrue(esperarVisible(TITULO).getText().contains("Products"));

        tap(MENU);
        tap(BOTON_LOGOUT);
        wait.until(this::dialogoVisible);
        assertTrue(esperarVisible(TEXTO_DIALOG).getText().contains("logout"));
        tap(CONFIRM_BOTON_LOGOUT);

    }

    private boolean dialogoVisible(WebDriver d){
            List<WebElement> textos = d.findElements(TEXTO_DIALOG);
            return !textos.isEmpty() && textos.get(0).isDisplayed();
        };
    }

