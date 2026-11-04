package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import screens.HomeScreen;
import screens.LoginScreen;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Tag("Android")
@EnabledOnOs(OS.WINDOWS)
@DisplayName("Login con Screen Objects")
public class LoginPomTest extends BaseTest{
    @Test
    @DisplayName("Login valido vuelve al catalo")
    void loginValido(){
        HomeScreen home = new HomeScreen(driver,wait);
        LoginScreen login = new LoginScreen(driver, wait);
        //Arrange
        home.irALogin();
        //Act
        login.ingresar("bob@example.com", "10203040");
        //Assert
        assertTrue(home.muestraTitulo("Products"));
    }

    @Test
    @DisplayName("Login sin password, se queda en login")
    void loginIncorrecto(){
        HomeScreen home = new HomeScreen(driver, wait);
        LoginScreen login = new LoginScreen(driver, wait);
        //Arrange
        home.irALogin();
        //Act
        login.escribirUsuario("bob@example.com");
        login.tapLogin();
        //Assert
        assertTrue(login.muestraTitulo("Login"));
    }
}
