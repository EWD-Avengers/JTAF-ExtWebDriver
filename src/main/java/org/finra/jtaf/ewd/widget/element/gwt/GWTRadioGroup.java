package org.finra.jtaf.ewd.widget.element.gwt;

import java.util.ArrayList;
import java.util.List;

import org.finra.jtaf.ewd.widget.WidgetException;
import org.finra.jtaf.ewd.widget.element.InteractiveElement;
import org.finra.jtaf.ewd.widget.element.html.RadioGroup;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GWTRadioGroup extends RadioGroup{

	public GWTRadioGroup(String locator) {
		super(locator);
		// TODO Auto-generated constructor stub
	}
	
    /*
     * (non-Javadoc)
     * 
     * @see
     * qc.automation.framework.widget.IEditableField#setValue(java.lang.String)
     */
    @Override
    public void setValue(Object value) throws WidgetException {
        try {
            if (value instanceof String) {
                boolean selected = false;
                List<WebElement> elements = findElements();
                for (WebElement we : elements) {
                	//Works only for XPath
                	String fullXpath = getLocator()+"/../label[text()=\""+value.toString()+"\"]/../input";
                	InteractiveElement radioOption = new InteractiveElement(fullXpath);
                    if (radioOption.isElementPresent()) {
                    	radioOption.click();
                        selected = true;
                        break;
                    }
                }

                if (!selected)
                    throw new WidgetException("Could not find desired option to select",
                            getLocator());
            } else {
                throw new WidgetException("Invalid type. 'value' must be a 'String' type of desired option to select",
                        getLocator());
            }
        }  catch(NoSuchElementException nse){
        	  throw new NoSuchElementException("Could not find elements matching " + getLocator(),
                      nse);     	
            }    
         catch (Exception e) {
            throw new WidgetException("Error while selecting option on radio group", getLocator(),
                    e);
        }
        
    }

    /**
     * 
     * @return list of web elements found based on all locators
     * @throws WidgetException
     */
    private List<WebElement> findElements() throws WidgetException {
        String locator = getLocator();
        getGUIDriver().selectLastFrame();
        WebDriver wd = getGUIDriver().getWrappedDriver();

        List<WebElement> webElements;
        try {
            webElements = wd.findElements(By.xpath(locator));
            if (webElements != null && webElements.size() > 0) {
                for (WebElement we : webElements) {
                    highlight( HIGHLIGHT_MODES.FIND);
                }
                return webElements;
            }
        } catch (Exception e) {
        }

        try {
            webElements = wd.findElements(By.id(locator));
            if (webElements != null && webElements.size() > 0) {
                for (WebElement we : webElements) {
                    highlight( HIGHLIGHT_MODES.FIND);
                }
                return webElements;
            }
        } catch (Exception e) {
        }

        try {
            webElements = wd.findElements(By.name(locator));
            if (webElements != null && webElements.size() > 0) {
                for (WebElement we : webElements) {
                    highlight( HIGHLIGHT_MODES.FIND);
                }
                return webElements;
            }
        } catch (Exception e) {
        }

        try {
            webElements = wd.findElements(By.cssSelector(locator));
            if (webElements != null && webElements.size() > 0) {
                for (WebElement we : webElements) {
                    highlight( HIGHLIGHT_MODES.FIND);
                }
                return webElements;
            }
        } catch (Exception e) {
        }

        try {
            webElements = wd.findElements(By.className(locator));
            if (webElements != null && webElements.size() > 0) {
                for (WebElement we : webElements) {
                    highlight( HIGHLIGHT_MODES.FIND);
                }
                return webElements;
            }
        } catch (Exception e) {
        }

        try {
            webElements = wd.findElements(By.tagName(locator));
            if (webElements != null && webElements.size() > 0) {
                for (WebElement we : webElements) {
                    highlight( HIGHLIGHT_MODES.FIND);
                }
                return webElements;
            }
        } catch (Exception e) {
        }

        throw new NoSuchElementException("Could not find elements matching " + locator);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * qc.automation.framework.widget.IEditableField#getValue(java.lang.String)
     */
    @Override
    public String getValue() throws WidgetException {
        List<WebElement> elements = findElements();
        int counter = 0;
        for (WebElement we : elements) {
        	counter++;
            if (we.getAttribute("checked") != null
                    && we.getAttribute("checked").equalsIgnoreCase("true")) {
            	InteractiveElement iE = new InteractiveElement("("+getLocator()+")["+counter+"]/../label");
                return(iE.getText());

            }
        }

        throw new WidgetException("Error while finding selected option on radio group",
                getLocator());
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see
     * qc.automation.framework.widget.IRadioGroup#getValues()
     */
    @Override
    public List<String> getValues() throws WidgetException {
        List<String> options = new ArrayList<String>();
        List<WebElement> elements = findElements();
        int counter = 0;
        for (WebElement we : elements) {
        	//Assumption: locator is xpath
        	counter++;
        	InteractiveElement iE = new InteractiveElement("("+getLocator()+")["+counter+"]/../label");
            options.add(iE.getText());
        }
        return options;
    }
    
}
