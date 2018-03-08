
package com.udaan18.udaan18.android.model.eventCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Department {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("heads")
    @Expose
    private List<Manager> heads = null;
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

    public List<Manager> getHeads() {
        return heads;
    }

    public void setHeads(List<Manager> heads) {
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
