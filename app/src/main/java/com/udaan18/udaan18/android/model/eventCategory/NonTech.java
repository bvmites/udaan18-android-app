
package com.udaan18.udaan18.android.model.eventCategory;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NonTech {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("participants")
    @Expose
    private String participants;
    @SerializedName("fees")
    @Expose
    private String fees;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("prizes")
    @Expose
    private List<Object> prizes = null;
    @SerializedName("rounds")
    @Expose
    private List<String> rounds = null;
    @SerializedName("managers")
    @Expose
    private List<Manager_> managers = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Object> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Object> prizes) {
        this.prizes = prizes;
    }

    public List<String> getRounds() {
        return rounds;
    }

    public void setRounds(List<String> rounds) {
        this.rounds = rounds;
    }

    public List<Manager_> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager_> managers) {
        this.managers = managers;
    }

}
