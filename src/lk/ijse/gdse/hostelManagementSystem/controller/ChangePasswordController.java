package lk.ijse.gdse.hostelManagementSystem.controller;

import animatefx.animation.Shake;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.gdse.hostelManagementSystem.bo.BOFactory;
import lk.ijse.gdse.hostelManagementSystem.bo.custom.UserBO;
import lk.ijse.gdse.hostelManagementSystem.util.Navigation;
import lk.ijse.gdse.hostelManagementSystem.util.Routes;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangePasswordController {
    public AnchorPane confirmPane;
    public JFXPasswordField currentPassword;
    public JFXTextField currentUsername;
    public AnchorPane changePane;
    public JFXPasswordField newPassword;
    public JFXTextField newUsername;
    public Label shownPassword;
    public ImageView imgPasswordView;
    public ToggleButton toggleButton;
    public AnchorPane pane;

    UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.Type.USER);

    public void initialize(){
        changePane.toBack();
        confirmPane.toFront();
        shownPassword.setVisible(false);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.navigate(Routes.DASHBOARD, pane);
    }

    public void confirmClickOnAction(ActionEvent actionEvent) {
        Shake shakeUserName = new Shake(currentUsername);
        Shake shakePassword = new Shake(currentPassword);

        if (isCorrectUserName() && isCorrectPassword()) {
            changePane.toFront();
            confirmPane.toBack();
            currentPassword.clear();
            currentUsername.clear();
        } else {
            if (!isCorrectUserName()) {
                shakeUserName.play();
                currentUsername.requestFocus();
            }
            if (!isCorrectPassword()) {
                shakePassword.play();
                currentPassword.requestFocus();
            }
        }
    }

    private boolean isCorrectPassword() {
        String user = userBO.getUserName("1");
        if (user == null) {
            new Alert(Alert.AlertType.ERROR, " Database Error !").show();
            return false;
        }
        return currentUsername.getText().equals(user);
    }

    private boolean isCorrectUserName() {
        String password = userBO.getPassword("1");
        if (password == null) {
            new Alert(Alert.AlertType.ERROR, " Database Error !").show();
            return false;
        }
        return currentPassword.getText().equals(password);
    }

    public void changeClickOnAction(ActionEvent actionEvent) throws IOException {
        String newUName = newUsername.getText();
        String newPword = newPassword.getText();

        if(isValidUserName() && isValidPassword()){
            Alert alert = new Alert(Alert.AlertType.WARNING,"Confirm update?", ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.YES) {
                boolean isUpdated = userBO.updateUserDetails(newUName, newPword);

                if (isUpdated) {
                    new Alert(Alert.AlertType.INFORMATION, " Changes Saved !").show();
                    changePane.toBack();
                    confirmPane.toFront();
                    newUsername.clear();
                    newPassword.clear();
                    Navigation.navigate(Routes.DASHBOARD, pane);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Wrong Inputs \nTry again !").show();
                }
            } else {
                changePane.toFront();
                confirmPane.toBack();
            }
        }
    }

    private boolean isValidPassword() {
        Pattern passwordPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher passwordMatcher = passwordPattern.matcher(newPassword.getText());

        boolean passwordIsMatches = passwordMatcher.matches();
        if (passwordIsMatches) {
            return true;
        } else {
            Shake shakePW = new Shake(newPassword);
            newPassword.requestFocus();
            shakePW.play();
            return false;
        }
    }

    private boolean isValidUserName() {
        Pattern userNamePattern = Pattern.compile("^[a-zA-Z]{4,}$");
        Matcher userNameMatcher = userNamePattern.matcher(newUsername.getText());

        boolean userNameIsMatches = userNameMatcher.matches();
        if (userNameIsMatches) {
            return true;
        } else {
            Shake shakeUserName = new Shake(newUsername);
            newUsername.requestFocus();
            shakeUserName.play();
            return false;
        }
    }

    public void passwordFieldKeyTyped(KeyEvent keyEvent) {
        shownPassword.textProperty().bind(Bindings.concat(newPassword.getText()));
    }

    public void toggleButton(ActionEvent actionEvent) {
        if(toggleButton.isSelected()){
            shownPassword.setVisible(true);
            shownPassword.textProperty().bind(Bindings.concat(newPassword.getText()));
            toggleButton.setText("Hide");
            imgPasswordView.setImage(new Image("lk/ijse/gdse/hostelManagementSystem/view/assests/hide.png"));
        }else{
            shownPassword.setVisible(false);
            newPassword.setVisible(true);
            toggleButton.setText("Show");
            imgPasswordView.setImage(new Image("lk/ijse/gdse/hostelManagementSystem/view/assests/view.png"));
        }
    }
}
