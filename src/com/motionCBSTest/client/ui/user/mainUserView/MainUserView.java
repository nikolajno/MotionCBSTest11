package com.motionCBSTest.client.ui.user.mainUserView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.motionCBSTest.client.ui.user.changeInfoUserView.ChangeInfoUserView;
import com.motionCBSTest.client.ui.user.tableUserView.TableUserView;

// We use DockLayoutPanel which contains option to place panels/widgets in North, east, west, south
// and center of the screen. We use DeckLayoutPanel in the center.

public class MainUserView extends Composite {

    private static MainUserViewUiBinder UiBinder = GWT.create(MainUserViewUiBinder.class);

    private ChangeInfoUserView changeInfoUserView;
    private TableUserView tableUserView;

    @UiField DeckLayoutPanel centerPanel;
    @UiField Button changeBtn;
    @UiField Button trainersTable;
    @UiField Button logoutBtn;

    interface MainUserViewUiBinder extends UiBinder<HTMLPanel, MainUserView> {}

    // The constructor is creating an instance of the panels and adding them to the DeckLayoutPanel
    public MainUserView() {
        initWidget(UiBinder.createAndBindUi(this));

        changeInfoUserView = new ChangeInfoUserView();
        centerPanel.add(changeInfoUserView);

        tableUserView = new TableUserView();
        centerPanel.add(tableUserView);

        // The panel that is shown when the application start
        centerPanel.showWidget(tableUserView);
    }

    //This method is used to add the clickHandlers from the logic to the menu buttons
    public void addClickHandlers(ClickHandler clickHandler) {
        changeBtn.addClickHandler(clickHandler);
        trainersTable.addClickHandler(clickHandler);
        logoutBtn.addClickHandler(clickHandler);
    }

    // This method is used to change the centerView
    public void changeView(Widget panel) {
        centerPanel.showWidget(panel);
    }

    // The following getters is for view and tables
    public ChangeInfoUserView getChangeInfoUserView() {return changeInfoUserView; }
    public TableUserView getTableUserView() {return tableUserView; }

    // The following getters is used for the menu buttons
    public Button getChangeBtn() {return changeBtn; }
    public Button getTrainersTable() {return trainersTable; }
    public Button getLogoutBtn() {return logoutBtn;}
}