/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alai02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author protim
 */
public class AddMenu extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JPanel buttonPanelVar;
    private final JPanel textFieldPanel;
    private ProductRecord entry;
    private final JPanel namePanelVar;
    private final JPanel messagePanelVar;

    private JLabel typeLabel, productIDLabel, authorsLabel, nameLabel, publisherLabel, yearLabel, makerLabel;
    private JTextField productIDText, authorsText, nameText, publisherText, yearText, makerText;
    private String type, productID, author, name, publisher, year, maker;

    private JComboBox optionBox;
    private JTextArea messageArea;
    private int returnValue;

    private Box horizontalBox1;
    private JScrollPane scroll;

    public static ArrayList<ProductRecord> productList = new ArrayList<ProductRecord>();
    public static HashMap<String, ProductRecord> map = new HashMap<>();

    public AddMenu() {
        super();
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);

        buttonPanelVar = new JPanel();
        textFieldPanel = new JPanel();
        namePanelVar = new JPanel();
        messagePanelVar = new JPanel();

        buttonPanel();
        textFieldPanel();
        namePanel();
        messagePanel();

        horizontalBox1 = Box.createHorizontalBox();
        scroll = new JScrollPane(messageArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setEnabled(false);
        horizontalBox1.add(messagePanelVar);
        horizontalBox1.add(scroll);

        add("North", namePanelVar);
        add("Center", textFieldPanel);
        add("East", buttonPanelVar);
        add("South", horizontalBox1);

    }

    public void namePanel() {
        namePanelVar.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameLabel = new JLabel();
        nameLabel.setText("Add a product");
        namePanelVar.add(nameLabel);
    }

    public void messagePanel() {

        messageArea = new JTextArea();
        messageArea.setLayout(new FlowLayout(FlowLayout.LEFT));
        messageArea.setText("Messages: ");
        messageArea.setEditable(false);
        messageArea.setPreferredSize(new Dimension(700, 80));
        messagePanelVar.add(messageArea);

    }

    public void buttonPanel() {

        buttonPanelVar.setLayout(new GridLayout(2, 1));
        setBounds(5, 5, 50, 50);
        JButton add, reset;

        add = new JButton("Add");
        add.setPreferredSize(new Dimension(100, 50));
        add.setMaximumSize(add.getPreferredSize());
        add.setMinimumSize(add.getPreferredSize());
        add.addActionListener(new addListener());

        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(100, 50));
        reset.setMaximumSize(reset.getPreferredSize());
        reset.setMinimumSize(reset.getPreferredSize());
        reset.addActionListener(new resetListener());

        buttonPanelVar.add(add);
        buttonPanelVar.add(reset);
    }

    public void textFieldPanel() {

        textFieldPanel.setPreferredSize(new Dimension(250, 250));
        textFieldPanel.setLayout(new GridBagLayout());
        String[] options = {"Book", "Electronic"};
        typeLabel = new JLabel("Type");
        productIDLabel = new JLabel("Product ID");
        authorsLabel = new JLabel("Authors");
        nameLabel = new JLabel("Name");
        publisherLabel = new JLabel("Publisher");
        yearLabel = new JLabel("Year");
        makerLabel = new JLabel("Maker");

        productIDText = new JTextField(10);
        authorsText = new JTextField(10);
        nameText = new JTextField(10);
        publisherText = new JTextField(10);
        yearText = new JTextField(10);
        makerText = new JTextField(10);

        optionBox = new JComboBox(options);
        optionBox.setSelectedIndex(0);
        optionBox.addActionListener(new optionListener());
        optionBox.setActionCommand("Electronic");

        textFieldPanel.add(typeLabel, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(productIDLabel, new GridBagConstraints(0, 10, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(authorsLabel, new GridBagConstraints(0, 20, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(nameLabel, new GridBagConstraints(0, 30, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(publisherLabel, new GridBagConstraints(0, 40, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(yearLabel, new GridBagConstraints(0, 50, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        //   textFieldPanel.add(makerLabel, new GridBagConstraints(0, 60, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(optionBox, new GridBagConstraints(10, 0, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(productIDText, new GridBagConstraints(10, 10, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(authorsText, new GridBagConstraints(10, 20, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(nameText, new GridBagConstraints(10, 30, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(publisherText, new GridBagConstraints(10, 40, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(yearText, new GridBagConstraints(10, 50, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        //   textFieldPanel.add(makerText, new GridBagConstraints(10, 60, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    }

    public int checkSame(ProductRecord entry, ArrayList<ProductRecord> productList) {
        if (entry == null) {
            System.out.println("\t*The product has not been initializaed*");
            return -1;
        }
        if (productList == null) {
            System.out.println("\t*The list has not been initializaed*");
            return -1;
        }
        for (int i = 0; i < productList.size(); i++) {
            ProductRecord duplicate = productList.get(i);
            if (duplicate != null) {
                if (duplicate.getProductID().equalsIgnoreCase(entry.getProductID())) {
                    System.out.println("\t*The product already exists*");
                    return -1;
                }
            }
        }
        return 1;
    }

    public class addListener implements ActionListener {

        int priceNum = 0;
        int yearNum = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            e.getActionCommand();
            type = optionBox.getActionCommand();
            if (type.equalsIgnoreCase("Book")) {
                productID = productIDText.getText().toString();
                author = authorsText.getText().toString();
                name = nameText.getText().toString();
                publisher = publisherText.getText().toString();
                year = yearText.getText().toString();
                yearNum = Integer.valueOf(year);
                entry = new BookRecord(author, publisher, priceNum, productID, name, yearNum);
                returnValue = checkSame(entry, productList);
                if (returnValue == 1) {
                    productList.add(entry);
                    messageArea.setText("Added: " + entry.toString() + "ProductID:" + productID);
                    revalidate();
                } else {
                    messageArea.setText("Cannot add book to the list");
                    productIDText.setText("");
                    authorsText.setText("");
                    nameText.setText("");
                    publisherText.setText("");
                    yearText.setText("");
                    optionBox.setSelectedIndex(0);
                    revalidate();
                }
            }
            if (type.equalsIgnoreCase("Electronic")) {
                productID = productIDText.getText().toString();
                maker = makerText.getText().toString();
                name = nameText.getText().toString();
                year = yearText.getText().toString();
                entry = new ElectronicRecord(maker, priceNum, productID, name, yearNum);
                returnValue = checkSame(entry, productList);
                if (returnValue == 1) {
                    productList.add(entry);
                    messageArea.setText("The product has been added successfully");
                    revalidate();
                } else {
                    messageArea.setText("Cannot add product to the list");
                    productIDText.setText("");
                    authorsText.setText("");
                    nameText.setText("");
                    publisherText.setText("");
                    yearText.setText("");
                    optionBox.setSelectedIndex(0);
                    revalidate();
                }
            }
        }
    }

    public class resetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            productIDText.setText("");
            authorsText.setText("");
            nameText.setText("");
            publisherText.setText("");
            yearText.setText("");
            optionBox.setSelectedIndex(0);
        }
    }

    public class optionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Book")) {
                textFieldPanel.remove(makerLabel);
                textFieldPanel.remove(makerText);
                textFieldPanel.add(authorsLabel, new GridBagConstraints(0, 20, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                textFieldPanel.add(authorsText, new GridBagConstraints(10, 20, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                textFieldPanel.add(publisherLabel, new GridBagConstraints(0, 40, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                textFieldPanel.add(publisherText, new GridBagConstraints(10, 40, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                revalidate();
                //       optionBox.setActionCommand("Electronic");
            } else if (e.getActionCommand().equals("Electronic")) {
                textFieldPanel.remove(publisherLabel);
                textFieldPanel.remove(publisherText);
                textFieldPanel.remove(authorsLabel);
                textFieldPanel.remove(authorsText);
                textFieldPanel.add(makerLabel, new GridBagConstraints(0, 60, 1, 1, 1.0, 1.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                textFieldPanel.add(makerText, new GridBagConstraints(10, 60, 1, 1, 1.0, 1.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
                revalidate();
                optionBox.setActionCommand("Book");
            }
        }
    }
}
