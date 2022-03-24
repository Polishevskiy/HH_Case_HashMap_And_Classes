package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Base class for selenide initialization
 */
abstract public class BaseSelenideTest {

    /**
     * Selenide initialization with settings
     */
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.driverManagerEnabled = true;
        Configuration.browserSize = "1920x1080";
        Configuration.headless = false;
    }

    /**
     * Execution of the method before each test run
     */
    @BeforeEach
    public void init(){
        setUp();
    }

    /**
     * Execution of the method after each test closure
     */
    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }
}