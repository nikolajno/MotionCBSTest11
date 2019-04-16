package com.motionCBSTest.client.ui.admin.MainAdminView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.motionCBSTest.client.ui.admin.DeleteTrainerView.DeleteTrainerView;
import com.motionCBSTest.client.ui.admin.ShowInfoAdminView;
import com.motionCBSTest.client.ui.user.changeUserInfoUserView.ChangeUserInfoUserView;
import com.motionCBSTest.client.ui.user.statisticsUserView.StatisticsUserView;

// We use DockLayoutPanel which contains option to place panels/widgets in North, east, west, south
// and center of the screen. We use DeckLayoutPanel in the center.

public class MainAdminView extends Composite {

    private static MainAdminViewUiBinder UiBinder = GWT.create(MainAdminViewUiBinder.class);

    private ChangeUserInfoUserView changeUserInfoUserView;
    private StatisticsUserView statisticsUserView;
    private ShowInfoAdminView showInfoAdminView;
    private DeleteTrainerView deleteTrainerView;

    @UiField
    DeckLayoutPanel centerPanel;
    @UiField
    Button showInfoBtn;
    @UiField
    Button deleteTrainerBtn;
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
        centerPanel.add(statisticsUserView);

        showInfoAdminView = new ShowInfoAdminView();
        centerPanel.add(showInfoAdminView);

        deleteTrainerView = new DeleteTrainerView();
        centerPanel.add(deleteTrainerView);

        // The panel that is shown when the application start
        centerPanel.showWidget(changeUserInfoUserView);
    }


    //This method is used to add the clickHandlers from the logic to the menu buttons
    public void addClickHandlers(ClickHandler clickHandler) {

        logoutBtn.addClickHandler(clickHandler);
        showInfoBtn.addClickHandler(clickHandler);
        deleteTrainerBtn.addClickHandler(clickHandler);
    }

    // This method is used to change the centerView
    public void changeView(Widget panel) {
        centerPanel.showWidget(panel);
    }

    // The following getters is for view and tables
    public ChangeUserInfoUserView getChangeUserInfoUserView() {return changeUserInfoUserView; }
    public StatisticsUserView getStatisticsUserView() {return statisticsUserView; }
    public ShowInfoAdminView getShowInfoAdminView() {return showInfoAdminView;}
    public DeleteTrainerView getDeleteTrainerView(){return deleteTrainerView;}

    // The following getters is used for the menu buttons
    public Button getDeleteTrainerBtn(){return deleteTrainerBtn;}
    public Button getLogoutBtn() {return logoutBtn;}
    public Button getChangeBtn() {return changeBtn;}
    public Button getShowInfoBtn() {return showInfoBtn; }
}