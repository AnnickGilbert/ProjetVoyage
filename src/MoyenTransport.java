// Source code is decompiled from a .class file using FernFlower decompiler.
public class MoyenTransport {
   private String id;
   private String matricule;
   private String type;
   private String capacite;

   public MoyenTransport(String id, String matricule, String type) {
      this.id = id;
      this.matricule = matricule;
      this.type = type;
      this.capacite = this.capacite;
   }

   public String getId() {
      return this.id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getMatricule() {
      return this.matricule;
   }

   public void setMatricule(String matricule) {
      this.matricule = matricule;
   }

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getCapacite() {
      return this.capacite;
   }

   public void setCapacite(String capacite) {
      this.capacite = capacite;
   }

   public String toString() {
      return "MoyenTransport [ID: " + this.id + ", Matricule: " + this.matricule + ", Type: " + this.type + ", Capacit\u00e9: " + this.capacite + "]";
   }
}
