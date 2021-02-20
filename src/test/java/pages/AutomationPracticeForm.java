package pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.io.File;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {
    private final SelenideElement
            registrationForm = $(".practice-form-wrapper"),
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            email = $("#userEmail"),
            mobileNumber = $("#userNumber"),
            dateOfBirth = $("#dateOfBirthInput"),
            yearSelector = $(".react-datepicker__year-select"),
            monthSelector = $(".react-datepicker__month-select"),
            subjects = $("#subjectsContainer input"),
            picture = $("#uploadPicture"),
            currentAddress = $("#currentAddress"),
            state = $("#state"),
            city = $("#city"),
            submitButton = $("#submit"),
            submitPopupHeader = $("#example-modal-sizes-title-lg");

    private final ElementsCollection
            hobbies = $$("#hobbiesWrapper label"),
            gender = $$("#genterWrapper label"),
            stateList = $$("#state [id^='react-select-3-option']"),
            cityList = $$("#city [id^='react-select-4-option']"),
            filledForm = $$(".table-responsive tbody tr");

    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Step("Fill first and last name")
    public void fillName(String firstNameValue, String lastNameValue) {
        firstName.setValue(firstNameValue);
        lastName.setValue(lastNameValue);
    }

    @Step("Fill email")
    public void fillEmail(String emailValue) {
        email.setValue(emailValue);
    }

    @Step("Select gender")
    public void selectGender(String genderValue) {
        gender.findBy(text(genderValue)).click();
    }

    @Step("Fill mobile number")
    public void fillMobileNumber(String number) {
        mobileNumber.setValue(number);
    }

    @Step("Select date of birth")
    public void selectDateOfBirth(String year, String month, String day) {
        dateOfBirth.click();
        yearSelector.click();
        yearSelector.$$("option").findBy(text(year)).click();
        monthSelector.click();
        monthSelector.$$("option").findBy(text(month)).click();
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }

    @Step("Fill subject")
    public void fillSubjects(String subjectValue) {
        subjects.setValue(subjectValue).pressEnter();
    }

    @Step("Select hobbies")
    public void selectHobbies(List<String> hobbiesList) {
        hobbiesList.forEach((item) -> hobbies.filterBy(text(item)).first().click());
    }

    @Step("Load picture")
    public void loadPictures(String name) {
        picture.uploadFile(new File("src/test/resources/" + name));
    }

    @Step("Fill current address")
    public void fillCurrentAddress(String address) {
        currentAddress.setValue(address);
    }

    @Step("Select state")
    public void selectState(String stateValue) {
        state.click();
        stateList.findBy(text(stateValue)).click();
    }

    @Step("Select city")
    public void selectCity(String cityValue) {
        city.click();
        cityList.findBy(text(cityValue)).click();
    }

    @Step("Submit the form")
    public void clickOnSubmitButton() {
        submitButton.click();
    }

    @Step("Verify that the form is open")
    public void checkForm() {
        registrationForm.shouldBe(visible);
    }

    @Step("Verify form header")
    public void formSuccessfullyFilled() {
        submitPopupHeader.shouldHave(text("Thanks for submitting the form"));
    }

    @Step("Verify that all fields are filled correctly")
    public void checkRegisterForm(Map<String, String> registerFormData) {
        for (Map.Entry<String, String> entry : registerFormData.entrySet()) {
            filledForm
                    .findBy(text(entry.getKey()))
                    .$$("td")
                    .last()
                    .shouldHave(text(entry.getValue()));
        }
    }
}
