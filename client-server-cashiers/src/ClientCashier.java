import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class ClientCashier extends JFrame{
    public Main.SessionData sessionData;
    public SocketHandler socketHandler;
    public JPanel mainPanel;
    private JTextField txtFechaMontoApertura;
    private JTextField txtMontoCaja;
    private JButton btnCerrarCaja;
    private JButton nuevaVentaButton;
    private JTextField txtSaleNumber;
    private JComboBox comboBoxProducts;
    private JSpinner spinnerQuantity;
    private JTextField txtTotal;
    private JButton agregarButton;
    private JTable tableDetalle;
    private JButton concretarVentaButton;
    private JButton anularVentaButton;
    private JTextField txtTotalVenta;
    private JPanel panTotal;
    private JPanel panFooter;
    private JPanel panTable;
    private JPanel panProductAdder;
    private JPanel panNewSale;
    private JPanel panHeader;

    public ClientCashier(Main.SessionData sessionData, SocketHandler socketHandler) throws HeadlessException, IOException {
        this.sessionData = sessionData;
        this.socketHandler = socketHandler;
        txtFechaMontoApertura.setText(sessionData.getOpenningDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")) + " - $" + sessionData.getOpenningAmount());
        txtMontoCaja.setText(String.valueOf(sessionData.getOpenningAmount()));

        //TODO Populate product combobox with socket connection

        nuevaVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Use database sequence to get sale number
            }
        });
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Add selected product, quantity and total amount to tableDetalle
            }
        });
        anularVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO Use socket to communicate that sale number won't be used
            }
        });
        concretarVentaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO send JSON with sale details, update sessionData
            }
        });
        btnCerrarCaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    socketHandler.stopConnection();
                    //TODO send JSON with sessionData
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }
}
