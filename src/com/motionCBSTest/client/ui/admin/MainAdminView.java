package com.motionCBSTest.client.ui.admin;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.motionCBSTest.client.ui.user.changeUserInfoUserView.ChangeUserInfoUserView;
import com.motionCBSTest.client.ui.user.statisticsUserView.StatisticsUserView;

// We use DockLayoutPanel which contains option to place panels/widgets in North, east, west, south
// and center of the screen. We use DeckLayoutPanel in the center.

public class MainAdminView extends Composite {

    private static MainAdminViewUiBinder UiBinder = GWT.create(MainAdminViewUiBinder.class);

    private ChangeUserInfoUserView changeUserInfoUserView;
    private StatisticsUserView statisticsUserView;

    @UiField
    DeckLayoutPanel centerPanel;
    @UiField
    Button changeBtn;
    @UiField
    Button logoutBtn;

    interface MainAdminViewUiBinder extends UiBinder<HTMLPanel, MainAdminView> {}

    // The constructor is creating an instance of the panels and adding them to the DeckLayoutPanel
    public MainAdminView() {
        initWidget(UiBinder.createAndBindUi(this));

        changeUserInfoUserView = new ChangeUserInfoUserView();
        centerPanel.add(changeUserInfoUserView);

        statisticsUserView = new StatisticsUserView();
        centerPanel.add((IsWidget) statisticsUserView); // Hjælp / forstå

        // The panel that is shown when the application start
        centerPanel.showWidget(changeUserInfoUserView);
    }


    //This method is used to add the clickHandlers from the logic to the menu buttons
    public void addClickHandlers(ClickHandler clickHandler) {
        logoutBtn.addClickHandler(clickHandler);
    }

    // This method is used to change the centerView
    public void changeView(Widget panel) {
        centerPanel.showWidget(panel);
    }

    // The following getters is for view and tables
    public ChangeUserInfoUserView getChangeUserInfoUserView() {return changeUserInfoUserView; }
    public StatisticsUserView getStatisticsUserView() {return statisticsUserView; }

    // The following getters is used for the menu buttons
    public Button getLogoutBtn() {return logoutBtn;}
    public Button getChangeBtn() {return changeBtn;}



}