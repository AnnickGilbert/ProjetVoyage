// Source code is decompiled from a .class file using FernFlower decompiler.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MoyenTransportDAO {
	static final String URL = "jdbc:mysql://localhost:3306/bddvoyage?serverTimezone=Europe/Paris";

	   static final String LOGIN = "root";
	   static final String PASS = "";


   public MoyenTransportDAO() {
      try {
         Class.forName("com.mysql.cj.jdbc.Driver");
      } catch (ClassNotFoundException var2) {
         System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
      }

   }

   public int ajouter(MoyenTransport nouvMoyenTransport) {
      Connection con = null;
      PreparedStatement ps = null;
      int retour = 0;

      try {
         con = DriverManager.getConnection(URL,LOGIN, PASS);
         ps = con.prepareStatement("INSERT INTO MoyenTransport (id, matricule, type, capacite) VALUES (?, ?, ?, ?)");
         ps.setString(1, nouvMoyenTransport.getId());
         ps.setString(2, nouvMoyenTransport.getMatricule());
         ps.setString(3, nouvMoyenTransport.getType());
         ps.setString(4, nouvMoyenTransport.getCapacite());
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

   public MoyenTransport getMoyenTransport(String id) {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      MoyenTransport retour = null;

      try {
    	  con = DriverManager.getConnection(URL,LOGIN, PASS);
    	  ps = con.prepareStatement("SELECT * FROM MoyenTransport WHERE id = ?");
         ps.setString(1, id);
         rs = ps.executeQuery();
         if (rs.next()) {
            retour = new MoyenTransport(rs.getString("id"), rs.getString("matricule"), rs.getString("type"));
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

   public List<MoyenTransport> getListeMoyensTransport() {
      Connection con = null;
      PreparedStatement ps = null;
      ResultSet rs = null;
      List<MoyenTransport> retour = new ArrayList();

      try {
    	  con = DriverManager.getConnection(URL,LOGIN, PASS);
    	  ps = con.prepareStatement("SELECT * FROM MoyenTransport");
         rs = ps.executeQuery();

         while(rs.next()) {
            retour.add(new MoyenTransport(rs.getString("id"), rs.getString("matricule"), rs.getString("type")));
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
      MoyenTransportDAO dao = new MoyenTransportDAO();
      MoyenTransport mt = new MoyenTransport("1", "AB-123-CD", "Bus");
      int lignesAjoutees = dao.ajouter(mt);
      System.out.println("" + lignesAjoutees + " lignes ajout\u00e9es");
      MoyenTransport mt2 = dao.getMoyenTransport("1");
      System.out.println(mt2);
      List<MoyenTransport> liste = dao.getListeMoyensTransport();
      Iterator var6 = liste.iterator();

      while(var6.hasNext()) {
         MoyenTransport m = (MoyenTransport)var6.next();
         System.out.println(m);
      }

   }
}
