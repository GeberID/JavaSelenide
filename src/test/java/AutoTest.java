import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

class AutoTest extends BaseTest{
    @BeforeEach // Аннотация Junit. Выполнять эти действия перед каждым автотестом
    void login(){
        Selenide.open("https://www.saucedemo.com/"); // Метод open позволяет открыть необходимый сайт
        $("input[data-test=\"username\"]").sendKeys("standard_user"); // Заполнение поля логин
        $("input[data-test=\"password\"]").sendKeys("secret_sauce"); // Заполнение поля пароль
        $("[data-test=\"login-button\"]").click(); // Клик по кнопке входа
    }
    @AfterEach // Аннотация Junit. Выполнять эти действия после каждого автотеста
    void logout(){
        $("div.bm-burger-button").click();
        $("a[data-test=\"logout-sidebar-link\"]").click();
    }
    @Test
    @Tag("AUTOTEST")
    void test(){
        $$("div[data-test=\"inventory-item-name\"]").findBy(Condition.text("Sauce Labs Bolt T-Shirt")).click();
        $("div[data-test=\"inventory-item-price\"]").should(Condition.text("$15.99"));
    }

    @Test
    @Tag("AUTOTEST")
    void test2(){
        $$("div[data-test=\"inventory-item-name\"]").findBy(Condition.text("Sauce Labs Bolt T-Shirt")).click();
        $("button[data-test=\"add-to-cart\"]").click();
        $("span[data-test=\"shopping-cart-badge\"]").should(Condition.text("1"));
        $("a[data-test=\"shopping-cart-link\"]").click();
        $$("div[data-test=\"inventory-item-name\"]").findBy(Condition.text("Sauce Labs Bolt T-Shirt")).should(Condition.visible);
    }
}
