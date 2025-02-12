import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * La classe `VoyageDAO` permet d'interagir avec la base de données pour effectuer des opérations sur les voyages.
 * Elle inclut des méthodes pour ajouter, supprimer, lister des voyages, compter le nombre de voyages et gérer des réservations.
 */
public class VoyageDAO {

    // Paramètres de connexion à la base de données
    static final String URL = "jdbc:mysql://localhost:3306/bddvoyage?serverTimezone=Europe/Paris";
    static final String LOGIN = "root";
    static final String PASS = "";

    /**
     * Constructeur de la classe `VoyageDAO`.
     * Il charge le driver JDBC pour la base de données.
     */
    public VoyageDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de charger le driver de la base de données, assurez-vous d'importer le fichier .jar.");
        }
    }

    /**
     * Ajoute un nouveau voyage dans la base de données.
     * 
     * @param nouveauVoyage le voyage à ajouter.
     */
    public void ajouter(Voyage nouveauVoyage) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement(
                    "INSERT INTO voyage (id, dateDepart, dateArrivee, heureDepart, heureArrivee, villeDepart_id, villeArrivee_id, moyenTransport_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, nouveauVoyage.getId());
            ps.setString(2, nouveauVoyage.getDateDepart().toString());
            ps.setString(3, nouveauVoyage.getDateArrivee().toString());
            ps.setString(4, nouveauVoyage.getHeureDepart());
            ps.setString(5, nouveauVoyage.getHeureArrivee());
            ps.setString(6, nouveauVoyage.getVilleDepartId());
            ps.setString(7, nouveauVoyage.getVilleArriveeId());
            ps.setString(8, nouveauVoyage.getMoyenTransportId());

            // Exécute la mise à jour
            ps.executeUpdate();
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Compte le nombre total de voyages dans la base de données.
     * 
     * @return le nombre de voyages.
     */
    public int countVoyage() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT COUNT(*) FROM voyage");
            rs = ps.executeQuery();  // Exécute la requête SELECT
            
            if (rs.next()) {
                retour = rs.getInt(1);  // Récupère le résultat (compte)
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return retour;
    }

    /**
     * Récupère la liste des noms de villes disponibles dans la base de données.
     * 
     * @return un tableau de chaînes de caractères contenant les noms des villes.
     */
    public String[] listVille() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String[] retour = new String[20];

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT nom FROM ville");
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                retour[i] = rs.getString("nom");
                i++;
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return retour;
    }

    /**
     * Récupère l'ID d'une ville à partir de son nom.
     * 
     * @param name le nom de la ville.
     * @return l'ID de la ville correspondante.
     */
    public static String getIdByName(String name) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String retour = "";

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT id FROM ville WHERE nom = ?");
            ps.setString(1, name);
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = rs.getString("id");
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return retour;
    }

    /**
     * Récupère la liste des voyages disponibles dans la base de données.
     * 
     * @return un tableau d'objets `Voyage`.
     */
    public Voyage[] listVoyage() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Voyage[] retour = new Voyage[25];

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM voyage");
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                retour[i] = new Voyage(
                        rs.getString("id"),
                        LocalDate.parse(rs.getString("dateDepart")),
                        LocalDate.parse(rs.getString("dateArrivee")),
                        rs.getString("heureDepart"),
                        rs.getString("heureArrivee"),
                        rs.getString("villeDepart_id"),
                        rs.getString("villeArrivee_id"),
                        rs.getString("moyenTransport_id"));
                i++;
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return retour;
    }

    /**
     * Supprime un voyage à partir de son ID.
     * 
     * @param id l'ID du voyage à supprimer.
     */
    public void supprimer(String id) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("DELETE FROM voyage WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Supprime les réservations associées à un voyage à partir de son ID.
     * 
     * @param voyageId l'ID du voyage pour lequel les réservations doivent être supprimées.
     */
    public void supprimerReservationByVoyageId(String voyageId) {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("DELETE FROM reservation WHERE voyage_id = ?");
            ps.setString(1, voyageId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
