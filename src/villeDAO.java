// Source code is decompiled from a .class file using FernFlower decompiler.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * La classe `villeDAO` permet d'interagir avec la base de données pour effectuer des opérations
 * sur la table `ville`. Elle offre des méthodes pour ajouter, récupérer une ville par son ID,
 * et obtenir la liste de toutes les villes.
 */
public class VilleDAO {
    // Paramètres de connexion à la base de données
    static final String URL = "jdbc:mysql://localhost:3306/bddvoyage?serverTimezone=Europe/Paris";
    static final String LOGIN = "root";
    static final String PASS = "";

    /**
     * Constructeur de la classe `VilleDAO`. Il charge le driver JDBC pour MySQL.
     */
    public VilleDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Impossible de charger le pilote de BDD, assurez-vous d'importer le fichier .jar dans le projet");
        }
    }

    /**
     * Ajoute une nouvelle ville dans la base de données.
     * 
     * @param nouvVille la ville à ajouter dans la base de données.
     * @return le nombre de lignes affectées (devrait être 1 si l'insertion est réussie).
     */
    public int ajouter(Ville nouvVille) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("INSERT INTO ville (id, nom, pays, code_postal) VALUES (?, ?, ?, ?)");
            ps.setString(1, nouvVille.getId());
            ps.setString(2, nouvVille.getNom());
            ps.setString(3, nouvVille.getPays());
            ps.setInt(4, nouvVille.getCodePostal());
            retour = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

        return retour;
    }

    /**
     * Récupère une ville à partir de son ID dans la base de données.
     * 
     * @param id l'ID de la ville à récupérer.
     * @return l'objet `Ville` correspondant à l'ID, ou `null` si la ville n'est pas trouvée.
     */
    public Ville getVille(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Ville retour = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM ville WHERE id = ?");
            ps.setString(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Ville(rs.getString("id"), rs.getString("nom"), rs.getString("pays"), rs.getInt("code_postal"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }

            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

        return retour;
    }

    /**
     * Récupère la liste de toutes les villes présentes dans la base de données.
     * 
     * @return une liste d'objets `Ville` représentant toutes les villes.
     */
    public List<Ville> getListeVilles() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Ville> retour = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM ville");
            rs = ps.executeQuery();

            while (rs.next()) {
                retour.add(new Ville(rs.getString("id"), rs.getString("nom"), rs.getString("pays"), rs.getInt("code_postal")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }

            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }

            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

        return retour;
    }

    /**
     * Méthode principale pour tester les opérations de la classe `VilleDAO`.
     * 
     * @param args les arguments en ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        VilleDAO villeDAO = new VilleDAO();
        Ville v = new Ville("30", "Paris", "France", 75000);
        int retour = villeDAO.ajouter(v);
        System.out.println("" + retour + " ligne ajoutée");
        Ville v2 = villeDAO.getVille("1");
        System.out.println(v2);
        List<Ville> liste = villeDAO.getListeVilles();
        Iterator<Ville> var6 = liste.iterator();

        while (var6.hasNext()) {
            Ville ville = var6.next();
            System.out.println(ville);
        }
    }
}
