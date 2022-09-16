import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;


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


    public void ReadFromFile() throws IOException {
        FileInputStream file = new FileInputStream("src/test/Kesmarki_automata_tesztadatok.xlsx");
        Files.deleteIfExists(Path.of("src/test/alerts.txt"));
        FileWriter fileWriter = new FileWriter("src/test/alerts.txt", true);
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet sheet = wb.getSheet("Kesmarki_automata_tesztadatok");


        Set<String> alertSet = new HashSet<>();
        for (int row = 1; row <= sheet.getLastRowNum(); row++) {
            System.out.println(row);
            String tdname;
            String tddate;
            try {
                tdname = sheet.getRow(row).getCell(1).getStringCellValue();
                tddate = sheet.getRow(row).getCell(2).getStringCellValue();

                driver.findElement(valueFieldName).clear();
                driver.findElement(valueFieldName).sendKeys(tdname);
                driver.findElement(ValueFieldDate).clear();
                driver.findElement(ValueFieldDate).sendKeys(tddate);
                driver.findElement(TesztButton).click();
            } catch (Exception e) {
                System.out.println("Nullpointer");
                alertSet.add(e.toString());
            }

            alertSet.add(getAlerts());

        }
        for (String result : alertSet) {
            if (result != null) {
                fileWriter.write(result);
                fileWriter.write("\n");
            }
        }

        wb.close();
        file.close();
        fileWriter.close();
    }


    public String getAlerts() {
        String result = null;
        WebElement AlertField = new WebDriverWait(driver, (10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'alert')]")));
        String getAttributeAlertField = AlertField.getAttribute("class");

        WebElement AlertFieldText = driver.findElement(By.xpath("//div[contains(@class, 'alert')]/h3"));
        String getAlertFieldText = AlertFieldText.getText();
        System.out.println(getAlertFieldText);

        if (getAttributeAlertField.contentEquals("alert danger-alert")) {
            result = getAlertFieldText;

        }
        return result;
    }
}

