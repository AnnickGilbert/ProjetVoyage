// Source code is decompiled from a .class file using FernFlower decompiler.
public class ville {
   private String id;
   private String nom;
   private String pays;
   private int codePostal;

   public ville(String id, String nom, String pays, int codePostal) {
      this.id = id;
      this.nom = nom;
      this.pays = pays;
      this.codePostal = codePostal;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getNom() {
      return this.nom;
   }

   public void setNom(String nom) {
      this.nom = nom;
   }

   public String getPays() {
      return this.pays;
   }

   public void setPays(String pays) {
      this.pays = pays;
   }

   public int getCodePostal() {
      return this.codePostal;
   }

   public void setCodePostal(int codePostal) {
      this.codePostal = codePostal;
   }

   public String toString() {
      return "Ville [ID: " + this.id + ", Nom: " + this.nom + ", Pays: " + this.pays + ", Code Postal: " + this.codePostal + "]";
   }
}
