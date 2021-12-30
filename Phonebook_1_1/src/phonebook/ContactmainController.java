package phonebook;

import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ContactmainController implements Initializable {

    @FXML
    TextField search;
    @FXML
    Button add = new Button("Add");
    @FXML
    Button exit = new Button("Exit");
    @FXML
    Button edit = new Button("Exit");
    @FXML
    ListView listView = new ListView();
    @FXML
    Label totalContact = new Label();
    @FXML
    MenuButton m = new MenuButton("");
    @FXML
    MenuItem m1 = new MenuItem("Write Contacts");
    @FXML
    MenuItem m2 = new MenuItem("Import Contacts");

    LinkedList l = new LinkedList();
    LinkedList l2 = new LinkedList();
    String fn, ln, e, addr, org, title, p1, p2, temp;
    String image;
    Contacts cc = new Contacts(fn, ln, p1, p2, e, addr, org, title, image);

    public void addnewcontact() throws IOException {////add contact button

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("newcontact.fxml"));
        Parent root1 = loader.load();

        NewcontactController c = loader.getController();
        //access the controller and call a method

        //This line gets the Stage information
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        c.Save.setOnAction(ep -> {
            try {

                l.addSorted((Contacts) c.addContactinlinkedlist());
                int listsize = listView.getItems().size();
                listView.getItems().remove(0, listsize);
                node temp = l.head;
                int size = l.length();
                for (int i = 0; i < size; i++) {
                    listView.getItems().add(temp.data.firstname + " " + temp.data.lastname);
                    temp = temp.next;
                }
                ImageIcon icon = new ImageIcon("src/images/add_icon.png");
                JFrame f = new JFrame();
                JOptionPane.showMessageDialog(f, "Contact added!", "AddContact", JOptionPane.INFORMATION_MESSAGE, icon);
                totalcontact();
                l.print();
                Stage primarystage = (Stage) c.Save.getScene().getWindow();
                primarystage.close();
            } catch (IOException ex) {
                Logger.getLogger(ContactmainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    public void edit() throws IOException {
        Contacts tempcontact = new Contacts(fn, ln, p1, p2, e, addr, org, title, image);
        String p = (String) listView.getSelectionModel().getSelectedItem();
        l.returnNode(p, tempcontact);
        l.removenode(p);

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("editcontact.fxml"));
        Parent root1 = loader.load();

        EditcontactController c = loader.getController();
        //access the controller and call a method
        c.setInfo(tempcontact.firstname, tempcontact.lastname, tempcontact.phone1, tempcontact.phone2, tempcontact.email, tempcontact.address, tempcontact.title, tempcontact.organization, tempcontact.image);

        //This line gets the Stage information
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));
        stage.show();

        c.Save.setOnAction(ep -> {
            tempcontact.setFirstname(c.firstnamefield.getText());
            tempcontact.setLastname(c.lastnamefield.getText());
            tempcontact.setPhone1(c.phonefield1.getText());
            tempcontact.setPhone2(c.phonefield2.getText());
            tempcontact.setEmail(c.emailfield.getText());
            tempcontact.setOrganization(c.organizationfield.getText());
            tempcontact.setTitle(c.titlefield.getText());
            tempcontact.setAddress(c.addressfield.getText());
            tempcontact.setImage(c.addimage.getText());

            l.addSorted(tempcontact);

            int listsize = listView.getItems().size();
            listView.getItems().remove(0, listsize);
            node temp = l.head;
            int size = l.length();
            for (int i = 0; i < size; i++) {
                listView.getItems().add(temp.data.firstname + " " + temp.data.lastname);
                temp = temp.next;
            }

            ImageIcon icon = new ImageIcon("src/images/add_icon.png");
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f, "Contact Edited!", "EditContact", JOptionPane.INFORMATION_MESSAGE, icon);
            totalcontact();
            l.print();
            Stage primarystage = (Stage) c.Save.getScene().getWindow();
            primarystage.close();
        });
    }

    public void totalcontact() {
        totalContact.setText(" Total Contacts : " + l.length());
    }

    public void searchcontact() {
        System.out.println("In Search Contact method???????????????????gagagaga");
        String s = search.getText();
        if (s == null) {
            int listsize = listView.getItems().size();
            listView.getItems().remove(0, listsize);
            node temp = l.head;
            int size = l.length();
            for (int i = 0; i < size; i++) {
                listView.getItems().add(temp.data.firstname + " " + temp.data.lastname);
                temp = temp.next;
            }

        } else {
            l.searchnode(s, l2);
            int listsize = listView.getItems().size();
            listView.getItems().remove(0, listsize);
            node temp = l2.head;
            int size = l2.length();
            for (int i = 0; i < size; i++) {
                listView.getItems().add(temp.data.firstname + " " + temp.data.lastname);
                temp = temp.next;
            }

        }
    }

    public void deletecontact() {
        String p = (String) listView.getSelectionModel().getSelectedItem();
        System.out.println("Item deleted with name :" + p);
        ImageIcon icon = new ImageIcon("src/images/delete_icon.png");
        int input = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this contact?", "Delete Contact",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == 0) {
            l.removenode(p);
        }

        int listsize = listView.getItems().size();
        listView.getItems().remove(0, listsize);
        node temp = l.head;
        int size = l.length();
        for (int i = 0; i < size; i++) {
            listView.getItems().add(temp.data.firstname + " " + temp.data.lastname);
            temp = temp.next;
        }
        totalcontact();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        totalcontact();

        search.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {

                //System.out.println(" Text Changed to  " + newValue + "\n");
                if (newValue == null || newValue.equals("") || newValue.equals(" ") || newValue.equals("  ")) {
                    int listsize = listView.getItems().size();
                    listView.getItems().remove(0, listsize);
                    node temp = l.head;
                    int size = l.length();
                    for (int i = 0; i < size; i++) {
                        listView.getItems().add(temp.data.firstname + " " + temp.data.lastname);
                        temp = temp.next;
                    }
                } else {
                    String p = newValue;
                    System.out.println("In else statement : " + p);
                    l.searchnode(p, l2);
                    int listsize = listView.getItems().size();
                    listView.getItems().remove(0, listsize);
                    node temp = l2.head;
                    int size = l2.length();
                    for (int i = 0; i < size; i++) {
                        listView.getItems().add(temp.data.firstname + " " + temp.data.lastname);
                        temp = temp.next;
                    }

                }

            }
        });

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    System.out.println("Mouse Event Clicks:   " + mouseEvent.getClickCount());
                    if (mouseEvent.getClickCount() == 2) {

                        String p = (String) listView.getSelectionModel().getSelectedItem();
                        System.out.println("Mouse Clicked : " + p);
                        if (p != null && l.searchnodeedit(p)) {
                            l.returnNode(p, cc);

                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("contactview.fxml"));
                                Parent root1 = loader.load();

                                //access the controller and call a method
                                ContactviewController c = loader.getController();
                                c.setInfo(cc.firstname, cc.lastname, cc.phone1, cc.phone2, cc.email, cc.address, cc.title, cc.organization, cc.image);
                                //This line gets the Stage information
                                Stage stage = new Stage();
                                stage.setScene(new Scene(root1));
                                stage.show();
                            } catch (IOException ex) {
                                Logger.getLogger(ContactmainController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }

                    }
                }
            }

        });

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                String p = ((MenuItem) e.getSource()).getText();
                if (p.equals("Write Contacts")) {
                    try {
                        l.writelinkedlist();
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ContactmainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (p.equals("Import Contacts")) {
                    try {
                        l.readlinkedlist();
                        l.removeDuplicates();
                        int listsize = listView.getItems().size();
                        listView.getItems().remove(0, listsize);
                        node temp = l.head;
                        int size = l.length();
                        for (int i = 0; i < size; i++) {
                            listView.getItems().add(temp.data.firstname + " " + temp.data.lastname);
                            temp = temp.next;
                        }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(ContactmainController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        m1.setOnAction(event1);
        m2.setOnAction(event1);

    }

}
