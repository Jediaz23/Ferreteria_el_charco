package Vistas;

import javax.swing.*;

public class ProveedoresGUI extends JPanel {
    public JPanel mainPanel;
    private JTable table1;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JButton editarButton;
    private JTextField textField1;
    private JTextField textField2;

    public ProveedoresGUI() {

        add(mainPanel);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
