
package com.udaan18.udaan18.android.model.eventCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Department {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("heads")
    @Expose
    private List<Head> heads = null;
    @SerializedName("coHeads")
    @Expose
    private List<Manager> coHeads = null;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<Head> getHeads() {
        return heads;
    }

    public void setHeads(List<Head> heads) {
        this.heads = heads;
    }

    public List<Manager> getCoHeads() {
        return coHeads;
    }

    public void setCoHeads(List<Manager> coHeads) {
        this.coHeads = coHeads;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}