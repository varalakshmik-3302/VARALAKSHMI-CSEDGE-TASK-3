import java.awt.*;
import java.awt.event.*;

public class Calculator extends Frame implements ActionListener {

    // Components for the calculator
    private TextField textField;
    private Button[] buttons;
    private String[] buttonLabels = {
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "C", "0", "=", "/"
    };

    // Variables to hold the operand and operator
    private double operand1, operand2;
    private char operator;

    public Calculator() {
        // Set layout for the frame
        setLayout(new BorderLayout());

        // Text field for displaying input and output
        textField = new TextField();
        textField.setEditable(false);
        add(textField, BorderLayout.NORTH);

        // Panel for holding buttons
        Panel buttonPanel = new Panel();
        buttonPanel.setLayout(new GridLayout(4, 4));

        // Initialize buttons and add them to panel
        buttons = new Button[buttonLabels.length];
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new Button(buttonLabels[i]);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        // Add panel to the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Frame settings
        setTitle("AWT Calculator");
        setSize(300, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);

        // Initialize operand and operator
        operand1 = 0;
        operand2 = 0;
        operator = ' ';
    }

    // ActionListener implementation for button clicks
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "+":
            case "-":
            case "*":
            case "/":
                operator = command.charAt(0);
                operand1 = Double.parseDouble(textField.getText());
                textField.setText("");
                break;
            case "=":
                operand2 = Double.parseDouble(textField.getText());
                double result = calculateResult();
                textField.setText(String.valueOf(result));
                break;
            case "C":
                operand1 = 0;
                operand2 = 0;
                operator = ' ';
                textField.setText("");
                break;
            default:
                textField.setText(textField.getText() + command);
                break;
        }
    }

    // Method to perform the calculation based on the operator
    private double calculateResult() {
        double result = 0;
        try {
            switch (operator) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '/':
                    if (operand2 == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    result = operand1 / operand2;
                    break;
            }
        } catch (ArithmeticException e) {
            textField.setText("Error: " + e.getMessage());
        }
        return result;
    }

    public static void main(String[] args) {
        new Calculator();
    }
}