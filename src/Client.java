import java.time.LocalDate;

/**
 * Classe {@code Client} représentant un client effectuant une réservation.
 * 
 * <p>
 * Cette classe contient les informations personnelles d'un client, 
 * y compris son identifiant, nom, prénom, date de naissance et numéro de téléphone.
 * </p>
 *
 * @author tran
 * @version 1.0
 */
public class Client {
    /** Identifiant unique du client. */
    private String id;
    
    /** Nom du client. */
    private String nom;
    
    /** Prénom du client. */
    private String prenom;
    
    /** Date de naissance du client. */
    private LocalDate dateNaissance;
    
    /** Numéro de téléphone du client. */
    private String numeroTelephone;

    /**
     * Constructeur de la classe {@code Client}.
     * 
     * @param id               L'identifiant unique du client.
     * @param nom              Le nom du client.
     * @param prenom           Le prénom du client.
     * @param dateNaissance    La date de naissance du client (au format {@link LocalDate}).
     * @param numeroTelephone  Le numéro de téléphone du client.
     */
    public Client(String id, String nom, String prenom, LocalDate dateNaissance, String numeroTelephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numeroTelephone = numeroTelephone;
    }

    /**
     * Retourne l'identifiant du client.
     * 
     * @return L'identifiant du client.
     */
    public String getId() {
        return id;
    }

    /**
     * Retourne le nom du client.
     * 
     * @return Le nom du client.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le prénom du client.
     * 
     * @return Le prénom du client.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Retourne la date de naissance du client.
     * 
     * @return La date de naissance du client sous forme d'un objet {@link LocalDate}.
     */
    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Retourne le numéro de téléphone du client.
     * 
     * @return Le numéro de téléphone du client.
     */
    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    /**
     * Retourne une représentation textuelle du client.
     * 
     * @return Une chaîne contenant les informations du client.
     */
    @Override
    public String toString() {
        return "Client{id='" + id + "', nom='" + nom + "', prenom='" + prenom 
                + "', dateNaissance=" + dateNaissance 
                + ", numeroTelephone='" + numeroTelephone + "'}";
    }
}
