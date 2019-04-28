package com.motionCBSTest.client.ui.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.motionCBSTest.shared.User;


public class LoginView extends Composite {

    private static loginViewUiBinder UiBinder = GWT.create(loginViewUiBinder.class);
    @UiField
    TextBox mobilenrTxtBox;
    @UiField
    PasswordTextBox passwordTxtBox;
    @UiField
    Button loginBtn;
    @UiField
    Button registerBtn;


    // reference to DTO
    User user;

    interface loginViewUiBinder extends UiBinder<HTMLPanel, LoginView> {
    }


    public LoginView() {

        initWidget(UiBinder.createAndBindUi(this));


        mobilenrTxtBox.getElement().setPropertyString("placeholder", "Mobilenr");
        passwordTxtBox.getElement().setPropertyString("placeholder", "Password");
    }

    // Method to add a Click Handler to the login button
    public void addClickHandlers(ClickHandler clickHandler) {
        loginBtn.addClickHandler(clickHandler);
        registerBtn.addClickHandler(clickHandler);
    }

    // Method to clean the textbox fiels
    public void clearTextBoxFields() {
        mobilenrTxtBox.setText("");
        passwordTxtBox.setText("");
    }


    /*/
    private void validate() {
        if (FieldVerifier.isValidMobileNo(mobilenrTxtBox.getText()) && FieldVerifier.isValidPassword(passwordTxtBox.getText())) {
            return;
        } else if (FieldVerifier.isValidMobileNo(mobilenrTxtBox.getText()) != true) {
            Window.alert("Mobile number must contain only 8 digits!");
            mobilenrTxtBox.setStyleName("textBox-invalidEntry");
        } else if (FieldVerifier.isValidPassword(passwordTxtBox.getText()) != true){
            Window.alert("Password must only contain 4 digits!");
            passwordTxtBox.setStyleName("textBox-invalidEntry");
        }
    }



        if (FieldVerifier.isValidMobileNo(mobilenrTxtBox.getText())){
            mobilenrTxtBox.setStyleName("textBox-invalidEntry");
        }
        else
            mobilenrTxtBox.setStyleName("textBox");
        if (FieldVerifier.isValidPassword(passwordTxtBox.getText())){
            passwordTxtBox.setStyleName("textBox-invalidEntry");
        }
        else passwordTxtBox.setStyleName("textBox");

        return false;
    }


    }

    @UiHandler("loginBtn")
    void getLoginBtn(ClickEvent event) {
        validate();
            return;

    /*/

    // Getters for the mobilenrTxtBox and passwordTxtBox
    public TextBox getMobilenrTxtBox() {
        return mobilenrTxtBox;
    }

    public PasswordTextBox getPasswordTxtBox() {
        return passwordTxtBox;
    }


        public Button getLoginBtn () {
            return loginBtn;
        }

        public Button getRegisterBtn () {
            return registerBtn;
        }
    }