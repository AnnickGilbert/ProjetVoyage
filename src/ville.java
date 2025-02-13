/**
 * Cette classe représente une ville avec des attributs tels que l'ID, le nom, le pays et le code postal.
 * Elle inclut des méthodes getter et setter pour chaque attribut ainsi qu'une méthode `toString`
 * qui fournit une représentation textuelle de la ville.
 */
public class Ville {
   private String id;
   private String nom;
   private String pays;
   private int codePostal;

   /**
    * Constructeur d'une ville avec les paramètres spécifiés : ID, nom, pays et code postal.
    *
    * @param id l'identifiant unique de la ville.
    * @param nom le nom de la ville.
    * @param pays le pays où la ville est située.
    * @param codePostal le code postal de la ville.
    */
   public Ville(String id, String nom, String pays, int codePostal) {
      this.id = id;
      this.nom = nom;
      this.pays = pays;
      this.codePostal = codePostal;
   }

   /**
    * Récupère l'ID de la ville.
    *
    * @return l'ID de la ville.
    */
   public String getId() {
      return this.id;
   }

   /**
    * Modifie l'ID de la ville.
    *
    * @param id le nouvel ID de la ville.
    */
   public void setId(String id) {
      this.id = id;
   }

   /**
    * Récupère le nom de la ville.
    *
    * @return le nom de la ville.
    */
   public String getNom() {
      return this.nom;
   }

   /**
    * Modifie le nom de la ville.
    *
    * @param nom le nouveau nom de la ville.
    */
   public void setNom(String nom) {
      this.nom = nom;
   }

   /**
    * Récupère le pays où la ville est située.
    *
    * @return le pays de la ville.
    */
   public String getPays() {
      return this.pays;
   }

   /**
    * Modifie le pays de la ville.
    *
    * @param pays le nouveau pays de la ville.
    */
   public void setPays(String pays) {
      this.pays = pays;
   }

   /**
    * Récupère le code postal de la ville.
    *
    * @return le code postal de la ville.
    */
   public int getCodePostal() {
      return this.codePostal;
   }

   /**
    * Modifie le code postal de la ville.
    *
    * @param codePostal le nouveau code postal de la ville.
    */
   public void setCodePostal(int codePostal) {
      this.codePostal = codePostal;
   }

   /**
    * Retourne une représentation sous forme de chaîne de caractères de l'objet Ville.
    *
    * @return une chaîne décrivant la ville, comprenant son ID, son nom, son pays et son code postal.
    */
   public String toString() {
      return "Ville [ID: " + this.id + ", Nom: " + this.nom + ", Pays: " + this.pays + ", Code Postal: " + this.codePostal + "]";
   }
}
