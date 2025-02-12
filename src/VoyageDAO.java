import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class VoyageDAO {

    // Database connection parameters
    static final String URL = "jdbc:mysql://localhost:3306/bddvoyage?serverTimezone=Europe/Paris";
    static final String LOGIN = "root";
    static final String PASS = "";

    // Constructor for initializing the driver
    public VoyageDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to load database driver, make sure the .jar file is imported.");
        }
    }

    /**
     * Adds a new voyage to the database.
     * @param nouveauVoyage the voyage to add.
     * @return the number of rows affected (should be 1 if successful).
     */
    public void ajouter(Voyage nouveauVoyage) {
        Connection con = null;
        PreparedStatement ps = null;
        
       

        // Connect to the database
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Prepare SQL statement for inserting a voyage
            ps = con.prepareStatement(
                    "INSERT INTO voyage (id, dateDepart, dateArrivee, heureDepart, heureArrivee, villeDepart_id, villeArrivee_id, moyenTransport_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            // Set the parameters of the SQL statement
            ps.setString(1, nouveauVoyage.getId());
            ps.setString(2, nouveauVoyage.getDateDepart().toString());
            ps.setString(3, nouveauVoyage.getDateArrivee().toString());
            ps.setString(4, nouveauVoyage.getHeureDepart());
            ps.setString(5, nouveauVoyage.getHeureArrivee());
            ps.setString(6, nouveauVoyage.getVilleDepartId());
            ps.setString(7, nouveauVoyage.getVilleArriveeId());
            ps.setString(8, nouveauVoyage.getMoyenTransportId());


            // Execute the update and get the affected rows
            ps.executeUpdate();
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
        
    }

    //compter le nombre de voyages
    // Count the number of voyages
public int countVoyage() {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int retour = 0;

    try {
        con = DriverManager.getConnection(URL, LOGIN, PASS);
        // Use executeQuery instead of executeUpdate for SELECT queries
        ps = con.prepareStatement("SELECT COUNT(*) FROM voyage");
        rs = ps.executeQuery();  // This will execute the SELECT query
        
        if (rs.next()) {
            retour = rs.getInt(1);  // Get the result (count)
        }
    } catch (SQLException ee) {
        ee.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();  // Close ResultSet
            if (ps != null) ps.close();  // Close PreparedStatement
            if (con != null) con.close();  // Close Connection
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    return retour;
}
    //list des nom de ville
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
    //get id by name
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
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return retour;
    }
    //get list voyage 
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
                retour[i] = new Voyage(rs.getString("id"), LocalDate.parse(rs.getString("dateDepart")), LocalDate.parse(rs.getString("dateArrivee")), rs.getString("heureDepart"), rs.getString("heureArrivee"), rs.getString("villeDepart_id"), rs.getString("villeArrivee_id"), rs.getString("moyenTransport_id"));
                i++;
            }

        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return retour;
    }

    //supmerimer un voyage
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
                if (ps != null)
                    ps.close();
                if (con != null)
                    con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
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

    


         // Main method for testing
    public static void main(String[] args) {
        //test suppimer
        VoyageDAO dao = new VoyageDAO();
        dao.supprimer("1");
        //test listVoyage
        
        
    }
}
