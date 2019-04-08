package com.motionCBSTest.client.ui.register;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.motionCBSTest.shared.FieldVerifier;

public class Register extends Composite {

    @UiField TextBox newtxtFname;
    @UiField TextBox newtxtLname;
    @UiField TextBox newtxtEmail;
    @UiField TextBox newtxtAddress;
    @UiField TextBox newtxtMobileNo;
    @UiField TextBox newtxtEducation;
    @UiField TextBox newtxtExperience;
    @UiField TextBox newtxtHoursPrWeek;
    @UiField PasswordTextBox newtxtPassword;
    @UiField RadioButton newCrossfitBtn;
    @UiField RadioButton newHitBtn;
    @UiField RadioButton newStramopBtn;
    @UiField RadioButton newSpinningBtn;
    @UiField Button registerBtn;


    interface registerUiBinder extends UiBinder<HTMLPanel, Register> {

    }

    private static registerUiBinder ourUiBinder = GWT.create(registerUiBinder.class);

    public Register() {
        initWidget(ourUiBinder.createAndBindUi(this));

        // define widgets
        newtxtFname.getElement().setPropertyString("placeholder", "Firstname");
        newtxtLname.getElement().setPropertyString("placeholder", "Lastname");
        newtxtEmail.getElement().setPropertyString("placeholder", "E-mail");
        newtxtAddress.getElement().setPropertyString("placeholder", "Address");
        newtxtMobileNo.getElement().setPropertyString("placeholder", "MobileNo");
        newtxtEducation.getElement().setPropertyString("placeholder", "Education");
        newtxtExperience.getElement().setPropertyString("placeholde","Experince");
        newtxtHoursPrWeek.getElement().setPropertyString("placeholder", "Hours Pr. Week");
        newtxtPassword.getElement().setPropertyString("placeholder", "Password");


        // set tooltip text
        newtxtFname.setTitle("Fornavn skal mindst være 2 bogstaver");
        newtxtLname.setTitle("Efternavn skal midst være 2 bogstaver");
        newtxtEmail.setTitle("Email skal indeholde '@'");
        newtxtAddress.setTitle("Adresse skal midst være 3 bogstaver");
        newtxtMobileNo.setTitle("Mobil nr. skal være 8 tal");
        newtxtEducation.setTitle("Uddannelse skal udfyldes");
        newtxtExperience.setTitle("Erfaring skal udfyldes");
        newtxtHoursPrWeek.setTitle("Timer pr. uge skal udfyldes");
        newtxtPassword.setTitle("Password skal mindst være 4 tegn");


        // add click handler
        registerBtn.addClickHandler(new Handler1());
    }

    // click handler definition
    private class Handler1 implements ClickHandler{

        @Override
        public void onClick(ClickEvent event) {
            // check if all fields are valid jj
            if (FieldVerifier.isValidFname(newtxtFname.getText())
                    && FieldVerifier.isValidLname(newtxtLname.getText())
                    && FieldVerifier.isValidEmail(newtxtEmail.getText())
                    && FieldVerifier.isValidAddress(newtxtAddress.getText())
                    && FieldVerifier.isValidMobileNo(newtxtMobileNo.getText())
                    && FieldVerifier.isValidEducation(newtxtEducation.getText())
                    && FieldVerifier.isValidExperience(newtxtExperience.getText())
                    && FieldVerifier.isValidHoursPrWeek(newtxtHoursPrWeek.getText())
                    && FieldVerifier.isValidPassword(newtxtPassword.getText()));
            /*&& FieldVerifier.isValidTeamtype()*/


        }
    }


    public TextBox getNewtxtFname() {return newtxtFname;}

    public TextBox getNewtxtLname() {return newtxtLname;}

    public TextBox getNewtxtEmail() {return newtxtEmail;}

    public TextBox getNewtxtAddress() {return newtxtAddress;}

    public TextBox getNewtxtMobileNo() {return newtxtMobileNo;}

    public TextBox getNewtxtEducation() {return newtxtEducation;}

    public TextBox getNewtxtExperience() {return newtxtExperience;}

    public TextBox getNewtxtHoursPrWeek() {return newtxtHoursPrWeek;}

    public PasswordTextBox getNewtxtPassword() {return newtxtPassword;}

    public RadioButton getNewCrossfitBtn() {return newCrossfitBtn;}

    public RadioButton getNewHitBtn() {return newHitBtn;}

    public RadioButton getNewStramopBtn() {return newStramopBtn;}

    public RadioButton getNewSpinningBtn() {return newSpinningBtn;}
}