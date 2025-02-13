import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'accès aux données (DAO) regroupant différentes méthodes
 * pour interagir avec la base de données des voyages.
 *
 * <p>
 * Cette classe permet de récupérer la liste des voyages, d'obtenir des informations détaillées
 * sur les voyages en effectuant des jointures entre plusieurs tables et de supprimer un voyage
 * à partir de son identifiant.
 * </p>
 *
 * <p>
 * Les paramètres de connexion à la base de données sont définis en tant que constantes.
 * </p>
 *
 * @see Voyage
 * @see VoyageInformation
 */
public class AllDAO {

    /** URL de connexion à la base de données. */
    static final String URL = "jdbc:mysql://localhost:3306/bddvoyage?serverTimezone=Europe/Paris";
    /** Login de la base de données. */
    static final String LOGIN = "root";
    /** Mot de passe de la base de données. */
    static final String PASS = "";

    /**
     * Constructeur de la classe {@code AllDAO}.
     *
     * <p>
     * Le constructeur charge le pilote JDBC MySQL nécessaire pour établir la connexion à la base de données.
     * </p>
     */
    public AllDAO() {
        // Chargement du pilote de base de données
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e2) {
            System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
        }
    }

    /**
     * Récupère la liste de tous les voyages enregistrés dans la base de données.
     *
     * <p>
     * Cette méthode établit une connexion à la base de données, exécute une requête SQL pour sélectionner
     * tous les enregistrements de la table {@code voyage} et crée des objets {@link Voyage} à partir des résultats.
     * La liste des voyages est ensuite retournée.
     * </p>
     *
     * @return une {@code List} de {@code Voyage} contenant tous les voyages récupérés depuis la base de données.
     */
    public List<Voyage> getVoyages() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Voyage> voyages = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM voyage");
            rs = ps.executeQuery();
            while (rs.next()) {
                Voyage voyage = new Voyage(
                        rs.getString("id"),
                        rs.getDate("dateDepart").toLocalDate(),
                        rs.getDate("dateArrivee").toLocalDate(),
                        rs.getTime("heureDepart").toString(),
                        rs.getTime("heureArrivee").toString(),
                        rs.getString("villeDepart_id"),
                        rs.getString("villeArrivee_id"),
                        rs.getString("MoyenTransport_id")
                );
                voyages.add(voyage);
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                // Ignorer l'exception
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                // Ignorer l'exception
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                // Ignorer l'exception
            }
        }
        return voyages;
    }

    /**
     * Récupère des informations détaillées sur les voyages en effectuant plusieurs jointures entre les tables.
     *
     * <p>
     * Cette méthode exécute une requête SQL complexe qui joint les tables {@code voyage}, {@code ville},
     * {@code MoyenTransport}, {@code reservation}, {@code client} et {@code sejour} pour obtenir les informations
     * complètes sur chaque voyage, telles que le nom des villes de départ et d'arrivée, les dates, les heures,
     * le moyen de transport, le prix, et les informations sur le client ainsi que le type de séjour.
     * </p>
     *
     * @return une {@code List} de {@code VoyageInformation} contenant les informations détaillées sur les voyages.
     */
    public List<VoyageInformation> getVoyageInformations() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<VoyageInformation> voyageInformations = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement(
                    "SELECT voy.id, v1.nom AS villeDepart, v2.nom AS villeArrivee, voy.dateDepart, voy.dateArrivee, " +
                    "voy.heureDepart, voy.heureArrivee, mt.type AS moyenTransport, res.prix, " +
                    "c.nom AS clientNom, c.prenom AS clientPrenom, se.type AS typeSejour " +
                    "FROM voyage voy " +
                    "JOIN ville v1 ON voy.villeDepart_id = v1.id " +
                    "JOIN ville v2 ON voy.villeArrivee_id = v2.id " +
                    "JOIN MoyenTransport mt ON voy.MoyenTransport_id = mt.id " +
                    "JOIN reservation res ON voy.id = res.voyage_id " +
                    "JOIN client c ON res.client_id = c.id " +
                    "JOIN sejour se ON res.sejour_id = se.id"
            );
            rs = ps.executeQuery();
            while (rs.next()) {
                VoyageInformation voyageInformation = new VoyageInformation(
                        rs.getString("id"),
                        rs.getString("villeDepart"),
                        rs.getString("villeArrivee"),
                        rs.getDate("dateDepart").toLocalDate(),
                        rs.getDate("dateArrivee").toLocalDate(),
                        rs.getTime("heureDepart").toString(),
                        rs.getTime("heureArrivee").toString(),
                        rs.getString("moyenTransport"),
                        rs.getDouble("prix"),
                        rs.getString("clientNom"),
                        rs.getString("clientPrenom"),
                        rs.getString("typeSejour")
                );
                voyageInformations.add(voyageInformation);
            }

        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                // Ignorer l'exception
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                // Ignorer l'exception
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                // Ignorer l'exception
            }
        }
        return voyageInformations;
    }

    /**
     * Supprime un voyage de la base de données en fonction de son identifiant.
     *
     * <p>
     * Cette méthode se connecte à la base de données et exécute une requête SQL de suppression sur la table
     * {@code voyage} pour l'enregistrement dont l'identifiant correspond à celui fourni en paramètre.
     * </p>
     *
     * @param id l'identifiant du voyage à supprimer.
     * @return {@code true} si la suppression a été effectuée avec succès, {@code false} en cas d'échec.
     */
    public boolean deleteVoyage(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        boolean retour = false;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("DELETE FROM voyage WHERE id = ?");
            ps.setString(1, id);
            ps.executeUpdate();
            retour = true;
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                // Ignorer l'exception
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                // Ignorer l'exception
            }
        }
        return retour;
    }

    //listeId de réservation
    
}
