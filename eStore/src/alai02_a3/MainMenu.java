package alai02_a3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainMenu extends JFrame implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private JPanel myStore = new EStoreSearch();
    private JPanel addPanel = new AddMenu();
    private JPanel searchPanel = new SearchMenu();

    public MainMenu() {

        super("EStore Search");

        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;
        
        try {
            menuBar = new JMenuBar();

            menu = new JMenu("Commands");
            menuBar.add(menu);

            menuItem = new JMenuItem("Add", KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription("Nothing");
            menuItem.addActionListener(this);
            menuItem.setActionCommand("Add");
            menu.addSeparator();
            menu.add(menuItem);

            menuItem = new JMenuItem("Search", KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription("Nothing");
            menuItem.addActionListener(this);
            menuItem.setActionCommand("Search");
            menu.addSeparator();
            menu.add(menuItem);

            menuItem = new JMenuItem("Quit", KeyEvent.VK_T);
            menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.CTRL_MASK));
            menuItem.getAccessibleContext().setAccessibleDescription("Nothing");
            menuItem.addActionListener(this);
            menuItem.setActionCommand("Quit");
            menu.addSeparator();
            menu.add(menuItem);

            setJMenuBar(menuBar);
            welcomeMessage();
        } catch (Exception a) {
            a.printStackTrace();

        }
    }

    private void welcomeMessage() {
        try {
            add(myStore);
            myStore.setVisible(true);
            revalidate();
        } catch (Exception b) {
            b.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add")) {
            try {
                myStore.setVisible(false);
                searchPanel.setVisible(false);
                add(addPanel);
                addPanel.setVisible(true);
                revalidate();
            } catch (Exception b) {
                b.getStackTrace();
            }
        }
        if (e.getActionCommand().equals("Search")) {
            myStore.setVisible(false);
            addPanel.setVisible(false);
            add(searchPanel);
            searchPanel.setVisible(true);
            revalidate();
        }
        if (e.getActionCommand().equals("Quit")) {
            System.exit(0);
        }

    }
}
