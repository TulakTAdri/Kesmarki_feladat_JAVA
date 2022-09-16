import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestKesmarki extends BaseTest {

    @Test
    public void TestAlerts() throws IOException {
        BasicKesmarki basicKesmarki = new BasicKesmarki(driver);
        basicKesmarki.navigate();

        basicKesmarki.ReadFromFile();
    }
}

