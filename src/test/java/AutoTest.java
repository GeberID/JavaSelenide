import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AutoTest extends BaseTest{
    @Test
    void test(){
        Selenide.open("https://www.saucedemo.com/");
        $("input[data-test=\"username\"]").sendKeys("standard_user");
        $("input[data-test=\"password\"]").sendKeys("secret_sauce");
        $("[data-test=\"login-button\"]").click();
        $$("div[data-test=\"inventory-item-name\"]").findBy(Condition.text("Sauce Labs Bolt T-Shirt")).click();
        $("div[data-test=\"inventory-item-price\"]").should(Condition.text("$15.99"));
    }

    @Test
    void test2(){
        Selenide.open("https://www.saucedemo.com/");
        $("input[data-test=\"username\"]").sendKeys("standard_user");
        $("input[data-test=\"password\"]").sendKeys("secret_sauce");
        $("[data-test=\"login-button\"]").click();
        $$("div[data-test=\"inventory-item-name\"]").findBy(Condition.text("Sauce Labs Bolt T-Shirt")).click();
        $("button[data-test=\"add-to-cart\"]").click();
        $("a[data-test=\"shopping-cart-link\"]").click();
        $$("div[data-test=\"inventory-item-name\"]").findBy(Condition.text("Sauce Labs Bolt T-Shirt")).should(Condition.visible);
    }
}
