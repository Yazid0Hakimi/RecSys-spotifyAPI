package ma.enset.recsys.Dao.Entities;

public class Genre {
    private int ID;
    private String name;

    public Genre(String name) {
//        this.seedGenres = seedGenres;
        this.name = name;
    }

    // Getters and setters for the attributes
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

//    public String getSeedGenres() {
//        return seedGenres;
//    }
//
//    public void setSeedGenres(String seedGenres) {
//        this.seedGenres = seedGenres;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

