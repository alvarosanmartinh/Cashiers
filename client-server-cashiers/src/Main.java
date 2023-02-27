import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;

public class Main {

    public static SessionData sessionData;
    public static void main(String[] args) throws IOException {
        ClientLogin clientLogin = new ClientLogin();
        clientLogin.setContentPane(clientLogin.mainPane);
        clientLogin.setTitle("Login cajeros");
        clientLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientLogin.setSize(900, 500);
        clientLogin.setVisible(true);
        clientLogin.ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.sessionData = new Main.SessionData(
                        clientLogin.txtCashierIdentifier.getText(),
                        clientLogin.txtCashierName.getText(),
                        (Integer) clientLogin.sprOpenningAmount.getValue(),
//                        TODO: Enable user's input date
//                        LocalDateTime.parse(
//                                txtOpenningDate.getText(),
//                                DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm")
//                        )
                        LocalDateTime.now(),
                        (Integer) clientLogin.sprOpenningAmount.getValue()
                );
                clientLogin.setVisible(false);

                try {
                    SocketHandler cli = new SocketHandler();
                    cli.startConnection();
                    ClientCashier clientCashier = new ClientCashier(sessionData, cli);
                    clientCashier.setContentPane(clientCashier.mainPanel);
                    clientCashier.setTitle("Cajero");
                    clientCashier.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    clientCashier.setSize(900, 500);
                    clientCashier.setVisible(true);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        });

    }

    public static class SessionData {
        private String cashierIdentifier;
        private String cashierName;
        private int openningAmount;
        private LocalDateTime openningDate;
        private int actualAmount;

        public SessionData(String cashierIdentifier, String cashierName, int openningAmount, LocalDateTime openningDate,
                           int actualAmount) {
            this.cashierIdentifier = cashierIdentifier;
            this.cashierName = cashierName;
            this.openningAmount = openningAmount;
            this.openningDate = openningDate;
            this.actualAmount = actualAmount;
        }

        public String getCashierIdentifier() {
            return cashierIdentifier;
        }

        public void setCashierIdentifier(String cashierIdentifier) {
            this.cashierIdentifier = cashierIdentifier;
        }

        public String getCashierName() {
            return cashierName;
        }

        public void setCashierName(String cashierName) {
            this.cashierName = cashierName;
        }

        public int getOpenningAmount() {
            return openningAmount;
        }

        public void setOpenningAmount(int openningAmount) {
            this.openningAmount = openningAmount;
        }

        public LocalDateTime getOpenningDate() {
            return openningDate;
        }

        public void setOpenningDate(LocalDateTime openningDate) {
            this.openningDate = openningDate;
        }

        public int getActualAmount() {
            return actualAmount;
        }

        public void setActualAmount(int actualAmount) {
            this.actualAmount = actualAmount;
        }
    }
}