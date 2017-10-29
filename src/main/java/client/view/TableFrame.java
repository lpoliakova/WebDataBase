package client.view;

import client.utils.ButtonsLogic;
import client.utils.CommonComponents;
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
            EventQueue.invokeLater(CreateTableFrame::new);
            setVisible(false);
            dispose();
        });

        JMenuItem loadTable = tableMenu.add("Load table");
        loadTable.addActionListener(e -> {
            EventQueue.invokeLater(SelectTableFrame::new);
            setVisible(false);
            dispose();
        });

        JMenuItem saveTable = tableMenu.add("Save table");
        saveTable.addActionListener(e -> ButtonsLogic.saveTable());

        JMenuItem deleteTable = tableMenu.add("Delete table");
        deleteTable.addActionListener(e -> {
            ButtonsLogic.deleteTable();
            setVisible(false);
            dispose();
            //TODO: add confirmation
        });

        JMenu entryMenu = new JMenu("Entry");
        menuBar.add(entryMenu);
        JMenuItem addEntry = entryMenu.add("Add entry");
        addEntry.addActionListener(e -> {
            EventQueue.invokeLater(CreateEntryFrame::new);
            setVisible(false);
            dispose();
        });

        JMenuItem deleteEntry = entryMenu.add("Delete entry");
        deleteEntry.addActionListener(e -> {
            EventQueue.invokeLater(DeleteEntryFrame::new);
            setVisible(false);
            dispose();
        });

        JMenu switcher = new JMenu("View");
        menuBar.add(switcher);
        JMenuItem twoTables = switcher.add("Two tables representation");
        twoTables.setEnabled(false);
        //TODO: create two table View

        return menuBar;
    }

    private JPanel drawTable() {
        JPanel panel = new JPanel(new GridLayout(3,1));

        JLabel schemaInfo = CommonComponents.createLabel(WorkingSet.getCurrentSchema().toString());
        panel.add(schemaInfo);

        Table table = WorkingSet.getCurrentTable();
        if (table == null) {
            panel.add(CommonComponents.createLabel("Choose table"));
        } else {
            JLabel tableInfo = CommonComponents.createLabel(table.toString());
            panel.add(tableInfo);

            panel.add(CommonComponents.createTable(table));
        }
        return panel;
    }
}
