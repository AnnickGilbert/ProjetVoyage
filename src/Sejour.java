import java.sql.Date;

/**
 * Classe Sejour
 * @version 1.1
 */

public class Sejour {

    /** 
     * identifiant du séjour
     */
    private String id;        
    /**
     * date d'entrée
     */
    private Date dateEntree;    
    /**
     * date de sortie
     */
    private Date dateSortie;    
    /**
     * type de séjour (hotel ou Particulier)
     */
    private String type;

    /**
     * Constructeur
     * @param id identifiant du séjour
     * @param dateEntree date d'entrée
     * @param dateSortie date de sortie
     * @param type type de séjour (hotel ou Particulier)
     */
    public Sejour(String id, Date dateEntree, Date dateSortie, String type) {
        this.id = id;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.type = type;
    }

    /**
     * getter pour l'attribut id
     * @return valeur de l'identifiant du séjour
     */
    public String getId() {
        return id;
    }

    /**
     * setter pour l'attribut id
     * @param id : nouvelle valeur de l'identifiant du séjour
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * getter pour l'attribut dateEntree
     * @return valeur de la date d'entrée
     */
    public Date getDateEntree() {
        return dateEntree;
    }

    /**
     * setter pour l'attribut dateEntree
     * @param dateEntree : nouvelle valeur de la date d'entrée
     */
    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    /**
     * getter pour l'attribut dateSortie
     * @return valeur de la date de sortie
     */
    public Date getDateSortie() {
        return dateSortie;
    }

    /**
     * setter pour l'attribut dateSortie
     * @param dateSortie : nouvelle valeur de la date de sortie
     */
    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    /**
     * getter pour l'attribut type
     * @return valeur du type de séjour
     */
    public String getType() {
        return type;
    }

    /**
     * setter pour l'attribut type
     * @param type : nouvelle valeur du type de séjour
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Redéfinition de la méthode toString permettant de définir la traduction de l'objet en String
     * pour l'affichage par exemple
     */
    @Override
    public String toString() {
        return "Sejour [id : " + id + ", date d'entrée : " + dateEntree.toString() + ", date de sortie : " + dateSortie.toString() + ", type : " + type + "]";
    }
}