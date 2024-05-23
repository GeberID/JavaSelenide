import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest { // Класс, который содержит статические методы для работы с браузером
    @BeforeAll // Аннотация Junit. Выполнять эти действия перед всеми автотестами
    static void beforeAll(){
        Configuration.browserSize = "1920x1080"; // Размер экрана
        Configuration.timeout = 10_000; // Ожидание в миллисекундах, сколько ждать появления того иои иного атрибута
        Configuration.pageLoadTimeout = 10_000; // Не явное ожидание в миллисекундах, сколько ждать загрузки страниц
        Configuration.browser = "chrome"; // Браузер
        Configuration.browserVersion = "125"; // Версия браузера
        DesiredCapabilities capabilities = new DesiredCapabilities(); // Объект DesiredCapabilities описывает особенности создаваемой сессии.
        ChromeOptions options = new ChromeOptions(); // объект ChromeOptions где будут устанавливаться доп опции
        options.addArguments("--guest"); // Опция гостя в браузере. Нужна, чтоб убрать лишние модальные окна самого браузера
        capabilities.setCapability(ChromeOptions.CAPABILITY, options); // Установка опций в DesiredCapabilities
        Configuration.browserCapabilities = capabilities;// Установка опций в браузер
        SelenideLogger.addListener("allure", new AllureSelenide().savePageSource(false));
    }
    @AfterAll // Аннотация Junit. Выполнять эти действия после всех автотестов
    static void afterAll(){

    }
}
