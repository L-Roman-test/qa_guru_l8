package settings;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.open;
import static pages.AttachmentsHelper.*;

public class BaseTest {

    @BeforeAll
    public static void config() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserSize = "1920x1080";

        if (System.getProperty("remote_driver") != null) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
            Configuration.browserCapabilities = capabilities;
            Configuration.remote = System.getProperty("remote_driver");
        }
        open("https://demoqa.com");
    }

    @AfterEach
    public void attachments() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachText("Browser console logs", getConsoleLogs());
        if (System.getProperty("video_storage") != null)
            attachVideo();
    }
}
