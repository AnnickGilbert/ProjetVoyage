import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    // Database connection parameters
    static final String URL = "jdbc:mysql://localhost:3306/bddvoyage?serverTimezone=Europe/Paris";
    static final String LOGIN = "root";
    static final String PASS = "";

    // Constructor for initializing the driver
    public ReservationDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to load database driver, make sure the .jar file is imported.");
        }
    }

    /**
     * Adds a new reservation to the database.
     * 
     * @param newReservation the reservation to add.
     * @return void
     * 
     * This method inserts a new reservation into the "reservation" table in the database
     * using the provided reservation details.
     */
    public void ajouter(Reservation newReservation) {
        System.out.println("Tentative d'insertion avec : " +
                "id=" + newReservation.getId() +
                ", dateReservation=" + newReservation.getDateReservation() +
                ", prix=" + newReservation.getPrix() +
                ", clientId=" + newReservation.getClientId() +
                ", sejourId=" + newReservation.getSejourId() +
                ", voyageId=" + newReservation.getVoyageId());

        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement(
                    "INSERT INTO reservation (id, dateReservation, prix, client_id, sejour_id, voyage_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, newReservation.getId());
            ps.setDate(2, java.sql.Date.valueOf(newReservation.getDateReservation()));
            ps.setString(3, newReservation.getPrix());
            ps.setString(4, newReservation.getClientId());
            ps.setString(5, newReservation.getSejourId());
            ps.setString(6, newReservation.getVoyageId());

            ps.executeUpdate();
        } catch (SQLException ee) {
            ee.printStackTrace();
            System.out.println("Erreur SQL : " + ee.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {}
            try {
                if (con != null) con.close();
            } catch (SQLException e) {}
        }
    }

    /**
     * Retrieves a reservation by its ID.
     * 
     * @param id the ID of the reservation.
     * @return the reservation object if found, otherwise null.
     * 
     * This method queries the database for a reservation by its ID and returns the corresponding
     * Reservation object. If no reservation is found, it returns null.
     */
    public Reservation getReservation(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reservation retour = null;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM reservation WHERE id = ?");
            ps.setString(1, id);

            rs = ps.executeQuery();
            if (rs.next()) {
                retour = new Reservation(
                    rs.getString("id"),
                    rs.getDate("dateReservation").toLocalDate(),
                    rs.getString("prix"),
                    rs.getString("client_id"),
                    rs.getString("sejour_id"),
                    rs.getString("voyage_id")
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
     * Retrieves all reservations from the database.
     * 
     * @return a list of all reservations.
     * 
     * This method queries the database to fetch all reservations and returns them as a list of
     * Reservation objects.
     */
    public List<Reservation> getListeReservations() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Reservation> reservations = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM reservation");

            rs = ps.executeQuery();
            while (rs.next()) {
                reservations.add(new Reservation(
                        rs.getString("id"),
                        rs.getDate("dateReservation").toLocalDate(),
                        rs.getString("prix"),
                        rs.getString("client_id"),
                        rs.getString("sejour_id"),
                        rs.getString("voyage_id")));
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
        return reservations;
    }

    /**
     * Retrieves the total number of reservations in the database.
     * 
     * @return the number of reservations.
     * 
     * This method queries the database to count the total number of reservations in the "reservation" table
     * and returns the result as an integer.
     */
    public int getNombreReservations() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int retour = 0;

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT COUNT(*) FROM reservation");

            rs = ps.executeQuery();
            if (rs.next()) {
                retour = rs.getInt(1);
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
}
