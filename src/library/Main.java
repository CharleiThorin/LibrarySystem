package library;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.Controller.HashFunction;

import java.security.NoSuchAlgorithmException;

import static library.Controller.HashFunction.generateHash;
import static library.Controller.HashFunction.hashMap;
import static library.Controller.SystemController.currentUser;

public class Main extends Application {

    public JFXTextField uname;
    public JFXPasswordField jfxPasswordField;
    public JFXButton loginButton;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View/Login.fxml"));
        Scene scene = new Scene(root, 1000,600);
        primaryStage.setScene(scene);
//        primaryStage.setMaximized(true);
        primaryStage.show();
    }
//    @FXML
//    private TextField uname;
//    @FXML
//    private PasswordField passwordField;


    public void login(ActionEvent actionEvent) throws Exception {
        refresh();
        HashFunction hashFunction = new HashFunction();
        if(hashFunction.checkPassword(uname.getText(), jfxPasswordField.getText())) {
            Parent root = FXMLLoader.load(getClass().getResource("View/LandingPage.fxml"));
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 700, 600);
            appStage.setScene(scene);
            appStage.setMaxHeight(700);
            appStage.setMaxWidth(1015);
            appStage.setMinWidth(1015);
            appStage.setMinHeight(700);
            currentUser.setUserName(uname.getText());
            appStage.show();
        } else {
            loginButton.setStyle("-fx-background-color: RED");
        }
    }


    public void refresh() throws NoSuchAlgorithmException {
//        HashFunction hashFunction = new HashFunction();
        hashMap.put("Brook", generateHash("password", "SHA-256"));
    }

    public boolean hashAndCheck(String password) {
        return false;
    }


    public static void main(String[] args) {
        launch(args);

    }
}
