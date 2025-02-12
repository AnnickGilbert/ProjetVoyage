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

public class VoyageForm extends JFrame {
    
    private JTextField dateDepartField;
    private JTextField dateArriveeField;
    private JTextField villeDepartField;
    private JTextField villeArriveeField;
    private JTextField heureDepartField;
    private JTextField heureArriveeField;
    private JComboBox<String> transportComboBox;

    private JButton saveButton;

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
        //fait JComboBox

        JComboBox<String> villeDepartComboBox = new JComboBox<>(villes);
        JComboBox<String> villeArriveeComboBox = new JComboBox<>(villes);
        
        JLabel villeDepartLabel = new JLabel("Ville de départ :");
        villeDepartField = new JTextField();
        villeDepartField.setMaximumSize(new Dimension(Integer.MAX_VALUE, villeDepartField.getPreferredSize().height));

        JLabel villeArriveeLabel = new JLabel("Ville d'arrivée :");
        villeArriveeField = new JTextField();
        villeArriveeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, villeArriveeField.getPreferredSize().height));




        JLabel heureDepartLabel = new JLabel("Heure de départ (HH:MM:SS) :");
        heureDepartField = new JTextField();
        heureDepartField.setMaximumSize(new Dimension(Integer.MAX_VALUE, heureDepartField.getPreferredSize().height));

        JLabel heureArriveeLabel = new JLabel("Heure d'arrivée (HH:MM:SS) :");
        heureArriveeField = new JTextField();
        heureArriveeField.setMaximumSize(new Dimension(Integer.MAX_VALUE, heureArriveeField.getPreferredSize().height));

        
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
        //add(villeDepartField);
        villeDepartComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, villeDepartComboBox.getPreferredSize().height));
        add(villeDepartComboBox);

        add(villeArriveeLabel);
        villeArriveeComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, villeArriveeComboBox.getPreferredSize().height));
        add(villeArriveeComboBox);

        //add(villeArriveeField);
        add(heureDepartLabel);
        add(heureDepartField);
        add(heureArriveeLabel);
        add(heureArriveeField);
        add(transportLabel);
        //resize the combobox
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

    private void saveVoyage() {
        try {
           
            LocalDate dateDepart = LocalDate.parse(dateDepartField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate dateArrivee = LocalDate.parse(dateArriveeField.getText(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            //String villeDepartId = villeDepartField.getText();
            //String villeArriveeId = villeArriveeField.getText();
            String heureDepart = heureArriveeField.getText();
            String heureArrivee = heureArriveeField.getText();
            String moyenTransportId = (String) transportComboBox.getSelectedItem();
            //get index of the selected item
            int index = transportComboBox.getSelectedIndex();
            String villeSelected = (String) villeDepartField.getText();
            //convert villeSelected to villeId
            VoyageDAO voyageDAO = new VoyageDAO();

            String[] villes = voyageDAO.listVille();
            //print villes
            for (int i = 0; i < villes.length; i++) {
                System.out.println(villes[i]);
            }
            String villeDepart_Id = "";
            String villeArrivee_Id = "";
            for (int i = 0; i < villes.length; i++) {
                if (villes[i].equals(villeSelected)) {
                    villeDepart_Id = VoyageDAO.getIdByName(villeSelected);
                }
            }
            
            villeSelected = (String) villeArriveeField.getText();

            for (int i = 0; i < villes.length; i++) {
                if (villes[i].equals(villeSelected)) {
                    villeArrivee_Id = VoyageDAO.getIdByName(villeSelected);
                }
            }


            
            

            //convert index to moyenTransportId
            switch(index){
                case 0:
                    moyenTransportId = "1";
                    break;
                case 1:
                    moyenTransportId = "2";
                    break;
                case 2:
                    moyenTransportId = "3";
                    break;
            }

            // Envoi des données à la base de données via DAO
            //VoyageDAO voyageDAO = new VoyageDAO();
            int countVoyages = voyageDAO.countVoyage();
            //convertir countVoyages en String
            String countVoyage = Integer.toString(countVoyages+1);
            
            Voyage v = new Voyage(countVoyage, dateDepart, dateArrivee, heureDepart, heureArrivee,villeDepart_Id, villeArrivee_Id,
                    moyenTransportId);

            voyageDAO.ajouter(v);
            JOptionPane.showMessageDialog(this, "Voyage enregistré avec succès !");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors de l'enregistrement : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new VoyageForm();
    }
}
