package ma.enset.recsys;

public class Song {
    private String coverImg;
    private String songName;
    private String artist;

    public Song() {
    }

    public Song(String coverImg, String songName, String artist) {
        this.coverImg = coverImg;
        this.songName = songName;
        this.artist = artist;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
