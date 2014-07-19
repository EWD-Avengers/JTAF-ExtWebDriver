package org.finra.jtaf.ewd.widget.element.gwt;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.session.SessionManager;
import org.finra.jtaf.ewd.widget.IButton;
import org.finra.jtaf.ewd.widget.ICalendar;
import org.finra.jtaf.ewd.widget.WidgetException;
import org.finra.jtaf.ewd.widget.element.Element;
import org.finra.jtaf.ewd.widget.element.html.Button;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by bryan on 7/19/14.
 */
public class GWTDatePickerTest {

    public static String url = "http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwDatePicker";
    public static String datePickerLocator = "(//table[contains(@class, \"gwt-DatePicker\")])[1]";

    GWTDatePicker datePicker;

    ExtWebDriver wd;

    public GWTDatePickerTest() throws WidgetException {
        datePicker = new GWTDatePicker(datePickerLocator);
    }

    private String getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat("MMM").format(cal.getTime());
    }

    private String getCurrentDay() {
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat("DD").format(cal.getTime());
    }

    private String getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat("YYYY").format(cal.getTime());
    }



    @Before
    public void setup() throws Exception {
        wd = SessionManager.getInstance().getNewSession("client", "localclientproperties/chrome.properties");
    }

    @After
    public void teardown() {
        wd.close();
        SessionManager.getInstance().removeSession(wd);
    }

    @Test
    public void isOpenTest() throws Exception {
        wd.open(url);
        datePicker.waitForElementPresent();
        Assert.assertTrue(datePicker.isOpen());
    }

    @Test
    public void selectedMonthAndYearTest() throws Exception {
        wd.open(url);
        datePicker.waitForElementPresent();
        Assert.assertEquals(getCurrentMonth(), datePicker.getSelectedMonth());
        Assert.assertEquals(getCurrentYear(), datePicker.getSelectedYear());
    }

    @Test
    public void previousMonthTest() throws WidgetException {
        wd.open(url);
        datePicker.waitForElementPresent();
        datePicker.goToPreviousMonth();

        Assert.assertEquals("Jun", datePicker.getSelectedMonth());
        Assert.assertEquals(getCurrentYear(), datePicker.getSelectedYear());
    }

    @Test
    public void nextMonthTest() throws WidgetException {
        wd.open(url);
        datePicker.waitForElementPresent();
        datePicker.goToNextMonth();

        Assert.assertEquals("Aug", datePicker.getSelectedMonth());
        Assert.assertEquals(getCurrentYear(), datePicker.getSelectedYear());
    }

    @Test
    public void setMonthAndYearTest() throws WidgetException {
        wd.open(url);
        datePicker.waitForElementPresent();
        datePicker.setMonthAndYear("Jul", "2012");

        Assert.assertEquals("2012", datePicker.getSelectedYear());
        Assert.assertEquals("Jul", datePicker.getSelectedMonth());
    }

}