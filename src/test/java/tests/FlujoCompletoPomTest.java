package tests;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import screens.HomeScreen;
import screens.LoginScreen;
import screens.ProductoScreen;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("Android")
@EnabledOnOs(OS.WINDOWS)
@DisplayName("Flujo completo con esperas, scroll y dialogos")
class FlujoCompletoPomTest extends BaseTest {

    @Test
    @DisplayName("Consulta Backpack, busca Onesie y prueba el dialogo de logout")
    void buscaProductoYPruebaElDialogoDeLogout() {
        HomeScreen home = new HomeScreen(driver, wait);
        LoginScreen login = new LoginScreen(driver, wait);
        ProductoScreen producto = new ProductoScreen(driver, wait);

        // 1. Login con las esperas de BaseScreen.
        // Los permisos y avisos conocidos se atienden si aparecen.
        home.irALogin();
        login.ingresar("bob@example.com", "10203040");
        assertTrue(home.muestraTitulo("Products"), "El login debe volver al catalogo");

        // 2. Abrir un producto y comprobar su nombre en el detalle.
        home.abrirProducto("Sauce Labs Backpack");
        producto.esperarProducto("Sauce Labs Backpack");
        assertEquals("Sauce Labs Backpack", producto.textoTitulo());

        // 3. Recorrer el catalogo hasta encontrar un producto fuera de la vista inicial.
        // Onesie se verifica sin abrirlo: la APK tiene un error al entrar a su detalle.
        producto.volverAlCatalogo();
        home.esperarCatalogo();
        String productoEncontrado = home.scrollHasta(AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"Sauce Labs Onesie\")")).getText();
        assertEquals("Sauce Labs Onesie", productoEncontrado);

        // 4. Cancelar logout debe conservar el catalogo.
        home.abrirDialogoLogout();
        assertTrue(home.textoDialogo().toLowerCase(Locale.ROOT).contains("logout"));
        home.cancelarLogout();
        assertTrue(home.muestraTitulo("Products"), "Cancelar debe conservar el catalogo");

        // 5. Confirmar logout debe cerrar la sesion y mostrar login.
        home.abrirDialogoLogout();
        home.confirmarLogout();
        assertTrue(login.muestraCampoUsuario(), "Confirmar logout debe mostrar login");
    }
}
