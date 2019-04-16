package com.motionCBSTest.client.ui.admin.statisticsAdminView;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;


public class StatisticsAdminView extends Composite {
    interface StatisticsAdminViewUiBinder extends UiBinder<HTMLPanel, StatisticsAdminView> {
    }

    /*private BarChart chart;

    private ArrayList<User>users;

    private static StatisticsAdminViewUiBinder UiBinder = GWT.create(StatisticsAdminViewUiBinder.class);

    public StatisticsAdminView() {
        initWidget(UiBinder.createAndBindUi(this));
        initialize();
    }


    private void initialize() {
            ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
            chartLoader.loadApi(new Runnable() {
                public void run() {
                    // Create and attach the chart
                    chart = new BarChart();
                    RootPanel.get().add(chart);
                    draw();
                }
            });
    }


    private void draw() {
        DataTable current = DataTable.create();
        current.addColumn(ColumnType.STRING, "Name");
        current.addColumn(ColumnType.NUMBER, "Hours");
        current.addRow("Jane", 30);
        current.addRow("Joe", 10);
        current.addRow("Janet", 15);
        current.addRow("Robert", 12);
        current.addRow("Julie", 15);
        

        // Draw the chart
        chart.draw(chart(current));
        chart.setWidth("400px");
        chart.setHeight("400px");
    }*/

    }
