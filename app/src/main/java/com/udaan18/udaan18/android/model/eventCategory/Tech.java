
package com.udaan18.udaan18.android.model.eventCategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tech {

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
    private List<CoHead> coHeads = null;
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

    public List<CoHead> getCoHeads() {
        return coHeads;
    }

    public void setCoHeads(List<CoHead> coHeads) {
        this.coHeads = coHeads;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

}
