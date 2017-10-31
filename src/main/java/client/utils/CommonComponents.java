package client.utils;

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

    public static JPanel createTable(Table table) {
        JPanel panel = new JPanel(new GridLayout(table.getEntries().size() + 1, table.getAttributes().size()));
        List<Attribute> attributes = new ArrayList<>(table.getAttributes());
        for (Attribute attribute : attributes) {
            panel.add(createLabel(attribute.toString()));
        }
        for (Entry entry : table.getEntries()) {
            for (Attribute attribute : attributes) {
                panel.add(createLabel(entry.getValueByAttribute(attribute)));
            }
        }
        return panel;
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

}
