package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import testMongoDB.MongoDB;
import testMongoDB.Model.User;
import testMongoDB.Model.UserCurrent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Matt on 5/27/2017.
 */
public class ControllerLogin implements Initializable {

    @FXML
    private Button registerBtn;
    @FXML
    private Button login;
    @FXML
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //set the action for logging in
        login.setOnAction(e -> {
            authenticate(e);
        });

        //set action listener for the register button; takes you to registration page
        registerBtn.setOnAction(e -> {
            try {
                LoadPage.loadRegistrationPage(e);

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     *  Authenticate checks if the username exists in the database
     *  and checks if the password matches the corresponding username
     *
     *  It then sets the authenticated user as the current user in UserCurrent.java
     *
     *  Finally the User page is loaded using LoadPage.loadUserPage(event)
     */
    @FXML
    private void authenticate(ActionEvent event) {
        User user = null;
        //check to see if the username and password fields are not empty
        if (usernameInput.getText().isEmpty() || passwordInput.getText().isEmpty()) {
            //If fields empty prompt user with message:
            displayMessage("Please enter a username and password", Color.RED);
        //If the fields are not empty:
        } else {
            //Check to see if a username exists in Database
            if (MongoDB.doesUsernameExist(usernameInput.getText())) {
                //If username exists get user from database:
                user = MongoDB.getUser(usernameInput.getText());
                //check password that user inputs
                if (passwordInput.getText().equals(user.getPassword())) {
                    //If the input password matches set current user:
                    UserCurrent.setCurrentUser(user);
                    //Load user page:
                    try {
                        LoadPage.loadUserPage(event);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        System.out.println("Problem opening user page!");
                    }
                } else {
                    //if the password doesn't match:
                    displayMessage("password for " + usernameInput.getText() + " does not match", Color.RED);
                }
            } else {
                //if there is no username found:
                displayMessage("Username not found", Color.RED);
            }
        }
    }

    //used to display messages to user on login screen.
    // Specify Color of display message font.
    private void displayMessage(String message, Color fontColor){
        errorLabel.setText(message);
        errorLabel.setVisible(true);
        errorLabel.setTextFill(fontColor);
    }
}
