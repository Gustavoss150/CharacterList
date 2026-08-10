package com.gustavo.characterlist.model;

import android.provider.CallLog;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Characters {

    private Integer id;
    private String name;
    private String image;
    private Location location;
    @SerializedName("episode")
    private List<String> episodeURL;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<String> getEpisodeURL() {
        return episodeURL;
    }

    public void setEpisodeURL(List<String> episode) {
        this.episodeURL = episodeURL;
    }

    public String getFirstEpisodeURL() {
        return (episodeURL != null && !episodeURL.isEmpty()) ? episodeURL.get(0) : null;
    }
}