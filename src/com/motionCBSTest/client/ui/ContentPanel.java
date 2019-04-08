package com.motionCBSTest.client.ui;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DeckLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.motionCBSTest.client.ui.login.LoginView;
import com.motionCBSTest.client.ui.user.mainUserView.MainUserView;

public class ContentPanel  extends Composite {

    private DeckLayoutPanel contentPanel;

    private LoginView loginView;
    private MainUserView mainUserView;

    public ContentPanel() {
        contentPanel = new DeckLayoutPanel();

        loginView = new LoginView();
        contentPanel.add(loginView);

        mainUserView = new MainUserView();
        contentPanel.add(mainUserView);

        contentPanel.showWidget(loginView);

        initWidget(contentPanel);
    }

    public void changeView(Widget panel) {
        contentPanel.showWidget(panel);
    }

    public LoginView getLoginView() {return loginView;}

    public MainUserView getMainUserView() {return mainUserView;}
}
