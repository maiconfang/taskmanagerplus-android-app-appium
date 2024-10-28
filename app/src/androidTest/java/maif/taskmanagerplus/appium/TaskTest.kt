package maif.taskmanagerplus.appium.tests

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.options.UiAutomator2Options
import maif.taskmanagerplus.appium.pages.TaskPage
import maif.taskmanagerplus.appium.Constants
import mu.two.KotlinLogging
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.openqa.selenium.By
import java.io.InputStream
import java.net.MalformedURLException
import java.net.URL
import java.util.Properties

private val logger = KotlinLogging.logger {}

/**
 * Test class for the Task screen.
 *
 * This class sets up the AppiumDriver, loads configuration properties,
 * initializes the TaskPage Page Object, and contains test methods
 * to interact with the Task screen.
 */
class TaskTest {

    private lateinit var driver: AppiumDriver
    private lateinit var config: Properties
    private lateinit var taskPage: TaskPage
    private lateinit var context: Context

    /**
     * Sets up the testing environment before each test.
     *
     * Initializes the application context, loads configuration properties,
     * configures and initializes the AppiumDriver, and initializes the TaskPage Page Object.
     *
     * @throws MalformedURLException if the Appium server URL is malformed.
     */
    @Before
    @Throws(MalformedURLException::class)
    fun setUp() {
        try {
            // Initialize the application context
            context = InstrumentationRegistry.getInstrumentation().context
            logger.info { "Application context obtained." }

            // Load properties from the config.properties file
            loadConfigProperties()

            // Configure and initialize the AppiumDriver
            initializeDriver()

            // Initialize Page Objects
            taskPage = TaskPage(driver)
            logger.info { "TaskPage object initialized." }

        } catch (e: Exception) {
            logger.error(e) { "Setup failed: ${e.message}" }
            throw e // Rethrow exception to fail the setup if something goes wrong
        }
    }

    /**
     * Loads configuration properties from the config.properties file located in assets.
     */
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

    /**
     * Configures and initializes the AppiumDriver using properties from config.properties.
     */
    private fun initializeDriver() {
        try {
            val options = UiAutomator2Options().apply {
                setPlatformName(config.getProperty("platform.name"))
                setDeviceName(config.getProperty("device.name"))
                setApp(config.getProperty("app.path"))
                setAutomationName(config.getProperty("automation.name"))
                setNoReset(config.getProperty("no.reset").toBoolean())
                setFullReset(config.getProperty("full.reset").toBoolean())
            }

            driver = AndroidDriver(URL(config.getProperty("appium.server.url")), options)
            logger.info { "AndroidDriver initialized successfully." }

        } catch (e: Exception) {
            logger.error(e) { "Error initializing AndroidDriver: ${e.message}" }
            throw e // Rethrow exception to fail the setup if the driver fails to initialize
        }
    }

    /**
     * Test method to navigate to the Task Manager screen and verify its display.
     */
    @Test
    fun testNavigateToTaskManager() {
        try {
            // Navigate to Task Manager
            taskPage.navigateToTaskManager()
            logger.info { "Navigation to Task Manager successful." }

            // Example assertion to verify that the TaskPage is displayed
            // Assuming TaskPage has a method to verify its presence, e.g., isTaskListDisplayed()
            // For now, we'll assert that the taskRecyclerView is displayed
            val isTaskListDisplayed = driver.findElement(By.id(Constants.TASK_RECYCLER_VIEW_ID)).isDisplayed
            assertTrue("Task Manager screen should be displayed.", isTaskListDisplayed)
            logger.info { "Test Passed: Task Manager screen is displayed." }

        } catch (e: Exception) {
            logger.error(e) { "Test Failed: ${e.message}" }
            throw e // Rethrow to mark the test as failed
        }
    }

    /**
     * Test method to add a new task and verify its presence in the task list.
     */
    @Test
    fun testAddNewTask() {
        try {
            // Navigate to Task Manager
            taskPage.navigateToTaskManager()

            // Define new task details
            val taskTitle = "Automated Task"
            val taskDescription = "This task was added by an automated test."
            val isCompleted = false

            // Add new task
            taskPage.addNewTask(taskTitle, taskDescription, isCompleted)
            logger.info { "Added new task with title: '$taskTitle'." }

            // Verify that the new task appears in the task list
            val taskList = taskPage.getTaskList()
            val isTaskAdded = taskList.any { it.contains(taskTitle) }
            assertTrue("The new task should be present in the task list.", isTaskAdded)
            logger.info { "Test Passed: New task '$taskTitle' was added successfully." }



        } catch (e: Exception) {
            logger.error(e) { "Test Failed: ${e.message}" }
            throw e // Rethrow to mark the test as failed
        }
    }

    /**
     * Tears down the testing environment after each test.
     *
     * Quits the AppiumDriver to close the application and end the session.
     */
    @After
    fun tearDown() {
        if (::driver.isInitialized) {
            driver.quit()
            logger.info { "Driver quit successfully." }
        }
    }
}
