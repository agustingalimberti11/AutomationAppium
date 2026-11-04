package screens;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseScreen {
    protected final AndroidDriver driver;
    protected final WebDriverWait wait;

    protected BaseScreen(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    protected WebElement esperarVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement esperarClickeable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void tap(By locator) {
        esperarClickeable(locator).click();
    }

    protected void escribir(By locator, String texto) {
        WebElement campo = esperarVisible(locator);
        campo.clear();
        campo.sendKeys(texto);
        try {
            driver.hideKeyboard();
        } catch (Exception ignored) {
        }
    }
}
