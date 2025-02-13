import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
/**
 * Interface principale de l'application de gestion des voyages.
 * Cette classe fournit une interface graphique permettant de visualiser, ajouter
 * et supprimer des voyages dans une table interactive.
 * 
 * @author tran
 * @version 1.0
 */
public class InterfacePrincipal1 extends JFrame implements ActionListener {
    //ajouter javadoc

    private JPanel containerPanel;
    private JPanel contentPanel;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JButton button;
    private List<VoyageInformation> voyageInformations;

    /* Boutons pour ajouter un voyage, une réservation ou un moyen de transport */
    private JButton buttonAjouterVoyage;
    private JButton buttonAjouterReservation;
    private JButton buttonAjouterMoyenTransport;

    /**
     * Constructeur de la classe {@code InterfacePrincipal}.
     * 
     * <p>
     * Ce constructeur initialise la fenêtre principale de l'application et crée
     * les composants graphiques nécessaires pour afficher les voyages dans une table.
     * </p>
     */
    public InterfacePrincipal1() {
        this.setTitle("Liste des voyages");
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // Création du panneau avec image de fond
        containerPanel = new BackgroundPanel("src\\image_c.png");
        containerPanel.setLayout(null); // Permet le placement personnalisé

        // Création d'un panneau transparent pour les composants
        contentPanel = new JPanel();
        contentPanel.setOpaque(false); // Rendre le panneau transparent
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBounds(20, 20, 750, 500); // Ajuster la position et la taille

        // Ajouter les boutons
        JPanel panelBouttons = new JPanel();
        panelBouttons.setOpaque(false); // Rendre le panneau transparent
        buttonAjouterVoyage = new JButton("Ajouter un voyage");
        buttonAjouterReservation = new JButton("Ajouter une réservation");
        buttonAjouterMoyenTransport = new JButton("Ajouter un moyen de transport");
        panelBouttons.add(buttonAjouterVoyage);
        panelBouttons.add(buttonAjouterReservation);
        panelBouttons.add(buttonAjouterMoyenTransport);
        // Action sur le bouton "Ajouter un voyage"
        buttonAjouterVoyage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre pour ajouter un voyage
                new VoyageForm();
            }
        });

        // Action sur le bouton "Ajouter une réservation"
        buttonAjouterReservation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir la fenêtre pour ajouter une réservation
                new AjoutReservationFenetre();
            }
        });

        contentPanel.add(panelBouttons);

        // Création de la table
        model = new DefaultTableModel();
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make only the delete button column editable
                return column == getColumnCount() - 1;
            }
        };

        model.addColumn("Ville de départ");
        model.addColumn("Ville d'arrivée");
        model.addColumn("<html>Date de départ<br>Date d'arrivée</html>");
        model.addColumn("<html>Heure de départ<br>Heure d'arrivée</html>");
        model.addColumn("Transport");
        model.addColumn("Prix");
        model.addColumn("Modifier");
        model.addColumn("Supprimer");


        table = new JTable(model);
        // Set up the delete button column
        TableColumn deleteColumn = table.getColumnModel().getColumn(7);
        deleteColumn.setCellRenderer(new ButtonRenderer());
        deleteColumn.setCellEditor(new ButtonEditor(new JCheckBox()));
        // Set up the modify button column
       TableColumn modifyColumn = table.getColumnModel().getColumn(6);  // Nouvelle colonne "Modifier"
        modifyColumn.setCellRenderer(new ButtonRendererModifier());
        modifyColumn.setCellEditor(new ButtonEditorModifier(new JCheckBox()));

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // 🔹 Définir la police en gras et en noir
                cell.setFont(new Font("Arial", Font.BOLD, 14)); // Texte en gras et taille 14
                cell.setForeground(Color.BLACK); // Texte en noir

                return cell;
            }
        });
        table.setOpaque(false);
        table.setBackground(new Color(0, 0, 0, 0)); // Fond totalement transparent
        table.setShowGrid(true); // Désactiver la grille
        table.setIntercellSpacing(new Dimension(0, 0)); // Supprimer les espaces entre cellules

        table.getTableHeader().setPreferredSize(new Dimension(table.getTableHeader().getPreferredSize().width, 50)); // Modifier la hauteur ici

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12)); // Changer la police et la taille
        table.getTableHeader().setForeground(Color.BLUE); // Modifier la couleur du texte
        table.getTableHeader().setBackground(new Color(255, 255, 255, 180)); // Modifier la couleur de fond
                /* Ajouter scrollPane pour la table */

        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700, 300));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null); // Supprimer la bordure

        contentPanel.add(scrollPane);

        // Ajouter le bouton d'affichage
        button = new JButton("Afficher les voyages");
        button.addActionListener(this);
        contentPanel.add(button);

        // Ajouter le panneau transparent au panneau de fond
        containerPanel.add(contentPanel);

        this.setContentPane(containerPanel);
    }
    
    /**
     * Classe interne pour le rendu des boutons de suppression dans la table.
     * Personnalise l'apparence des boutons de suppression dans chaque ligne.
     */
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        /**
         * Configure l'apparence du bouton pour chaque cellule.
         * 
         * @param table      La table contenant le bouton
         * @param value      La valeur à afficher
         * @param isSelected Indique si la cellule est sélectionnée
         * @param hasFocus   Indique si la cellule a le focus
         * @param row        L'index de la ligne
         * @param column     L'index de la colonne
         * @return Le composant configuré pour l'affichage
         */
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Supprimer");
            setBackground(new Color(255, 99, 71)); // Red-ish color
            setForeground(Color.WHITE);
            return this;
        }

    }
    
    class ButtonRendererModifier extends JButton implements TableCellRenderer {
        public ButtonRendererModifier() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Modifier");
            setBackground(new Color(70, 130, 180)); // Bleu pour le bouton Modifier
            setForeground(Color.WHITE);
            return this;
        }
    }

    /**
     * Classe interne pour gérer l'édition et les actions des boutons de modification.
     * Gère les interactions utilisateur avec les boutons de modification.
     */
