package com.motionCBSTest.client.ui.admin.statisticsPartTimeAdminView;


import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.motionCBSTest.client.ui.admin.statisticsFullTimeAdminView.StatisticsFullTimeAdminView;

public class TabLayout extends Composite {

    private TabLayoutPanel tp;
    private StatisticsPartTimeAdminView sdeltid;
    private StatisticsFullTimeAdminView sfuldtid;

    public TabLayout() {
        tp = new TabLayoutPanel(2.5, Unit.EM);
        // This is animating the switch between tabs. 300 indicates the time of the animation in milliseconds
        tp.setAnimationDuration(300);
        tp.setHeight("100%");


        // Here we are creating two different objects of statisticTable which is added to the TabLayoutPanel.
        // "Part time" and "Full time" is the title of the tab
        sdeltid = new StatisticsPartTimeAdminView();
        tp.add(sdeltid," Part time ");
        sdeltid.setStyleName("gwt-TabBar");

        sfuldtid = new StatisticsFullTimeAdminView();
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
    public StatisticsPartTimeAdminView getSdeltid() {return sdeltid;}
    public StatisticsFullTimeAdminView getSfuldtid() {return sfuldtid;}
}
