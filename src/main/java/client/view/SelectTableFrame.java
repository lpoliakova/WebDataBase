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
public class SelectTableFrame extends JFrame{
    public SelectTableFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(ViewConstants.SMALL_WINDOW_WIDTH, ViewConstants.SMALL_WINDOW_HEIGHT);
        setTitle("Create Table");
        setResizable(false);
        setLocation(ViewConstants.getSmallWindowLocation());

        JPanel panel = new JPanel(new GridLayout(3, 1));
        add(panel);

        panel.add(CommonComponents.createLabel("Select table name:"));
        panel.add(createTablePanel());
        panel.add(CommonComponents.createCancelButton(this));

        setVisible(true);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel();

        JComboBox<String> name = new JComboBox<>();
        name.setEditable(false);
        WorkingSet.getCurrentSchema().getTables().values().stream()
                .map(Table::getName)
                .forEach(name::addItem);
        panel.add(name);
        //TODO: handle empty schema

        JButton selectButton = new JButton("Select");
        panel.add(selectButton);
        selectButton.addActionListener(e -> {
            ButtonsLogic.loadTable((String) name.getSelectedItem());
            setVisible(false);
            dispose();
        });

        return panel;
    }

}
