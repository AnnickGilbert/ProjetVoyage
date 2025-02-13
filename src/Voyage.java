import java.sql.Time;
import java.time.LocalDate;

/**
 * La classe `Voyage` représente un voyage avec ses caractéristiques essentielles telles que 
 * la date et l'heure de départ, la date et l'heure d'arrivée, les villes de départ et d'arrivée, 
 * ainsi que le moyen de transport utilisé pour ce voyage.
 */
public class Voyage {
    
    private String id;
    private LocalDate dateDepart;
    private LocalDate dateArrivee;
    private String heureDepart;
    private String heureArrivee;
    private String villeDepartId;
    private String villeArriveeId;
    private String moyenTransportId;

    /**
     * Constructeur de la classe `Voyage`.
     * 
     * @param id l'ID du voyage.
     * @param dateDepart la date de départ du voyage.
     * @param dateArrivee la date d'arrivée du voyage.
     * @param heureDepart l'heure de départ du voyage.
     * @param heureArrivee l'heure d'arrivée du voyage.
     * @param villeDepartId l'ID de la ville de départ.
     * @param villeArriveeId l'ID de la ville d'arrivée.
     * @param moyenTransportId l'ID du moyen de transport utilisé pour le voyage.
     */
    public Voyage(String id, LocalDate dateDepart, LocalDate dateArrivee, String heureDepart, String heureArrivee, String villeDepartId, String villeArriveeId, String moyenTransportId) {
        this.id = id;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.villeDepartId = villeDepartId;
        this.villeArriveeId = villeArriveeId;
        this.heureDepart = heureDepart.toString();
        this.heureArrivee = heureArrivee.toString();
        this.moyenTransportId = moyenTransportId;
    }

    /**
     * Récupère l'ID du voyage.
     * 
     * @return l'ID du voyage.
     */
    public String getId() {
        return id;
    }

    /**
     * Récupère la date de départ du voyage.
     * 
     * @return la date de départ.
     */
    public LocalDate getDateDepart() {
        return dateDepart;
    }

    /**
     * Récupère la date d'arrivée du voyage.
     * 
     * @return la date d'arrivée.
     */
    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    /**
     * Récupère l'ID de la ville de départ.
     * 
     * @return l'ID de la ville de départ.
     */
    public String getVilleDepartId() {
        return villeDepartId;
    }

    /**
     * Récupère l'ID de la ville d'arrivée.
     * 
     * @return l'ID de la ville d'arrivée.
     */
    public String getVilleArriveeId() {
        return villeArriveeId;
    }

    /**
     * Récupère l'ID du moyen de transport utilisé pour le voyage.
     * 
     * @return l'ID du moyen de transport.
     */
    public String getMoyenTransportId() {
        return moyenTransportId;
    }

    /**
     * Récupère l'heure de départ du voyage.
     * 
     * @return l'heure de départ sous forme de chaîne de caractères.
     */
    public String getHeureDepart() {
        return heureDepart;
    }

    /**
     * Récupère l'heure d'arrivée du voyage.
     * 
     * @return l'heure d'arrivée sous forme de chaîne de caractères.
     */
    public String getHeureArrivee() {
        return heureArrivee;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet `Voyage`.
     * 
     * @return une chaîne décrivant le voyage.
     */
    @Override
    public String toString() {
        return "Voyage{id='" + id + "', dateDepart=" + dateDepart + ", dateArrivee=" + dateArrivee + 
               ", villeDepartId='" + villeDepartId + "', villeArriveeId='" + villeArriveeId + 
               "', moyenTransportId='" + moyenTransportId + "'}";
    }
}
