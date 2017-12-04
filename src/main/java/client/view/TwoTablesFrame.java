package client.view;

import client.utils.ButtonsLogic;
import client.utils.CommonComponents;
import client.utils.ViewConstants;
import client.utils.WorkingSet;

import javax.swing.*;
import java.awt.*;

public class TwoTablesFrame extends JFrame {
    public TwoTablesFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(ViewConstants.STANDARD_WINDOW_WIDTH, ViewConstants.STANDARD_WINDOW_HEIGHT);
        setTitle("Table Representation");
        setResizable(false);
        setLocation(ViewConstants.getStandardWindowLocation());

        WorkingSet.setOneTable(false);

        setJMenuBar(createMenuBar());
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(CommonComponents.createTablePanel(WorkingSet.getCurrentTable()));
        panel.add(CommonComponents.createTablePanel(WorkingSet.getCurrentOtherTable()));
        add(panel);

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

        JMenu tableMenu = new JMenu("Left Table");
        menuBar.add(tableMenu);
        JMenuItem loadTable = tableMenu.add("Load table");
        loadTable.addActionListener(e -> {
            EventQueue.invokeLater(SelectTableFrame::new);
            WorkingSet.setFirstTable(true);
            setVisible(false);
            dispose();
        });

        JMenuItem saveTable = tableMenu.add("Save table");
        saveTable.addActionListener(e -> ButtonsLogic.saveTable(true));

        JMenu otherTableMenu = new JMenu("Right Table");
        menuBar.add(otherTableMenu);
        JMenuItem loadOtherTable = otherTableMenu.add("Load table");
        loadOtherTable.addActionListener(e -> {
            EventQueue.invokeLater(SelectTableFrame::new);
            WorkingSet.setFirstTable(false);
            setVisible(false);
            dispose();
        });

        JMenuItem saveOtherTable = otherTableMenu.add("Save table");
        saveOtherTable.addActionListener(e -> ButtonsLogic.saveTable(false));

        JMenu additionalOperations = new JMenu("Operations");
        menuBar.add(additionalOperations);
        JMenuItem subtraction = additionalOperations.add("Subtract");
        subtraction.addActionListener(e -> {
            ButtonsLogic.subtractTables();
            setVisible(false);
            dispose();
        });

        JMenuItem intersection = additionalOperations.add("Intersect");
        intersection.addActionListener(e -> {
            ButtonsLogic.intersectTables();
            setVisible(false);
            dispose();
        });

        JMenu switcher = new JMenu("View");
        menuBar.add(switcher);
        JMenuItem oneTable = switcher.add("One table representation");
        oneTable.addActionListener(e -> {
            EventQueue.invokeLater(TableFrame::new);
            WorkingSet.setCurrentOtherTable(null);
            setVisible(false);
            dispose();
        });

        return menuBar;
    }
}
