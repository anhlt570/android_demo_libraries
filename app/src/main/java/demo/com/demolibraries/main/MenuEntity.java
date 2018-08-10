package demo.com.demolibraries.main;

import android.support.annotation.DrawableRes;

public class MenuEntity {
    private int id;
    private String title;
    private String description;
    private int iconId;

    public int getId() {
        return id;
    }

    public MenuEntity setId(@MenuID int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MenuEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public MenuEntity setDescription(String description) {
        if(description == null)
            description = "";
        this.description = description;
        return this;
    }

    public int getIconId() {
        return iconId;
    }

    public MenuEntity setIconId(@DrawableRes int iconId) {
        this.iconId = iconId;
        return this;
    }
}
