package maif.taskmanagerplus.appium.pages

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import maif.taskmanagerplus.appium.Constants
import mu.two.KotlinLogging

private val logger = KotlinLogging.logger {}

class HomePage(private val driver: AppiumDriver) {

    // Locator for the TextView with ID "text_home"
    private val textHome = By.id(Constants.TEXT_HOME_ID)

    /**
     * Retrieves the text from the home TextView.
     *
     * @return The text displayed in the home TextView.
     */
    fun getHomeText(): String {
        return try {
            val text = driver.findElement(textHome).text
            logger.debug { "Retrieved home text: $text" }
            text
        } catch (e: Exception) {
            logger.error(e) { "Error retrieving home text: ${e.message}" }
            throw e
        }
    }
}
