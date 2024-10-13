import javax.swing.*;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.text.StyleConstants.setIcon;

public class HomeGUI extends JFrame {

    private JButton CreateCon;
    private JLabel WelMSG;
    private JPanel HomePannel;


    public HomeGUI() {
        setContentPane(HomePannel);
        setTitle("Home Screen");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);


        CreateCon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // open JFrame to Insert numbers
                InsertNum InsertPannel = new InsertNum();
                InsertPannel.show(); // diaplays it

                dispose(); // closes this jframe

            }
        });
    }

    private void setIcon(ImageIcon imageIcon) {
    }

    public static void main(String[]args) {
        new HomeGUI();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
