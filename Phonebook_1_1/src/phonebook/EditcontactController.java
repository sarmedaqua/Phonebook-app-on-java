package phonebook;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class EditcontactController implements Initializable {

    @FXML
    TextField firstnamefield = new TextField();
    @FXML
    TextField lastnamefield = new TextField();
    @FXML
    TextField phonefield1 = new TextField();
    @FXML
    TextField phonefield2 = new TextField();
    @FXML
    TextField emailfield = new TextField();
    @FXML
    TextField addressfield = new TextField();
    @FXML
    TextField organizationfield = new TextField();
    @FXML
    TextField titlefield = new TextField();
    @FXML
    Label heading = new Label();
    @FXML
    Button Save = new Button("Save");
    @FXML
    Button Cancel = new Button("Cancel");
    @FXML
    Button addimage = new Button("Add Avatar");
    @FXML
    ImageView myImageView = new ImageView();

    public void setInfo(String firstname, String lastname, String phone1, String phone2, String email, String address, String title, String organization, String image) throws IOException {
        firstnamefield.setText(firstname);
        lastnamefield.setText(lastname);
        this.phonefield1.setText(phone1);
        this.phonefield2.setText(phone2);
        this.emailfield.setText(email);
        this.addressfield.setText(address);
        this.titlefield.setText(title);
        this.organizationfield.setText(organization);
        imagev(image);

    }

    public void imagev(String fp) throws IOException {
        File file = new File(fp);
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        myImageView.setImage(image);
    }

    public void canceledit() {
        Stage primaryStage = (Stage) Cancel.getScene().getWindow();
        primaryStage.close();
    }

    public void addimg() {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        String filepath;
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            filepath = file.getPath();
            System.out.println("File Path : " + filepath);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            addimage.setText(filepath);
            myImageView.setImage(image);

        } catch (IOException ex) {
            //Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
