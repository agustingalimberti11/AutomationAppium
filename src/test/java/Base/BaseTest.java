package Base;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.LeerCapability;
import java.net.URI;
import java.time.Duration;

public class BaseTest {
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    @BeforeEach
    void AbrirApp() throws Exception {
         driver = new AndroidDriver(
                 URI.create(LeerCapability.serverUrl()).toURL(),
                LeerCapability.opciones());
         wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        try{
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.elementToBeClickable(AppiumBy.id("android:id/button1")))
                    .click();
        }catch (Exception ignore){}
    }

    @AfterEach
    void cerrarSesion(){
        if(driver != null){
            driver.quit();
        }
    }

    protected WebElement esperarVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement esperarClickeable(By locator){
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void tap(By locator){
        esperarClickeable(locator).click();
    }

    protected void escribir(By locator, String texto){
        WebElement campo = esperarVisible(locator);
        campo.clear();
        campo.sendKeys(texto);
        try{
            driver.hideKeyboard();
        }catch (Exception ignored){}
    }

    protected WebElement scrollAbajoHasta(String texto){
        return driver.findElement(AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))"
                        + ".scrollIntoView(new UiSelector().text(\"" + texto + "\"))"
        ));
    }

    protected Wait<WebDriver> esperaFluida(){
        return new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(15)) //tiempo limite de 15 segundos
                .pollingEvery(Duration.ofMillis(500)) //pregunta
                .ignoring(NoSuchElementException.class)  //ignoramos una excepcion por si no aparece el elemento
                .ignoring(StaleElementReferenceException.class); //la pantalla se redibujo
    }

}
