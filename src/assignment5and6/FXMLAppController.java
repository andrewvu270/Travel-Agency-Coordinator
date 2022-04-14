/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment5and6;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Hoang Vu <vuhoan@sheridancollege.ca> <andrewvu270@gmail.com>
 */
public class FXMLAppController implements Initializable {

    private static int count;
    ArrayList<Destination> destinationList = new ArrayList();
//    ArrayList<Destination> destinationListNew = new ArrayList();
    File file;
    FileInputStream fi;
    FileOutputStream fo;
    ObjectInputStream oi;
    ObjectOutputStream oo;

    @FXML
    TextField txtDestination, txtDuration, txtWentWith, txtYear, txtComments;

    @FXML
    Button btnOpen;

    @FXML
    ListView lstDestinationList;

    @FXML
    private void add(ActionEvent event) {
        if (txtDestination.getText().trim().isEmpty() || txtDestination.getText().trim().isEmpty()
                || txtDestination.getText().trim().isEmpty() || txtDestination.getText().trim().isEmpty()
                || txtDestination.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR! :(");
            alert.setTitle("Add Error");
            String s = "Please fill in all boxes!";
            alert.setContentText(s);

            alert.showAndWait();
        } else {
            try {
                Destination d = new Destination();
                d.setName(txtDestination.getText());
                d.setDuration(Integer.parseInt(txtDuration.getText()));
                d.setWentWith(Integer.parseInt(txtWentWith.getText()));
                d.setYear(Integer.parseInt(txtYear.getText()));
                d.setComments(txtComments.getText());
                destinationList.add(d);
                lstDestinationList.getItems().add(d);
                count = destinationList.size();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("ERROR! :(");
                alert.setTitle("Add Error");
                String s = "Invalid inputs! Please fill in:\n"
                        + "¤Appropriate numbers for Duration, People Went With and Year\n"
                        + "¤Appropriate name for Destination and proper Comments";
                alert.setContentText(s);
                alert.getDialogPane().setPrefSize(350, 320);
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void save(ActionEvent event) throws IOException {
        if (!destinationList.isEmpty()) {
            fo = new FileOutputStream("output.txt");
            oo = new ObjectOutputStream(fo);
            oo.writeObject(destinationList);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR! :(");
            alert.setTitle("Save Error");
            String s = "List is empty!";
            alert.setContentText(s);

            alert.showAndWait();
        }
    }

    @FXML
    private void open(ActionEvent event) throws ClassNotFoundException, FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();
        file = fileChooser.showOpenDialog(btnOpen.getScene().getWindow());
        fi = new FileInputStream(file.getName());
        oi = new ObjectInputStream(fi);

        destinationList = (ArrayList) oi.readObject();
        if (!destinationList.isEmpty()) {
            count = 1;
            Destination d = destinationList.get(0);
            txtDestination.setText(d.getName());
            txtYear.setText("" + d.getYear());
            txtDuration.setText("" + d.getDuration());
            txtWentWith.setText("" + d.getWentWith());
            txtComments.setText(d.getComments());
            lstDestinationList.getItems().removeAll(lstDestinationList.getItems());
            for (int i = 0; i < destinationList.size(); i++) {
                lstDestinationList.getItems().add(destinationList.get(i));
            }
        }

    }

    @FXML
    private void next(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (!destinationList.isEmpty()) {
            if (count < destinationList.size()) {
                Destination d = destinationList.get(count);
                txtDestination.setText(d.getName());
                txtDuration.setText("" + d.getDuration());
                txtComments.setText(d.getComments());
                txtWentWith.setText("" + d.getWentWith());
                txtYear.setText("" + d.getYear());
                count++;
            }
        }
    }

    @FXML
    private void previous(ActionEvent event) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (!destinationList.isEmpty()) {
            if (count > 1) {
                Destination d = destinationList.get(count - 2);
                txtDestination.setText(d.getName());
                txtDuration.setText("" + d.getDuration());
                txtWentWith.setText("" + d.getWentWith());
                txtYear.setText("" + d.getYear());
                txtComments.setText(d.getComments());
                count--;
            }
        }
    }

    @FXML
    private void first(ActionEvent event) {
        if (!destinationList.isEmpty()) {
            Destination d = destinationList.get(0);
            txtDestination.setText(d.getName());
            txtDuration.setText("" + d.getDuration());
            txtWentWith.setText("" + d.getWentWith());
            txtYear.setText("" + d.getYear());
            txtComments.setText(d.getComments());
            count = 1;
        }
    }

    @FXML
    private void last(ActionEvent event) {
        if (!destinationList.isEmpty()) {
            Destination d = destinationList.get(destinationList.size() - 1);
            txtDestination.setText(d.getName());
            txtDuration.setText("" + d.getDuration());
            txtWentWith.setText("" + d.getWentWith());
            txtYear.setText("" + d.getYear());
            txtComments.setText(d.getComments());
            count = destinationList.size();
        }
    }

    @FXML
    private void edit(ActionEvent event) throws IOException {
        if (!destinationList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Edit");
            alert.setHeaderText("Edit Confirmation");
            String s = "Confirm to edit record?";
            alert.setContentText(s);

            Optional<ButtonType> result = alert.showAndWait();

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {
                destinationList.get(count - 1).setName(txtDestination.getText());
                destinationList.get(count - 1).setDuration(Integer.parseInt(txtDuration.getText()));
                destinationList.get(count - 1).setWentWith(Integer.parseInt(txtWentWith.getText()));
                destinationList.get(count - 1).setYear(Integer.parseInt(txtYear.getText()));
                destinationList.get(count - 1).setComments(txtComments.getText());
                lstDestinationList.getItems().removeAll(lstDestinationList.getItems());
                for (int i = 0; i < destinationList.size(); i++) {
                    lstDestinationList.getItems().add(destinationList.get(i));
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR! :(");
            alert.setTitle("Edit Error");
            String s = "Cannot edit record! List is empty!";
            alert.setContentText(s);

            alert.showAndWait();

        }
    }

    @FXML
    private void delete(ActionEvent event) throws IOException {
        if (!destinationList.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Delete Confirmation");
            String s = "Confirm to remove record?";
            alert.setContentText(s);

            Optional<ButtonType> result = alert.showAndWait();

            if ((result.isPresent()) && (result.get() == ButtonType.OK)) {

                destinationList.remove(count - 1);
                count--;

                if (!destinationList.isEmpty()) {
                    if (count == 0) {
                        count++;
                    }
                    txtDestination.setText(destinationList.get(count - 1).getName());
                    txtDuration.setText("" + destinationList.get(count - 1).getDuration());
                    txtWentWith.setText("" + destinationList.get(count - 1).getWentWith());
                    txtYear.setText("" + destinationList.get(count - 1).getYear());
                    txtComments.setText(destinationList.get(count - 1).getComments());

                    lstDestinationList.getItems().removeAll(lstDestinationList.getItems());
                    for (int i = 0; i < destinationList.size(); i++) {
                        lstDestinationList.getItems().add(destinationList.get(i));
                    }
                } else {
                    txtDestination.clear();
                    txtDuration.clear();
                    txtWentWith.clear();
                    txtYear.clear();
                    txtComments.clear();
                    lstDestinationList.getItems().removeAll(lstDestinationList.getItems());
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("ERROR! :(");
            alert.setTitle("Delete Error");
            String s = "Cannot remove record! List is empty!";
            alert.setContentText(s);

            alert.showAndWait();

        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lstDestinationList.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Object p = lstDestinationList.getSelectionModel().getSelectedItem();
                txtDestination.setText(((Destination) p).getName());
                txtDuration.setText("" + ((Destination) p).getDuration());
                txtWentWith.setText("" + ((Destination) p).getWentWith());
                txtYear.setText("" + ((Destination) p).getYear());
                txtComments.setText(((Destination) p).getComments());
                Destination d = (Destination) p;
                for (int i = 0; i < destinationList.size(); i++) {
                    if (destinationList.get(i).equals(d)) {
                        count = i + 1;
                    }
                }
            }
        });
    }

}
