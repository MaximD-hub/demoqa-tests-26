package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.TestData.*;

public class TestBoxTests extends TestBase {

    @Test
    void fillFormTest() {
        open("/text-box");
        $("#userName").setValue(FULL_NAME);
        $("#userEmail").setValue(EMAIL);
        $("#currentAddress").setValue(CURRENT_ADDRESS);
        $("#permanentAddress").setValue(PERMANENT_ADDRESS);
        $("#submit").click();

        $("#output").$("#name").shouldHave(text("Name:" + FULL_NAME));
        $("#output").$("#email").shouldHave(text("Email:" + EMAIL));
        $("#output").$("#currentAddress").shouldHave(text("Current Address :" + CURRENT_ADDRESS));
        $("#output").$("#permanentAddress").shouldHave(text("Permananet Address :" + PERMANENT_ADDRESS));
    }
}
