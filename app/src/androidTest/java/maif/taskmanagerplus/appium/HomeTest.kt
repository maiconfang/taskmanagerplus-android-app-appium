package maif.taskmanagerplus.appium

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import maif.taskmanagerplus.appium.pages.HomePage
import mu.two.KotlinLogging
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.util.Properties

private val logger = KotlinLogging.logger {}

class HomeTest {

    private lateinit var driver: AppiumDriver
    private lateinit var config: Properties
    private lateinit var homePage: HomePage
    private lateinit var context: Context

    @Before
    @Throws(MalformedURLException::class)
    fun setUp() {
        try {
            // Initialize the application context
            context  = InstrumentationRegistry.getInstrumentation().context
            logger.info { "Application context obtained." }

            // Load properties from the config.properties file
            loadConfigProperties()

            // Configure and initialize the AppiumDriver
            initializeDriver()

            // Initialize Page Objects
            homePage = HomePage(driver)
            logger.info { "HomePage object initialized." }

        } catch (e: Exception) {
            logger.error(e) { "Setup failed: ${e.message}" }
            throw e // Rethrow exception to fail the setup if something goes wrong
        }
    }

    private fun loadConfigProperties() {
        config = Properties()
        try {

            val assetFiles = context.assets.list("")
            logger.info { "Assets found: ${assetFiles?.joinToString()}" }

            val inputStream: InputStream = context.assets.open("config.properties")
            config.load(inputStream)
            logger.info { "Configuration properties loaded successfully." }
            inputStream.close() // Close the inputStream after loading the properties
        } catch (e: Exception) {
            logger.error(e) { "Error loading configuration properties: ${e.message}" }
            throw e // Rethrow exception for proper error handling
        }
    }

    private fun initializeDriver() {
        try {
            val options = UiAutomator2Options().apply {
                setPlatformName(config.getProperty("platform.name"))
                setDeviceName(config.getProperty("device.name"))
                setApp(config.getProperty("app.path"))
                setAutomationName(config.getProperty("automation.name"))
                setNoReset(config.getProperty("no.reset").toBoolean())
            }

            driver = AndroidDriver(URL(config.getProperty("appium.server.url")), options)
            logger.info { "AndroidDriver initialized successfully." }

        } catch (e: Exception) {
            logger.error(e) { "Error initializing AndroidDriver: ${e.message}" }
            throw e // Rethrow exception to fail the setup if the driver fails to initialize
        }
    }

    @Test
    fun testHomeTextView() {
        try {
            val actualText = homePage.getHomeText()
            logger.debug { "Actual text retrieved: $actualText" }

            val expectedText = config.getProperty("home.text.expected")
            logger.debug { "Expected text: $expectedText" }

            assertEquals("The TextView value is incorrect!", expectedText, actualText)
            logger.info { "Test Passed: Home TextView contains the expected text." }

        } catch (e: Exception) {
            logger.error(e) { "Test Failed: ${e.message}" }
            throw e // Rethrow to mark the test as failed
        }
    }

    @After
    fun tearDown() {
        if (::driver.isInitialized) {
            driver.quit()
            logger.info { "Driver quit successfully." }
        }
    }
}