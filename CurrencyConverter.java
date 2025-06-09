import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class CurrencyConverter extends JFrame implements ActionListener {
    private JComboBox<String> fromCurrency, toCurrency;
    private JTextField amountField, resultField;
    private JButton convertButton;

    private final HashMap<String, Double> rates = new HashMap<>();

    public CurrencyConverter() {
        setTitle("Currency Converter");
        setSize(400, 250);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Example static rates (can be replaced with live data)
        rates.put("INR", 1.0);        // Base currency
        rates.put("USD", 0.012);
        rates.put("EUR", 0.011);
        rates.put("JPY", 1.67);
        rates.put("GBP", 0.0095);

        JLabel fromLabel = new JLabel("From:");
        JLabel toLabel = new JLabel("To:");
        JLabel amountLabel = new JLabel("Amount:");
        JLabel resultLabel = new JLabel("Result:");

        fromCurrency = new JComboBox<>(rates.keySet().toArray(new String[0]));
        toCurrency = new JComboBox<>(rates.keySet().toArray(new String[0]));

        amountField = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        add(fromLabel);
        add(fromCurrency);
        add(toLabel);
        add(toCurrency);
        add(amountLabel);
        add(amountField);
        add(resultLabel);
        add(resultField);
        add(new JLabel());
        add(convertButton);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = fromCurrency.getSelectedItem().toString();
            String to = toCurrency.getSelectedItem().toString();

            double rate = rates.get(to) / rates.get(from);
            double result = amount * rate;

            resultField.setText(String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
