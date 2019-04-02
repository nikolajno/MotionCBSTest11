package com.motionCBSTest.client.ui.login;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

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
    public void clearTextBoxFields(){
        mobilenrTxtBox.setText("");
        passwordTxtBox.setText("");
    }

    // Getters for the mobilenrTxtBox and passwordTxtBox
    public TextBox getMobilenrTxtBox() { return mobilenrTxtBox; }
    public PasswordTextBox getPasswordTxtBox() { return passwordTxtBox; }
}