import org.junit.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.By;

class MainPage extends PageBase {

    public MainPage(WebDriver driver) {
        super(driver);
        this.driver.get("https://dribbble.com/");
    }

    public String back() {
        this.waitAndReturnElement(By.linkText("Sign in")).click();
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        js.executeScript("window.history.go(-1)");
        return this.driver.getTitle();

    }

    public String Login() {
        FileReader fr = new FileReader();
        this.waitAndReturnElement(By.linkText("Sign in")).click();
        this.waitAndReturnElement(By.id("login")).sendKeys(fr.gProperties().getProperty("email"));
        this.waitAndReturnElement(By.id("password")).sendKeys(fr.gProperties().getProperty("password") + "\n");
        this.waitAndReturnElement(By.xpath("//a[@href='/zghal']")).click();
        return this.driver.getTitle();
    }

    public String Logout() {
        Login();
        WebElement menu = this.waitAndReturnElement(By.xpath("//a[@href='/zghal']"));
        Actions action = new Actions(this.driver);
        action.moveToElement(menu).perform();
        this.waitAndReturnElement(By.linkText("Sign Out")).click();
        return this.driver.getTitle();

    }

    public SearchResultPage uploadPic() {
        this.waitAndReturnElement(By.linkText("Edit Profile")).click();
        WebElement browse = this.waitAndReturnElement(By.name("user[avatar]"));
        browse.sendKeys(System.getProperty("user.dir") + "\\DSC_9916.jpg");
        this.waitAndReturnElement(By.xpath("//input[@type='submit' and @value='Upload Now']")).submit();
        return new SearchResultPage(this.driver);
    }

    public SearchResultPage UpdateNotification() {
        this.waitAndReturnElement(By.linkText("Edit Profile")).click();
        // uploading
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        this.waitAndReturnElement(By.xpath("//a[@href='/account/notifications']")).click();
        this.waitAndReturnElement(By.id("notification_communication")).click();
        this.waitAndReturnElement(By.id("notification_meetup_nearby")).click();
        js.executeScript("window.scrollBy(0,650)", "");
        this.waitAndReturnElement(By.xpath("//input[@type='submit' and @value='Update Email Notifications']")).submit();

        return new SearchResultPage(this.driver);

    }

    public SearchResultPage sending() {
        Login();
        this.waitAndReturnElement(By.linkText("Edit Profile")).click();
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        WebElement element = this.waitAndReturnElement(By.id("profile_bio"));
        element.sendKeys(Keys.TAB);
        element.clear();
        element.sendKeys("hello from budaffside");
        js.executeScript("document.getElementById('profile_url').setAttribute('value', 'https://www.google.cdom')");
        js.executeScript("window.scrollBy(0,250)", "");
        this.waitAndReturnElement(By.xpath("//input[@type='submit' and @value='Save Profile']")).submit();

        return new SearchResultPage(this.driver);
    }

    public void getTitle() {
        String actualTitle = this.driver.getTitle();
        String newS = "Dribbble - Discover the World’s Top Designers & Creative Professionals";
        try {
            Assert.assertEquals(newS, actualTitle);
            System.out.println("same Title");
        } catch (AssertionError e) {
            System.out.println("Not the same page" + e.getMessage());
        }

    }

    public void openPage(String exceptedWebSite) {

        try {
            Assert.assertEquals(exceptedWebSite, this.driver.getCurrentUrl());
            System.out.println("web page opened");
        } catch (Throwable pageNavigationError) {
            System.out.println("web page not opene");
        }
    }

    // dropdown
    public void filInput() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "");
        this.waitAndReturnElement(By.xpath("//div[contains(@class, 'filter-views js-shot-views')]/span/a")).click();
        WebElement dropdown = this.waitAndReturnElement(By.xpath(
                "//div[contains(@class, 'filter-views js-shot-views')]/span/div[contains(@class, 'btn-dropdown-options')]/ul"));
        dropdown.findElement(By.id("recent-btn")).click();

    }

    public SearchResultPage search(String searchQuery) {
        this.waitAndReturnElement(By.xpath("//a[@href='/search']")).click();
        this.waitAndReturnElement(By.id("search")).sendKeys(searchQuery + "\n");
        return new SearchResultPage(this.driver);
    }
}
