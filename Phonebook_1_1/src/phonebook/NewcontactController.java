package phonebook;

import javafx.scene.image.Image;
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
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NewcontactController implements Initializable {

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

    public Contacts addContactinlinkedlist() throws IOException {

        String fn, ln, e, addr, org, title, p1, p2, image;

        fn = this.firstnamefield.getText();
        ln = this.lastnamefield.getText();
        ln = ln.toUpperCase();
        e = this.emailfield.getText();
        addr = this.addressfield.getText();
        org = this.organizationfield.getText();
        title = this.titlefield.getText();
        p1 = this.phonefield1.getText();
        p2 = this.phonefield2.getText();
        image = this.addimage.getText();
        if(image.equals("Add Avatar")|| image.equals(null)){
            image = "C:\\Users\\Shaheer\\Documents\\NetBeansProjects\\Phonebook_1_1\\src\\images\\default_avatar.png";
        }        
        clearfields();
        System.out.println("Sent Contact object to the contactmainadder with refrence to name : " + fn);
        return (new Contacts(fn, ln, p1, p2, e, addr, title, org, image));

    }

    public void clearfields() {
        this.firstnamefield.setText("");
        this.lastnamefield.setText("");
        this.emailfield.setText("");
        this.addressfield.setText("");
        this.organizationfield.setText("");
        this.titlefield.setText("");
        this.phonefield1.setText("");
        this.phonefield2.setText("");
    }

    public void addchangescene() {
        JFrame f = new JFrame();
        JOptionPane.showMessageDialog(f, "Contact added");
        Stage primaryStage = (Stage) Save.getScene().getWindow();
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

    public void cancelnewcontact() throws IOException {
        Stage primaryStage = (Stage) Cancel.getScene().getWindow();
        primaryStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

}
