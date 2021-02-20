package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pages.AutomationPracticeForm;
import settings.BaseTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.page;

public class PracticeFormTest extends BaseTest {

    private static final AutomationPracticeForm practiceForm = page(AutomationPracticeForm.class);
    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String genderMale = faker.dog().gender();
    String number = faker.phoneNumber().subscriberNumber(10);
    String birthYear = String.valueOf(faker.number().numberBetween(1900, 2100));
    String birthMonth = "august";
    String birthDay = String.valueOf(faker.number().numberBetween(10, 28));
    String subject = "English";
    String sports = "Sports";
    String music = "Music";
    String pictureName = "dart-veyder-boba-fett-kostyumy.jpg";
    String address = faker.address().streetAddress();
    String state = "Uttar Pradesh";
    String city = "Agra";

    Map<String, String> registerForm = new HashMap<>();

    @Test
    void studentRegistrationForm() {
        fillMapForRegisterFormMap();
        practiceForm.openPage();
        practiceForm.checkForm();
        practiceForm.fillName(firstName, lastName);
        practiceForm.fillEmail(email);
        practiceForm.selectGender(genderMale);
        practiceForm.fillMobileNumber(number);
        practiceForm.selectDateOfBirth(birthYear, birthMonth, birthDay);
        practiceForm.fillSubjects(subject);
        practiceForm.selectHobbies(Arrays.asList(sports, music));
        practiceForm.loadPictures(pictureName);
        practiceForm.fillCurrentAddress(address);
        practiceForm.selectState(state);
        practiceForm.selectCity(city);
        practiceForm.clickOnSubmitButton();
        practiceForm.formSuccessfullyFilled();
        practiceForm.checkRegisterForm(registerForm);
    }

    private void fillMapForRegisterFormMap() {
        registerForm.put("Student Name", firstName + " " + lastName);
        registerForm.put("Student  Email", email);
        registerForm.put("Gender", genderMale);
        registerForm.put("Mobile", number);
        registerForm.put("Date of Birth", birthDay + " " + birthMonth + "," + birthYear);
        registerForm.put("Subjects", subject);
        registerForm.put("Hobbies", sports + ", " + music);
        registerForm.put("Picture", pictureName);
        registerForm.put("Address", address);
        registerForm.put("State and City", state + " " + city);
    }
}
