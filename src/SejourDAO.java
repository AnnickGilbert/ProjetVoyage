import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

/**
 * Classe d'accès aux données contenues dans la table sejour
 * @version 1.1
 */
public class SejourDAO {

    /**
     * Paramètres de connexion à la base de données MySQL
     * URL, LOGIN et PASS sont des constantes
     */
    static final String URL = "jdbc:mysql://localhost:3306/bddvoyage?serverTimezone=Europe/Paris";
    static final String LOGIN = "root";
    static final String PASS = "";

    /**
     * Constructeur de la classe
     */
    public SejourDAO() {
        // Chargement du pilote de base de données
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
        }
    }

    /**
     * Permet d'ajouter un séjour dans la table sejour
     * Le mode est auto-commit par défaut : chaque insertion est validée
     * @param nouvSejour le séjour à ajouter
     * @return le nombre de lignes ajoutées dans la table
     */
    public int ajouter(Sejour nouvSejour) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Préparation de l'instruction SQL
            ps = con.prepareStatement("INSERT INTO sejour (id, dateEntree, dateSortie, type) VALUES (?, ?, ?, ?)");
            ps.setString(1, nouvSejour.getId());
            ps.setDate(2, new java.sql.Date(nouvSejour.getDateEntree().getTime()));
            ps.setDate(3, new java.sql.Date(nouvSejour.getDateSortie().getTime()));
            ps.setString(4, nouvSejour.getType());

            // Exécution de la requête
            retour = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture du PreparedStatement et de la connexion
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return retour;
    }

    /**
     * Permet de récupérer un séjour à partir de son identifiant
     * @param id l'identifiant du séjour à récupérer
     * @return le séjour
     * @return null si aucun séjour ne correspond à cet identifiant
     */
    public Sejour getSejour(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Sejour retour = null;

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM sejour WHERE id = ?");
            ps.setString(1, id);

            // Exécution de la requête
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Sejour(
                    rs.getString("id"),
                    rs.getDate("dateEntree"),
                    rs.getDate("dateSortie"),
                    rs.getString("type")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture du ResultSet, du PreparedStatement et de la connexion
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return retour;
    }

    /**
     * Permet de récupérer tous les séjours stockés dans la table sejour
     * @return une ArrayList de Sejours
     */
    public List<Sejour> getListeSejours() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Sejour> retour = new ArrayList<>();

        // Connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM sejour");

            // Exécution de la requête
            rs = ps.executeQuery();
            while (rs.next()) {
                retour.add(new Sejour(
                    rs.getString("id"),
                    rs.getDate("dateEntree"),
                    rs.getDate("dateSortie"),
                    rs.getString("type")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Fermeture du ResultSet, du PreparedStatement et de la connexion
            try { if (rs != null) rs.close(); } catch (Exception e) {}
            try { if (ps != null) ps.close(); } catch (Exception e) {}
            try { if (con != null) con.close(); } catch (Exception e) {}
        }
        return retour;
    }

    // Méthode main pour tester la classe
    public static void main(String[] args) throws SQLException {
        SejourDAO sejourDAO = new SejourDAO();

        // Test de la méthode ajouter
         
        Sejour s = new Sejour("SEJ001", new java.util.Date(), new java.util.Date(), "type1");
        int retour = sejourDAO.ajouter(s);
        System.out.println(retour + " lignes ajoutées");

        // Test de la méthode getSejour
        Sejour s2 = sejourDAO.getSejour("SEJ001");
        System.out.println(s2);

        // Test de la méthode getListeSejours
        List<Sejour> liste = sejourDAO.getListeSejours();
        for (Sejour sejour : liste) {
            System.out.println(sejour.toString());
        }
    }
}