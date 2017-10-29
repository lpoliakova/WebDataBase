package client.view;

import client.utils.ButtonsLogic;
import client.utils.CommonComponents;
import client.utils.ViewConstants;
import database.Attribute;
import database.DatabaseTypes;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class CreateTableFrame extends JFrame{
    private Set<Attribute> attributes = new HashSet<>();

    CreateTableFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(ViewConstants.SMALL_WINDOW_WIDTH, ViewConstants.SMALL_WINDOW_HEIGHT);
        setTitle("Create Table");
        setResizable(false);
        setLocation(ViewConstants.getSmallWindowLocation());

        JPanel panel = new JPanel(new GridLayout(6, 1));
        add(panel);

        panel.add(CommonComponents.createLabel("Enter table name:"));
        panel.add(createTablePanel());
        if (!attributes.isEmpty()) {
            panel.add(createCurrentAttributesList());
        }
        panel.add(CommonComponents.createLabel("Add new attribute: "));
        panel.add(createAttributePanel());
        panel.add(CommonComponents.createCancelButton(this));

        setVisible(true);
    }

    private JPanel createTablePanel() {
        JPanel panel = new JPanel();

        JTextField nameField = new JTextField(ViewConstants.SMALL_WINDOW_WIDTH / 20);
        panel.add(nameField);

        JButton createButton = new JButton("Create");
        panel.add(createButton);
        createButton.addActionListener(e -> {
            if (!attributes.isEmpty()) {
                ButtonsLogic.createTable(nameField.getText(), attributes);
                setVisible(false);
                dispose();
            }
            //TODO: add empty attributes action
        });

        return panel;
    }

    private JPanel createCurrentAttributesList() {
        JPanel panel = new JPanel();
        //TODO: add existing attributes
        return panel;
    }

    private JPanel createAttributePanel() {
        JPanel panel = new JPanel();

        panel.add(CommonComponents.createLabel("Name: "));
        JTextField attributeName = new JTextField(ViewConstants.SMALL_WINDOW_WIDTH / 60);
        panel.add(attributeName);

        panel.add(CommonComponents.createLabel("Type: "));
        JComboBox<DatabaseTypes> types = new JComboBox<>();
        types.setEditable(false);
        Arrays.stream(DatabaseTypes.values()).forEach(types::addItem);
        panel.add(types);

        JButton addButton = new JButton("Add");
        panel.add(addButton);
        addButton.addActionListener(e -> {
            Attribute attribute = ButtonsLogic.addAttribute(attributeName.getText(), (DatabaseTypes) types.getSelectedItem());
            attributes.add(attribute);
            //TODO: refresh table creation
        });

        return panel;
    }
}
