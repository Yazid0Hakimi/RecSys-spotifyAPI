package ma.enset.recsys.Dao.Entities;

public class Album {
    private int ID;
    private String seedAlbums;
    private String name;

    public Album(String seedAlbums, String name) {
        this.seedAlbums = seedAlbums;
        this.name = name;
    }

    public Album() {
    }

    // Getters and setters for the attributes
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSeedAlbums() {
        return seedAlbums;
    }

    public void setSeedAlbums(String seedAlbums) {
        this.seedAlbums = seedAlbums;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
