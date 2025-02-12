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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
public class InterfacePrincipal1 extends JFrame implements ActionListener {
    private JPanel containerPanel;
    private JPanel contentPanel;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JButton button;
    private List<VoyageInformation> voyageInformations;

    private JButton buttonAjouterVoyage;
    private JButton buttonAjouterReservation;
    private JButton buttonAjouterMoyenTransport;

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
        //model.addColumn("Date d'arrivée");
        model.addColumn("<html>Heure de départ<br>Heure d'arrivée</html>");
        //model.addColumn("Heure d'arrivée");
        model.addColumn("Transport");
        model.addColumn("Prix");
        //model.addColumn("Nom du client");
        //model.addColumn("Prénom du client");
        model.addColumn("Action");

        table = new JTable(model);
        // Set up the delete button column
        TableColumn deleteColumn = table.getColumnModel().getColumn(6);
        deleteColumn.setCellRenderer(new ButtonRenderer());
        deleteColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

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

        //table.setOpaque(false);

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

    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setText("Supprimer");
            setBackground(new Color(255, 99, 71)); // Red-ish color
            setForeground(Color.WHITE);
            return this;
        }
    }
    
    // Custom editor for the delete button
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;
        private int targetRow;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
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
                // Show confirmation dialog
                int response = JOptionPane.showConfirmDialog(
                    button,
                    "Voulez-vous vraiment supprimer ce voyage ?",
                    "Confirmation de suppression",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (response == JOptionPane.YES_OPTION) {
                    // Delete from database
                    VoyageInformation voyageToDelete = voyageInformations.get(targetRow);
                    
                    VoyageDAO voyageDAO = new VoyageDAO();
                    voyageDAO.supprimer(voyageToDelete.getId());
                    boolean deleted = true; // Dummy value for testing
                    if (deleted) {
                        // Remove from the table
                        ((DefaultTableModel)table.getModel()).removeRow(targetRow);
                        // Remove from the list
                        voyageInformations.remove(targetRow);
                    } else {
                        JOptionPane.showMessageDialog(
                            button,
                            "Erreur lors de la suppression du voyage",
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
    }


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
                    "Supprimer" // Text for delete button column
                });
            }
            
            table.setBackground(new Color(255, 253, 208, 80));
            table.setOpaque(true);
            table.setRowHeight(40);
            table.repaint();
        }
    }
    public static void main(String[] args) {
        InterfacePrincipal1 interfacePrincipal1 = new InterfacePrincipal1();
        interfacePrincipal1.setVisible(true);
    }
}