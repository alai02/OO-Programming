package alai02_a3;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class EStoreSearch extends JPanel implements ActionListener {

    public EStoreSearch() {
        super();
        setLayout(new FlowLayout(FlowLayout.LEADING));
        JLabel message = new JLabel();
        message.setText("<html>Welcome to EStore Search<br>"
                + "<br>                                                 <br>"
                + "                                                     <br>"
                + "Choose a command from the \"Commands\n menu above for<br>"
                + "adding a product, searching products, or quitting the program<br>"
                + "</html>");
        add(message);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        MainMenu window = new MainMenu();
        window.setVisible(true);
    }

} 
