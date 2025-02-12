import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Cette classe représente un formulaire pour ajouter un voyage.
 * Elle permet à l'utilisateur de saisir les informations du voyage 
 * telles que la date de départ, la date d'arrivée, les villes de départ et d'arrivée,
 * l'heure de départ et d'arrivée, ainsi que le moyen de transport.
 */
public class VoyageForm extends JFrame {

    private JTextField dateDepartField;
    private JTextField dateArriveeField;
    private JTextField villeDepartField;
    private JTextField villeArriveeField;
    private JTextField heureDepartField;
    private JTextField heureArriveeField;
    private JComboBox<String> transportComboBox;
    private JButton saveButton;

    /**
     * Constructeur de la classe VoyageForm.
     * Initialise l'interface utilisateur en configurant les composants nécessaires
     * comme les champs de texte, les labels, les boutons et les combobox pour les villes et moyens de transport.
     */
    public VoyageForm() {
        setTitle("Ajouter un Voyage");
        setSize(400, 500); // Augmenter la taille de la fenêtre pour plus d'espace
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Utilisation d'un BoxLayout pour organiser les composants verticalement
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setResizable(false);

        // Création des composants
        JLabel dateDepartLabel = new JLabel("Date de départ (YYYY-MM-DD) :");
        dateDepartField = new JTextField();
        dateDepartField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dateDepartField.getPreferredSize().height));

        JLabel dateArriveeLabel = new JLabel("Date d'arrivée (YYYY-MM-DD) :");
        dateArriveeField = new JTextField();
        dateArriveeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, dateArriveeField.getPreferredSize().height));
        VoyageDAO voyageDAO = new VoyageDAO();
        String[] villes = voyageDAO.listVille();

        // Créer des JComboBox pour la sélection des villes de départ et d'arrivée
        JComboBox<String> villeDepartComboBox = new JComboBox<>(villes);
        JComboBox<String> villeArriveeComboBox = new JComboBox<>(villes);

        JLabel villeDepartLabel = new JLabel("Ville de départ :");
        villeDepartField = new JTextField();
        villeDepartField.setMaximumSize(new Dimension(Integer.MAX_VALUE, villeDepartField.getPreferredSize().height));

        JLabel villeArriveeLabel = new JLabel("Ville d'arrivée :");
        villeArriveeField = new JTextField();
        villeArriveeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, villeArriveeField.getPreferredSize().height));

        // Champs pour l'heure de départ et d'arrivée
        JLabel heureDepartLabel = new JLabel("Heure de départ (HH:MM:SS) :");
        heureDepartField = new JTextField();
        heureDepartField.setMaximumSize(new Dimension(Integer.MAX_VALUE, heureDepartField.getPreferredSize().height));

        JLabel heureArriveeLabel = new JLabel("Heure d'arrivée (HH:MM:SS) :");
        heureArriveeField = new JTextField();
        heureArriveeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, heureArriveeField.getPreferredSize().height));

        // JComboBox pour sélectionner le moyen de transport
        JLabel transportLabel = new JLabel("Moyen de transport :");
        String[] transportOptions = {"Avion","Train","Voiture"};
        transportComboBox = new JComboBox<>(transportOptions);

        // Bouton pour enregistrer les informations
        saveButton = new JButton("Enregistrer");

        // Ajouter les composants à la fenêtre
        add(dateDepartLabel);
        add(dateDepartField);
        add(dateArriveeLabel);
        add(dateArriveeField);
        add(villeDepartLabel);
        villeDepartComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, villeDepartComboBox.getPreferredSize().height));
        add(villeDepartComboBox);

        add(villeArriveeLabel);
        villeArriveeComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, villeArriveeComboBox.getPreferredSize().height));
        add(villeArriveeComboBox);

        add(heureDepartLabel);
        add(heureDepartField);
        add(heureArriveeLabel);
        add(heureArriveeField);
        add(transportLabel);
        transportComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, transportComboBox.getPreferredSize().height));
        add(transportComboBox);

        add(Box.createRigidArea(new Dimension(0, 10))); // Espacement vertical
        add(saveButton);

        // Action sur le bouton "Enregistrer"
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveVoyage();
            }
        });

        setVisible(true);
    }

    /**
     * Méthode permettant d'enregistrer un voyage dans la base de données.
     * Cette méthode récupère les informations saisies dans le formulaire,
     * effectue les conversions nécessaires, et envoie les données à la base de données via un DAO.
     */
    private void saveVoyage() {
        try {
            // Récupération des dates de départ et d'arrivée
            LocalDate dateDepart = LocalDate.parse(dateDepartField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dateArrivee = LocalDate.parse(dateArriveeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            
            // Récupération des heures de départ et d'arrivée
            String heureDepart = heureArriveeField.getText();
            String heureArrivee = heureArriveeField.getText();
            
            // Récupération du moyen de transport
            String moyenTransportId = (String) transportComboBox.getSelectedItem();
            int index = transportComboBox.getSelectedIndex();
            String villeDepartSelected = (String) villeDepartField.getText();
            String villeArriveeSelected = (String) villeArriveeField.getText();
            
            // Recherche des ID de ville en fonction des noms sélectionnés
            VoyageDAO voyageDAO = new VoyageDAO();
            String[] villes = voyageDAO.listVille();
            String villeDepart_Id = VoyageDAO.getIdByName(villeDepartSelected);
            String villeArrivee_Id = VoyageDAO.getIdByName(villeArriveeSelected);
            
            // Conversion de l'index du moyen de transport en ID
            switch(index){
                case 0: moyenTransportId = "1"; break;
                case 1: moyenTransportId = "2"; break;
                case 2: moyenTransportId = "3"; break;
            }

            // Comptage du nombre de voyages existants et création d'un nouvel objet Voyage
            int countVoyages = voyageDAO.countVoyage();
            String countVoyage = Integer.toString(countVoyages+1);
            Voyage v = new Voyage(countVoyage, dateDepart, dateArrivee, heureDepart, heureArrivee, villeDepart_Id, villeArrivee_Id, moyenTransportId);

            // Envoi de l'objet Voyage à la base de données via le DAO
            voyageDAO.ajouter(v);
            JOptionPane.showMessageDialog(this, "Voyage enregistré avec succès !");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Méthode principale pour lancer l'application.
     * Crée une instance de la fenêtre VoyageForm.
     */
    public static void main(String[] args) {
        new VoyageForm();
    }
}
