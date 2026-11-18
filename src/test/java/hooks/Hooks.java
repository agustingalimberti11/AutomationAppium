package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriverException;
import util.ContextoEscenario;

import java.io.IOException;

public class Hooks {

    private final ContextoEscenario contexto;

    public Hooks(ContextoEscenario contexto) {
        this.contexto = contexto;
    }


    @Before
    public void abrirApp() throws IOException {
        contexto.abrir();
    }


    @After
    public void cerrarApp(Scenario escenario){
        try {
            if (escenario.isFailed() && contexto.driver() != null) {
                try {
                    escenario.attach(contexto.driver().getScreenshotAs(OutputType.BYTES),
                            "image/png", "Pantalla al fallar");
                } catch (WebDriverException error) {
                    escenario.log("No se pudo capturar la pantalla: " + error.getMessage());
                }
            }
        } finally {
            try {
                contexto.cerrar();
            } catch (WebDriverException error) {
                if (!escenario.isFailed()) {
                    throw error;
                }
                escenario.log("No se pudo cerrar la sesion: " + error.getMessage());
            }
        }
    }
}
