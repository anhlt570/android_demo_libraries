package demo.com.demolibraries.main

import android.support.annotation.DrawableRes

class MenuItem {
    private var id: Int = 0
    private var title: String? = null
    private var description: String? = null
    private var activityToOpen: Class<*>? = null
    private var iconId: Int = 0

    fun setId(@MenuID id: Int): MenuItem {
        this.id = id
        return this
    }

    fun getTitle(): String? {
        return title
    }

    fun setTitle(title: String): MenuItem {
        this.title = title
        return this
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String?): MenuItem {
        this.description = description ?: ""
        return this
    }

    fun getIconId(): Int {
        return iconId
    }

    fun setIconId(@DrawableRes iconId: Int): MenuItem {
        this.iconId = iconId
        return this
    }

    fun getActivityToOpen(): Class<*>? {
        return activityToOpen
    }

    fun setActivityToOpen(activityToOpen: Class<*>): MenuItem {
        this.activityToOpen = activityToOpen
        return this
    }
}
