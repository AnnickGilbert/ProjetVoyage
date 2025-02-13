/**
 * Source code is decompiled from a .class file using FernFlower decompiler.
 * 
 * Classe MoyenTransport représentant un moyen de transport avec un identifiant,
 * un matricule, un type et une capacité.
 */
public class MoyenTransport {
   private String id;
   private String matricule;
   private String type;
   private String capacite;

   /**
    * Constructeur de la classe MoyenTransport.
    * 
    * @param id Identifiant du moyen de transport
    * @param matricule Matricule du moyen de transport
    * @param type Type du moyen de transport
    */
   public MoyenTransport(String id, String matricule, String type) {
      this.id = id;
      this.matricule = matricule;
      this.type = type;
      this.capacite = this.capacite; // Problème potentiel : affectation incorrecte
   }

   /**
    * Retourne l'identifiant du moyen de transport.
    * 
    * @return id
    */
   public String getId() {
      return this.id;
   }

   /**
    * Définit l'identifiant du moyen de transport.
    * 
    * @param id Nouvel identifiant
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    * Retourne le matricule du moyen de transport.
    * 
    * @return matricule
    */
   public String getMatricule() {
      return this.matricule;
   }

   /**
    * Définit le matricule du moyen de transport.
    * 
    * @param matricule Nouveau matricule
    */
   public void setMatricule(String matricule) {
      this.matricule = matricule;
   }

   /**
    * Retourne le type du moyen de transport.
    * 
    * @return type
    */
   public String getType() {
      return this.type;
   }

   /**
    * Définit le type du moyen de transport.
    * 
    * @param type Nouveau type
    */
   public void setType(String type) {
      this.type = type;
   }

   /**
    * Retourne la capacité du moyen de transport.
    * 
    * @return capacite
    */
   public String getCapacite() {
      return this.capacite;
   }

   /**
    * Définit la capacité du moyen de transport.
    * 
    * @param capacite Nouvelle capacité
    */
   public void setCapacite(String capacite) {
      this.capacite = capacite;
   }

   /**
    * Retourne une représentation sous forme de chaîne de caractères du moyen de transport.
    * 
    * @return Description du moyen de transport
    */
   @Override
   public String toString() {
      return "MoyenTransport [ID: " + this.id + ", Matricule: " + this.matricule + ", Type: " + this.type + ", Capacité: " + this.capacite + "]";
   }
}
