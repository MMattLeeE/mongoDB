package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Matt on 6/6/2017.
 */
public class LoadPage {

    public static void loadLoginPage(ActionEvent event) throws IOException {

        Stage stage = (Stage) ((Control) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(ControllerLogin.class.getResource("/loginPage.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void loadRegistrationPage(ActionEvent event) throws IOException{

        Stage stage = (Stage) ((Control) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(ControllerRegister.class.getResource("/registerPage.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void loadRegSuccessPage(ActionEvent event) throws IOException{

        Stage stage = (Stage) ((Control) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(ControllerRegSuccess.class.getResource("/registrationSuccess.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static void loadUserPage(ActionEvent event) throws IOException{
        Stage stage = (Stage) ((Control) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(ControllerRegSuccess.class.getResource("/userPage.fxml"));

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
