package testMongoDB;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Matt on 5/27/2017.
 */
public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        //setup connection to MongoDB
        MongoDB.setMongoDatabaseConnection();

        Parent root = FXMLLoader.load(Main.class.getResource("/loginPage.fxml"));

        Scene scene = new Scene(root, 600, 530);

        primaryStage.setTitle("Matt Login");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
