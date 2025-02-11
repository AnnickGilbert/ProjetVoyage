import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoyageDAO {

    // Database connection parameters
    static final String URL = "jdbc:mysql://localhost:3366/stocks?serverTimezone=Europe/Paris";
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
    public int ajouter(Voyage nouveauVoyage) {
        Connection con = null;
        PreparedStatement ps = null;
        int retour = 0;

        // Connect to the database
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Prepare SQL statement for inserting a voyage
            ps = con.prepareStatement("INSERT INTO voyage (id, dateDepart, dateArrivee, villeDepart_id, villeArrivee_id, MoyenTransport_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, nouveauVoyage.getId());
            ps.setDate(2, java.sql.Date.valueOf(nouveauVoyage.getDateDepart()));
            ps.setDate(3, java.sql.Date.valueOf(nouveauVoyage.getDateArrivee()));
            ps.setString(4, nouveauVoyage.getVilleDepartId());
            ps.setString(5, nouveauVoyage.getVilleArriveeId());
            ps.setString(6, nouveauVoyage.getMoyenTransportId());

            // Execute the update and get the affected rows
            retour = ps.executeUpdate();
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {}
            try {
                if (con != null) con.close();
            } catch (SQLException e) {}
        }
        return retour;
    }

    /**
     * Retrieves a voyage by its ID.
     * @param id the ID of the voyage.
     * @return the voyage object if found, otherwise null.
     */
    public Voyage getVoyage(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Voyage retour = null;

        // Connect to the database
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM voyage WHERE id = ?");
            ps.setString(1, id);

            // Execute the query and retrieve the result
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Voyage(
                    rs.getString("id"),
                    rs.getDate("dateDepart").toLocalDate(),
                    rs.getDate("dateArrivee").toLocalDate(),
                    rs.getString("villeDepart_id"),
                    rs.getString("villeArrivee_id"),
                    rs.getString("MoyenTransport_id")
                );
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {}
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {}
            try {
                if (con != null) con.close();
            } catch (SQLException e) {}
        }
        return retour;
    }

    /**
     * Retrieves all voyages from the database.
     * @return a list of all voyages.
     */
    public List<Voyage> getListeVoyages() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Voyage> voyages = new ArrayList<>();

        // Connect to the database
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM voyage");

            // Execute the query and retrieve all voyages
            rs = ps.executeQuery();
            while (rs.next()) {
                voyages.add(new Voyage(
                    rs.getString("id"),
                    rs.getDate("dateDepart").toLocalDate(),
                    rs.getDate("dateArrivee").toLocalDate(),
                    rs.getString("villeDepart_id"),
                    rs.getString("villeArrivee_id"),
                    rs.getString("MoyenTransport_id")
                ));
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {}
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {}
            try {
                if (con != null) con.close();
            } catch (SQLException e) {}
        }
        return voyages;
    }

    // Main method for testing
    public static void main(String[] args) {
        VoyageDAO voyageDAO = new VoyageDAO();
        
        // Test adding a new voyage
        Voyage v = new Voyage("V001", java.time.LocalDate.of(2025, 5, 1), java.time.LocalDate.of(2025, 5, 10), "V100", "V200", "MT01");
        int retour = voyageDAO.ajouter(v);
        System.out.println(retour + " ligne(s) ajoutée(s)");

        // Test retrieving a voyage by ID
        Voyage v2 = voyageDAO.getVoyage("V001");
        System.out.println(v2);

        // Test getting the list of all voyages
        List<Voyage> voyages = voyageDAO.getListeVoyages();
        for (Voyage voyage : voyages) {
            System.out.println(voyage);
        }
    }
}
