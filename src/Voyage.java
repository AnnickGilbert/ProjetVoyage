import java.sql.Time;
import java.time.LocalDate;

public class Voyage {
    
    private String id;
    private LocalDate dateDepart;
    private LocalDate dateArrivee;
    private String heureDepart;
    private String heureArrivee;
    private String villeDepartId;
    private String villeArriveeId;
    private String moyenTransportId;

    public Voyage(String id, LocalDate dateDepart, LocalDate dateArrivee,String heureDepart, String heureArrivee,  String villeDepartId, String villeArriveeId, String moyenTransportId) {
        this.id = id;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.villeDepartId = villeDepartId;
        this.villeArriveeId = villeArriveeId;
        this.heureDepart = heureDepart.toString();
        this.heureArrivee = heureArrivee.toString();
        this.moyenTransportId = moyenTransportId;
    }
    public String getId() {
        return id;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public String getVilleDepartId() {
        return villeDepartId;
    }

    public String getVilleArriveeId() {
        return villeArriveeId;
    }

    public String getMoyenTransportId() {
        return moyenTransportId;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    @Override
    public String toString() {
        return "Voyage{id='" + id + "', dateDepart=" + dateDepart + ", dateArrivee=" + dateArrivee + 
               ", villeDepartId='" + villeDepartId + "', villeArriveeId='" + villeArriveeId + 
               "', moyenTransportId='" + moyenTransportId + "'}";
    }
}
