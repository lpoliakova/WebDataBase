package client.view;

import client.utils.ButtonsLogic;
import client.utils.ViewConstants;
import client.utils.WorkingSet;
import database.Table;

import javax.swing.*;
import java.awt.*;

/**
 * Created by oradchykova on 10/29/17.
 */
public class TableFrame extends JFrame {

    public TableFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(ViewConstants.STANDARD_WINDOW_WIDTH, ViewConstants.STANDARD_WINDOW_HEIGHT);
        setTitle("Table Representation");
        setResizable(false);
        setLocation(ViewConstants.getStandardWindowLocation());

        setJMenuBar(createMenuBar());
        add(drawTable(), BorderLayout.PAGE_START);

        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu schemaMenu = new JMenu("Schema");
        menuBar.add(schemaMenu);
        JMenuItem leaveSchema = schemaMenu.add("Leave schema");
        leaveSchema.addActionListener(e -> {
            ButtonsLogic.leaveSchema();
            setVisible(false);
            dispose();
        });

        JMenu tableMenu = new JMenu("Table");
        menuBar.add(tableMenu);
        JMenuItem createTable = tableMenu.add("Create new table");
        createTable.addActionListener(e -> {
            //TODO: show table creation
        });

        JMenuItem loadTable = tableMenu.add("Load table");
        loadTable.addActionListener(e -> {
            //TODO: show table selection
        });

        JMenuItem saveTable = tableMenu.add("Save table");
        saveTable.addActionListener(e -> ButtonsLogic.saveTable());

        JMenuItem deleteTable = tableMenu.add("Delete table");
        deleteTable.addActionListener(e -> {
            ButtonsLogic.deleteTable();
            //TODO: update Table window
        });

        JMenu entryMenu = new JMenu("Entry");
        menuBar.add(entryMenu);
        JMenuItem addEntry = entryMenu.add("Add entry");
        addEntry.addActionListener(e -> {
            //TODO: show entry creation
        });

        JMenuItem deleteEntry = entryMenu.add("Delete entry");
        deleteEntry.addActionListener(e -> {
            //TODO: show entry selection
        });

        JMenu switcher = new JMenu("View");
        menuBar.add(switcher);
        JMenuItem twoTables = switcher.add("Two tables representation");
        twoTables.setEnabled(false);

        return menuBar;
    }

    private JPanel drawTable() {
        JPanel panel = new JPanel(new GridLayout(3,1));

        JLabel schemaInfo = new JLabel(convertString(WorkingSet.getCurrentSchema().toString()));
        panel.add(schemaInfo);

        Table table = WorkingSet.getCurrentTable();
        JLabel tableInfo = new JLabel(table == null ? convertString("Choose table") : convertString(table.toString()));
        panel.add(tableInfo);

        //TODO: draw table;

        return panel;
    }

    private String convertString(String text) {
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
