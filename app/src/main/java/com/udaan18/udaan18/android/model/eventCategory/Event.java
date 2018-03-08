
package com.udaan18.udaan18.android.model.eventCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Event {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("participants")
    @Expose
    private Integer participants;
    @SerializedName("fees")
    @Expose
    private Integer fees;
    @SerializedName("tagline")
    @Expose
    private String tag;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("prizes")
    @Expose
    private List<Integer> prizes = null;
    @SerializedName("rounds")
    @Expose
    private List<String> rounds = null;
    @SerializedName("managers")
    @Expose
    private List<Manager> managers = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParticipants() {
        return participants;
    }

    public void setParticipants(Integer participants) {
        this.participants = participants;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }


    public Integer getFees() {
        return fees;
    }

    public void setFees(Integer fees) {
        this.fees = fees;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String description) {
        this.tag = description;
    }

    public List<Integer> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Integer> prizes) {
        this.prizes = prizes;
    }

    public List<String> getRounds() {
        return rounds;
    }

    public void setRounds(List<String> rounds) {
        this.rounds = rounds;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

}
