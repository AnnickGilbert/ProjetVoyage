// Source code is decompiled from a .class file using FernFlower decompiler.
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VilleFenetre extends JFrame implements ActionListener {
   private static final long serialVersionUID = 1L;
   private JPanel containerPanel;
   private JTextField textFieldId;
   private JTextField textFieldNom;
   private JTextField textFieldPays;
   private JTextField textFieldCodePostal;
   private JButton boutonEnvoi;
   private JButton boutonAffichageToutesLesVilles;
   private JTextArea zoneTextListVille;
   private JScrollPane zoneDefilement;
   private VilleDAO monVilleDAO = new VilleDAO();

   public VilleFenetre() {
      this.setTitle("Gestion des Villes");
      this.setSize(400, 350);
      this.containerPanel = new JPanel();
      this.containerPanel.setLayout(new BoxLayout(this.containerPanel, 3));
      this.containerPanel.setBackground(Color.LIGHT_GRAY);
      this.textFieldId = new JTextField();
      this.textFieldNom = new JTextField();
      this.textFieldPays = new JTextField();
      this.textFieldCodePostal = new JTextField();
      this.boutonEnvoi = new JButton("Ajouter Ville");
      this.boutonAffichageToutesLesVilles = new JButton("Afficher toutes les villes");
      this.zoneTextListVille = new JTextArea(5, 20);
      this.zoneDefilement = new JScrollPane(this.zoneTextListVille);
      this.zoneTextListVille.setEditable(false);
      this.containerPanel.add(new JLabel("ID :"));
      this.containerPanel.add(this.textFieldId);
      this.containerPanel.add(new JLabel("Nom :"));
      this.containerPanel.add(this.textFieldNom);
      this.containerPanel.add(new JLabel("Pays :"));
      this.containerPanel.add(this.textFieldPays);
      this.containerPanel.add(new JLabel("Code Postal :"));
      this.containerPanel.add(this.textFieldCodePostal);
      this.containerPanel.add(this.boutonEnvoi);
      this.containerPanel.add(this.boutonAffichageToutesLesVilles);
      this.containerPanel.add(this.zoneDefilement);
      this.containerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
      this.boutonEnvoi.addActionListener(this);
      this.boutonAffichageToutesLesVilles.addActionListener(this);
      this.setDefaultCloseOperation(3);
      this.setContentPane(this.containerPanel);
      this.setVisible(true);
   }

   public void actionPerformed(ActionEvent ae) {
      try {
         if (ae.getSource() == this.boutonEnvoi) {
            Ville v = new Ville(this.textFieldId.getText(), this.textFieldNom.getText(), this.textFieldPays.getText(), Integer.parseInt(this.textFieldCodePostal.getText()));
            int retour = this.monVilleDAO.ajouter(v);
            System.out.println("" + retour + " ligne ajout\u00e9e");
         } else if (ae.getSource() == this.boutonAffichageToutesLesVilles) {
            List<Ville> liste = this.monVilleDAO.getListeVilles();
            this.zoneTextListVille.setText("");
            Iterator var7 = liste.iterator();

            while(var7.hasNext()) {
               Ville v = (Ville)var7.next();
               this.zoneTextListVille.append(v.toString() + "\n");
            }
         }
      } catch (Exception var5) {
         System.err.println("Veuillez contr\u00f4ler vos saisies");
      }

   }

   public static void main(String[] args) {
      new VilleFenetre();
   }
}
