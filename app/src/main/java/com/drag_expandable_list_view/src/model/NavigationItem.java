package com.drag_expandable_list_view.src.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by benholmes on 3/31/18.
 */

public class NavigationItem extends RealmObject {

    @PrimaryKey
    @SerializedName("displayName")
    @Expose
    private String displayName;

    @SerializedName("position")
    @Expose
    @Index
    private int position;

    @SerializedName("resource")
    @Expose
    @Index
    private int resource;


    public NavigationItem(){

    }

    public String getName() {
        return displayName;
    }

    public void setName(String name) {
        this.displayName = name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }
}

