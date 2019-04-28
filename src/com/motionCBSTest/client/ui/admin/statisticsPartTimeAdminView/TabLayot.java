package com.motionCBSTest.client.ui.admin.statisticsPartTimeAdminView;


import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.motionCBSTest.client.ui.admin.statisticsFullTimeAdminView.StatisticsFullTimeAdminView;

public class TabLayot extends Composite {

    private TabLayoutPanel tp;
    private StatisticsPartTimeAdminView sdeltid;
    private StatisticsFullTimeAdminView sfuldtid;

    public TabLayot() {
        tp = new TabLayoutPanel(2.5, Style.Unit.EM);
        // This is animating the switch between tabs. 300 indicates the time of the animation in milliseconds
        tp.setAnimationDuration(300);
        tp.setHeight("100%");

        // Here we are creating two different objects of statisticTable which is added to the TabLayoutPanel.
        // "Deltid" and "Fuldtid" is the title of the tab
        sdeltid = new StatisticsPartTimeAdminView();
        tp.add(sdeltid,"Deltid");
        sdeltid.setStyleName("gwt-TabBar");

        sfuldtid = new StatisticsFullTimeAdminView();
        tp.add(sfuldtid,"Fuldtid");
        sfuldtid.setStyleName("gwt-TabBar");


        tp.selectTab(sdeltid);

        initWidget(tp);
    }

    // This method is adding the handler to the TabLayoutPanel
    public void addSelectionHandler(SelectionHandler<Integer> statisticSelectionHandler) {
        tp.addSelectionHandler(statisticSelectionHandler);
    }

    // Getters
    public StatisticsPartTimeAdminView getSdeltid() {return sdeltid;}
    public StatisticsFullTimeAdminView getSfuldtid() {return sfuldtid;}
}
