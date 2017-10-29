package client.view;

import client.utils.ButtonsLogic;
import client.utils.CommonComponents;
import client.utils.ViewConstants;
import client.utils.WorkingSet;
import database.Attribute;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class DeleteEntryFrame extends JFrame{
    private Map<Attribute, JTextField> textFields = new HashMap<>();

    DeleteEntryFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(ViewConstants.SMALL_WINDOW_WIDTH, ViewConstants.SMALL_WINDOW_HEIGHT);
        setTitle("Delete Entry");
        setResizable(false);
        setLocation(ViewConstants.getSmallWindowLocation());

        Set<Attribute> attributes = WorkingSet.getCurrentTable().getAttributes();

        JPanel panel = new JPanel(new GridLayout(attributes.size() * 2 + 2, 1));
        add(panel);

        panel.add(CommonComponents.createLabel("Enter entry values:"));
        for (Attribute attribute : attributes) {
            panel.add(CommonComponents.createLabel(attribute.toString()));
            panel.add(createValueTextField(attribute));
        }

        panel.add(createControlsPanel());

        pack();
        setVisible(true);
    }

    private JPanel createValueTextField(Attribute attribute) {
        JPanel panel = new JPanel();

        JTextField nameField = new JTextField(ViewConstants.SMALL_WINDOW_WIDTH / 20);
        panel.add(nameField);
        textFields.put(attribute, nameField);

        return panel;
    }

    private JPanel createControlsPanel() {
        JPanel panel = CommonComponents.createCancelButton(this);

        JButton okButton = new JButton("Ok");
        panel.add(okButton);
        okButton.addActionListener(e -> {
            ButtonsLogic.deleteEntry(
                    textFields.entrySet().stream()
                            .collect(Collectors.toMap(Map.Entry::getKey, v -> v.getValue().getText())
                            ));
            setVisible(false);
            dispose();
        });

        return panel;
    }
}
