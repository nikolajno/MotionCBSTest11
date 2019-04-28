package com.motionCBSTest.client.ui.register;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

public class RegisterView extends Composite {

    @UiField TextBox newtxtFname;
    @UiField TextBox newtxtLname;
    @UiField TextBox newtxtEmail;
    @UiField TextBox newtxtAddress;
    @UiField IntegerBox newtxtMobileNo;
    @UiField TextBox newtxtEducation;
    @UiField IntegerBox newtxtExperience;
    @UiField IntegerBox newtxtHoursPrWeek;
    @UiField PasswordTextBox newtxtPassword;
    @UiField RadioButton newCrossfitBtn;
    @UiField RadioButton newHitBtn;
    @UiField RadioButton newStramopBtn;
    @UiField RadioButton newSpinningBtn;
    @UiField Button registerBtn;
    @UiField Button gobackBtn;

    interface registerUiBinder extends UiBinder<HTMLPanel, RegisterView> {}

    private static registerUiBinder ourUiBinder = GWT.create(registerUiBinder.class);

    public RegisterView() {
        initWidget(ourUiBinder.createAndBindUi(this));

        // define widgets
        newtxtFname.getElement().setPropertyString("placeholder", "First name");
        newtxtLname.getElement().setPropertyString("placeholder", "Last name");
        newtxtEmail.getElement().setPropertyString("placeholder", "E-mail");
        newtxtAddress.getElement().setPropertyString("placeholder", "Address");
        newtxtMobileNo.getElement().setPropertyString("placeholder", "Mobile Number");
        newtxtEducation.getElement().setPropertyString("placeholder", "Education");
        newtxtExperience.getElement().setPropertyString("placeholder","Experience");
        newtxtHoursPrWeek.getElement().setPropertyString("placeholder", "Hours Pr. Week");
        newtxtPassword.getElement().setPropertyString("placeholder", "Password");


        // set tooltip text
        newtxtFname.setTitle("Fornavn skal mindst være 2 bogstaver");
        newtxtLname.setTitle("Efternavn skal midst være 2 bogstaver");
        newtxtEmail.setTitle("Email skal indeholde '@' og '.'");
        newtxtAddress.setTitle("Adressen skal midst være 3 bogstaver");
        newtxtMobileNo.setTitle("Mobil nr. skal være 8 tal");
        newtxtEducation.setTitle("Uddannelse skal udfyldes");
        newtxtExperience.setTitle("Erfaring skal udfyldes i år i hele tal");
        newtxtHoursPrWeek.setTitle("Timer pr. uge skal udfyldes");
        newtxtPassword.setTitle("Password skal være 4 tegn");
    }

    // Method to add a Click Handler to the register and goback button
    public void addClickHandler (ClickHandler clickHandler) {
        registerBtn.addClickHandler(clickHandler);
        gobackBtn.addClickHandler(clickHandler);
    }

    // Method to clean the textbox fiels
    public void clearTextBoxFields () {
        newtxtFname.setText("");
        newtxtLname.setText("");
        newtxtEmail.setText("");
        newtxtAddress.setText("");
        newtxtMobileNo.setText("");
        newtxtEducation.setText("");
        newtxtExperience.setText("");
        newtxtHoursPrWeek.setText("");
        newtxtPassword.setText("");
    }

    // Getters
    public TextBox getNewtxtFname() {return newtxtFname;}

    public TextBox getNewtxtLname() {return newtxtLname;}

    public TextBox getNewtxtEmail() {return newtxtEmail;}

    public TextBox getNewtxtAddress() {return newtxtAddress;}

    public IntegerBox getNewtxtMobileNo() {return newtxtMobileNo;}

    public TextBox getNewtxtEducation() {return newtxtEducation;}

    public IntegerBox getNewtxtExperience() {return newtxtExperience;}

    public IntegerBox getNewtxtHoursPrWeek() {return newtxtHoursPrWeek;}

    public PasswordTextBox getNewtxtPassword() {return newtxtPassword;}

    public RadioButton getNewCrossfitBtn() {return newCrossfitBtn;}

    public RadioButton getNewHitBtn() {return newHitBtn;}

    public RadioButton getNewStramopBtn() {return newStramopBtn;}

    public RadioButton getNewSpinningBtn() {return newSpinningBtn;}

    public Button getRegisterBtn() {
        return registerBtn;
    }

    public Button getGobackBtn() {
        return gobackBtn;
    }
}