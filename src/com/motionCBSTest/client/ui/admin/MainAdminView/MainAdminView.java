package com.motionCBSTest.client.ui.admin.MainAdminView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.motionCBSTest.client.ui.admin.showInfoAdminView.ShowInfoAdminView;
import com.motionCBSTest.client.ui.admin.trainerStatusView.TrainerStatusView;
import com.motionCBSTest.client.ui.user.changeInfoUserView.ChangeInfoUserView;
import com.motionCBSTest.client.ui.user.trainersTableUserView.TrainersTableUserView;

// We use DockLayoutPanel which contains option to place panels/widgets in North, east, west, south
// and center of the screen. We use DeckLayoutPanel in the center.

public class MainAdminView extends Composite {

    private static MainAdminViewUiBinder UiBinder = GWT.create(MainAdminViewUiBinder.class);

    private ChangeInfoUserView changeInfoUserView;
    private TrainersTableUserView trainersTableUserView;
    private ShowInfoAdminView showInfoAdminView;
    private TrainerStatusView trainerStatusView;

    @UiField
    DeckLayoutPanel centerPanel;
    @UiField
    Button showInfoBtn;
    @UiField
    Button trainerStatusBtn;
    @UiField
    Button changeBtn;
    @UiField
    Button logoutBtn;

    interface MainAdminViewUiBinder extends UiBinder<HTMLPanel, MainAdminView> {}

    // The constructor is creating an instance of the panels and adding them to the DeckLayoutPanel
    public MainAdminView() {
        initWidget(UiBinder.createAndBindUi(this));

        changeInfoUserView = new ChangeInfoUserView();
        centerPanel.add(changeInfoUserView);

        trainersTableUserView = new TrainersTableUserView();
        centerPanel.add(trainersTableUserView);

        showInfoAdminView = new ShowInfoAdminView();
        centerPanel.add(showInfoAdminView);

        trainerStatusView = new TrainerStatusView();
        centerPanel.add(trainerStatusView);

        // The panel that is shown when the application start
        centerPanel.showWidget(changeInfoUserView);
    }


    //This method is used to add the clickHandlers from the logic to the menu buttons
    public void addClickHandlers(ClickHandler clickHandler) {

        logoutBtn.addClickHandler(clickHandler);
        showInfoBtn.addClickHandler(clickHandler);
        trainerStatusBtn.addClickHandler(clickHandler);
    }

    // This method is used to change the centerView
    public void changeView(Widget panel) {
        centerPanel.showWidget(panel);
    }

    // The following getters is for view and tables
    public ChangeInfoUserView getChangeInfoUserView() {return changeInfoUserView; }
    public TrainersTableUserView getTrainersTableUserView() {return trainersTableUserView; }
    public ShowInfoAdminView getShowInfoAdminView() {return showInfoAdminView;}
    public TrainerStatusView getTrainerStatusView(){return trainerStatusView;}

    // The following getters is used for the menu buttons
    public Button getTrainerStatusBtn(){return trainerStatusBtn;}
    public Button getLogoutBtn() {return logoutBtn;}
    public Button getChangeBtn() {return changeBtn;}
    public Button getShowInfoBtn() {return showInfoBtn; }
}