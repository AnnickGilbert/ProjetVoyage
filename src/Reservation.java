import java.time.LocalDate;

/**
 * Représente une réservation effectuée par un client pour un voyage ou un séjour.
 */
public class Reservation {
    private String id;
    private LocalDate dateReservation;
    private String prix;
    private String clientId;
    private String sejourId;
    private String voyageId;

    /**
     * Constructeur de la classe Reservation.
     * 
     * @param id               Identifiant unique de la réservation
     * @param dateReservation  Date de la réservation
     * @param prix             Prix de la réservation
     * @param clientId         Identifiant du client ayant effectué la réservation
     * @param sejourId         Identifiant du séjour réservé (peut être null)
     * @param voyageId         Identifiant du voyage réservé (peut être null)
     */
    public Reservation(String id, LocalDate dateReservation, String prix, String clientId,
            String sejourId, String voyageId) {
        this.id = id;
        this.dateReservation = dateReservation;
        this.prix = prix;
        this.clientId = clientId;
        this.sejourId = sejourId;
        this.voyageId = voyageId;
    }
    
    /**
     * Retourne l'identifiant de la réservation.
     * @return L'identifiant unique de la réservation.
     */
    public String getId() {
        return id;
    }

    /**
     * Retourne la date de la réservation.
     * @return La date de la réservation.
     */
    public LocalDate getDateReservation() {
        return dateReservation;
    }

    /**
     * Retourne le prix de la réservation.
     * @return Le prix sous forme de chaîne de caractères.
     */
    public String getPrix() {
        return prix;
    }

    /**
     * Retourne l'identifiant du client ayant effectué la réservation.
     * @return L'identifiant du client.
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * Retourne l'identifiant du séjour réservé.
     * @return L'identifiant du séjour, peut être null.
     */
    public String getSejourId() {
        return sejourId;
    }

    /**
     * Retourne l'identifiant du voyage réservé.
     * @return L'identifiant du voyage, peut être null.
     */
    public String getVoyageId() {
        return voyageId;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la réservation.
     * @return Une chaîne représentant la réservation.
     */
    @Override
    public String toString() {
        return "Reservation{id='" + id + "', dateReservation=" + dateReservation +
               ", prix='" + prix + "', clientId='" + clientId + "', sejourId='" + sejourId + "', voyageId='" + voyageId + "'}";
    }
}
