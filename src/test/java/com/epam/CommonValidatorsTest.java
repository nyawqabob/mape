package com.epam;

import by.epam.validator.CommonValidator;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import validatorfortest.ValidatorForTest;

public class CommonValidatorsTest {

    public ValidatorForTest checkOnValueAndLenghthValidator = new ValidatorForTest();
    List<String> messages = new ArrayList<>();

    private static final Integer WRONG_DATA_FOR_CHECK_ON_VALUE_AND_LENGTH_VALIDATOR = 1111111111;
    private static final Integer RIGHT_DATA_FOR_CHECK_ON_VALUE_AND_LENGTH_VALIDATOR = 322;

    private static final String WRONG_DATA_FOR_CHECK_ON_REG_EXP_VALIDATOR = "<b>hi</b>";
    private static final String RIGHT_DATA_FOR_CHECK_ON_REG_EXP_VALIDATOR = "hello";

    @Before
    public void setUp() {
        messages.clear();
    }

    @Test
    public void listShouldBeEmptyWhenAddRightData() {
        //when
        checkOnValueAndLenghthValidator.checkOnValueAndLength(RIGHT_DATA_FOR_CHECK_ON_VALUE_AND_LENGTH_VALIDATOR, messages, "don't need");
        //then
        boolean mustBeTrue = messages.isEmpty();
        Assert.assertTrue(mustBeTrue);
    }

    @Test
    public void listShouldBeNotEmptyWhenAddWrongData() {
        //when
        checkOnValueAndLenghthValidator.checkOnValueAndLength(WRONG_DATA_FOR_CHECK_ON_VALUE_AND_LENGTH_VALIDATOR, messages, "don't need");
        //then
        boolean mustBeFalse = messages.isEmpty();
        Assert.assertFalse(mustBeFalse);
    }

    @Test
    public void listShouldBeEmptyWhenAddRightDataSecond() {
        //when
        checkOnValueAndLenghthValidator.checkOnRegExp(RIGHT_DATA_FOR_CHECK_ON_REG_EXP_VALIDATOR, messages, "don't need");
        //then
        boolean mustBeTrue = messages.isEmpty();
        Assert.assertTrue(mustBeTrue);
    }

    @Test
    public void listShouldBeNotEmptyWhenAddWrongDataSecond() {
        //when
        checkOnValueAndLenghthValidator.checkOnRegExp(WRONG_DATA_FOR_CHECK_ON_REG_EXP_VALIDATOR, messages, "don't need");
        //then
        boolean mustBeFalse = messages.isEmpty();
        Assert.assertFalse(mustBeFalse);
    }
}
