package library.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import library.Model.Employee;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;


public class Login implements Initializable {
    public static Employee currentUser;
    @FXML
    private TextField uname;
    @FXML
    private JPasswordField jfxPasswordField;


//    public void login(ActionEvent actionEvent) throws Exception {
//        System.out.println(uname.getText());
//        System.out.println(jfxPasswordField.getText());
////        HashFunction hashFunction = new HashFunction();
////        if(hashFunction.checkPassword(uname.getText(), jfxPasswordField.getText())) {
////            Parent root = FXMLLoader.load(getClass().getResource("View/SystemController.fxml"));
////            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
////            Scene scene = new Scene(root,700,600);
////            appStage.setScene(scene);
////            appStage.setMaxHeight(700);
////            appStage.setMaxWidth(1015);
////            appStage.setMinWidth(1015);
////            appStage.setMinHeight(700);
////            appStage.show();
//
////        approve(actionEvent);
//    }
//
//
//    public void approve(ActionEvent actionEvent) throws Exception {
////        currentUser = new Employee(UserRole.ADMIN, "Sol");
////        Parent root = FXMLLoader.load(getClass().getResource("View/SystemController.fxml"));
////        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
////        Scene scene = new Scene(root,700,600);
////        appStage.setScene(scene);
////        appStage.setMaxHeight(700);
////        appStage.setMaxWidth(1015);
////        appStage.setMinWidth(1015);
////        appStage.setMinHeight(700);
////        appStage.show();
//    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
