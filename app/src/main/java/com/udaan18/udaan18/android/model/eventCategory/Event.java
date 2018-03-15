
package com.udaan18.udaan18.android.model.eventCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Locale;

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

    public String getRoundsDescription() {
        String roundsDescription = "";

        if (this.rounds != null) {
            for (int i = 0; i < this.rounds.size(); i++) {
                String roundInfo = this.rounds.get(i);
                if (roundInfo != null && roundInfo.trim().length() > 0) {
                    if (i > 0) {
                        roundsDescription += "\n\n";
                    }
                    roundsDescription += String.format(Locale.getDefault(), "Round %d:\n%s", (i + 1), roundInfo);
                }
            }
        }

        return roundsDescription;
    }

    public String getPrizeDescription(String rupee) {

        String prizeDescription = "";

        if (this.prizes != null) {

            for (int i = 0; i < this.prizes.size(); i++) {

                String positionInfo = "";
                int prizeInfo = this.prizes.get(i);
                if (prizeInfo > 0) {
                    if (i > 0) {
                        prizeDescription += "\n\n";
                    }
                    if (i == 0) {
                        positionInfo = "Winner";
                    } else if (i == 1) {
                        positionInfo = "Runner's Up";
                    } else if (i == 2) {
                        positionInfo = "Second Runner's Up";
                    }
                    prizeDescription += String.format(Locale.getDefault(), "%s: " + rupee + "%s", positionInfo, prizeInfo);

                }
            }
        }
        return prizeDescription;
    }


}
