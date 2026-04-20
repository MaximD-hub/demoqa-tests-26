package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByTagAndText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byTagAndText;
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
        $("#firstName").setValue(TestData.FIRST_NAME);
        $("#lastName").setValue(TestData.SECOND_NAME);
        $("#userEmail").setValue(TestData.EMAIL);

        $(byTagAndText("label","Male")).click();

        $("#userNumber").setValue(TestData.MOBILE_NUMBER);

        // Выбор подходящей даты (с учетом что в сетке могут быть 2 одинаковые даты, но ра разные месяца)
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $(byTagAndText("option","2026")).click();
        $(".react-datepicker__month-select").click();
        $(byTagAndText("option","October")).click();
        $x("//div[contains(@aria-label, 'October') and text()='30']").click();

        $("#subjectsInput").setValue("Math, Physics, History");

        $("#hobbies-checkbox-1").click();
        $("#hobbies-checkbox-3").click();

        //Загрузка фотографии
        $("#uploadPicture").uploadFile(TestData.PICTURE_FILE);

        $("#currentAddress").setValue(TestData.CURRENT_ADDRESS);

        $("#state").click();



    }
}
