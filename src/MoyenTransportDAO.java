/**
 * Source code is decompiled from a .class file using FernFlower decompiler.
 * Classe permettant la gestion des moyens de transport dans la base de données.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoyenTransportDAO {
    static final String URL = "jdbc:mysql://localhost:3306/bddvoyage?serverTimezone=Europe/Paris";
    static final String LOGIN = "root";
    static final String PASS = "";

    /**
     * Constructeur chargeant le driver JDBC.
     */
    public MoyenTransportDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
        }
    }

    /**
     * Ajoute un moyen de transport à la base de données.
     * @param nouvMoyenTransport Le moyen de transport à ajouter.
     * @return Nombre de lignes ajoutées (1 si succès, 0 sinon).
     */
    public int ajouter(MoyenTransport nouvMoyenTransport) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("INSERT INTO MoyenTransport (id, matricule, type, capacite) VALUES (?, ?, ?, ?)");
            ps.setString(1, nouvMoyenTransport.getId());
            ps.setString(2, nouvMoyenTransport.getMatricule());
            ps.setString(3, nouvMoyenTransport.getType());
            ps.setString(4, nouvMoyenTransport.getCapacite());
            retour = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return retour;
    }

    /**
     * Récupère un moyen de transport à partir de son ID.
     * @param id L'identifiant du moyen de transport.
     * @return Le moyen de transport correspondant ou null s'il n'existe pas.
     */
    public MoyenTransport getMoyenTransport(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MoyenTransport retour = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM MoyenTransport WHERE id = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new MoyenTransport(rs.getString("id"), rs.getString("matricule"), rs.getString("type"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return retour;
    }

    /**
     * Récupère la liste de tous les moyens de transport.
     * @return Liste des moyens de transport.
     */
    public List<MoyenTransport> getListeMoyensTransport() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MoyenTransport> retour = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM MoyenTransport");
            rs = ps.executeQuery();
            while (rs.next()) {
                retour.add(new MoyenTransport(rs.getString("id"), rs.getString("matricule"), rs.getString("type")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return retour;
    }

    /**
     * Méthode principale pour tester les fonctionnalités de la classe.
     * @param args Arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        MoyenTransportDAO dao = new MoyenTransportDAO();
        MoyenTransport mt = new MoyenTransport("1", "AB-123-CD", "Bus");
        int lignesAjoutees = dao.ajouter(mt);
        System.out.println(lignesAjoutees + " lignes ajoutées");

        MoyenTransport mt2 = dao.getMoyenTransport("1");
        System.out.println(mt2);

        List<MoyenTransport> liste = dao.getListeMoyensTransport();
        for (MoyenTransport m : liste) {
            System.out.println(m);
        }
    }
}
