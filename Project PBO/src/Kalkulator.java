import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Kalkulator extends JFrame {

    private JTextField textField;
    private double bil1, bil2, hasil;
    private char operator;

    public Kalkulator() {
        setTitle("Kalkulator Aka Akmal");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create components
        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);

        // Set font to increase space between characters
        Font textFieldFont = new Font("Arial", Font.PLAIN, 20);
        textField.setFont(textFieldFont);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4)); // Increased rows for "C" button

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C" // clear button
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        textField.setBackground(new Color(255, 255, 204)); // Light Yellow
        buttonPanel.setBackground(new Color(153, 204, 255)); // Light Blue

        for (Component component : buttonPanel.getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                button.setBackground(new Color(255, 204, 204)); // Light Red
                button.setForeground(Color.BLACK);
            }
        }

        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String buttonText = source.getText();

            switch (buttonText) {
                case "=":
                    bil2 = Double.parseDouble(textField.getText());
                    calculate();
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    operator = buttonText.charAt(0);
                    bil1 = Double.parseDouble(textField.getText());
                    textField.setText("");
                    break;
                case "C":
                    clear();
                    break;
                default:
                    textField.setText(textField.getText() + buttonText);
                    break;
            }
        }

        private void calculate() {
            switch (operator) {
                case '+':
                    hasil = bil1 + bil2;
                    break;
                case '-':
                    hasil = bil1 - bil2;
                    break;
                case '*':
                    hasil = bil1 * bil2;
                    break;
                case '/':
                    if (bil2 != 0) {
                        hasil = bil1 / bil2;
                    } else {
                        JOptionPane.showMessageDialog(null, "Cannot divide by zero!", "Error", JOptionPane.ERROR_MESSAGE);
                        clear();
                        return;
                    }
                    break;
            }

            textField.setText(String.valueOf(hasil));
            bil1 = hasil;
            operator = '\u0000'; // Reset operator
        }

        private void clear() {
            textField.setText("");
            bil1 = 0;
            bil2 = 0;
            hasil = 0;
            operator = '\u0000';
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Kalkulator());
    }
}
