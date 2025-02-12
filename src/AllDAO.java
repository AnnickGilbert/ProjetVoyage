import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AllDAO {
	
	
    static final String URL = "jdbc:mysql://localhost:3306/stocks?serverTimezone=Europe/Paris";

   static final String LOGIN = "root";
   static final String PASS = "";

   /**
	 * Constructeur de la classe
	 * 
	 */
	public AllDAO()
    {
        // chargement du pilote de bases de donn�es
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e2) {
            System.err.println(
                    "Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
        }

    }
    /*CREATE DATABASE IF NOT EXISTS `stocks`;
USE `stocks`;

-- Table structure for table `ville`
DROP TABLE IF EXISTS `ville`;
CREATE TABLE `ville` (
  `id` VARCHAR(50) NOT NULL,
  `nom` VARCHAR(100) NOT NULL,
  `pays` VARCHAR(100) NOT NULL,
  `code_postal` INT NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Datas for table `ville`
INSERT INTO `ville` VALUES ('1', 'Paris', 'France', 75000);
INSERT INTO `ville` VALUES ('2', 'Lyon', 'France', 69000);
INSERT INTO `ville` VALUES ('3', 'Marseille', 'France', 13000);
INSERT INTO `ville` VALUES ('4', 'Bordeaux', 'France', 33000);
INSERT INTO `ville` VALUES ('5', 'Toulouse', 'France', 31000);
INSERT INTO `ville` VALUES ('6', 'Lille', 'France', 59000);
INSERT INTO `ville` VALUES ('7', 'Nice', 'France', 06000);
INSERT INTO `ville` VALUES ('8', 'Nantes', 'France', 44000);
INSERT INTO `ville` VALUES ('9', 'Strasbourg', 'France', 67000);
INSERT INTO `ville` VALUES ('10', 'Montpellier', 'France', 34000);
INSERT INTO `ville` VALUES ('11', 'Rennes', 'France', 35000);
INSERT INTO `ville` VALUES ('12', 'Reims', 'France', 51100);
INSERT INTO `ville` VALUES ('13', 'Toulon', 'France', 83000);
INSERT INTO `ville` VALUES ('14', 'Grenoble', 'France', 38000);
INSERT INTO `ville` VALUES ('15', 'Dijon', 'France', 21000);
INSERT INTO `ville` VALUES ('16', 'Angers', 'France', 49000);
INSERT INTO `ville` VALUES ('17', 'Nîmes', 'France', 30000);
INSERT INTO `ville` VALUES ('18', 'Villeurbanne', 'France', 69100);
INSERT INTO `ville` VALUES ('19', 'Le Havre', 'France', 76600);
INSERT INTO `ville` VALUES ('20', 'Saint-Étienne', 'France', 42000);


-- Table structure for table `MoyenTransport`
DROP TABLE IF EXISTS `MoyenTransport`;
CREATE TABLE `MoyenTransport` (
  `id` VARCHAR(50) NOT NULL,
  `matricule` VARCHAR(100) NOT NULL,
  `type` VARCHAR(100) NOT NULL,
  `capacite` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
-- Insertion des données dans la table MoyenTransport
INSERT INTO `MoyenTransport` (`id`, `matricule`, `type`, `capacite`) VALUES
('1', 'AB-123-CD', 'Bus', '50'),
('2', 'EF-456-GH', 'Train', '300'),
('3', 'QR-345-ST', 'Avion', '250');



-- Table structure for table `sejour`
DROP TABLE IF EXISTS `sejour`;
CREATE TABLE `sejour` (
  `id` VARCHAR(50) NOT NULL,
  `dateEntree` DATE NOT NULL,
  `dateSortie` DATE NOT NULL,
  `type` ENUM('hotel', 'Particulier') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Insertion des données dans la table sejour
INSERT INTO `sejour` (`id`, `dateEntree`, `dateSortie`, `type`) VALUES
('1', '2023-10-01', '2023-10-05', 'hotel'),
('2', '2023-10-02', '2023-10-07', 'Particulier'),
('3', '2023-10-03', '2023-10-08', 'hotel'),
('4', '2023-10-04', '2023-10-09', 'Particulier'),
('5', '2023-10-05', '2023-10-10', 'hotel'),
('6', '2023-10-06', '2023-10-11', 'Particulier'),
('7', '2023-10-07', '2023-10-12', 'hotel'),
('8', '2023-10-08', '2023-10-13', 'Particulier'),
('9', '2023-10-09', '2023-10-14', 'hotel'),
('10', '2023-10-10', '2023-10-15', 'Particulier');


-- Table structure for table `client`
DROP TABLE IF EXISTS `client`;
CREATE TABLE `client` (
  `id` VARCHAR(50) NOT NULL,
  `nom` VARCHAR(100) NOT NULL,
  `prenom` VARCHAR(100) NOT NULL,
  `dateNaissance` DATE NOT NULL,
  `numeroTelephone` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Insertion des données dans la table client
INSERT INTO `client` (`id`, `nom`, `prenom`, `dateNaissance`, `numeroTelephone`) VALUES  
('1', 'Dupont', 'Jean', '1990-01-01', '0601020304'),  
('2', 'Durand', 'Pierre', '1991-02-02', '0602030405'),  
('3', 'Duchemin', 'Paul', '1992-03-03', '0603040506'),  
('4', 'Dufour', 'Jacques', '1993-04-04', '0604050607'),  
('5', 'Dumas', 'Jeanne', '1994-05-05', '0605060708'),  
('6', 'Dumont', 'Pierre', '1995-06-06', '0606070809'),  
('7', 'Duval', 'Paul', '1996-07-07', '0607080910'),  
('8', 'Duvall', 'Jacques', '1997-08-08', '0608091011'),  
('9', 'Duvivier', 'Jeanne', '1998-09-09', '0609101112'),  
('10', 'Duvillard', 'Pierre', '1999-10-10', '0610111213');  



DROP TABLE IF EXISTS `voyage`;
CREATE TABLE `voyage` (
  `id` VARCHAR(50) NOT NULL,
  `dateDepart` DATE NOT NULL,
  `dateArrivee` DATE NOT NULL,
  `villeDepart_id` VARCHAR(50) NOT NULL,
  `villeArrivee_id` VARCHAR(50) NOT NULL,
  `MoyenTransport_id` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`villeDepart_id`) REFERENCES `ville` (`id`),
  FOREIGN KEY (`villeArrivee_id`) REFERENCES `ville` (`id`),
  FOREIGN KEY (`MoyenTransport_id`) REFERENCES `MoyenTransport` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `voyage` (`id`, `dateDepart`, `dateArrivee`, `villeDepart_id`, `villeArrivee_id`, `MoyenTransport_id`) VALUES
('1', '2023-09-01', '2023-09-05', '1', '2', '1'),
('2', '2023-09-02', '2023-09-06', '2', '3', '2'),
('3', '2023-09-03', '2023-09-07', '3', '4', '3'),
('4', '2023-09-04', '2023-09-08', '4', '5', '1'),
('5', '2023-09-05', '2023-09-09', '5', '6', '2'),
('6', '2023-09-06', '2023-09-10', '6', '7', '3'),
('7', '2023-09-07', '2023-09-11', '7', '8', '1'),
('8', '2023-09-08', '2023-09-12', '8', '9', '2'),
('9', '2023-09-09', '2023-09-13', '9', '10', '3'),
('10', '2023-09-10', '2023-09-14', '10', '11', '1');

-- Table structure for table `reservation`
DROP TABLE IF EXISTS `reservation`;
CREATE TABLE `reservation` (
  `id` VARCHAR(50) NOT NULL,
  `dateReservation` DATE NOT NULL,
  `prix` VARCHAR(50) NOT NULL,
  `client_id` VARCHAR(50) NOT NULL,
  `sejour_id` VARCHAR(50) NOT NULL,
  `voyage_id` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  FOREIGN KEY (`sejour_id`) REFERENCES `sejour` (`id`),
  FOREIGN KEY (`voyage_id`) REFERENCES `voyage` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `reservation` (`id`, `dateReservation`, `prix`, `client_id`, `sejour_id`, `voyage_id`) VALUES
('1', '2023-09-01', '100', '1', '1', '1'),
('2', '2023-09-02', '200', '2', '2', '2'),
('3', '2023-09-03', '300', '3', '3', '3'),
('4', '2023-09-04', '400', '4', '4', '4'),
('5', '2023-09-05', '500', '5', '5', '5'),
('6', '2023-09-06', '600', '6', '6', '6'),
('7', '2023-09-07', '700', '7', '7', '7'),
('8', '2023-09-08', '800', '8', '8', '8'),
('9', '2023-09-09', '900', '9', '9', '9'),
('10', '2023-09-10', '1000', '10', '10', '10');*/
    //Faire une requete pour recuperer les voyages
    public List<Voyage> getVoyages() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Voyage> voyages = new ArrayList<>();

        try {
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement("SELECT * FROM voyage");
            rs = ps.executeQuery();
            while (rs.next()) {
                /*private String id;
    `id` VARCHAR(50) NOT NULL,
  `dateDepart` DATE NOT NULL,
  `dateArrivee` DATE NOT NULL,
  `heureDepart` VARCHAR(50) NOT NULL,
  `heureArrivee` VARCHAR(50) NOT NULL,
  `villeDepart_id` VARCHAR(50) NOT NULL,
  `villeArrivee_id` VARCHAR(50) NOT NULL,
  `MoyenTransport_id` VARCHAR(50) NOT NULL,*/
                Voyage voyage = new Voyage(rs.getString("id"), rs.getDate("dateDepart").toLocalDate(),
                        rs.getDate("dateArrivee").toLocalDate(), rs.getTime("heureDepart").toString(),
                        rs.getTime("heureArrivee").toString(), rs.getString("villeDepart_id"), rs.getString("villeArrivee_id"),
                        rs.getString("MoyenTransport_id"));
                voyages.add(voyage);
            }
        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
            }
        }
        return voyages;
    }
    
    //une requete pour recuperer les informations des voyages
    public List<VoyageInformation> getVoyageInformations() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<VoyageInformation> voyageInformations = new ArrayList<>();

        try {
            /*VoyageInformation {
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
 */
            con = DriverManager.getConnection(URL, LOGIN, PASS);
            ps = con.prepareStatement(
                    "SELECT v1.nom AS villeDepart, v2.nom AS villeArrivee, voy.dateDepart,voy.dateArrivee, voy.heureDepart, voy.heureArrivee, mt.type AS moyenTransport, res.prix, c.nom AS clientNom, c.prenom AS clientPrenom, se.type AS typeSejour FROM voyage voy JOIN ville v1 ON voy.villeDepart_id = v1.id JOIN ville v2 ON voy.villeArrivee_id = v2.id JOIN MoyenTransport mt ON voy.MoyenTransport_id = mt.id JOIN reservation res ON voy.id = res.voyage_id JOIN client c ON res.client_id = c.id JOIN sejour se ON res.sejour_id = se.id");
            rs = ps.executeQuery();
            while (rs.next()) {
                VoyageInformation voyageInformation = new VoyageInformation(rs.getString("villeDepart"),
                        rs.getString("villeArrivee"), rs.getDate("dateDepart").toLocalDate(),
                        rs.getDate("dateArrivee").toLocalDate(), rs.getTime("heureDepart").toString(),
                        rs.getTime("heureArrivee").toString(), rs.getString("moyenTransport"), rs.getDouble("prix"),
                        rs.getString("clientNom"), rs.getString("clientPrenom"), rs.getString("typeSejour"));
                voyageInformations.add(voyageInformation);
            }

        } catch (SQLException ee) {
            ee.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
            }
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
            }
            try {
                if (con != null)
                    con.close();
            } catch (SQLException e) {
            }
        }
        return voyageInformations;
    }


    
    
}
