import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class AjoutReservationFenetre extends JFrame {
    private JTextField idField, nomField, prenomField, dateNaissanceField, numeroTelephoneField;
    private JComboBox<Voyage> voyageComboBox;
    private JButton reserverButton;

    public AjoutReservationFenetre() {
        setTitle("Ajouter une Réservation");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));
        
        
        
        add(new JLabel("Nom:"));
        nomField = new JTextField();
        add(nomField);
        
        add(new JLabel("Prénom:"));
        prenomField = new JTextField();
        add(prenomField);
        
        add(new JLabel("Date de Naissance (YYYY-MM-DD):"));
        dateNaissanceField = new JTextField();
        add(dateNaissanceField);
        
        add(new JLabel("Numéro de Téléphone:"));
        numeroTelephoneField = new JTextField();
        add(numeroTelephoneField);
        
        // Liste déroulante des voyages
        add(new JLabel("Voyage:"));
        VoyageDAO reservationDAO = new VoyageDAO();
        Voyage[] voyages = reservationDAO.listVoyage();
        voyageComboBox = new JComboBox<>(voyages);
        add(voyageComboBox);
        
        // Bouton de réservation
        reserverButton = new JButton("Ajouter");
        add(reserverButton);
        
        reserverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creerReservation();
            }
        });
        
        setVisible(true);
    }
    
    private void creerReservation() {
        ClientDAO clientDAO = new ClientDAO();
        int nbClients = clientDAO.getNombreClients();
        String id = "" + (nbClients + 1);
        ReservationDAO reservationDAO = new ReservationDAO();
        int nbReservations = reservationDAO.getNombreReservations();

        //String id = idField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        LocalDate dateNaissance = LocalDate.parse(dateNaissanceField.getText());
        String numeroTelephone = numeroTelephoneField.getText();
        Voyage voyageSelectionne = (Voyage) voyageComboBox.getSelectedItem();
        
        Client client = new Client(id, nom, prenom, dateNaissance, numeroTelephone);
        clientDAO.ajouter(client);
        reservationDAO.ajouter(new Reservation("" + (nbReservations + 1), LocalDate.now(), "1000", id, "12",
                voyageSelectionne.getId()));
        
        JOptionPane.showMessageDialog(this, "Réservation effectuée pour " + client.getNom() + " au voyage " + voyageSelectionne);
    }


    public static void main(String[] args) {
         
        SwingUtilities.invokeLater(() -> new AjoutReservationFenetre());
    }
}
