package com.motionCBSTest.client.ui.user.changeUserInfoUserView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.motionCBSTest.shared.User;

public class ChangeUserInfoUserView extends Composite {

    private static ChangeUserInfoUserViewUiBinder ourUiBinder = GWT.create(ChangeUserInfoUserViewUiBinder.class);

    @UiField
    TextBox txtFname;
    @UiField
    TextBox txtLname;
    @UiField
    TextBox txtEmail;
    @UiField
    TextBox txtAddress;
    @UiField
    TextBox txtMobileNo;
    @UiField
    TextBox txtEducation;
    @UiField
    TextBox txtExperience;
    @UiField
    TextBox txtHoursPrWeek;
    @UiField
    TextBox txtPassword;
    @UiField
    TextBox txtTeamtype;
    @UiField
    Button changeProfileBtn;

    interface ChangeUserInfoUserViewUiBinder extends UiBinder<HTMLPanel, ChangeUserInfoUserView> {}

    public ChangeUserInfoUserView() {initWidget(ourUiBinder.createAndBindUi(this));}


    // This method is adding the click handler to the change profile button
    public void addClickHandlers(ClickHandler clickHandler) {
        changeProfileBtn.addClickHandler(clickHandler);
    }


    // This method is used to set all the widgets with the current users information
    public void setProfileChanges(User user) {
        txtFname.setText(user.getFname());
        txtLname.setText(user.getLname());
        txtEmail.setText(user.getEmail());
        txtAddress.setText(user.getAddress());
        txtMobileNo.setText(user.getMobilenr()); // Måske forkert??
        txtEducation.setText(user.getEducation());
        txtExperience.setTabIndex(user.getExperience()); // Måske forkert??
        txtHoursPrWeek.setTabIndex(user.getHoursPrWeek()); // Måske forkert??
        txtPassword.setText(user.getPassword());
        txtTeamtype.setText(user.getTeamtype()); // måske forkert??
    }

    // Getters
    public TextBox getTxtFname() { return txtFname; }

    public TextBox getTxtLname() { return txtLname; }

    public TextBox getTxtEmail() { return txtEmail; }

    public TextBox getTxtAddress() { return txtAddress; }

    public TextBox getTxtMobileNo() { return txtMobileNo; }

    public TextBox getTxtEducation() { return txtEducation; }

    public TextBox getTxtExperience() { return txtExperience; }

    public TextBox getTxtHoursPrWeek() { return txtHoursPrWeek; }

    public TextBox getTxtPassword() { return txtPassword; }

    public TextBox getTxtTeamtype() { return txtTeamtype; }
}