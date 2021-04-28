import org.junit.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainAssingment {
    public WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @Before
    public void setup() {
        driver = new ChromeDriver();
        WebDriverManager.chromedriver().config().setProperties("webdrivermanager.properties");
        driver.manage().window().maximize();
    }

    @Test
    /**
     * Login Account
     */
    public void loginAccount() {
        MainPage mainPage = new MainPage(this.driver);
        String my_profile = mainPage.Login();
        Assert.assertEquals("yossra_test | Dribbble", my_profile);

    }

    @Test
    /**
     * Logout
     */
    public void logoutAccount() {
        MainPage mainPage = new MainPage(this.driver);
        String loggedInPage = mainPage.Logout();
        Assert.assertEquals("Dribbble - Discover the World’s Top Designers & Creative Professionals", loggedInPage);

    }

    @Test
    /**
     * Editing Profile
     */
    public void editForm() {
        MainPage mainPage = new MainPage(this.driver);
        SearchResultPage searchResultPage = mainPage.sending();
        String bodyText = searchResultPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Profile updated successfully"));
    }

    @Test
    /**
     * Uploading Picture
     */
    public void UploadPic() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.Login();
        SearchResultPage searchResultPage = mainPage.uploadPic();
        String bodyText = searchResultPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Upload in progress. Refresh in a few moments to see your new avatar."));

    }

    @Test
    /**
     * Updating Notification
     */
    public void UpdateNotificaton() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.Login();
        SearchResultPage searchResultPage = mainPage.UpdateNotification();
        String bodyText = searchResultPage.getBodyText();
        Assert.assertTrue(bodyText.contains("Your notification preferences have been updated."));

    }

    @Test
    /**
     * Static Search
     */
    public void Search() {
        String[] searchQueries = { "mobile app", "ios mobile" };
        for (String searchQuery : searchQueries) {
            MainPage mainPage = new MainPage(this.driver);
            SearchResultPage searchResultPage = mainPage.search(searchQuery);
            String bodyText = searchResultPage.getBodyText();
            System.out.println(bodyText);
            Assert.assertTrue(bodyText.contains(searchQuery));
        }
    }

    @Test
    /**
     * Going backto Prevous Page
     */
    public void backBrowser() {
        MainPage mainPage = new MainPage(this.driver);
        String backPage = mainPage.back();
        Assert.assertEquals("Dribbble - Discover the World’s Top Designers & Creative Professionals", backPage);

    }

    @Test
    /**
     * Reading The Page Title
     */
    public void titlePage() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.getTitle();
    }

    @Test
    /**
     * Checking if the page opened Correctly
     */
    public void pageOpened() {

        String expectedUrl = "https://dribbble.com/";
        MainPage mainPage = new MainPage(this.driver);
        mainPage.openPage(expectedUrl);
    }

    @Test
    public void fillingcheckButtonandDropDown() {
        MainPage mainPage = new MainPage(this.driver);
        mainPage.filInput();

    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
