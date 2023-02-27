import repository.ActivityRepository;
import repository.ProductRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ServerApplication extends JFrame {

    private JTextArea logTextArea;
    private JTable productsTable;
    private JButton modificarButton;
    private JButton agregarButton;
    private JButton eliminarButton;
    private JTextField txtCashierIdentifier;
    private JButton consultarButton;
    private JTable cashierTable;
    private JButton cerrarServidorButton;
    private JPanel panelMain;
    private JPanel tablePane;
    protected ServerSocket serverSocket;
    protected ProductRepository productRepository;
    protected ActivityRepository activityRepository;

    protected boolean running;

    public ServerApplication() {
        productRepository = new ProductRepository();
        activityRepository = new ActivityRepository();
        productsTable.setModel(buildProductsTableModel(productRepository));
        cashierTable.setModel(buildCashiersActivityTableModel(activityRepository, ""));

        // Listeners
        cerrarServidorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    serverSocket.close();
                    running = false;
                    logTextArea.append("Servidor cerrado");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO: Add Rut validator
                cashierTable.setModel(buildCashiersActivityTableModel(activityRepository, txtCashierIdentifier.getText()));
            }
        });
    }

    public static void main(String[] args) throws IOException {
        ServerApplication server = new ServerApplication();
        server.setContentPane(server.panelMain);
        server.setTitle("Servidor cajeros");
        server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        server.setSize(900, 500);
        server.setVisible(true);
        server.start();
    }

    public static DefaultTableModel buildProductsTableModel(ProductRepository productRepository) {
        // TODO: Add checkboxes to interact with data
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("id");
        columnNames.add("Nombre");
        columnNames.add("Precio");
        columnNames.add("Stock");
        columnNames.add("St. Res.");

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        productRepository.selectAll().forEach(product -> {
            Vector<Object> vector = new Vector<Object>();
            vector.add(product.getId());
            vector.add(product.getName());
            vector.add(product.getPrice());
            vector.add(product.getStock());
            vector.add(product.getReserved_stock());
            data.add(vector);
        });

        return new DefaultTableModel(data, columnNames);
    }

    public static DefaultTableModel buildCashiersActivityTableModel(ActivityRepository activityRepository, String cashier) {
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Fecha");
        columnNames.add("Actividad");

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        activityRepository.selectAllByCashier(cashier).forEach(activity -> {
            Vector<Object> vector = new Vector<Object>();
            // TODO: use MovementDictionary
            vector.add(activity.getDate());
            vector.add(activity.getMovement() + activity.getDetail());
            data.add(vector);
        });

        return new DefaultTableModel(data, columnNames);
    }


    /* Socket methods */
    public void start() throws IOException {
        serverSocket = new ServerSocket(1998);
        running = true;
        while (running)
            new ClientHandler(serverSocket.accept()).start();
    }

    protected class ClientHandler extends Thread {

        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String inputLine;

                //TODO: Add JSON Parser
                while ((inputLine = in.readLine()) != null) {
                    if (".".equals(inputLine)) {
                        break;
                    }
                    logTextArea.append(inputLine);
                }

                in.close();
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Exception catched on client handler: " + e.getMessage());
            }
        }

    }
}
