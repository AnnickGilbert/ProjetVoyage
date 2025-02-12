import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe d'acc�s aux donn�es contenues dans la table client
 * @version 1.1
 * */
public class ClientDAO {

    /**
     * Param�tres de connexion � la base de donn�es MySQL
     * URL, LOGIN et PASS sont des constantes
     */
    static final String URL = "jdbc:mysql://localhost:3306/stocks?serverTimezone=Europe/Paris";
    static final String LOGIN = "root";
    static final String PASS = "";

    /**
     * Constructeur de la classe
     * 
     */
    public ClientDAO() {
        // chargement du pilote de bases de donn�es
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e2) {
            System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
        }
    }

    /**
     * Permet d'ajouter un client dans la table client
     * @param nouvClient le client à ajouter
     * @return le nombre de lignes ajoutées dans la table
     */
    public int ajouter(Client nouvClient) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        // connexion à la base de données
        try {
            // tentative de connexion
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // préparation de l'instruction SQL, chaque ? représente une valeur à communiquer dans l'insertion
            ps = con.prepareStatement("INSERT INTO client (id, nom, prenom, dateNaissance, numeroTelephone) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, nouvClient.getId());
            ps.setString(2, nouvClient.getNom());
            ps.setString(3, nouvClient.getPrenom());
            ps.setDate(4, java.sql.Date.valueOf(nouvClient.getDateNaissance()));
            ps.setString(5, nouvClient.getNumeroTelephone());

            // Exécution de la requête
            retour = ps.executeUpdate();

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // fermeture du preparedStatement et de la connexion
            try {
                if (ps != null) ps.close();
            } catch (Exception t) {
            }
            try {
                if (con != null) con.close();
            } catch (Exception t) {
            }
        }
        return retour;
    }

    /**
     * Permet de récupérer un client à partir de son ID
     * @param id l'ID du client à récupérer
     * @return le client
     * @return null si aucun client ne correspond à cet ID
     */
    public Client getClient(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Client retour = null;

        // connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM client WHERE id = ?");
            ps.setString(1, id);

            // on exécute la requête
            rs = ps.executeQuery();
            // passe à la première (et unique) ligne retournée
            if (rs.next())
                retour = new Client(rs.getString("id"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance").toLocalDate(), rs.getString("numeroTelephone"));

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // fermeture du ResultSet, du PreparedStatement et de la Connection
            try {
                if (rs != null) rs.close();
            } catch (Exception t) {
            }
            try {
                if (ps != null) ps.close();
            } catch (Exception t) {
            }
            try {
                if (con != null) con.close();
            } catch (Exception t) {
            }
        }
        return retour;
    }

    /**
     * Permet de récupérer tous les clients stockés dans la table client
     * @return une ArrayList de Clients
     */
    public List<Client> getListeClients() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Client> retour = new ArrayList<Client>();

        // connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM client");

            // on exécute la requête
            rs = ps.executeQuery();
            // on parcourt les lignes du résultat
            while (rs.next()) {
                retour.add(new Client(rs.getString("id"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("dateNaissance").toLocalDate(), rs.getString("numeroTelephone")));
            }

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // fermeture du rs, du preparedStatement et de la connexion
            try {
                if (rs != null) rs.close();
            } catch (Exception t) {
            }
            try {
                if (ps != null) ps.close();
            } catch (Exception t) {
            }
            try {
                if (con != null) con.close();
            } catch (Exception t) {
            }
        }
        return retour;
    }
    //get nombre de clients
    public int getNombreClients() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int retour = 0;

        // connexion à la base de données
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT COUNT(*) FROM client");

            // on exécute la requête
            rs = ps.executeQuery();
            // on parcourt les lignes du résultat
            if (rs.next()) {
                retour = rs.getInt(1);
            }

        } catch (Exception ee) {
            ee.printStackTrace();
        } finally {
            // fermeture du rs, du preparedStatement et de la connexion
            try {
                if (rs != null) rs.close();
            } catch (Exception t) {
            }
            try {
                if (ps != null) ps.close();
            } catch (Exception t) {
            }
            try {
                if (con != null) con.close();
            } catch (Exception t) {
            }
        }
        return retour;
    }
    // main permettant de tester la classe
    public static void main(String[] args) throws SQLException {

        ClientDAO clientDAO = new ClientDAO();
        // test de la méthode ajouter
        Client c = new Client("C123", "Doe", "John", java.time.LocalDate.of(1985, 5, 15), "0102030405");
        int retour = clientDAO.ajouter(c);

        System.out.println("" + retour + " ligne(s) ajoutée(s)");
        // test de la méthode getClient
        Client c2 = clientDAO.getClient("C123");
        System.out.println(c2);

        // test de la méthode getListeClients
        List<Client> liste = clientDAO.getListeClients();
        // System.out.println(liste);
        for (Client cli : liste) {
            System.out.println(cli.toString());
        }

    }
}
