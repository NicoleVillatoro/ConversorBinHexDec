import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class PRUEBA extends JFrame {
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton convertButton;
    private JButton copyButton;
    private JCheckBox invertCheckBox;

    public PRUEBA() {
        setTitle("Conversor Binario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        inputTextArea = new JTextArea(3, 20);
        outputTextArea = new JTextArea(6, 20);
        outputTextArea.setEditable(false);
        convertButton = new JButton("Convertir");
        copyButton = new JButton("Copiar Resultado");
        invertCheckBox = new JCheckBox("Invertir cadena");

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertirBinario();
            }
        });

        copyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiarResultado();
            }
        });

        add(new JLabel("Introduce una cadena de dígitos binarios:"));
        add(inputTextArea);
        add(invertCheckBox);
        add(convertButton);
        add(copyButton);
        add(new JLabel("Resultados (binarios invertidos, hexadecimal, decimal):"));
        add(new JScrollPane(outputTextArea));

        pack();
        setLocationRelativeTo(null);
    }

    private void convertirBinario() {
        String inputText = inputTextArea.getText();
        String[] binaryLines = inputText.split("\n");
        StringBuilder outputText = new StringBuilder();

        for (String binaryLine : binaryLines) {
            String[] binaryDigits = binaryLine.split("\\s+");
            StringBuilder invertedBinary = new StringBuilder();

            for (String binaryDigit : binaryDigits) {
                try {
                    String binaryToConvert = invertCheckBox.isSelected() ? new StringBuilder(binaryDigit).reverse().toString() : binaryDigit;
                    int decimalValue = Integer.parseInt(binaryToConvert, 2);
                    String hexValue = Integer.toHexString(decimalValue).toUpperCase();
                outputText.append("\n");
                outputText.append("").append(binaryDigit).append("");
 //               outputText.append("Binario invertido: ").append(binaryToConvert).append("\n");
 //                   outputText.append("Hexadecimal: ").append(hexValue).append("\n");
 //                   outputText.append("Decimal: ").append(decimalValue).append("\n");
                    invertedBinary.append(binaryToConvert);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Ingresa dígitos binarios válidos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            outputText.insert(0, "Binarios invertidos: " + invertedBinary.toString() + "\n\n");
        }

        outputTextArea.setText(outputText.toString());
    }

    private void copiarResultado() {
        String resultText = outputTextArea.getText();
        StringSelection selection = new StringSelection(resultText);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        JOptionPane.showMessageDialog(this, "Resultado copiado al portapapeles.", "Copia Exitosa", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PRUEBA().setVisible(true);
        });
    }
}
