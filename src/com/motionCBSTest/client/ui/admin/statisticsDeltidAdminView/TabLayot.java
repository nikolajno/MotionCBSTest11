package com.motionCBSTest.client.ui.admin.statisticsDeltidAdminView;


import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.motionCBSTest.client.ui.admin.statisticsFuldtidAdminView.StatisticsFuldtidAdminView;

public class TabLayot extends Composite {

    private TabLayoutPanel tp;
    private StatisticsDeltidAdminView sdeltid;
    private StatisticsFuldtidAdminView sfuldtid;

    public TabLayot() {
        tp = new TabLayoutPanel(2.5, Style.Unit.EM);
        // This is animating the switch between tabs. 300 indicates the time of the animation in milliseconds
        tp.setAnimationDuration(300);
        tp.setHeight("100%");

        // Here we are creating two different objects of statisticTable which is added to the TabLayoutPanel.
        // "Deltid" and "Fuldtid" is the title of the tab
        sdeltid = new StatisticsDeltidAdminView();
        tp.add(sdeltid,"Deltid");

        sfuldtid = new StatisticsFuldtidAdminView();
        tp.add(sfuldtid,"Fuldtid");

        tp.selectTab(sdeltid);

        initWidget(tp);
    }

    // This method is adding the handler to the TabLayoutPanel
    public void addSelectionHandler(SelectionHandler<Integer> statisticSelectionHandler) {
        tp.addSelectionHandler(statisticSelectionHandler);
    }

    // Getters
    public StatisticsDeltidAdminView getSdeltid() {return sdeltid;}
    public StatisticsFuldtidAdminView getSfuldtid() {return sfuldtid;}
}
