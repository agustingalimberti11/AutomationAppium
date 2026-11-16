package util;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import screens.*;

import java.io.IOException;
import java.time.Duration;

public class ContextoEscenario {

    private AndroidDriver driver;
    private HomeScreen home;
    private LoginScreen login;
    private ProductoScreen producto;
    private CarritoScreen carrito;
    private PerfilScreen perfil;

    public void abrir() throws IOException {
        LeerCapability configuracion = new LeerCapability();
        driver = new AndroidDriver(configuracion.servidor(), configuracion.opciones());
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        WebDriverWait espera = BaseScreen.crearEspera(driver);
        home = new HomeScreen(driver, espera);
        login = new LoginScreen(driver, espera);
        producto = new ProductoScreen(driver, espera);
        carrito = new CarritoScreen(driver, espera);
        perfil = new PerfilScreen(driver, espera);
    }

    public void cerrar() {
        if (driver != null) {
            AndroidDriver sesion = driver;
            driver = null;
            sesion.quit();
        }
    }

    public AndroidDriver driver() { return driver; }
    public HomeScreen home() { return home; }
    public LoginScreen login() { return login; }
    public ProductoScreen producto() { return producto; }
    public CarritoScreen carrito() { return carrito; }
    public PerfilScreen perfil() { return perfil; }
}