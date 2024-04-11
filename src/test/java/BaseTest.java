import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {
    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize
                = "1920x1080";
        Configuration.timeout = 10_000;
        Configuration.fastSetValue = true;
        Configuration.pageLoadTimeout = 10_000;
        Configuration.pageLoadStrategy = "eager";
        Configuration.browser = "chrome";
        Configuration.browserVersion = "123";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;

    }
    @AfterAll
    static void afterAll(){

    }
}
