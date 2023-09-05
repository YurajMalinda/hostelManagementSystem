package lk.ijse.gdse.hostelManagementSystem.controller;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import lk.ijse.gdse.hostelManagementSystem.bo.BOFactory;
import lk.ijse.gdse.hostelManagementSystem.bo.custom.UserBO;
import lk.ijse.gdse.hostelManagementSystem.util.Navigation;
import lk.ijse.gdse.hostelManagementSystem.util.Routes;

import java.io.IOException;

public class LoginController {
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    public JFXTextField showPassword;
    public ToggleButton toggleButton;
    public AnchorPane pane;
    public ImageView imgPasswordView;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.Type.USER);

    public void initialize(){
        showPassword.setVisible(false);
    }

    public void passwordFieldKeyTyped(KeyEvent keyEvent) {
        showPassword.textProperty().bind(Bindings.concat(txtPassword.getText()));
    }

    public void btnToggleOnAction(ActionEvent actionEvent) {
        if(toggleButton.isSelected()){
            showPassword.setVisible(true);
            txtPassword.setVisible(false);
            showPassword.textProperty().bind(Bindings.concat(txtPassword.getText()));
            toggleButton.setText("Hide");
            imgPasswordView.setImage(new Image("lk/ijse/gdse/hostelManagementSystem/view/assests/hide.png"));
        }else{
            showPassword.setVisible(false);
            txtPassword.setVisible(true);
            toggleButton.setText("Show");
            imgPasswordView.setImage(new Image("lk/ijse/gdse/hostelManagementSystem/view/assests/view.png"));
        }
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        Shake userName = new Shake(txtUserName);
        Shake password = new Shake(txtPassword);
        
        if(isCorrectUserName() && isCorrectPassword()){
            txtUserName.setFocusColor(Paint.valueOf("BLUE"));
            Navigation.navigate(Routes.DASHBOARD, pane);
            new FadeIn(pane).setSpeed(3).play();
        }else if (isCorrectPassword() && !isCorrectUserName()) {
            txtUserName.requestFocus();
            txtUserName.setFocusColor(Paint.valueOf("RED"));
            userName.play();
        } else if (!isCorrectPassword() && isCorrectUserName()) {
            txtPassword.requestFocus();
            txtPassword.setFocusColor(Paint.valueOf("RED"));
            password.play();
        } else{
            new Alert(Alert.AlertType.ERROR,"Try again !").show();
            txtPassword.clear();
            txtUserName.clear();
        }
    }

    private boolean isCorrectPassword() {
        String userName = userBO.getUserName("1");
        if(userName == null){
            new Alert(Alert.AlertType.ERROR," Database Error !").show();
            return false;
        }
        return txtUserName.getText().equals(userName);
    }

    private boolean isCorrectUserName() {
        String password = userBO.getPassword("1");
        if(password == null){
            new Alert(Alert.AlertType.ERROR," Database Error !").show();
            return false;
        }
        return txtPassword.getText().equals(password);
    }


    public void forgotPasswordOnAction(ActionEvent actionEvent) {
        new Alert(Alert.AlertType.INFORMATION,"Still developing this stage.\nKindly contact the developer.").show();
    }
}
