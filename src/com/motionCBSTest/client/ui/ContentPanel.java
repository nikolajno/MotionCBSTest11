package com.motionCBSTest.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.motionCBSTest.client.ui.admin.MainAdminView.MainAdminView;
import com.motionCBSTest.client.ui.login.LoginView;
import com.motionCBSTest.client.ui.register.RegisterView;
import com.motionCBSTest.client.ui.user.mainUserView.MainUserView;

public class ContentPanel  extends Composite {

    private DeckLayoutPanel contentPanel;

    private LoginView loginView;
    private MainUserView mainUserView;
    private RegisterView registerView;
    private MainAdminView mainAdminView;

    // The constructor of this class is creating an instance of the panels
    // and adding them to the DeckLayoutPanel
    public ContentPanel() {
        contentPanel = new DeckLayoutPanel();

        loginView = new LoginView();
        contentPanel.add(loginView);

        mainUserView = new MainUserView();
        contentPanel.add(mainUserView);

        registerView = new RegisterView();
        contentPanel.add(registerView);

        mainAdminView = new MainAdminView();
        contentPanel.add(mainAdminView);

        //The panel that is shown when the application starts
        contentPanel.showWidget(loginView);

        //Init the main widget in this class
        initWidget(contentPanel);
    }

    // Method used to change the view
    public void changeView(Widget panel) {
        contentPanel.showWidget(panel);
    }

    // Getters
    public LoginView getLoginView() {return loginView;}

    public MainUserView getMainUserView() {return mainUserView;}

    public MainAdminView getMainAdminView() {return mainAdminView;}

    public RegisterView getRegisterView() {return registerView;}
}
