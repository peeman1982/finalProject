import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class smokeTestsSkillfactory {

    private WebDriver driver;

    @Before
    public void setUp() {
        try {
            driver = new ChromeDriver();
        } catch (Exception e) {
            System.out.println("Error initializing WebDriver: " + e.getMessage());
        }
        driver.manage().window().maximize();
        driver.get("https://www.skillfactory.ru/");
    }

    @After
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Error closing WebDriver: " + e.getMessage());
        }
    }

    @Test
    public void mainTest() {
        String actualUrl = driver.getCurrentUrl();
        assertEquals("https://skillfactory.ru/", actualUrl);
    }

    @Test
    public void logoCheck() {
        try {
            WebElement logo = driver.findElement(By.xpath("//img[@alt='SkillFactory - научитесь работать в IT']"));
            if (logo.isDisplayed()) {
                System.out.println("Logo Presence Test Passed: Logo is present on the page.");
            } else {
                System.out.println("Logo Presence Test Failed: Logo is not present on the page.");
            }
        } catch (Exception e) {
            System.out.println("Error during test execution: " + e.getMessage());
        }
    }

    @Test
    public void titleCheck() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                    .titleIs("Онлайн-школа Skillfactory — онлайн-обучение востребованным IT-профессиям"));
        } catch (Exception e) {
            System.out.println("Error during test execution: " + e.getMessage());
        }
    }

    @Test
    public void allCoursesListPresent() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//a[text()='онлайн-курсы']"))).perform();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By
                .xpath("//ul[@class='t978__menu' and @style='width: 250px;']//a")));
    }

    @Test
    public void allCoursesPageCheck() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//a[text()='онлайн-курсы']"))).perform();
        driver.findElement(By.xpath("//span[text()='Все онлайн-курсы']")).click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        assertEquals("https://skillfactory.ru/courses", actualUrl);
    }

    @Test
    public void itRentgenBlockPresented() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//section[@class='rentgen']")));
    }

    @Test
    public void itRentgenButtonClickable() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//a[@class='rentgen__button']")));
    }

    @Test
    public void itRentgenPageCheck() {
        driver.findElement(By.xpath("//a[@class='rentgen__button']")).click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        assertEquals("https://free.skillfactory.ru/itrentgen", actualUrl);
    }

    @Test
    public void careerCenterBlockPresented() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//section[@class='carrier']")));
    }

    @Test
    public void refundMoneyPageCheck() {
        driver.findElement(By.xpath("//a[contains(text(),'оферте')]")).click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        assertEquals("https://skillfactory.ru/refund-money", actualUrl);
    }

    @Test
    public void realStoriesBlockPresented() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//section[@class='reviews']")));
    }

    @Test
    public void nurullovReviewPageCheck() {
        driver.findElement(By.xpath("//a[@target='_blank']//span[contains(text(),'Читать историю')]"))
                .click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualUrl).contains("aleksandr-nurullov");
    }

    @Test
    public void mentorsBlockPresented() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//section[@class='mentors']")));
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .elementToBeClickable(By.xpath("//div[@id='mentors-next-btn']")));
    }

    @Test
    public void mediaBlockPresented() {
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//section[@class='media']")));
    }

    @Test
    public void mediaPageCheck() {
        driver.findElement(By.xpath("//span[text()='перейти в Медиа']")).click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualUrl).contains("https://blog.skillfactory.ru/");
    }

    @Test
    public void contactFormCheck() {
        driver.findElement(By.xpath("//button[contains(text(),'Отправить')]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath("//div[@class='t-form__errorbox-text t-text t-text_xs']")));
    }

    @Test
    public void contactFormNameCheck() {
        driver.findElement(By.xpath("//input[@name='name']")).sendKeys("123");
        driver.findElement(By.xpath("//button[contains(text(),'Отправить')]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath("//p[contains(text(),'Please put a name')]")));
    }

    @Test
    public void contactFormEmailCheck() {
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("abc");
        driver.findElement(By.xpath("//button[contains(text(),'Отправить')]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath("//p[contains(text(), 'Please enter a valid email address')]")));
    }

    @Test
    public void contactFormPhoneCheck() {
        driver.findElement(By.xpath("//input[@id='input_1676828667932']")).sendKeys("123");
        driver.findElement(By.xpath("//button[contains(text(),'Отправить')]")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By
                        .xpath("//p[contains(text(), 'Value is too short')]")));
    }
    @Test
    public void testingPageCheck() {
        driver.findElement(By
                        .xpath("//a[@class='directions__list-link']//span[contains(text(),'Тестирование')]"))
                .click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualUrl).isEqualTo("https://skillfactory.ru/courses/testirovanie");
    }
    @Test
    public void testingPostmanFlowPageCheck() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//a[text()='онлайн-курсы']")))
                        .perform();
        driver.findElement(By
                        .xpath("//span[@class='t978__link-inner t978__link-inner_left'][contains(text(),'Тестирование')]"))
                .click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualUrl).isEqualTo("https://skillfactory.ru/courses/testirovanie");
        driver.findElement(By
                .xpath("//a[text()='Postman']")).click();;
        String actualNextUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualNextUrl).contains("https://skillfactory.ru/courses/postman");
    }
    @Test
    public void testingSeleniumFlowPageCheck() {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(By.xpath("//a[text()='онлайн-курсы']")))
                .perform();
        driver.findElement(By
                        .xpath("//span[@class='t978__link-inner t978__link-inner_left'][contains(text(),'Тестирование')]"))
                .click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualUrl).isEqualTo("https://skillfactory.ru/courses/testirovanie");
        driver.findElement(By
                .xpath("//a[text()='Selenium WebDriver']")).click();;
        String actualNextUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualNextUrl).contains("https://skillfactory.ru/courses/selenium-webdriver");
    }
    @Test
    public void newSkillFactoryFlowPageCheck() {
        driver.findElement(By
                .xpath("//a[@class='higher-education__link']")).click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualUrl)
                .isEqualTo("https://new.skillfactory.ru/vyssheye-obrazovaniye?utm_source=skillfactory");
        driver.findElement(By
                .xpath("//a[@class='tn-atom'][contains(text(),'Узнать подробнее')]")).click();
        ;
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//button[contains(text(),'ОСТАВИТЬ ЗАЯВКУ')]")));
    }
    @Test
    public void sechenovDpoFlowPageCheck() {
        driver.findElement(By
                .xpath("//a[@class='higher-education__link']")).click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualUrl)
                .isEqualTo("https://new.skillfactory.ru/vyssheye-obrazovaniye?utm_source=skillfactory");
        driver.findElement(By
                .xpath("//a[contains(text(),'Выбрать программу')]")).click();
        driver.findElement(By
                .xpath("//div[text()='ДПО']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//a[contains(text(),'Сеченовский')]")));
    }
    @Test
    public void freeCoursesPageCheck() {
        driver.findElement(By
                .xpath("//a[contains(text(),'БЕСПЛАТНО')]")).click();
        String[] windowHandles = driver.getWindowHandles().toArray(new String[]{});
        driver.switchTo().window(windowHandles[1]);
        String actualUrl = driver.getCurrentUrl();
        Assertions.assertThat(actualUrl)
                .isEqualTo("https://skillfactory.ru/free-events");
    }
}



