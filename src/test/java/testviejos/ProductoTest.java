package testviejos;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductoTest extends BaseTest {

    private static final By TITULO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");
    private static final By MENU = AppiumBy.accessibilityId("View menu");
    private static final By LOGIN_MENU = AppiumBy.accessibilityId("Login Menu Item");
    private static final By USUARIO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    private static final By PASSWORD = AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET");
    private static final By BOTON_LOGIN = AppiumBy.accessibilityId("Tap to login with given credentials");
    private static final By PRIMER_PRODUCTO = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/productIV\").instance(0)");
    private static final By PRECIO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/priceTV");
    private static final By BOTON_AGREGAR = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt");
    private static final By ICONO_CARRITO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartIV");
    private static final By IR_A_CHECKOUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartBt");
    private static final By NOMBRE_ENVIO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/fullNameET");
    private static final By DIRECCION = AppiumBy.id("com.saucelabs.mydemoapp.android:id/address1ET");
    private static final By CIUDAD = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cityET");
    private static final By ZIP = AppiumBy.id("com.saucelabs.mydemoapp.android:id/zipET");
    private static final By PAIS = AppiumBy.id("com.saucelabs.mydemoapp.android:id/countryET");
    private static final By IR_A_PAGO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn");
    private static final By NOMBRE_TARJETA = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    private static final By NUMERO_TARJETA = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cardNumberET");
    private static final By VENCIMIENTO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/expirationDateET");
    private static final By CVV = AppiumBy.id("com.saucelabs.mydemoapp.android:id/securityCodeET");
    private static final By REVISAR_ORDEN = AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn");
    private static final By CONFIRMAR_ORDEN = AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn");
    private static final By CHECKOUT_OK = AppiumBy.id("com.saucelabs.mydemoapp.android:id/completeTV");


    @Test
    void comprar(){
        esperarVisible(TITULO);
        tap(MENU);
        tap(LOGIN_MENU);
        escribir(USUARIO, "bod@example.com");
        escribir(PASSWORD, "10203040");
        tap(BOTON_LOGIN);
        Assertions.assertTrue(esperarVisible(TITULO).getText().contains("Products"));
        tap(PRIMER_PRODUCTO);
        Assertions.assertTrue(esperarVisible(TITULO).getText().contains("Backpack"));
        Assertions.assertTrue(esperarVisible(PRECIO).getText().contains("29.99"));
        tap(BOTON_AGREGAR);
        tap(ICONO_CARRITO);
        Assertions.assertTrue(esperarVisible(TITULO).getText().contains("My Cart"));
        tap(IR_A_CHECKOUT);
        escribir(NOMBRE_ENVIO,"agustin");
        escribir(DIRECCION,"calle 123");
        escribir(CIUDAD,"La Rioja");
        escribir(ZIP,"5300");
        escribir(PAIS,"Argentina");
        tap(IR_A_PAGO);
        escribir(NOMBRE_TARJETA,"Agustin Galimberti");
        escribir(NUMERO_TARJETA,"325812657568789");
        escribir(VENCIMIENTO,"03/28");
        escribir(CVV,"123");
        tap(REVISAR_ORDEN);
        tap(CONFIRMAR_ORDEN);
        Assertions.assertTrue(esperarVisible(CHECKOUT_OK).getText().contains("Checkout Complete"));
    }

}
