package com.motionCBSTest.client.ui.admin.statisticsAdminView;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class StatisticsAdminView extends Composite {
    interface StatisticsAdminViewUiBinder extends UiBinder<HTMLPanel, StatisticsAdminView> {
    }

    private static StatisticsAdminViewUiBinder ourUiBinder = GWT.create(StatisticsAdminViewUiBinder.class);

    public StatisticsAdminView() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }





}