package org.finra.jtaf.ewd.widget.element.gwt;

import org.apache.http.MethodNotSupportedException;
import org.finra.jtaf.ewd.widget.ICalendar;
import org.finra.jtaf.ewd.widget.WidgetException;
import org.finra.jtaf.ewd.widget.element.Element;
import org.finra.jtaf.ewd.widget.element.InteractiveElement;
import org.finra.jtaf.ewd.widget.element.html.Button;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by bryan on 7/19/14.
 */
public class GWTDatePicker extends InteractiveElement implements ICalendar {

    /**
     * @param locator XPath, ID, name, CSS Selector, class name, or tag name
     * @throws org.finra.jtaf.ewd.widget.WidgetException
     */

    private static final String monthAndYearLocator = "//td[contains(@class, \"datePickerMonth\")]";
    private static final String nextMonthButtonLocator = "//div[contains(@class, \"datePickerNextButton\")]/div";
    private static final String prevMonthButtonLocator = "//div[contains(@class, \"datePickerPreviousButton\")]/div";
    private static final String dayLocator = "//div[contains(@class, \"datePickerDay\") and not(contains(@class, \"datePickerDayIsFiller\"))]";

    public GWTDatePicker(String locator) throws WidgetException {
        super(locator);
    }

    @Override
    public boolean isOpen() throws WidgetException {
        return this.isElementVisible();
    }

    @Override
    public void close() throws WidgetException {
        return;
    }

    private String getCurrentMonth() {
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat("MMMM").format(cal.getTime());
    }

    private String getCurrentDay() {
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat("DD").format(cal.getTime());
    }

    private String getCurrentYear() {
        Calendar cal = Calendar.getInstance();
        return new SimpleDateFormat("YYYY").format(cal.getTime());
    }

    @Override
    public void chooseToday() throws WidgetException {
        setMonthAndYear(getCurrentMonth(), getCurrentYear());
        setDayInCurrentMonth(getCurrentDay());
    }

    public void setDayInCurrentMonth(String currentDay) {
        Element day = new Element(getLocator() + dayLocator);
    }

    public String getSelectedMonth() throws WidgetException {
        Element month = new Element(getLocator() + "//td[contains(@class, \"datePickerMonth\")]");
        return month.getText().split(" ")[0];
    }

    public String getSelectedYear() throws WidgetException {
        Element month = new Element(getLocator() + "//td[contains(@class, \"datePickerMonth\")]");
        return month.getText().split(" ")[1];
    }

    public void setMonthAndYear(String month, String year) throws WidgetException {

        // Get the currently selected year and month
        String nowYear = this.getSelectedYear();
        String nowMonth = this.getSelectedMonth();


        // Figure out which direction to go
        boolean past = false;

        if (Integer.parseInt(year) < Integer.parseInt(nowYear)) {
            past = true;
        }

        // Get to the right year
        while (!nowYear.equals(year)) {
            if (past) {
                goToPreviousMonth();
            } else {
                goToNextMonth();
            }

            nowYear = this.getSelectedYear();
        }

        nowMonth = this.getSelectedMonth();

        // Get to the right month
        while (!nowMonth.equals(month)) {
            if (past) {
                goToPreviousMonth();
            } else {
                goToNextMonth();
            }
            nowMonth = this.getSelectedMonth();
        }
    }

    public void goToNextMonth() throws WidgetException {
        Button prevMonthButton = new Button(getLocator() + nextMonthButtonLocator);
        prevMonthButton.click();
    }

    public void goToPreviousMonth() throws WidgetException {
        Button prevMonthButton = new Button(getLocator() + prevMonthButtonLocator);
        prevMonthButton.click();
    }

    @Override
    public void enterDate(String value) {

    }

    @Override
    public void setValue(Object value) throws WidgetException {

    }
}