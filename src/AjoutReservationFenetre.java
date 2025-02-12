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

/**
 * Fenêtre graphique pour ajouter une réservation de voyage.
 * 
 * <p>
 * Cette classe crée une interface utilisateur permettant de saisir les informations
 * d'un client (nom, prénom, date de naissance et numéro de téléphone) et de sélectionner
 * un voyage dans une liste déroulante. Lorsque l'utilisateur clique sur le bouton "Ajouter",
 * une réservation est créée en ajoutant le client et la réservation via les classes DAO
 * correspondantes (ClientDAO, ReservationDAO et VoyageDAO).
 * </p>
 * 
 * @author tran
 * @version 1.0
 */
public class AjoutReservationFenetre extends JFrame {
    private JTextField idField, nomField, prenomField, dateNaissanceField, numeroTelephoneField;
    private JComboBox<Voyage> voyageComboBox;
    private JButton reserverButton;

    /**
     * Construit une nouvelle fenêtre pour l'ajout d'une réservation.
     *
     * <p>
     * La fenêtre est initialisée avec un titre, une taille de 600x600 pixels et un layout en grille.
     * Les différents champs de saisie (nom, prénom, date de naissance, numéro de téléphone) et la liste
     * déroulante pour le choix d'un voyage sont ajoutés, ainsi que le bouton "Ajouter" pour confirmer
     * la réservation. Un ActionListener est associé au bouton afin de déclencher la méthode
     * {@link #creerReservation()} lors d'un clic.
     * </p>
     */
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
    
    /**
     * Crée une réservation en utilisant les informations saisies dans l'interface.
     *
     * <p>
     * Cette méthode récupère les données entrées par l'utilisateur (nom, prénom, date de naissance
     * et numéro de téléphone) ainsi que le voyage sélectionné dans la liste déroulante. Un identifiant
     * unique est généré pour le client et la réservation en se basant sur le nombre actuel d'enregistrements.
     * Un objet {@code Client} est créé et ajouté via {@link ClientDAO}, puis une réservation est
     * enregistrée via {@link ReservationDAO}. Enfin, un message de confirmation est affiché.
     * </p>
     *
     * @throws java.time.format.DateTimeParseException si la date de naissance n'est pas au format "YYYY-MM-DD"
     */
    private void creerReservation() {
        ClientDAO clientDAO = new ClientDAO();
        int nbClients = clientDAO.getNombreClients();
        String id = "" + (nbClients + 1);
        ReservationDAO reservationDAO = new ReservationDAO();
        int nbReservations = reservationDAO.getNombreReservations();

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

    /**
     * Point d'entrée de l'application.
     *
     * <p>
     * Lance l'interface graphique de l'ajout de réservation en créant une instance de
     * {@code AjoutReservationFenetre} sur le thread d'exécution de l'interface graphique via
     * {@link SwingUtilities#invokeLater(Runnable)}.
     * </p>
     *
     * @param args les arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AjoutReservationFenetre());
    }
}
