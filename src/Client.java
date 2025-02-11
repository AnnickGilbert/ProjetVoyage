import java.time.LocalDate;

public class Client {
    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String numeroTelephone;

    public Client(String id, String nom, String prenom, LocalDate dateNaissance, String numeroTelephone) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.numeroTelephone = numeroTelephone;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    @Override
    public String toString() {
        return "Client{id='" + id + "', nom='" + nom + "', prenom='" + prenom + "', dateNaissance=" + dateNaissance + ", numeroTelephone='" + numeroTelephone + "'}";
    }
}
