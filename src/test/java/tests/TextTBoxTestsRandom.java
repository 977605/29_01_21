package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.RandomUtils;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;


public class TextTBoxTestsRandom extends RandomUtils {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void textTBoxTestsRandom() {
        String firstName = getRandomString(6),
                lastName = getRandomString(9),
                userEmail = getRandomEmail(),
                gender = "Male",
                userNumber = getRandomPhone(),
                month = "April",
                year = "2009",
                subjectsInput = "Chemistry",
                hobby = "Sports",
                currentAddress = getRandomString(12),
                state = "NCR",
                city = "Noida";

        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(userNumber);

        //date
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__week").$(byText("1")).click();

        $("#subjectsInput").setValue(subjectsInput).pressEnter();

        $("#hobbiesWrapper").$(byText(hobby)).click();

        //pict
        $("#uploadPicture").uploadFile(new File("src/test/java/docs/1.jpg"));

        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();

        $("#submit").click();

        //form testing
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $$(".table-responsive tr").filterBy(text("Student name")).shouldHave(texts(firstName + " " + lastName));
        $$(".table-responsive tr").filterBy(text("Student email")).shouldHave(texts(userEmail));
        $$(".table-responsive tr").filterBy(text("Gender")).shouldHave(texts("Male"));
        $$(".table-responsive tr").filterBy(text("Mobile")).shouldHave(texts(userNumber.substring(0, 9)));
        $$(".table-responsive tr").filterBy(text("Date of birth")).shouldHave(texts("1 April,2009"));
        $$(".table-responsive tr").filterBy(text("Subjects")).shouldHave(texts(subjectsInput));
        $$(".table-responsive tr").filterBy(text("Hobbies")).shouldHave(texts("Sports"));
        $$(".table-responsive tr").filterBy(text("Picture")).shouldHave(texts("1.jpg"));
        $$(".table-responsive tr").filterBy(text("Address")).shouldHave(texts(currentAddress));
        $$(".table-responsive tr").filterBy(text("State and City")).shouldHave(texts(state + " " + city));

    }
}
