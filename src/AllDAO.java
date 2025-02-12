import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllDAO {
	
	
    static final String URL = "jdbc:mysql://localhost:3306/bddvoyage?serverTimezone=Europe/Paris";

   static final String LOGIN = "root";
   static final String PASS = "";

   /**
	 * Constructeur de la classe
	 * 
	 */
	public AllDAO()
    {
        // chargement du pilote de bases de donn�es
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e2) {
            System.err.println(
                    "Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
        }

    }
   
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
                Voyage voyage = new Voyage(rs.getString("id"), rs.getDate("dateDepart").toLocalDate(),
                        rs.getDate("dateArrivee").toLocalDate(), rs.getTime("heureDepart").toString(),
                        rs.getTime("heureArrivee").toString(), rs.getString("villeDepart_id"), rs.getString("villeArrivee_id"),
                        rs.getString("MoyenTransport_id"));
                voyages.add(voyage);
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
            }
        }
        return voyages;
    }
    
    //une requete pour recuperer les informations des voyages
    public List<VoyageInformation> getVoyageInformations() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<VoyageInformation> voyageInformations = new ArrayList<>();

        try {
            
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement(
                     "SELECT voy.id, v1.nom AS villeDepart, v2.nom AS villeArrivee, voy.dateDepart,voy.dateArrivee, voy.heureDepart, voy.heureArrivee, mt.type AS moyenTransport, res.prix, c.nom AS clientNom, c.prenom AS clientPrenom, se.type AS typeSejour FROM voyage voy JOIN ville v1 ON voy.villeDepart_id = v1.id JOIN ville v2 ON voy.villeArrivee_id = v2.id JOIN MoyenTransport mt ON voy.MoyenTransport_id = mt.id JOIN reservation res ON voy.id = res.voyage_id JOIN client c ON res.client_id = c.id JOIN sejour se ON res.sejour_id = se.id");            rs = ps.executeQuery();
            while (rs.next()) {
                VoyageInformation voyageInformation = new VoyageInformation(rs.getString("id"), rs.getString("villeDepart"),
                        rs.getString("villeArrivee"), rs.getDate("dateDepart").toLocalDate(),
                        rs.getDate("dateArrivee").toLocalDate(), rs.getTime("heureDepart").toString(),
                        rs.getTime("heureArrivee").toString(), rs.getString("moyenTransport"), rs.getDouble("prix"),
                        rs.getString("clientNom"), rs.getString("clientPrenom"), rs.getString("typeSejour"));
                voyageInformations.add(voyageInformation);
            }

        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
            }
        }
        return voyageInformations;
    }

    //suprimer by id
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
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
            }
        }
        return retour;
    }

    
    
}
