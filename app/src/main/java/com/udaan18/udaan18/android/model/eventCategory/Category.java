package com.udaan18.udaan18.android.model.eventCategory;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pranshu on 11/3/17.
 */

public class Category {

    @SerializedName("category")
    private String category;

    @SerializedName("members")
    private List<Member> members;

    public Category(String category, List<Member> members) {
        this.category = category;
        this.members = members;
    }

    public String getCategory() {
        return this.category;
    }

    public List<Member> getMembers() {
        return this.members;
    }
}
