package ma.enset.recsys;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SongController {
    @FXML private Label artist;
    @FXML private ImageView coverImg;
    @FXML private Label songName;

    public void setData(Song song){
        Image image = new Image(getClass().getResourceAsStream(song.getCoverImg()));
        coverImg.setImage(image);
        songName.setText(song.getSongName());
        artist.setText(song.getArtist());
    }
}
