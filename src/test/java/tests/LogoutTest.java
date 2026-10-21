package tests;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Android")
@EnabledOnOs(OS.WINDOWS)
@DisplayName("tests.LogoutTest")
public class LogoutTest extends BaseTest {

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
    @DisplayName("Confirmar logout")
    void confirmarLogOut() {
        //Arrange
        iniciarSesion("bod@example.com", "10203040");
        //Act
        abrirDialogoLogout();
        confirmar();
        //assert
        assertTrue(esperarVisible(USUARIO).isDisplayed());
    }

    private void iniciarSesion(String usuario, String password){
        esperarVisible(TITULO);
        tap(MENU);
        tap(LOGIN_MENU);
        escribir(USUARIO,usuario);
        escribir(PASSWORD , password);
        tap(BOTON_LOGIN);
        assertTrue(esperarVisible(TITULO).getText().contains("Products"));
    }

    private void abrirDialogoLogout(){
        tap(MENU);
        tap(BOTON_LOGOUT);
        assertTrue(esperarVisible(TEXTO_DIALOG).getText().contains("logout"));
    }

    private void confirmar(){
        tap(CONFIRM_BOTON_LOGOUT);
    }
}
