package maif.taskmanagerplus.appium

import org.openqa.selenium.By

object Constants {
    // Element IDs
    const val TEXT_HOME_ID = "text_home"

    const val SEARCH_TASK_ID = "et_search_task"
    const val ADD_TASK_BUTTON_ID = "btn_add_task"
    const val EDIT_TASK_BUTTON_ID = "btn_edit_task"
    const val DELETE_TASK_BUTTON_ID = "btn_delete_task"

    const val COMPLETED_CHECKBOX_ID = "cb_completed"
    const val PENDING_CHECKBOX_ID = "cb_pending"
    const val TASK_RECYCLER_VIEW_ID = "rv_task_list"

    // Locators for EditTaskActivity
    const val ADD_TASK_TITLE_ID = "et_task_title"
    const val ADD_TASK_DESCRIPTION_ID = "et_task_description"
    const val ADD_TASK_COMPLETED_CHECK_BOX_ID = "cb_task_completed"

    // Locators for EditTaskActivity
    const val SAVE_TASK_BUTTON_ID = "btn_save_task" // AddTaskActivity and EditTaskActivity
    const val EDIT_TASK_TITLE_ID = "et_edit_task_title"
    const val EDIT_TASK_DESCRIPTION_ID = "et_edit_task_description"
    const val EDIT_TASK_PENDING_CHECK_BOX_ID = "cb_edit_task_completed"

    // Task Details IDs (Assumed)
    const val TASK_DETAIL_TITLE_ID = "tv_task_title"
    const val TASK_DETAIL_DESCRIPTION_ID = "tv_task_description"
    const val TASK_DETAIL_STATUS_ID = "tv_task_status"

    // Navigation Menu IDs
    const val NAVIGATION_MENU_BUTTON_CONTENT_DESC = "Open navigation drawer"
    const val TASK_MANAGER_MENU_ITEM_TEXT = "Task Manager"
    const val TASK_MENU_ID = "nav_task"
}
