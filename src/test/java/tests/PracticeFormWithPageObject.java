package tests;


import org.junit.jupiter.api.Test;
import pages.RegistrationPage;


public class PracticeFormWithPageObject extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();



    @Test
    public void automationPracticeForm() {
        registrationPage.openPage();

        registrationPage.setFirstName(TestData.FIRST_NAME)
                .setLastName(TestData.SECOND_NAME)
                .setEmail(TestData.EMAIL)
                .setGender("Male")
                .setUserNumber(TestData.MOBILE_NUMBER)
                .setDateOfBirth("30","October","2026")
                .setSubject("M", "Maths")
                .setHobbies()
                .setPicture(TestData.PICTURE_FILE)
                .setCurrentAddress(TestData.CURRENT_ADDRESS)
                .setStateCity()
                .clickSubmit()
                .verifySuccessModal("Thanks for submitting the form")
                .checkResult("Student Name",TestData.FULL_NAME)
                .checkResult("Student Email",TestData.EMAIL);
    }

}