class ButtonEditorModifier extends DefaultCellEditor {
    protected JButton button;
    private int targetRow;
    private boolean isPushed;

    public ButtonEditorModifier(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        button.setText("Modifier");
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        targetRow = row;
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            // Code pour ouvrir un formulaire de modification ou une nouvelle fenêtre
            //VoyageInformation voyageToEdit = voyageInformations.get(targetRow);

            // Par exemple, ouvrir une fenêtre pour modifier le voyage
            new VoyageForm();

            // Vous pouvez ajouter ici le code pour mettre à jour les informations dans la base de données si nécessaire.
        }
        isPushed = false;
        return null;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }
}

    
/**
* Classe interne pour gérer l'édition et les actions des boutons de suppression.
* Gère les interactions utilisateur avec les boutons de suppression.
*/
 class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private int targetRow;
    private boolean isPushed;
    private JTable table;  // Ajout d'une référence à la table

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> fireEditingStopped());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        this.table = table;  // Sauvegarde de la référence à la table
        button.setText("Supprimer");
        button.setBackground(new Color(255, 99, 71));
        button.setForeground(Color.WHITE);
        targetRow = row;
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed) {
            System.out.println("Bouton Supprimer cliqué pour la ligne : " + targetRow);
            int response = JOptionPane.showConfirmDialog(
                SwingUtilities.getWindowAncestor(button),
                "Voulez-vous vraiment supprimer ce voyage ?",
                "Confirmation de suppression",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
            );
            
            if (response == JOptionPane.YES_OPTION) {
                try {
                    // Récupérer le modèle de la table
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    
                    // Supprimer de la base de données
                    VoyageInformation voyageToDelete = voyageInformations.get(targetRow);
                    VoyageDAO voyageDAO = new VoyageDAO();
                    voyageDAO.supprimer(voyageToDelete.getId());
                    
                    // Supprimer de la liste des voyages
                    voyageInformations.remove(targetRow);
                    System.out.println("Élément supprimé de la liste. Taille actuelle : " + voyageInformations.size());
                    
                    // Supprimer la ligne de la table
                    SwingUtilities.invokeLater(() -> {
                        model.removeRow(targetRow);
                        System.out.println("Ligne supprimée de la table.");
                        table.revalidate();
                        table.repaint();
                    });
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                        button,
                        "Erreur lors de la suppression : " + e.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
        isPushed = false;
        return null;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    @Override
    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}

    /**
     * Gère les événements d'action des boutons de l'interface.
     * Met à jour l'affichage de la table avec les données actuelles des voyages.
     * 
     * @param ae L'événement d'action déclenché
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == button) {
            AllDAO allDAO = new AllDAO();
            voyageInformations = allDAO.getVoyageInformations();
            model.setRowCount(0);
            for (VoyageInformation voyageInformation : voyageInformations) {
                model.addRow(new Object[] {
                voyageInformation.getVilleDepart(),
                voyageInformation.getVilleArrivee(),
                "<html>" + voyageInformation.getDateDepart() + "<br>" + voyageInformation.getDateArrivee() + "</html>",
                "<html>" + voyageInformation.getHeureDepart() + "<br>" + voyageInformation.getHeureArrivee() + "</html>",
                voyageInformation.getMoyenTransport(),
                voyageInformation.getPrix(),
                "Supprimer", // Text for delete button column
                "Modifier"   // Text for modify button column
            });

            }

            table.setBackground(new Color(255, 253, 208, 80));
            table.setOpaque(true);
            table.setRowHeight(40);
            table.repaint();
        }
    }
    /**
     * Point d'entrée principal de l'application.
     * Crée et affiche l'interface principale.
     * 
     * @param args Arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        InterfacePrincipal1 interfacePrincipal1 = new InterfacePrincipal1();
        interfacePrincipal1.setVisible(true);
    }
}