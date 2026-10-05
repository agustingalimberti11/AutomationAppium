import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AgregarAlcarritoTest extends BaseTest {


    private static final By CATALOGO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");
    private static final By PRIMER_PRODUCTO = AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"com.saucelabs.mydemoapp.android:id/productIV\").instance(0)");
    private static final By BOTON_AGREGAR = AppiumBy.accessibilityId("Tap to add product to cart");
    private static final By ICONO_CARRITO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartIV");
    private static final By TITLE_PRODUCTO_CARRITO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/titleTV");

    @Test
    void agregaProductoAlCarrito(){
        esperarVisible(CATALOGO);
        tap(PRIMER_PRODUCTO);
        assertTrue(esperarVisible(CATALOGO).getText().contains("Backpack"));

        tap(BOTON_AGREGAR);
        tap(ICONO_CARRITO);
        assertTrue(esperarVisible(TITLE_PRODUCTO_CARRITO).getText().contains("Backpack"));

    }
}
