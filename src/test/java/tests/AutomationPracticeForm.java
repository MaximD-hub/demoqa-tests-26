package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    public void automationPracticeForm() {
        open("/automation-practice-form");
        $(".text-center").shouldHave(text("Practice Form"));
      //  executeJavaScript("$('fixedban').remove()"); // удаление рекламы, которая накладывается на команды
      //  executeJavaScript("$('footer').remove()");


        $("#firstName").setValue(TestData.FIRST_NAME);
        $("#lastName").setValue(TestData.SECOND_NAME);
        $("#userEmail").setValue(TestData.EMAIL);

        $("#genterWrapper").$(byText("Male")).click();
        //$("#gender-radio-1").parent().click();
        //$(byTagAndText("label","Male")).click();
        //$("label[for=gender-radio-1]).click();

        $("#userNumber").setValue(TestData.MOBILE_NUMBER);

        // Выбор подходящей даты (с учетом что в сетке могут быть 2 одинаковые даты, но ра разные месяца)
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("2026");
        $(".react-datepicker__month-select").selectOption("October");
        $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue("M").pressTab();
        $(".subjects-auto-complete__multi-value").shouldHave(text("Maths"));
                // " Physics, History");

        $("#hobbies-checkbox-1").click();
        $("#hobbies-checkbox-3").click();

        //Загрузка фотографии
        $("#uploadPicture").uploadFile(TestData.PICTURE_FILE);

        $("#currentAddress").setValue(TestData.CURRENT_ADDRESS);

        $("#state").scrollIntoView(true).click();
        $("#react-select-3-option-3").click();
        $("#city").click();
        $("#react-select-4-option-1").click();

        $("#submit").pressEnter();

        $(".modal-content").shouldBe(appear);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(TestData.FIRST_NAME), text(TestData.SECOND_NAME));


    }
}
