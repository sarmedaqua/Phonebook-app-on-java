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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class ContactviewController implements Initializable {

    @FXML
    Label fullname = new Label();
    @FXML
    Label phone1 = new Label();
    @FXML
    Label phone2 = new Label();
    @FXML
    Label addr = new Label();
    @FXML
    Label email = new Label();
    @FXML
    Label title = new Label();
    @FXML
    Label organization = new Label();
    @FXML
    Button back = new Button("Back");
    @FXML
    Button edit = new Button("Edit");
    @FXML
    ImageView myImageView = new ImageView();

    ContactmainController n = new ContactmainController();

    public void setInfo(String firstname, String lastname, String phone1, String phone2, String email, String address, String title, String organization, String image) throws IOException {
        fullname.setText(firstname + " " + lastname);
        this.phone1.setText(phone1);
        this.phone2.setText(phone2);
        this.email.setText(email);
        this.addr.setText(address);
        this.title.setText(title);
        this.organization.setText(organization);
        imagev(image);

    }

    public void imagev(String fp) throws IOException {
        File file = new File(fp);
        BufferedImage bufferedImage = ImageIO.read(file);
        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        myImageView.setImage(image);
    }

    public void backtomain() {
        Stage primaryStage = (Stage) back.getScene().getWindow();
        primaryStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
