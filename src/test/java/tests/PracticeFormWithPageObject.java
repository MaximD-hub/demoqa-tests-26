package tests;


import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;

import static com.codeborne.selenide.Selenide.$;


public class PracticeFormWithPageObject extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();



    @Test
    public void automationPracticeForm() {
        registrationPage.openPage();

        registrationPage.setFirstName(TestData.FIRST_NAME)
                .setLastName(TestData.SECOND_NAME)
                .setEmail(TestData.EMAIL);

        registrationPage.setGender("Male");

        registrationPage.setUserNumber(TestData.MOBILE_NUMBER);

        registrationPage.setDateOfBirth("30","October","2026");




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

        registrationPage.checkResult("Student Name",TestData.FULL_NAME)
                .checkResult("Student Email",TestData.EMAIL);




    }
}
