
package alai02;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchMenu extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    private JPanel buttonPanelVar;
    private final JPanel textFieldPanel;
    private final JPanel titlePanelVar;
    private final JPanel messagePanelVar;

    private JLabel productIDLabel, nameLabel, startYearLabel, endYearLabel;
    private JTextField productIDText, nameText, startYearText, endYearText;
    private String nameSearch, productID, startingYear, endingYear;

    private JTextArea messageArea;
    private JButton search, reset;
    private int returnValue;
    private ProductRecord item;

    private Box horizontalBox1;
    private JScrollPane scroll;

    public static ArrayList<ProductRecord> productList = AddMenu.productList;
    public static HashMap<String, ProductRecord> map = AddMenu.map;

    public SearchMenu() {
        super();
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);

        buttonPanelVar = new JPanel();
        textFieldPanel = new JPanel();
        titlePanelVar = new JPanel();
        messagePanelVar = new JPanel();

        namePanel();
        buttonPanel();
        textFieldPanel();
        messagePanel();

        horizontalBox1 = Box.createHorizontalBox();
        scroll = new JScrollPane(messageArea);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setEnabled(false);
        horizontalBox1.add(messagePanelVar);
        horizontalBox1.add(scroll);

        add("North", titlePanelVar);
        add("South", horizontalBox1);
        add("Center", textFieldPanel);
        add("East", buttonPanelVar);
    }

    public void namePanel() {
        titlePanelVar.setLayout(new FlowLayout(FlowLayout.LEFT));
        nameLabel = new JLabel();
        nameLabel.setText("Search for products");
        titlePanelVar.add(nameLabel);
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

        search = new JButton("Search");
        search.setPreferredSize(new Dimension(100, 50));
        search.setMaximumSize(search.getPreferredSize());
        search.setMinimumSize(search.getPreferredSize());
        search.addActionListener(new searchListener());

        reset = new JButton("Reset");
        reset.setPreferredSize(new Dimension(100, 50));
        reset.setMaximumSize(reset.getPreferredSize());
        reset.setMinimumSize(reset.getPreferredSize());
        reset.addActionListener(new resetListener());

        buttonPanelVar.add(search);
        buttonPanelVar.add(reset);
    }

    public void textFieldPanel() {
        /*change panel size*/
        textFieldPanel.setPreferredSize(new Dimension(250, 250));
        textFieldPanel.setLayout(new GridBagLayout());
        productIDLabel = new JLabel("Product ID: ");
        nameLabel = new JLabel("Name: ");
        startYearLabel = new JLabel("Start Year");
        endYearLabel = new JLabel("End Year");

        productIDText = new JTextField(10);
        nameText = new JTextField(10);
        startYearText = new JTextField(10);
        endYearText = new JTextField(10);

        textFieldPanel.add(productIDLabel, new GridBagConstraints(0, 10, 1, 1, 1.0,
                1.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(nameLabel, new GridBagConstraints(0, 30, 1, 1, 1.0,
                1.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(startYearLabel, new GridBagConstraints(0, 40, 1, 1, 1.0,
                1.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(endYearLabel, new GridBagConstraints(0, 50, 1, 1, 1.0,
                1.0, GridBagConstraints.WEST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));

        textFieldPanel.add(productIDText, new GridBagConstraints(10, 10, 1, 1, 1.0,
                1.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(nameText, new GridBagConstraints(10, 30, 1, 1, 1.0,
                1.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(startYearText, new GridBagConstraints(10, 40, 1, 1, 1.0,
                1.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        textFieldPanel.add(endYearText, new GridBagConstraints(10, 50, 1, 1, 1.0,
                1.0, GridBagConstraints.EAST, GridBagConstraints.NONE,
                new Insets(0, 0, 0, 0), 0, 0));
        //textFieldPanel.add(callNoText);
    }

    public class resetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            productIDText.setText("");
            nameText.setText("");
            startYearText.setText("");
            endYearText.setText("");
        }
    }

    public class searchListener implements ActionListener {

        int numStartYear = 0;
        int numEndYear = 0;
        int numExactYear = 0;
        int iteamYear, intStartingYear, intEndingYear;

        @Override
        public void actionPerformed(ActionEvent e) {
           
            nameSearch = nameText.getText().toString();
            productID = productIDText.getText().toString();
            startingYear = startYearText.getText().toString();
            endingYear = endYearText.getText().toString();
            if (productList != null) {
                if (nameSearch.equals("") || nameSearch.equals(" ") && productID.equals("") || productID.equals(" ") && startingYear.equals("") || startingYear.equals(" ") && endingYear.equals("") || endingYear.equals(" ")) {
                    for (int i = 0; i < productList.size(); i++) {
                        item = productList.get(i);
                        messageArea.setText(messageArea.getText() + "\nProduct ID:" + item.getProductID() + "\nName:" + item.getName());
                    }
                }
                for (int i = 0; i < productList.size(); i++) {
                    item = productList.get(i);
                    if (nameSearch.equalsIgnoreCase(item.getName()) && productID.equalsIgnoreCase(item.getProductID())) {
                        messageArea.setText(messageArea.getText() + "\nProduct ID:" + item.getProductID() + "\nName:" + item.getName());
                        
                        iteamYear = new Integer(item.getYear());
                        if(startingYear.length() > 3){
                            intStartingYear = new Integer(startingYear);
                        }
                        if(endingYear.length() > 3){
                            intEndingYear = new Integer(endingYear);
                        }
                       
                        if (iteamYear >= intStartingYear) {
                            messageArea.setText(messageArea.getText() + "\nProduct ID:" + item.getProductID() + "\nName:" + item.getName());
                        }
                        if (iteamYear <= intEndingYear) {
                            messageArea.setText(messageArea.getText() + "\nProduct ID:" + item.getProductID() + "\nName:" + item.getName());
                        }
                    }

                }

            }
            if (productList == null) {
                messageArea.setText("Add a product first");
            }
        }
    }

}
