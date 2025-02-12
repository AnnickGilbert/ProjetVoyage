import java.time.LocalDate;

/**
 * Cette classe représente les informations relatives à un voyage.
 * Elle contient les détails sur le voyage, tels que les villes de départ et d'arrivée,
 * les dates et heures de départ et d'arrivée, le moyen de transport, le prix, 
 * et les informations sur le client (nom, prénom) ainsi que le type de séjour.
 */
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

    /**
     * Constructeur de la classe VoyageInformation.
     * Initialise toutes les propriétés du voyage avec les informations fournies.
     * 
     * @param id L'identifiant unique du voyage.
     * @param villeDepart La ville de départ du voyage.
     * @param villeArrivee La ville d'arrivée du voyage.
     * @param dateDepart La date de départ du voyage.
     * @param dateArrivee La date d'arrivée du voyage.
     * @param heureDepart L'heure de départ du voyage.
     * @param heureArrivee L'heure d'arrivée du voyage.
     * @param moyenTransport Le moyen de transport utilisé pour ce voyage.
     * @param prix Le prix du voyage.
     * @param clientNom Le nom du client.
     * @param clientPrenom Le prénom du client.
     * @param typeSejour Le type de séjour (ex: vacances, affaires, etc.)
     */
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

    // Getters et Setters

    /**
     * Récupère la ville de départ.
     * 
     * @return La ville de départ.
     */
    public String getVilleDepart() {
        return villeDepart;
    }

    /**
     * Récupère l'identifiant du voyage.
     * 
     * @return L'identifiant du voyage.
     */
    public String getId() {
        return id;
    }

    /**
     * Modifie la ville de départ.
     * 
     * @param villeDepart La nouvelle ville de départ.
     */
    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    /**
     * Récupère la ville d'arrivée.
     * 
     * @return La ville d'arrivée.
     */
    public String getVilleArrivee() {
        return villeArrivee;
    }

    /**
     * Modifie la ville d'arrivée.
     * 
     * @param villeArrivee La nouvelle ville d'arrivée.
     */
    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    /**
     * Récupère la date de départ.
     * 
     * @return La date de départ.
     */
    public LocalDate getDateDepart() {
        return dateDepart;
    }

    /**
     * Modifie la date de départ.
     * 
     * @param dateDepart La nouvelle date de départ.
     */
    public void setDateDepart(LocalDate dateDepart) {
        this.dateDepart = dateDepart;
    }

    /**
     * Récupère la date d'arrivée.
     * 
     * @return La date d'arrivée.
     */
    public LocalDate getDateArrivee() {
        return dateArrivee;
    }

    /**
     * Récupère l'heure d'arrivée.
     * 
     * @return L'heure d'arrivée.
     */
    public String getHeureArrivee() {
        return heureArrivee;
    }

    /**
     * Modifie la date d'arrivée.
     * 
     * @param dateArrivee La nouvelle date d'arrivée.
     */
    public void setDateArrivee(LocalDate dateArrivee) {
        this.dateArrivee = dateArrivee;
    }

    /**
     * Récupère le moyen de transport.
     * 
     * @return Le moyen de transport.
     */
    public String getMoyenTransport() {
        return moyenTransport;
    }

    /**
     * Modifie le moyen de transport.
     * 
     * @param moyenTransport Le nouveau moyen de transport.
     */
    public void setMoyenTransport(String moyenTransport) {
        this.moyenTransport = moyenTransport;
    }

    /**
     * Récupère le prix du voyage.
     * 
     * @return Le prix du voyage.
     */
    public double getPrix() {
        return prix;
    }

    /**
     * Modifie le prix du voyage.
     * 
     * @param prix Le nouveau prix du voyage.
     */
    public void setPrix(double prix) {
        this.prix = prix;
    }

    /**
     * Récupère le nom du client.
     * 
     * @return Le nom du client.
     */
    public String getClientNom() {
        return clientNom;
    }

    /**
     * Modifie le nom du client.
     * 
     * @param clientNom Le nouveau nom du client.
     */
    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    /**
     * Récupère le prénom du client.
     * 
     * @return Le prénom du client.
     */
    public String getClientPrenom() {
        return clientPrenom;
    }

    /**
     * Modifie le prénom du client.
     * 
     * @param clientPrenom Le nouveau prénom du client.
     */
    public void setClientPrenom(String clientPrenom) {
        this.clientPrenom = clientPrenom;
    }

    /**
     * Récupère le type de séjour.
     * 
     * @return Le type de séjour.
     */
    public String getTypeSejour() {
        return typeSejour;
    }

    /**
     * Modifie le type de séjour.
     * 
     * @param typeSejour Le nouveau type de séjour.
     */
    public void setTypeSejour(String typeSejour) {
        this.typeSejour = typeSejour;
    }

    /**
     * Récupère l'heure de départ.
     * 
     * @return L'heure de départ.
     */
    public String getHeureDepart() {
        return heureDepart;
    }

    /**
     * Modifie l'heure de départ.
     * 
     * @param heureDepart La nouvelle heure de départ.
     */
    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères des informations du voyage.
     * 
     * @return Une chaîne représentant les informations du voyage.
     */
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
