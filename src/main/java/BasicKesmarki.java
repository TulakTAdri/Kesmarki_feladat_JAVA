import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class BasicKesmarki extends BasePage {
    public BasicKesmarki(WebDriver driver) {
        super(driver);
    }

    private final String url = "https://www.kesmarki.com/test_form";
    private final By valueFieldName = By.id("name");
    private final By ValueFieldDate = By.id("date");
    private final By TesztButton = By.xpath("//button[text()=\"Teszt\"]");


    public void navigate() {
        driver.get(url);
    }

    public void inputNameClear() {
        driver.findElement(valueFieldName).clear();
    }

    public void inputName(String name) {
        driver.findElement(valueFieldName).sendKeys(name);
    }

    public void inputDateClear() {
        driver.findElement(ValueFieldDate).clear();
    }

    public void inputDate(String date) {
        driver.findElement(ValueFieldDate).sendKeys(date);
    }

    public void clickButton() {
        driver.findElement(TesztButton).click();
    }

    List<String> AlertList = new ArrayList<String>();

    public void getAlerts() {

        WebElement AlertField = new WebDriverWait(driver, (10)).
                until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'alert')]")));
        String getAttributeAlertField = AlertField.getAttribute("class");

        WebElement AlertFieldText = driver.findElement(By.xpath("//div[contains(@class, 'alert')]/h3"));
        String getAlertFieldText = AlertFieldText.getText();
        System.out.println(getAlertFieldText);

        if (getAttributeAlertField.contentEquals("alert danger-alert") && !AlertList.contains(getAlertFieldText)) {
            AlertList.add(getAlertFieldText);
        }
    }

    public void setAlertList() throws IOException {
        FileWriter fileWriter = new FileWriter("src/test/alerts.txt");

        for (String s : AlertList) {
            fileWriter.write(s);
            fileWriter.flush();
        }
        fileWriter.close();
    }
}
