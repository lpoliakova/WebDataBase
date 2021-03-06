package client.view;

import client.utils.ButtonsLogic;
import client.utils.ViewConstants;
import client.utils.WorkingSet;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StartFrame extends JFrame {
    private List<JRadioButton> schemaSelection = new ArrayList<>();

    public StartFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(ViewConstants.SMALL_WINDOW_WIDTH, ViewConstants.SMALL_WINDOW_HEIGHT);
        setTitle("Start");
        setResizable(false);
        setLocation(ViewConstants.getSmallWindowLocation());

        JPanel panel = new JPanel(new GridLayout(2,1));
        panel.add(createSchemaActions());
        panel.add(createSchemaNameSpace());
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createSchemaActions() {
        JPanel panel = new JPanel(new GridBagLayout());
        ButtonGroup box = new ButtonGroup();

        JRadioButton create = new JRadioButton("Create Schema", true);
        schemaSelection.add(create);
        box.add(create);
        panel.add(create);

        JRadioButton load = new JRadioButton("Load Schema", false);
        schemaSelection.add(load);
        box.add(load);
        panel.add(load);

        JRadioButton delete = new JRadioButton("Delete Schema", false);
        schemaSelection.add(delete);
        box.add(delete);
        panel.add(delete);

        return panel;
    }

    private JPanel createSchemaNameSpace() {
        JPanel panel = new JPanel();
        JComboBox<String> nameField = createSchemaComboBox();
        panel.add(nameField);

        JButton ok = createOkButton(nameField);
        panel.add(ok);

        return panel;
    }

    private JComboBox<String> createSchemaComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setEditable(true);
        comboBox.setPreferredSize(
                new Dimension(ViewConstants.SMALL_WINDOW_WIDTH / 2, ViewConstants.SMALL_WINDOW_HEIGHT / 8));

        String[] schemas = WorkingSet.getConnection().listSchemaNames();
        for (String schema : schemas) {
            comboBox.addItem(schema);
        }
        return comboBox;
    }

    private JButton createOkButton(JComboBox<String> nameField) {
        JButton ok = new JButton("Ok");
        ok.addActionListener(e -> {
            if (schemaSelection.get(0).isSelected()) {
                ButtonsLogic.createSchema((String) nameField.getSelectedItem());
                setVisible(false);
                dispose();
            } else if (schemaSelection.get(1).isSelected()) {
                ButtonsLogic.loadSchema((String) nameField.getSelectedItem());
                setVisible(false);
                dispose();
            } else if (schemaSelection.get(2).isSelected()) {
                ButtonsLogic.deleteSchema((String) nameField.getSelectedItem());
                setVisible(false);
                dispose();
            }
        });
        return ok;
    }
}
