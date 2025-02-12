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
    static final String URL = "jdbc:mysql://localhost:3306/stocks?serverTimezone=Europe/Paris";
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
     * @param newReservation the reservation to add.
     * @return the number of rows affected (should be 1 if successful).
     */
    public void ajouter(Reservation newReservation) {
        System.out.println("Tentative d'insertion avec : " +
    "id=" + newReservation.getId() +
    ", dateReservation=" + newReservation.getDateReservation() +
                ", prix=" + newReservation.getPrix()+
                ", clientId=" + newReservation.getClientId() +
                ", sejourId=" + newReservation.getSejourId() +
                ", voyageId=" + newReservation.getVoyageId());
    
        Connection con = null;
        PreparedStatement ps = null;
        
        
        // Connect to the database
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            // Prepare SQL statement for inserting a reservation
            ps = con.prepareStatement(
                    "INSERT INTO reservation (id, dateReservation, prix, client_id, sejour_id, voyage_id) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, newReservation.getId());
            ps.setDate(2, java.sql.Date.valueOf(newReservation.getDateReservation()));
            ps.setString(3, newReservation.getPrix());
            ps.setString(4, newReservation.getClientId());
            ps.setString(5, newReservation.getSejourId());
            ps.setString(6, newReservation.getVoyageId());

            // Execute the update and get the affected rows
            ps.executeUpdate();
        } 
        catch (SQLException ee) {
            ee.printStackTrace(); // Assure-toi de bien voir l'erreur
            System.out.println("Erreur SQL : " + ee.getMessage());
        }
        finally {
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
     * @param id the ID of the reservation.
     * @return the reservation object if found, otherwise null.
     */
    public Reservation getReservation(String id) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Reservation retour = null;

        // Connect to the database
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM reservation WHERE id = ?");
            ps.setString(1, id);

            // Execute the query and retrieve the result
           
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
     * @return a list of all reservations.
     */
    public List<Reservation> getListeReservations() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Reservation> reservations = new ArrayList<>();

        // Connect to the database
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM reservation");

            // Execute the query and retrieve all reservations
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
        return reservations;
    }

    //nb of reservation
    public int getNombreReservations() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int retour = 0;

        // Connect to the database
        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT COUNT(*) FROM reservation");

            // Execute the query and retrieve the result
            rs = ps.executeQuery();
            if (rs.next()) {
                retour = rs.getInt(1);
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
        return retour;
    }

    


    // Main method for testing
    public static void main(String[] args) {
        ReservationDAO reservationDAO = new ReservationDAO();
        //test ajout
        reservationDAO.ajouter(new Reservation("11", LocalDate.now(), "1000", "1", "1", "1"));


       
    }
}
