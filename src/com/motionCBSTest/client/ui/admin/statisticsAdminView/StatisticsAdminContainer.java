package com.motionCBSTest.client.ui.admin.statisticsAdminView;


import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;

public class StatisticsAdminContainer extends Composite {

    private TabLayoutPanel tp;
    private StatisticsAdminView sdeltid;
    private StatisticsAdminView sfuldtid;

    public StatisticsAdminContainer() {
        tp = new TabLayoutPanel(2.5, Unit.EM);
        // This is animating the switch between tabs. 300 indicates the time of the animation in milliseconds
        tp.setAnimationDuration(300);
        tp.setHeight("100%");

        // Here we are creating two different objects of statisticTable which is added to the TabLayoutPanel.
        // "Part time" and "Full time" is the title of the tab
        sdeltid = new StatisticsAdminView();
        tp.add(sdeltid," Part time ");
        sdeltid.setStyleName("gwt-TabBar");

        sfuldtid = new StatisticsAdminView();
        tp.add(sfuldtid," Full time ");
        sfuldtid.setStyleName("gwt-TabBar");

        tp.selectTab(sdeltid);

        initWidget(tp);
    }

    // This method is adding the handler to the TabLayoutPanel
    public void addSelectionHandler(SelectionHandler<Integer> statisticSelectionHandler) {
        tp.addSelectionHandler(statisticSelectionHandler);
    }

    // Getters
    public TabLayoutPanel getTp() { return tp; }
    public StatisticsAdminView getSdeltid() {return sdeltid;}
    public StatisticsAdminView getSfuldtid() {return sfuldtid;}
}
