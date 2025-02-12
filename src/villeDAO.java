// Source code is decompiled from a .class file using FernFlower decompiler.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class villeDAO {
	static final String URL = "jdbc:mysql://localhost:3306/stocks?serverTimezone=Europe/Paris";

	   static final String LOGIN = "root";
	   static final String PASS = "";


   public villeDAO() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException var2) {
         System.err.println("Impossible de charger le pilote de BDD, assurez-vous d'importer le fichier .jar dans le projet");
      }

   }

   public int ajouter(ville nouvVille) {
      Connection con = null;
      PreparedStatement ps = null;
      int retour = 0;

      try {
         con = DriverManager.getConnection(URL,LOGIN, PASS);
         ps = con.prepareStatement("INSERT INTO ville (id, nom, pays, code_postal) VALUES (?, ?, ?, ?)");
         ps.setString(1, nouvVille.getId());
         ps.setString(2, nouvVille.getNom());
         ps.setString(3, nouvVille.getPays());
         ps.setInt(4, nouvVille.getCodePostal());
         retour = ps.executeUpdate();
      } catch (Exception var18) {
         var18.printStackTrace();
      } finally {
         try {
            if (ps != null) {
               ps.close();
            }
         } catch (Exception var17) {
         }

         try {
            if (con != null) {
               con.close();
            }
         } catch (Exception var16) {
         }

      }

      return retour;
   }

   public ville getVille(String id) {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      ville retour = null;

      try {
    	  con = DriverManager.getConnection(URL,LOGIN, PASS);
    	  ps = con.prepareStatement("SELECT * FROM ville WHERE id = ?");
         ps.setString(1, id);
         rs = ps.executeQuery();
         if (rs.next()) {
            retour = new ville(rs.getString("id"), rs.getString("nom"), rs.getString("pays"), rs.getInt("code_postal"));
         }
      } catch (Exception var23) {
         var23.printStackTrace();
      } finally {
         try {
            if (rs != null) {
               rs.close();
            }
         } catch (Exception var22) {
         }

         try {
            if (ps != null) {
               ps.close();
            }
         } catch (Exception var21) {
         }

         try {
            if (con != null) {
               con.close();
            }
         } catch (Exception var20) {
         }

      }

      return retour;
   }

   public List<ville> getListeVilles() {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<ville> retour = new ArrayList();

      try {
         con = DriverManager.getConnection(URL, LOGIN, PASS);
         ps = con.prepareStatement("SELECT * FROM ville");
         rs = ps.executeQuery();

         while (rs.next()) {
            retour.add(
                  new ville(rs.getString("id"), rs.getString("nom"), rs.getString("pays"), rs.getInt("code_postal")));
         }
      } catch (Exception var22) {
         var22.printStackTrace();
      } finally {
         try {
            if (rs != null) {
               rs.close();
            }
         } catch (Exception var21) {
         }

         try {
            if (ps != null) {
               ps.close();
            }
         } catch (Exception var20) {
         }

         try {
            if (con != null) {
               con.close();
            }
         } catch (Exception var19) {
         }

      }

      return retour;
   }
   
   



      


   public static void main(String[] args) {
      villeDAO villeDAO = new villeDAO();
      ville v = new ville("30", "Paris", "France", 75000);
      int retour = villeDAO.ajouter(v);
      System.out.println("" + retour + " ligne ajout\u00e9e");
      ville v2 = villeDAO.getVille("1");
      System.out.println(v2);
      List<ville> liste = villeDAO.getListeVilles();
      Iterator var6 = liste.iterator();

      while(var6.hasNext()) {
         ville ville = (ville)var6.next();
         System.out.println(ville);
      }

   }
}
