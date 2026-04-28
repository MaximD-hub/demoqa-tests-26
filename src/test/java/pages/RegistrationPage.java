package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import java.io.File;


import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage  {
    
    private SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#email"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            subjectsValues = $(".subjects-auto-complete__multi-value"),
            sportsCheckbox = $("#hobbies-checkbox-1"),
            musicCheckbox = $("#hobbies-checkbox-3"),
            uploadPicture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            stateCity = $("#state"),
            haryanaOption = $("#react-select-3-option-3"),
            city = $("#city"),
            karnalOption = $("#react-select-4-option-1"),
            submit = $("#submit"),
            resultModal = $(".modal-content"),
            resultTitle = $("#example-modal-sizes-title-lg");


    CalendarComponent calendarComponent = new CalendarComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".text-center").shouldHave(text("Practice Form"));

        return this;
    }

    public RegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setUserNumber(String value) {
        userNumberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubject (String value1, String value2) {
        subjectInput.setValue(value1).pressTab();
        subjectsValues.shouldHave(text(value2));

        return this;
    }

    public RegistrationPage setHobbies () {
        sportsCheckbox.click();
        musicCheckbox.click();

        return this;
    }

    public RegistrationPage setPicture(File file) {
        uploadPicture.uploadFile(file);

        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        currentAddress.setValue(value);

        return this;
    }

    public RegistrationPage setStateCity() {

        stateCity.scrollIntoView(true).click();
        haryanaOption.click();
        city.click();
        karnalOption.click();

        return this;
    }

    public RegistrationPage clickSubmit () {
        submit.click();

        return this;
    }

    public RegistrationPage verifySuccessModal (String message) {
        resultModal.shouldBe(appear);
        resultTitle.shouldHave(text(message));

        return this;
    }

    public RegistrationPage checkResult(String key, String value) {
        $(".table-responsive").$(byText(key)).parent()
                .shouldHave(text(value));

        return this;
    }


}
