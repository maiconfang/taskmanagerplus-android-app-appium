package maif.taskmanagerplus.appium.pages

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import maif.taskmanagerplus.appium.Constants
import mu.two.KotlinLogging
import java.time.Duration

import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import org.openqa.selenium.WebElement

private val logger = KotlinLogging.logger {}

/**
 * Page Object representing the Task screen.
 *
 * This class provides methods to interact with the Task screen, including searching,
 * adding, filtering, and managing tasks.
 *
 * @param driver The AppiumDriver instance used to interact with the mobile application.
 */
class TaskPage(private val driver: AppiumDriver) {

    // Locators for UI elements
    private val searchEditText: By = By.id(Constants.SEARCH_TASK_ID)
    private val addTaskButton: By = By.id(Constants.ADD_TASK_BUTTON_ID)
    private val completedCheckBox: By = By.id(Constants.COMPLETED_CHECKBOX_ID)
    private val pendingCheckBox: By = By.id(Constants.PENDING_CHECKBOX_ID)
    private val taskRecyclerView: By = By.id(Constants.TASK_RECYCLER_VIEW_ID)
    private val navigationMenuButton: By = By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")
//    private val taskManagerMenuItem: By = By.xpath("//android.widget.TextView[@text='Task Manager']")

    private val taskManagerMenuItem: By = By.id(Constants.TASK_MENU_ID)


    /**
     * Navigates to the Task Manager screen by opening the navigation drawer and selecting the Task Manager menu item.
     */
    fun navigateToTaskManager() {
        try {
            // Open the navigation drawer
            val menuButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(navigationMenuButton))
            menuButton.click()
            logger.info { "Clicked on navigation menu button." }

            // Click on "Task Manager" menu item
            val taskManagerItem = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(taskManagerMenuItem))
            taskManagerItem.click()
            logger.info { "Clicked on 'Task Manager' menu item." }

            // Verify if the Task Manager screen is displayed
            WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(taskRecyclerView))
            logger.info { "Task Manager screen is displayed." }

        } catch (e: Exception) {
            logger.error(e) { "Failed to navigate to Task Manager: ${e.message}" }
            throw e
        }
    }

    /**
     * Enters text into the search field to filter tasks by title.
     *
     * @param text The text to enter into the search field.
     */
    fun enterSearchText(text: String) {
        try {
            val searchField = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(searchEditText))
            searchField.clear()
            searchField.sendKeys(text)
            logger.info { "Entered search text: '$text'" }
        } catch (e: Exception) {
            logger.error(e) { "Failed to enter search text: ${e.message}" }
            throw e
        }
    }

    /**
     * Clicks the button to add a new task.
     */
    fun clickAddTaskButton() {
        try {
            val addButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(addTaskButton))
            addButton.click()
            logger.info { "Clicked on Add Task button." }
        } catch (e: Exception) {
            logger.error(e) { "Failed to click Add Task button: ${e.message}" }
            throw e
        }
    }

    /**
     * Sets the completed filter checkbox.
     *
     * @param isChecked True to check the checkbox, false to uncheck.
     */
    fun setCompletedFilter(isChecked: Boolean) {
        try {
            val completedCb = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(completedCheckBox))
            if (completedCb.isSelected != isChecked) {
                completedCb.click()
                logger.info { "Set Completed filter to: $isChecked" }
            } else {
                logger.info { "Completed filter already set to: $isChecked" }
            }
        } catch (e: Exception) {
            logger.error(e) { "Failed to set Completed filter: ${e.message}" }
            throw e
        }
    }

    /**
     * Sets the pending filter checkbox.
     *
     * @param isChecked True to check the checkbox, false to uncheck.
     */
    fun setPendingFilter(isChecked: Boolean) {
        try {
            val pendingCb = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(pendingCheckBox))
            if (pendingCb.isSelected != isChecked) {
                pendingCb.click()
                logger.info { "Set Pending filter to: $isChecked" }
            } else {
                logger.info { "Pending filter already set to: $isChecked" }
            }
        } catch (e: Exception) {
            logger.error(e) { "Failed to set Pending filter: ${e.message}" }
            throw e
        }
    }

    /**
     * Retrieves the list of tasks displayed in the RecyclerView.
     *
     * @return A list of MobileElement representing each task item.
     */
    fun getTaskList(): List<WebElement> {
        return try {
            val recyclerView = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(
                    By.className("androidx.recyclerview.widget.RecyclerView")
                ))

            recyclerView.findElements(By.className("androidx.recyclerview.widget.RecyclerView"))
                .filterIsInstance<WebElement>()
                .also {
                    logger.info { "Retrieved ${it.size} tasks from the list." }
                }
        } catch (e: Exception) {
            logger.error(e) { "Failed to retrieve task list: ${e.message}" }
            throw e
        }
    }

    /**
     * Clicks on a task item to view its details.
     *
     * @param taskTitle The title of the task to click.
     */
    fun clickOnTask(taskTitle: String) {
        try {
            val taskElement = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='$taskTitle']")))
            taskElement.click()
            logger.info { "Clicked on task with title: '$taskTitle'" }
        } catch (e: Exception) {
            logger.error(e) { "Failed to click on task '$taskTitle': ${e.message}" }
            throw e
        }
    }

    /**
     * Edits a specific task by clicking the edit button.
     *
     * @param taskTitle The title of the task to edit.
     */
    fun editTask(taskTitle: String) {
        try {
            // Assuming each task item has an edit button with a specific ID or description
            val editButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='$taskTitle']/../android.widget.ImageButton[@content-desc='Edit Task']")))
            editButton.click()
            logger.info { "Clicked on Edit button for task: '$taskTitle'" }
        } catch (e: Exception) {
            logger.error(e) { "Failed to edit task '$taskTitle': ${e.message}" }
            throw e
        }
    }

    /**
     * Deletes a specific task by clicking the delete button.
     *
     * @param taskTitle The title of the task to delete.
     */
    fun deleteTask(taskTitle: String) {
        try {
            // Assuming each task item has a delete button with a specific ID or description
            val deleteButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.TextView[@text='$taskTitle']/../android.widget.ImageButton[@content-desc='Delete Task']")))
            deleteButton.click()
            logger.info { "Clicked on Delete button for task: '$taskTitle'" }

            // Optionally handle confirmation dialog
            val confirmButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")))
            confirmButton.click()
            logger.info { "Confirmed deletion of task: '$taskTitle'" }
        } catch (e: Exception) {
            logger.error(e) { "Failed to delete task '$taskTitle': ${e.message}" }
            throw e
        }
    }

    /**
     * Retrieves detailed information of a specific task.
     *
     * @param taskTitle The title of the task to retrieve details for.
     * @return A map containing task details such as title, description, status, etc.
     */
    fun getTaskDetails(taskTitle: String): Map<String, String> {
        return try {
            clickOnTask(taskTitle)
            // Assuming the details screen displays information in TextViews with specific IDs
            val title = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(Constants.TASK_DETAIL_TITLE_ID))).text
            val description = driver.findElement(By.id(Constants.TASK_DETAIL_DESCRIPTION_ID)).text
            val status = driver.findElement(By.id(Constants.TASK_DETAIL_STATUS_ID)).text

            logger.info { "Retrieved details for task: '$taskTitle'" }

            mapOf(
                "title" to title,
                "description" to description,
                "status" to status
            )
        } catch (e: Exception) {
            logger.error(e) { "Failed to retrieve details for task '$taskTitle': ${e.message}" }
            throw e
        }
    }
}
