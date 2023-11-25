package com.example.demo.Dao.Entities;

public class Artist {
    private int ID;
    private String seedTrack;
    private String name;

    public Artist(String seedTrack, String name) {
        this.seedTrack = seedTrack;
        this.name = name;
    }

    // Getters and setters for the attributes
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSeedTrack() {
        return seedTrack;
    }

    public void setSeedTrack(String seedTrack) {
        this.seedTrack = seedTrack;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
