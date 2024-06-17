import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Notes extends JFrame {
    private JTextPane textPane;
    private JCheckBox darkModeCheckBox;
    private JComboBox<String> fontSizeComboBox;
    private JComboBox<String> fontColorComboBox;
    private JComboBox<String> textAlignComboBox;
    private JButton saveButton;
    private JButton loadButton;

    public Notes() {
        setTitle("Notes App");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        darkModeCheckBox = new JCheckBox("Dark Mode");
        darkModeCheckBox.addActionListener(new DarkModeAction());
        controlPanel.add(darkModeCheckBox);

        fontSizeComboBox = new JComboBox<>(new String[]{"12", "14", "16", "18", "20", "24", "28", "32"});
        fontSizeComboBox.setSelectedIndex(0);
        fontSizeComboBox.addActionListener(new FontSizeAction());
        controlPanel.add(new JLabel("Font Size:"));
        controlPanel.add(fontSizeComboBox);

        panel.add(controlPanel, BorderLayout.NORTH);
        add(panel);

        setVisible(true);
    }

    private class DarkModeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (darkModeCheckBox.isSelected()) {
                textPane.setBackground(Color.BLACK);
                textPane.setForeground(Color.WHITE);
            } else {
                textPane.setBackground(Color.WHITE);
                textPane.setForeground(Color.BLACK);
            }
        }
    }

    private class FontSizeAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sizeStr = (String) fontSizeComboBox.getSelectedItem();
            int size = Integer.parseInt(sizeStr);
            SimpleAttributeSet attributeSet = new SimpleAttributeSet();
            StyleConstants.setFontSize(attributeSet, size);
            textPane.setCharacterAttributes(attributeSet, false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Notes::new);
    }
}
