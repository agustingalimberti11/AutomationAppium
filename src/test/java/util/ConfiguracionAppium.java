package util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfiguracionAppium {

    private final JsonNode datos;

    public ConfiguracionAppium() throws IOException {
        try (InputStream archivo = getClass().getResourceAsStream("/caps.json")) {
            if (archivo == null) {
                throw new IOException("No se encontro caps.json en los recursos de test");
            }
            datos = new ObjectMapper().readTree(archivo);
        }
    }

    public URL servidor() throws IOException {
        return URI.create(valor("appium.serverUrl", "serverUrl")).toURL();
    }

    public UiAutomator2Options opciones() throws IOException {
        Path apk = Path.of(valor("android.app", "app")).toAbsolutePath().normalize();
        if (!Files.isRegularFile(apk)) {
            throw new IOException("No se encontro la APK: " + apk);
        }

        UiAutomator2Options opciones = new UiAutomator2Options()
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setDeviceName(valor("android.deviceName", "deviceName"))
                .setUdid(valor("android.udid", "udid"))
                .setApp(apk.toString())
                .setAppWaitActivity("*")
                .setAutoGrantPermissions(true)
                // El carrito y la sesion de usuario se reinician en cada escenario.
                .setNoReset(false);

        String avd = System.getProperty("android.avd", datos.path("avd").asText());
        if (!avd.isBlank()) {
            opciones.setAvd(avd);
        }
        return opciones;
    }

    private String valor(String propiedad, String campo) {
        String valor = System.getProperty(propiedad, datos.path(campo).asText());
        if (valor.isBlank()) {
            throw new IllegalArgumentException("Falta " + campo + " en caps.json o -D" + propiedad);
        }
        return valor;
    }
}
