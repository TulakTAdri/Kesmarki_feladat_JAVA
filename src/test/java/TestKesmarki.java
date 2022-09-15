import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import java.io.IOException;

public class TestKesmarki extends BaseTest {

    @Test
    public void TestAlerts() throws IOException {
        BasicKesmarki basicKesmarki = new BasicKesmarki(driver);
        basicKesmarki.navigate();

        // test data:
        String nameToInput = "Tóth Adri";
        String dateToInput = "1800-01-01";

        basicKesmarki.inputNameClear();
        basicKesmarki.inputName(nameToInput);
        basicKesmarki.inputDateClear();
        basicKesmarki.inputDate(dateToInput);
        basicKesmarki.clickButton();

        basicKesmarki.getAlerts();
        basicKesmarki.setAlertList();
    }
}