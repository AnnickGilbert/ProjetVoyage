import java.time.LocalDate;

public class Reservation {
    private String id;
    private LocalDate dateReservation;
    private String prix;
    private String clientId;
    private String sejourId;
    private String voyageId;

    public Reservation(String id, LocalDate dateReservation, String prix, String clientId,
            String sejourId, String voyageId) {
        this.id = id;
        this.dateReservation = dateReservation;
        this.prix = prix;
        this.clientId = clientId;
        this.sejourId = sejourId;
        this.voyageId = voyageId;
    }
    //Reservation reservation = new Reservation(designation, prix, quantite);
    

    public String getId() {
        return id;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public String getPrix() {
        return prix;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSejourId() {
        return sejourId;
    }

    public String getVoyageId() {
        return voyageId;
    }

    @Override
    public String toString() {
        return "Reservation{id='" + id + "', dateReservation=" + dateReservation +
               ", prix='" + prix + "', clientId='" + clientId + "', sejourId='" + sejourId + "', voyageId='" + voyageId + "'}";
    }
}
