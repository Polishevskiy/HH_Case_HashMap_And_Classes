package tests;

import core.BaseSelenideTest;
import pages.Resume;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HhResumePage;
import java.util.*;

public class HhTest extends BaseSelenideTest {
    /**
     * Link to summary for data
     */
    private final static String URL = "https://hh.ru/applicant/resumes/view?resume=1edf0c93ff095811d20039ed1f6a3638497073";

    /**
     * Gets candidate attributes via HashMap, compares actual and expected result
     */
    @Test
    public void checkAttributesMap(){
        HhResumePage hhResumePage = new HhResumePage(URL);
        //создаем карту ключ-значение с ожидаемыми данными
        Map<String,Object> expectedAttributes = new HashMap<>();
        expectedAttributes.put(HhResumePage.GENDER,"М");
        expectedAttributes.put(HhResumePage.AGE, 26);
        expectedAttributes.put(HhResumePage.CITY, "Санкт-Петербург");
        expectedAttributes.put(HhResumePage.RELOCATE, false);
        expectedAttributes.put(HhResumePage.CONFIRMED_PHONE, true);

        //получаем карту ключ-значение с актуальными данными
        Map<String,Object> actualAttributes = hhResumePage.getAttributes();

        //сравниваем ожидаемый и актуальный результат
        Assertions.assertEquals(expectedAttributes,actualAttributes);
    }

    /**
     * Gets candidate attributes via Class, compares the actual and expected results in two ways
     */
    @Test
    public void checkAttributesClass(){
        HhResumePage hhResumePage = new HhResumePage(URL);
        //создаем экземпляр класса с ожидаемыми данными через конструктор
        Resume expectedResume = new Resume("М", "Санкт-Петербург",26, true,false);

        //получаем экземпляр класса с актуальными данными через конструктор
        Resume actualResume = new Resume(hhResumePage.getGenderEasy(), hhResumePage.getCityEasy(), hhResumePage.getAge(),
                hhResumePage.isPhoneConfirmed(),hhResumePage.isReadyToRelocate());

        //1 способ сравнивнения классов
        Assertions.assertTrue(EqualsBuilder.reflectionEquals(expectedResume,actualResume));

        //2 способ сравнения отдельных переменных в классе
        Assertions.assertEquals(expectedResume.getGender(), actualResume.getGender());
        Assertions.assertEquals(expectedResume.getCity(), actualResume.getCity());
        Assertions.assertEquals(expectedResume.getAge(), actualResume.getAge());
        Assertions.assertEquals(expectedResume.isNumberConfirmed(), actualResume.isNumberConfirmed());
        Assertions.assertEquals(expectedResume.isReadyToRelocate(), actualResume.isReadyToRelocate());
    }
}
