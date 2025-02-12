import java.sql.Time;
import java.time.LocalDate;

public class VoyageInformation {
    private String id;
    private String villeDepart;
    private String villeArrivee;
    private LocalDate dateDepart;
    private LocalDate dateArrivee;
    private String heureDepart;
    private String heureArrivee;
    private String moyenTransport;
    private double prix;
    private String clientNom;
    private String clientPrenom;
    private String typeSejour;

    // Constructeur
    public VoyageInformation(String id, String villeDepart, String villeArrivee, LocalDate dateDepart,
            LocalDate dateArrivee, String heureDepart, String heureArrivee, String moyenTransport, double prix,
            String clientNom, String clientPrenom, String typeSejour) {
        this.id = id;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.dateDepart = dateDepart;
        this.dateArrivee = dateArrivee;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.moyenTransport = moyenTransport;
        this.prix = prix;
        this.clientNom = clientNom;
        this.clientPrenom = clientPrenom;
        this.typeSejour = typeSejour;
    }
    

    // Getters and Setters
    public String getVilleDepart() {
        return villeDepart;
    }

    //getid

    public String getId() {
        return id;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public LocalDate getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    public String getMoyenTransport() {
        return moyenTransport;
    }

    public void setMoyenTransport(String moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    public String getClientPrenom() {
        return clientPrenom;
    }

    public void setClientPrenom(String clientPrenom) {
        this.clientPrenom = clientPrenom;
    }

    public String getTypeSejour() {
        return typeSejour;
    }

    public void setTypeSejour(String typeSejour) {
        this.typeSejour = typeSejour;
    }

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    @Override
    public String toString() {
        return "VoyageInformation{" +
                "villeDepart='" + villeDepart + '\'' +
                ", villeArrivee='" + villeArrivee + '\'' +
                ", dateDepart=" + dateDepart +
                ", dateArrivee=" + dateArrivee +
                ", moyenTransport='" + moyenTransport + '\'' +
                ", prix=" + prix +
                ", clientNom='" + clientNom + '\'' +
                ", clientPrenom='" + clientPrenom + '\'' +
                ", typeSejour='" + typeSejour + '\'' +
                '}';
    }
}

