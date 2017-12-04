package client.utils;

import client.view.ExceptionFrame;
import client.view.TableFrame;
import shared.Attribute;
import shared.Entry;
import shared.Table;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CommonComponents {

    public static JPanel createCancelButton(JFrame frame) {
        JPanel panel = new JPanel();
        JButton cancelButton = new JButton("Cancel");
        panel.add(cancelButton);
        cancelButton.addActionListener(e -> {
            EventQueue.invokeLater(TableFrame::new);
            frame.setVisible(false);
            frame.dispose();
        });
        return panel;
    }

    public static JPanel createTablePanel(Table table) {
        JPanel panel = new JPanel();
        JPanel labelPanel = new JPanel(new GridLayout(3, 1));
        panel.add(labelPanel, BorderLayout.PAGE_START);

        JLabel schemaInfo = createLabel(WorkingSet.getCurrentSchema().toString());
        labelPanel.add(schemaInfo);

        if (table == null) {
            labelPanel.add(createLabel("Choose table"));
        } else {
            JLabel tableInfo = CommonComponents.createLabel(table.toString());
            labelPanel.add(tableInfo);

            panel.add(createTable(table), BorderLayout.CENTER);
        }
        return panel;
    }

    public static JScrollPane createTable(Table table) {
        List<Attribute> attributes = new ArrayList<>(table.getAttributes());
        String[] columnNames = attributes.stream().map(Attribute::getName).toArray(String[]::new);
        String[][] rowData = table.getEntries().stream().map(e ->
                attributes.stream().map(e::getValueByAttribute).toArray(String[]::new))
                .toArray(String[][]::new);

        JTable tableView = new JTable(rowData, columnNames);
        tableView.setShowGrid(true);

        return new JScrollPane(tableView);
    }

    public static JLabel createLabel(String text) {
        return new JLabel(convertToProperLabel(text));
    }

    private static String convertToProperLabel(String text) {
        StringBuilder html = new StringBuilder();
        html.append("<html>").append("<div style = \"padding-left: 10px\">");
        String[] lines = text.split(System.lineSeparator());
        for (String line : lines) {
            html.append(line).append("<br>");
        }

        html.append("</div>").append("</html>");
        return html.toString();
    }

    public static void showConnectionException(Exception ex) {
        ExceptionFrame.showException("An error occurred while connecting to the server!");
        System.out.println(ex);
        ex.printStackTrace();
    }

}
