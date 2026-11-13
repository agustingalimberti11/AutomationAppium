package screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Locale;
import java.util.Map;

public abstract class BaseScreen {

    private static final int MAX_SCROLLS = 8;
    private static final int MAX_INTERRUPCIONES = 5;
    private static final By CONFIRMAR = AppiumBy.id("android:id/button1");
    private static final By CANCELAR = AppiumBy.id("android:id/button2");
    private static final By PERMITIR = By.xpath(
            "//*[@resource-id='com.android.permissioncontroller:id/permission_allow_foreground_only_button'"
                    + " or @resource-id='com.android.permissioncontroller:id/permission_allow_button'"
                    + " or @resource-id='com.google.android.permissioncontroller:id/permission_allow_foreground_only_button'"
                    + " or @resource-id='com.google.android.permissioncontroller:id/permission_allow_button'"
                    + " or @resource-id='com.android.packageinstaller:id/permission_allow_button']");
    private static final By MENSAJE_DIALOGO = AppiumBy.id("android:id/message");


    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    protected BaseScreen(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(20)), this);
    }

    protected WebElement esperarVisible(WebElement locator) {
        return wait.until(ExpectedConditions.visibilityOf(locator));
    }

    protected WebElement esperarClickeable(WebElement locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Todas las esperas atienden primero los permisos y avisos conocidos.
    private <T> T esperar(ExpectedCondition<T> condicion, String descripcion) {
        int[] interrupciones = {0};
        return wait.withMessage(descripcion).until(d -> {
            WebElement aviso = buscarInterrupcion();
            if (aviso != null && aviso.isEnabled()) {
                if (interrupciones[0] >= MAX_INTERRUPCIONES) {
                    throw new IllegalStateException("Demasiadas interrupciones al " + descripcion);
                }
                aviso.click();
                interrupciones[0]++;
                return null;
            }
            return condicion.apply(d);
        });
    }

    private WebElement buscarInterrupcion() {
        WebElement permiso = buscarVisible(PERMITIR);
        if (permiso != null) {
            return permiso;
        }
        WebElement confirmar = buscarVisible(CONFIRMAR);
        if (confirmar != null && "android".equals(confirmar.getAttribute("package"))) {
            WebElement mensaje = buscarVisible(MENSAJE_DIALOGO);
            if (mensaje != null) {
                String texto = mensaje.getText().toLowerCase(Locale.ROOT).replace('\u00a0', ' ');
                if (texto.contains("16 kb") || texto.contains("16kb")
                        || texto.contains("older version of android")
                        || texto.contains("versi\u00f3n anterior de android")
                        || texto.contains("version anterior de android")) {
                    return confirmar;
                }
            }
        }
        return null; // Los dialogos de la app se responden explicitamente.
    }

    private WebElement buscarVisible(By localizador) {
        for (WebElement elemento : driver.findElements(localizador)) {
            if (elemento.isDisplayed()) {
                return elemento;
            }
        }
        return null;
    }

    protected void esperarTexto(WebElement elemento, String texto){
        esperar( d -> texto.equals(elemento.getText()), "esperar texto: " + texto);
    }

    protected void tap(WebElement locator) {
        esperarClickeable(locator).click();
    }

    protected void escribir(WebElement locator, String texto) {
        WebElement campo = esperarVisible(locator);
        campo.clear();
        campo.sendKeys(texto);
        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }
    }

    protected void ocultarTeclado(){
        if(driver.isKeyboardShown()){
            driver.hideKeyboard();
        }
    }

    // Busca antes de cada gesto y tambien despues de llegar al final.
    public WebElement scrollHasta(By localizador) {
        ocultarTeclado();
        int[] scrolls = {0};
        boolean[] puedeSeguir = {true};
        return esperar(d -> {
            WebElement elemento = buscarVisible(localizador);
            if (elemento != null) {
                return elemento;
            }
            if (scrolls[0] >= MAX_SCROLLS || !puedeSeguir[0]) {
                throw new TimeoutException("No se encontro " + localizador
                        + " tras " + scrolls[0] + " desplazamientos");
            }
            puedeSeguir[0] = scrollAbajo();
            scrolls[0]++;
            return null;
        }, "buscar mediante scroll: " + localizador);
    }

    public void scrollYTap(By localizador){
        scrollHasta(localizador);
        tap((WebElement) localizador);
    }

    private boolean scrollAbajo() {
        Dimension ventana = driver.manage().window().getSize();
        return Boolean.TRUE.equals(driver.executeScript("mobile: scrollGesture", Map.of(
                "left", (int) (ventana.width * 0.1),
                "top", (int) (ventana.height * 0.2),
                "width", Math.max(1, (int) (ventana.width * 0.8)),
                "height", Math.max(1, (int) (ventana.height * 0.6)),
                "direction", "down",
                "percent", 0.75
        )));
    }
}
