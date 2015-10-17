
import java.awt.*; //GUI objects
import java.awt.event.*; //Action Listener
import javax.swing.*; // Message dialogbox

public class Lab3 extends JApplet implements ActionListener {

    String name;
    float rate;
    int hours;
    double grosspay, netpay;
    Label nameLabel = new Label("Enter Name");
    TextField nameField = new TextField();
    Label hoursLabel = new Label("Enter Hours");
    TextField hoursField = new TextField();
    Label rateLabel = new Label("Enter Rate");
    TextField rateField = new TextField();
    Label msgLabel = new Label("Click button to calculate");
    Button Calcbutton = new Button("Calculate Pay");
    Label GP = new Label("Gross Pay:");
    Label GPout = new Label("");
    Label NP = new Label("Net Pay:");
    Label NPout = new Label(" ");
    Color green = new Color(100, 200, 100);

    public void init() {
        Container c = getContentPane();
        c.setLayout(null);
        c.setBackground(green);
        c.setForeground(Color.red);
        nameLabel.setSize(100, 20);
        nameLabel.setLocation(10, 10);
        c.add(nameLabel);
        nameField.setSize(100, 20);
        nameField.setLocation(130, 10);
        c.add(nameField);
        hoursLabel.setSize(100, 20);
        hoursLabel.setLocation(10, 50);
        c.add(hoursLabel);
        hoursField.setSize(100, 20);
        hoursField.setLocation(130, 50);
        c.add(hoursField);
        rateLabel.setSize(100, 20);
        rateLabel.setLocation(10, 90);
        c.add(rateLabel);
        F rateField
        .setSize(100, 20);
        rateField.setLocation(130, 90);
        c.add(rateField);
        msgLabel.setSize(150, 20);
        msgLabel.setLocation(10, 130);
        c.add(msgLabel);
        c.setForeground(Color.black);
        Calcbutton.setSize(130, 30);
        Calcbutton.setLocation(10, 160);
        c.add(Calcbutton);
        Calcbutton.addActionListener(this);
        GP.setSize(100, 20);
        GP.setLocation(40, 200);
        c.add(GP);
        GPout.setSize(100, 20);
        GPout.setLocation(140, 200);
        c.add(GPout);
        NP.setSize(100, 30);
        NP.setLocation(40, 250);
        c.add(NP);
        NPout.setSize(100, 20);
        NPout.setLocation(140, 250);
        c.add(NPout);
//add Components to Applet Window
    }

    public void actionPerformed(ActionEvent e) {
        if (nameField.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Enter Name", "Attention", JOptionPane.WARNING_MESSAGE);
        } else {
            if (hoursField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Enter hours", "Attention", JOptionPane.WARNING_MESSAGE);
            } else {
                hours = Integer.parseInt(hoursField.getText());
                if (hours <= 0) {
                    JOptionPane.showMessageDialog(null, "Hours must be Positive", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (rateField.getText().equals("")) {
                        JOptionPane.showMessageDialog(null, "Enter Rate", "Attention", JOptionPane.WARNING_MESSAGE);
                    } else {
                        rate = Float.parseFloat(rateField.getText());
                        if (rate < 7.50) {
                            JOptionPane.showMessageDialog(null, "Rate must be >= 7.50", "Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            grosspay = rate * hours;
                            netpay = .85 * grosspay;
                            GPout.setText(String.format("$%,.2f", grosspay));
                            NPout.setText(String.format("$%,.2f", netpay));
                        }
                    }
                }
            }
        }
    }
}
