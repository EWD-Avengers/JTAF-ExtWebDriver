/*
 * (C) Copyright 2013 Java Test Automation Framework Contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.finra.jtaf.ewd.widget.element.gwt;

import org.finra.jtaf.ewd.ExtWebDriver;
import org.finra.jtaf.ewd.session.SessionManager;
import org.finra.jtaf.ewd.widget.IRadioGroup;
import org.finra.jtaf.ewd.widget.WidgetException;
import org.finra.jtaf.ewd.widget.element.Element;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class GWTRadioGroupTest {

    public static String url = "http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwRadioButton";


    public ExtWebDriver wd;

    protected String radioLocator = "//input[@type=\"radio\" and @name=\"color\"]";
    protected String radioOption1Locator = "//input[@type=\"radio\" and @name=\"color\"]/../label[text()=\"blue\"]/../input";
    protected String radioOption2Locator = "//input[@type=\"radio\" and @name=\"color\"]/../label[text()=\"red\"]/../input";
    protected String brokenLocator = "//input[@type=\"radio\" and @name=\"color\"]";

    private IRadioGroup radio = new GWTRadioGroup(radioLocator);
    private IRadioGroup brokenRadio = new GWTRadioGroup(brokenLocator);

    @Before
    public void setup() throws Exception {
        wd = SessionManager.getInstance().getNewSession("client","localclientproperties/chrome.properties");
        
    }

    @After
    public void teardown() {
        wd.close();
        SessionManager.getInstance().removeSession(wd);
    }

    @Test
    public void testSelect() throws WidgetException{
        wd.open(url);
        radio.waitForElementPresent();
        radio.select("blue");
        Assert.assertTrue(new Element(radioOption1Locator).getAttribute("checked").equals("true"));
        Assert.assertTrue(new Element(radioOption2Locator).getAttribute("checked").equals(""));
    }

    @Test
    public void testSelectException() throws WidgetException{
        wd.open(url);
        radio.waitForElementPresent();

        boolean exception = false;
        try {
            brokenRadio.select("test");
        } catch (WidgetException we){
            exception = true;
        }
        Assert.assertTrue("Exception wasn't thrown", exception);
    }

    @Test
    public void testSelectExceptionNoOption() throws WidgetException{
        wd.open(url);
        radio.waitForElementPresent();

        boolean exception = false;
        try {
            radio.select("test");
        } catch (WidgetException we){
            exception = true;
        }
        Assert.assertTrue("Exception wasn't thrown", exception);
    }

    @Test
    public void testSetValueException() throws WidgetException{
        wd.open(url);
        radio.waitForElementPresent();

        boolean exception = false;
        try {
            radio.setValue(new Boolean("true"));
        } catch (WidgetException we){
            exception = true;
        }
        Assert.assertTrue("Exception wasn't thrown", exception);
    }

    @Test
    public void testSelectedOption() throws WidgetException{
        wd.open(url);
        radio.waitForElementPresent();
        radio.select("red");
        Assert.assertNotEquals("blue", radio.getSelectedValue());
        Assert.assertEquals("red", radio.getSelectedValue());
        radio.select("blue");
        Assert.assertNotEquals("red", radio.getSelectedValue());
        Assert.assertEquals("blue", radio.getSelectedValue());
    }

    @Test
    public void testGetOptions() throws WidgetException{
        wd.open(url);
        radio.waitForElementPresent();
        List<String> actualOptions = radio.getValues();
        Assert.assertEquals(4, actualOptions.size());
        Assert.assertEquals("blue", actualOptions.get(0));
        Assert.assertEquals("red", actualOptions.get(1));
        Assert.assertEquals("yellow", actualOptions.get(2));
        Assert.assertEquals("green", actualOptions.get(3));
    }

    @Test
    public void testSelectedOptionException() throws WidgetException{
        wd.open(url);
        radio.waitForElementPresent();
        boolean exception = false;
        try {
            brokenRadio.getSelectedValue();
        } catch (WidgetException we){
            exception = true;
        }
        Assert.assertTrue("Exception wasn't thrown", exception);
    }
}
