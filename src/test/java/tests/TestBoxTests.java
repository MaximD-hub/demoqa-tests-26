package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestBoxTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        open("/text-box");
        $("#userName").setValue(TestData.FULL_NAME);
        $("#userEmail").setValue(TestData.EMAIL);
        $("#currentAddress").setValue(TestData.CURRENT_ADDRESS);
        $("#permanentAddress").setValue(TestData.PERMANENT_ADDRESS);
        $("#submit").click();

        $("#output").$("#name").shouldHave(text("Name:" + TestData.FULL_NAME));
        $("#output").$("#email").shouldHave(text("Email:" + TestData.EMAIL));
        $("#output").$("#currentAddress").shouldHave(text("Current Address :" + TestData.CURRENT_ADDRESS));
        $("#output").$("#permanentAddress").shouldHave(text("Permananet Address :" + TestData.PERMANENT_ADDRESS));
    }
}
