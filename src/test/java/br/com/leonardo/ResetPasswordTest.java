import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

public class ResetPasswordTest {

    @Test
    public void testInvalidEmail() {
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        try {
            driver.get("http://localhost:3000/reset-password");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            
            WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div/main/form/div[1]/div/div/div/input")));
            emailField.sendKeys("teste@teste.c");

            WebElement button = driver.findElement(By.cssSelector("button"));
            button.click();

            WebElement errorMessageElement;
            try {
                errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-slot='error-message']")));
                String errorMessage = errorMessageElement.getText();

                assertEquals("E-mail inválido", errorMessage);
            } catch (org.openqa.selenium.TimeoutException e) {
                throw new AssertionError("A mensagem de erro esperada ('E-mail inválido') não foi encontrada no tempo esperado.");
            }

        } finally {
            driver.quit();
        }
    }
}
