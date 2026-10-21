package testviejos;

import Base.BaseTest;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FluentWaitOrdenarTest extends BaseTest {

    private static final By CATALOGO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV");
    private static final By BOTON_ORDENAR = AppiumBy.accessibilityId(
            "Shows current sorting order and displays available sorting options"
    );
    private static final By PRECIO_MAYOR_A_MENOR = AppiumBy.id(
            "com.saucelabs.mydemoapp.android:id/priceDesCL"
    );
    private static final By NOMBRE_PRODUCTO = AppiumBy.id("com.saucelabs.mydemoapp.android:id/titleTV");

    @Test
    void esperaQueElCatalogoQuedeOrdenadoPorPrecio() {
        esperarVisible(CATALOGO);
        Assertions.assertTrue(esperarVisible(NOMBRE_PRODUCTO).getText().contains("Backpack"));

        tap(BOTON_ORDENAR);
        tap(PRECIO_MAYOR_A_MENOR);

        esperaFluida().until(d -> primerProductoEs(d, "Fleece Jacket"));

        assertTrue(primerProductoEs(driver, "Fleece Jacket"));
    }

    private boolean primerProductoEs(WebDriver d, String nombre) {
        for (WebElement titulo : d.findElements(NOMBRE_PRODUCTO)) {
            String texto = titulo.getText();
            if (texto != null && !texto.isBlank()) {
                return texto.contains(nombre);
            }
        }
        return false;
    }
}
