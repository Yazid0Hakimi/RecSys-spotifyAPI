package ma.enset.recsys.Dao.Entities;

public class Genre {
    private int ID;
    private String name;

    public Genre( String name) {
        this.name = name;
    }

    // Getters and setters for the attributes
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

