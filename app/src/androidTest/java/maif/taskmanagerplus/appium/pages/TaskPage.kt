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
    private val editTaskButton: By = By.id(Constants.EDIT_TASK_BUTTON_ID)
    private val deleteTaskButton: By = By.id(Constants.DELETE_TASK_BUTTON_ID)
    private val completedCheckBox: By = By.id(Constants.COMPLETED_CHECKBOX_ID)
    private val pendingCheckBox: By = By.id(Constants.PENDING_CHECKBOX_ID)
    private val taskRecyclerView: By = By.id(Constants.TASK_RECYCLER_VIEW_ID)
    private val navigationMenuButton: By = By.xpath("//android.widget.ImageButton[@content-desc='Open navigation drawer']")
//    private val taskManagerMenuItem: By = By.xpath("//android.widget.TextView[@text='Task Manager']")

    // Locators for AddTaskActivity
    private val taskTitleAddText: By = By.id(Constants.ADD_TASK_TITLE_ID)
    private val taskDescriptionAddText: By = By.id(Constants.ADD_TASK_DESCRIPTION_ID)

    // Locators for EditTaskActivity
    private val saveTaskButton: By = By.id(Constants.SAVE_TASK_BUTTON_ID) // AddTaskActivity and EditTaskActivity
    private val taskTitleEditText: By = By.id(Constants.EDIT_TASK_TITLE_ID)
    private val taskDescriptionEditText: By = By.id(Constants.EDIT_TASK_DESCRIPTION_ID)
    private val pendingCheckEditBox: By = By.id(Constants.EDIT_TASK_PENDING_CHECK_BOX_ID)

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
     * Clicks the button to edit a edit task.
     */
    fun clickEditTaskButton() {
        try {
            val editButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(editTaskButton))
            editButton.click()
            logger.info { "Clicked on Edit Task button." }
        } catch (e: Exception) {
            logger.error(e) { "Failed to click Edit Task button: ${e.message}" }
            throw e
        }
    }

    /**
     * Clicks the button to delete a delete task.
     */
    fun clickDeleteTaskButton() {
        try {
            val deleteButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(deleteTaskButton))
            deleteButton.click()
            logger.info { "Clicked on Delete Task button." }
        } catch (e: Exception) {
            logger.error(e) { "Failed to click Delete Task button: ${e.message}" }
            throw e
        }
    }

    /**
     * Clicks the button to add a new task.
     */
    fun clickCheckBoxPending() {
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
     * Retrieves the list of task titles displayed in the RecyclerView.
     *
     * @return A list of task titles as Strings.
     */
    fun getTaskList(): List<String> {
        return try {
            val recyclerView = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(taskRecyclerView))

            // Locate all TextViews with the task titles within the RecyclerView
            val taskTitleElements = recyclerView.findElements(By.id("tv_task_title"))
                .filterIsInstance<WebElement>()
                .map { it.text }
                .also {
                    logger.info { "Retrieved ${it.size} tasks from the list." }
                }

            taskTitleElements
        } catch (e: Exception) {
            logger.error(e) { "Failed to retrieve task list: ${e.message}" }
            throw e
        }
    }

    /**
     * Clicks the "DELETE" button in the deletion confirmation dialog.
     */
    fun clickDeleteConfirmationButton() {
        try {
            // Locate the "DELETE" button by its text and click it
            val deleteButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//android.widget.Button[@text='DELETE']")))
            deleteButton.click()
            logger.info { "Clicked on 'DELETE' button in confirmation dialog." }
        } catch (e: Exception) {
            logger.error(e) { "Failed to click 'DELETE' button: ${e.message}" }
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
     * Edits the details of an existing task.
     *
     * @param newTitle The new title for the task.
     * @param newDescription The new description for the task.
     * @param isCompleted The new completion status for the task.
     */
    fun editTask(newTitle: String, newDescription: String, isCompleted: Boolean) {
        try {
            // Locate and edit the task title
            val titleField = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(taskTitleEditText))
            titleField.clear()
            titleField.sendKeys(newTitle)
            logger.info { "Updated task title to: '$newTitle'" }

            // Locate and edit the task description
            val descriptionField = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(taskDescriptionEditText))
            descriptionField.clear()
            descriptionField.sendKeys(newDescription)
            logger.info { "Updated task description to: '$newDescription'" }

            // Set the completed checkbox
            val completedCb = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(pendingCheckEditBox))
            if (completedCb.isSelected != isCompleted) {
                completedCb.click()
                logger.info { "Set task as completed: $isCompleted" }
            } else {
                logger.info { "Task completion status already set to: $isCompleted" }
            }

            // Click Save Task button
            val saveButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(saveTaskButton))
            saveButton.click()
            logger.info { "Clicked on Save Task button after editing." }

            // Optionally, wait for the task list to refresh
            WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(taskRecyclerView))
            logger.info { "Task list refreshed after editing." }

        } catch (e: Exception) {
            logger.error(e) { "Failed to edit task details: ${e.message}" }
            throw e
        }
    }

    /**
     * Deletes a specific task by clicking the delete button and confirming the deletion.
     *
     * @param taskTitle The title of the task to delete.
     */
    fun deleteTask(taskTitle: String) {
        try {

            // Click the "DELETE" button in the confirmation dialog using its resource-id
            val confirmDeleteButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")))
            confirmDeleteButton.click()
            logger.info { "Clicked on 'DELETE' button in confirmation dialog." }

            // Optionally, verify that the task has been deleted
            WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//android.widget.TextView[@text='$taskTitle']")))
            logger.info { "Verified that task '$taskTitle' has been deleted from the list." }
        } catch (e: Exception) {
            logger.error(e) { "Failed to delete task '$taskTitle': ${e.message}" }
            throw e
        }
    }


    /**
     * Deletes a specific task by clicking the delete button and confirming the deletion.
     *
     * @param taskTitle The title of the task to delete.
     */
    fun deleteTaskWithConfirmation(taskTitle: String) {
        try {
            // Click on the delete button for the specified task
            deleteTask(taskTitle)
        } catch (e: Exception) {
            logger.error(e) { "Failed to delete task '$taskTitle' with confirmation: ${e.message}" }
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

    /**
     * Adds a new task with the provided title and description.
     *
     * @param title The title of the new task.
     * @param description The description of the new task.
     * @param isCompleted Boolean indicating if the task is completed.
     */
    fun addNewTask(title: String, description: String, isCompleted: Boolean = false) {
        try {
            // Click on Add Task button
            clickAddTaskButton()

            // Enter task title
            val titleField = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(taskTitleAddText))
            titleField.clear()
            titleField.sendKeys(title)
            logger.info { "Entered task title: '$title'" }

            // Enter task description
            val descriptionField = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(taskDescriptionAddText))
            descriptionField.clear()
            descriptionField.sendKeys(description)
            logger.info { "Entered task description: '$description'" }

            // Set completed checkbox if needed
            if (isCompleted) {
                val completedCb = WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(completedCheckBox))
                if (!completedCb.isSelected) {
                    completedCb.click()
                    logger.info { "Set task as completed." }
                }
            }

            // Click Save Task button
            val saveButton = WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(saveTaskButton))
            saveButton.click()
            logger.info { "Clicked on Save Task button." }

            // Optionally, wait for the task list to refresh
            WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(taskRecyclerView))
            logger.info { "Task list refreshed after saving." }

        } catch (e: Exception) {
            logger.error(e) { "Failed to add new task: ${e.message}" }
            throw e
        }
    }

}
