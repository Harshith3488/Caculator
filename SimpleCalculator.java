import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleCalculator extends JFrame implements ActionListener {
    JTextField input;
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    public SimpleCalculator() {
        setTitle("Simple Calculator");
        setSize(300, 400);
        setLocationRelativeTo(null); // center window
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        input = new JTextField();
        input.setFont(new Font("Arial", Font.BOLD, 24));
        input.setHorizontalAlignment(SwingConstants.RIGHT);
        input.setEditable(false);
        add(input, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 20));
            btn.addActionListener(this);
            panel.add(btn);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.charAt(0) >= '0' && cmd.charAt(0) <= '9') {
            input.setText(input.getText() + cmd);
        } else if (cmd.equals("C")) {
            input.setText("");
            num1 = num2 = result = 0;
        } else if (cmd.equals("=")) {
            num2 = Double.parseDouble(input.getText());
            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/': 
                    if (num2 == 0) {
                        input.setText("Error");
                        return;
                    } else {
                        result = num1 / num2;
                    }
                    break;
            }
            input.setText(String.valueOf(result));
        } else {
            if (!input.getText().isEmpty()) {
                num1 = Double.parseDouble(input.getText());
                operator = cmd.charAt(0);
                input.setText("");
            }
        }
    }

    public static void main(String[] args) {
        new SimpleCalculator();
    }
}
