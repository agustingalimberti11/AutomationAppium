package util;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.nio.file.Path;

public class LeerCapability {

    public static JsonNode leer() throws Exception {
        return new ObjectMapper().readTree(Path.of("src/test/resources/config/caps.json").toFile());
    }

    public static String serverUrl() throws Exception {
        return leer().get("serverUrl").asText();
    }

    public static UiAutomator2Options opciones() throws Exception {
        JsonNode json = leer();
        return new UiAutomator2Options()
                .setPlatformName(json.get("platformName").asText())
                .setAutomationName(json.get("automationName").asText())
                .setDeviceName(json.get("deviceName").asText())
                .setAvd(json.get("avd").asText())
                .setUdid(json.get("udid").asText())
                .setApp(Path.of(json.get("app").asText()).toAbsolutePath().toString())
                .setAppWaitActivity(json.get("appWaitActivity").asText())
                .setAutoGrantPermissions(json.get("autoGrantPermissions").asBoolean());
    }
}
