package com.gustavo.characterlist.model;

import java.util.List;

public class Character {

    private Integer id;
    private String name;
    private String image;
    private Location location;
    private List<String> episode;

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

    public List<String> getEpisode() {
        return episode;
    }

    public void setEpisode(List<String> episode) {
        this.episode = episode;
    }

    public String getFirstEpisodeUrl() {
        return (episode != null && !episode.isEmpty()) ? episode.get(0) : null;
    }
}