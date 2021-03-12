package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class TextBoxTests {
    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void dataAppearsTestForPracticeForm() {

        Data data = new Data(
                "Oleg", "Koll",
                "oleg@bk.ru", "2312312321",
                "Chemistry", "usa",
                "NCR", "Noida");
        String  dayOfBirth = "10",
                monthOfBirth = "May",
                yearOfBirth = "1988";

        open("https://demoqa.com/automation-practice-form");
        $(".main-header").shouldHave(text("Practice Form"));

        $("#firstName").setValue(data.getFirstName());
        $("#lastName").setValue(data.getLastName());
        $("#userEmail").setValue(data.getUserEmail());
        $("[class='custom-control custom-radio custom-control-inline'], [id ='gender-radio-1']").click();
        $("#userNumber").setValue(data.getUserNumber());


        // date
        $("#dateOfBirthInput").clear();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__day--0" + dayOfBirth).click();

        $("#subjectsInput").setValue(data.getSubjectsInput()).pressEnter();

        $("[class='custom-control custom-checkbox custom-control-inline'], [id ='hobbies-checkbox-1']").click();

        //pict
        $("#uploadPicture").uploadFile(new File("src/test/java/docs/1.jpg"));

        $("#currentAddress").setValue(data.getCurrentAddress());
        $("#react-select-3-input").setValue(data.getState()).pressEnter();
        $("#react-select-4-input").setValue(data.getCity()).pressEnter();

        $("#submit").click();

        //form testing
        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $$(".table-responsive tr").filterBy(text("Student name")).shouldHave(texts(data.getFirstName() + " " + data.getLastName()));
        $$(".table-responsive tr").filterBy(text("Student email")).shouldHave(texts(data.getUserEmail()));
        $$(".table-responsive tr").filterBy(text("Gender")).shouldHave(texts("Male"));
        $$(".table-responsive tr").filterBy(text("Mobile")).shouldHave(texts(data.getUserNumber()));
        $$(".table-responsive tr").filterBy(text("Date of birth")).shouldHave(texts("10 May,1988"));
        $$(".table-responsive tr").filterBy(text("Subjects")).shouldHave(texts(data.getSubjectsInput()));
        $$(".table-responsive tr").filterBy(text("Hobbies")).shouldHave(texts("Sports"));
        $$(".table-responsive tr").filterBy(text("Picture")).shouldHave(texts("1.jpg"));
        $$(".table-responsive tr").filterBy(text("Address")).shouldHave(texts(data.getCurrentAddress()));
        $$(".table-responsive tr").filterBy(text("State and City")).shouldHave(texts(data.getState() + " " + data.getCity()));
    }


    //result
    //BUILD SUCCESSFUL in 28s
    //2 actionable tasks: 2 executed
}
