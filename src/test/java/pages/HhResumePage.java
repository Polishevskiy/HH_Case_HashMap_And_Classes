package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import java.util.HashMap;
import java.util.Map;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Resume page on hh
 */
public class HhResumePage {
    private final SelenideElement gender = $x("//span[@data-qa='resume-personal-gender']");
    private final SelenideElement age = $x("//span[@data-qa='resume-personal-age']/span");
    private final SelenideElement liveString = $x("//span[@data-qa='resume-personal-address']/ancestor::p");
    private final SelenideElement confirmedNumber = $x("//div[@data-qa='resume-contacts-phone']//span[1]");
    private final SelenideElement city = $x("//span[@data-qa='resume-personal-address']");

    /**
     * Constants for the key designation for the hash map
     */
    public static String GENDER = "Пол";
    public static String CITY = "Город";
    public static String AGE = "Возраст";
    public static String RELOCATE = "Готовность к переезду";
    public static String CONFIRMED_PHONE = "Подтрвежденный номер телефона";

    public HhResumePage(String url) {
        Selenide.open(url);
    }

    /**
     * Returns a map (key-value pairs) with actual CV data
     * The key of the map is an attribute of type String, the value of the map is an attribute of type Object (any type)
     *
     * An inline variant to get the hash of the card with the values. Simplified variant, you can see in the test class
     * @return filled map with resume
     */
    public Map<String,Object> getAttributes(){
        return new HashMap<String ,Object>(){{
            put(GENDER, getGenderHard());
            put(CITY, getCityHard());
            put(AGE, getAge());
            put(RELOCATE, isReadyToRelocate());
            put(CONFIRMED_PHONE, isPhoneConfirmed());
        }};
    }

    /**
     * Checks if the phone is confirmed in the profile or not
     * @return visibility checkbox
     */
    public boolean isPhoneConfirmed(){
        return confirmedNumber.isDisplayed();
    }

    /**
     * Returns the city in which the candidate lives
     * @return candidate's city
     */
    public String getCityEasy(){
        return city.getText();
    }

    /**
     * Splits the line about the information with the candidate's residence in the array into parts with the division ", "
     *
     * String of residency information to be received: St. Petersburg, not ready to move, not ready to travel
     *
     * The array with the split parts will have the following parts
     * [0] - St. Petersburg, [1] - not ready to move, [2] - not ready to travel
     *
     * @return the city where the candidate lives from the general string with information about residency
     */
    public String getCityHard(){
        return liveString.getText().split(", ")[0];
    }

    /**
     * Checks whether the candidate is ready to move or not
     * @return ready to move
     */
    public boolean isReadyToRelocate(){
        return !liveString.getText().split(", ")[1].equals("не готов к переезду");
    }

    /**
     * Gets the candidate's age from the resume using regular expressions
     *
     * Regular expression "[^0-9]" tells you to exclude everything that does not start with a number
     * You can implement it with a regular expression "\\D+" and thus you exclude all characters from the string
     *
     * @return candidate's age in numeric format
     */
    public int getAge(){
        return Integer.parseInt(age.getText().replaceAll("[^0-9]",""));
    }

    /**
     * Returns the gender of a candidate using a logical condition
     * equals can be replaced by ==
     * @return M or W, depending on gender
     */
    public String getGenderEasy(){
        String genderValue = gender.getText();
        if(genderValue.equals("Мужчина")){
            return "М";
        }
        return "Ж";
    }

    /**
     * Returns the candidate's gender via a ternary operator
     * @return M or W, depending on gender
     */
    public String getGenderHard(){
        return gender.getText().equals("Мужчина") ? "М" : "Ж";
    }
}