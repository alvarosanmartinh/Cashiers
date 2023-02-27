import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ClientLogin extends JFrame{
    public JTextField txtCashierIdentifier;
    public JTextField txtCashierName;
    public JButton ingresarButton;
    private JButton salirButton;
    public JPanel mainPane;
    public JFormattedTextField txtOpenningDate;
    public JSpinner sprOpenningAmount;

    public ClientLogin() {
        txtOpenningDate.setValue(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")));

    }
}
