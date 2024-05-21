import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
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
    @Test
    @Tag("AUTOTEST")
    void buyBikeLight(){
        $$("div[data-test=\"inventory-item-name\"]").findBy(Condition.text("Sauce Labs Bike Light")).click();
        $("button[data-test=\"add-to-cart\"]").click();
        $("a[data-test=\"shopping-cart-link\"] span[data-test=\"shopping-cart-badge\"]").should(exist,Condition.text("1"));
        $("a[data-test=\"shopping-cart-link\"]").click();
        $("div[data-test=\"inventory-item-name\"]").has(Condition.text("Sauce Labs Bike Light"));
        $("button[data-test=\"remove-sauce-labs-bike-light\"]").is(exist);
        $("button[data-test=\"checkout\"]").click();

        $("input[data-test=\"firstName\"]").sendKeys("Tester");
        $("input[data-test=\"lastName\"]").sendKeys("Tester");
        $("input[data-test=\"postalCode\"]").sendKeys("12345");
        $("input[data-test=\"continue\"]").click();

        $("div[data-test=\"payment-info-value\"]").has(text("SauceCard #31337"));
        $("div[data-test=\"shipping-info-value\"]").has(text("Free Pony Express Delivery!"));
        $("button[data-test=\"finish\"]").click();

        $("[data-test=\"complete-header\"]").has(text("Thank you for your order!"));
        $("button[data-test=\"back-to-products\"]").is(exist);
    }
}
