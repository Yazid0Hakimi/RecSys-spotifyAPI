package ma.enset.recsys.Dao.Entities;

public class Artist {
    private long ID;
    private String seedTrack;
    private String name;

    public Artist() {
    }

    public Artist(String seedTrack, String name) {
        this.seedTrack = seedTrack;
        this.name = name;
    }

    // Getters and setters for the attributes
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
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
