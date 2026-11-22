# AutomationExercise Selenium 2 — Java TestNG Framework

End-to-end web test automation for [automationexercise.com](https://automationexercise.com/) built with **Java 21**, **Selenium WebDriver 4**, **TestNG**, and **Maven**. Demonstrates Page Object Model, explicit waits, WebDriverManager, and ExtentReports.

> **Note**: This project was created as a learning exercise. The main QA portfolio now uses Playwright (TypeScript) and Cypress (JavaScript) for modern web automation. This Java/Selenium project is kept for reference and to demonstrate cross-technology competency.

---

## 🧪 Covered Scenarios

| Test | Flow |
|---|---|
| `OrderPlacementTest` | Product selection → Add to cart → Checkout → Login/Register → Payment → Order confirmation |

---

## 🏗️ Project Structure

```
src/
├── main/java/
│   ├── org.example.AutomationExerciseSelenium2/
│   │   └── App.java                 # Entry point (runs TestNG programmatically)
│   ├── pages/                       # Page Object Model
│   │   ├── BasePage.java            # Base page with common utilities
│   │   ├── CartPage.java            # Cart interactions
│   │   ├── CheckoutPage.java        # Checkout flow
│   │   ├── LoginPage.java           # Login & registration
│   │   ├── OrderConfirmationPage.java
│   │   ├── PaymentPage.java         # Payment form
│   │   └── ProductPage.java         # Product details
│   └── utils/
│       ├── DriverFactory.java       # WebDriver setup with WebDriverManager
│       └── BasePage.java            # Common page utilities
├── test/java/
│   ├── listeners/
│   │   └── TestListener.java        # TestNG listener for screenshots on failure
│   └── tests/
│       └── OrderPlacementTest.java  # Main test suite
```

---

## 🎯 Key Technical Decisions

| Area | Choice | Rationale |
|---|---|---|
| **Language** | Java 21 | LTS version, modern syntax (records, pattern matching) |
| **Build** | Maven | Standard for Java, excellent CI integration |
| **Test Framework** | TestNG | Native parallel execution, data providers, flexible suite XML |
| **Driver Management** | WebDriverManager | Auto-downloads matching ChromeDriver, no manual setup |
| **Wait Strategy** | Explicit waits (`WebDriverWait`) | Best practice; avoids flaky implicit waits |
| **Reporting** | ExtentReports 5 | Rich HTML reports with screenshots, logs, metrics |
| **Locators** | `@FindBy` + CSS selectors | PageFactory pattern, maintainable and readable |
| **Parallel Execution** | TestNG suite XML | Configured for cross-browser scaling |

---

## 🚀 Quick Start

### Prerequisites
- **JDK 21** (or later)
- **Maven 3.9+**
- **Google Chrome** (for local runs)

### Run Tests

```bash
# Clone
git clone https://github.com/yazanus94-dot/AutomationExcerciseSelenium2.git
cd AutomationExcerciseSelenium2/AutomationExerciseSelenium2

# Run via Maven (uses testng.xml suite)
mvn test

# Or run directly via Java (bypasses IDE TestNG plugin issues)
mvn exec:java -Dexec.mainClass="org.example.AutomationExerciseSelenium2.App"
```

### IDE Setup (IntelliJ / Eclipse)
1. Import as **Maven Project**
2. Ensure **JDK 21** is configured
3. Install **TestNG plugin** (IntelliJ: Settings → Plugins → TestNG)
4. Run `testng.xml` as TestNG Suite

---

## ⚙️ CI / CD

**GitHub Actions** (`.github/workflows/ci.yml`) runs on every push/PR:

- **JDK 21** (Temurin) with Maven cache
- **Chrome** installed via `browser-actions/setup-chrome`
- Runs `mvn test -B` (non-interactive)
- **Artifacts uploaded**:
  - TestNG XML/HTML reports (`target/surefire-reports/`)
  - ExtentReports HTML (`test-output/ExtentReports/`)
  - Screenshots on failure (via `TestListener`)

---

## 📊 Reporting

| Report | Location | Description |
|---|---|---|
| **TestNG HTML** | `target/surefire-reports/` | Standard TestNG output |
| **TestNG XML** | `target/surefire-reports/` | JUnit-compatible for CI |
| **ExtentReports** | `test-output/ExtentReports/` | Rich dashboard with screenshots, logs, categories |

---

## 🛠️ Extending the Framework

### Add a New Page Object
```java
// src/main/java/pages/NewPage.java
public class NewPage extends BasePage {
    @FindBy(css = "selector")
    private WebElement element;

    public NewPage(WebDriver driver) { super(driver); }
    
    public void doSomething() {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
}
```

### Add a New Test
```java
// src/test/java/tests/NewTest.java
public class NewTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() { driver = DriverFactory.createDriver(); }

    @Test
    public void myTest() {
        // Use page objects...
    }

    @AfterClass
    public void tearDown() { if (driver != null) driver.quit(); }
}
```

### Run Cross-Browser
Update `testng.xml` to add parameters, or create multiple suite files:
```xml
<test name="Chrome Tests">
    <parameter name="browser" value="chrome"/>
    <classes><class name="tests.NewTest"/></classes>
</test>
<test name="Firefox Tests">
    <parameter name="browser" value="firefox"/>
    <classes><class name="tests.NewTest"/></classes>
</test>
```

---

## 📂 Dependencies (from `pom.xml`)

| Dependency | Version | Purpose |
|---|---|---|
| `selenium-java` | 4.15.0 | WebDriver API |
| `testng` | 7.8.0 | Test framework |
| `webdrivermanager` | 5.6.2 | Auto driver management |
| `extentreports` | 5.1.1 | Rich HTML reporting |

---

## 📄 License

MIT License — see [LICENSE](../LICENSE).

---

## 👤 Author

**Yazan Aljabari** — QA Engineer  
GitHub: [@yazanus94-dot](https://github.com/yazanus94-dot)  
Primary QA Portfolio: [QA-Software-Testing-Projects](https://github.com/yazanus94-dot/QA-Software-Testing-Projects) (Playwright, Cypress, Appium, k6, API, AI/LLM testing)
