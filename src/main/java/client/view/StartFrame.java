package client.view;

import database.Schema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oradchykova on 10/26/17.
 */
public class StartFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 100;
    private boolean choose = true;

    public StartFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setTitle("Start");
        setResizable(false);
        //setLocation(x, y);
        //setBounds(x, y, width, height);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((dimension.width - DEFAULT_WIDTH) / 2, (dimension.height - DEFAULT_HEIGHT) / 2);

        //TODO: add switch, add text field, add button

        /*String[] tables = {"t1", "t2"};//server.Api.getSchemaTables();
        JList<String> tableList = new JList<>(tables);
        tableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(tableList);
        getContentPane().add(scrollPane);*/
        //JPanel panel = new JPanel(new GridLayout(2,1));
        add(createSchemaActions(), BorderLayout.PAGE_START);
        add(createSchemaNameSpace());
        //add(panel, BorderLayout.CENTER);
    }

    private JPanel createSchemaActions() {
        JPanel panel = new JPanel(new GridBagLayout());
        ButtonGroup box = new ButtonGroup();

        JRadioButton create = new JRadioButton("Create Schema", false);
        create.addActionListener(e -> {choose = false; validate(); repaint(); setVisible(true);});
        box.add(create);
        panel.add(create);

        JRadioButton load = new JRadioButton("Load Schema", false);
        load.addActionListener(e -> {choose = true; validate(); repaint(); setVisible(true);});
        box.add(load);
        panel.add(load);

        JRadioButton delete = new JRadioButton("Delete Schema", true);
        delete.addActionListener(e -> {
            choose = true;
            validate();
            repaint();
            setVisible(true);
            SwingUtilities.updateComponentTreeUI(this);
        });
        box.add(delete);
        panel.add(delete);

        return panel;
    }

    private JPanel createSchemaNameSpace() {
        JPanel panel = new JPanel();
        JButton ok = new JButton("Ok");
        //panel.add(createSchemaComboBox());
        if (choose) {
            panel.add(createSchemaComboBox());
        } else {
            panel.add(createSchemaTextField());
        }
        panel.add(ok);
        return panel;
    }

    private JTextField createSchemaTextField() {
        return new JTextField("Enter name", 20);
    }

    private JComboBox<String> createSchemaComboBox() {
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setEditable(false);
        comboBox.setPreferredSize(new Dimension(50, 10));

        List<Schema> schemas = new ArrayList<>();
        //TODO: get real schemas
        for (Schema schema : schemas) {
            comboBox.addItem(schema.getName());
        }

        comboBox.addItem("t1");
        comboBox.addItem("t2");
        return comboBox;
    }

}
