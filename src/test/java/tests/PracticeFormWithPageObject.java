package tests;


import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static tests.TestData.*;


public class PracticeFormWithPageObject extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();



    @Test
    public void automationPracticeForm() {
        registrationPage.openPage();

        registrationPage.setFirstName(FIRST_NAME)
                .setLastName(SECOND_NAME)
                .setEmail(EMAIL)
                .setGender("Male")
                .setUserNumber(MOBILE_NUMBER)
                .setDateOfBirth("30","October","2026")
                .setSubject("M", "Maths")
                .setHobbies()
                .setPicture(PICTURE_FILE)
                .setCurrentAddress(CURRENT_ADDRESS)
                .setStateCity()
                .clickSubmit()
                .verifySuccessModal("Thanks for submitting the form")
                .checkResult("Student Name",FULL_NAME)
                .checkResult("Student Email",EMAIL);
    }

}
