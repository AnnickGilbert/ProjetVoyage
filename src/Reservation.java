import java.time.LocalDate;

public class Reservation {
    private String id;
    private LocalDate dateReservation;
    private LocalDate dateAnnulation;
    private String prix;
    private String clientId;
    private String sejourId;
    private String voyageId;

    public Reservation(String id, LocalDate dateReservation, LocalDate dateAnnulation, String prix, String clientId, String sejourId, String voyageId) {
        this.id = id;
        this.dateReservation = dateReservation;
        this.dateAnnulation = dateAnnulation;
        this.prix = prix;
        this.clientId = clientId;
        this.sejourId = sejourId;
        this.voyageId = voyageId;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public LocalDate getDateAnnulation() {
        return dateAnnulation;
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
        return "Reservation{id='" + id + "', dateReservation=" + dateReservation + ", dateAnnulation=" + dateAnnulation +
               ", prix='" + prix + "', clientId='" + clientId + "', sejourId='" + sejourId + "', voyageId='" + voyageId + "'}";
    }
}
