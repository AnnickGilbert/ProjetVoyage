import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Classe {@code BackgroundPanel} représentant un panneau avec une image de fond.
 *
 * <p>
 * Cette classe hérite de {@link JPanel} et surcharge la méthode {@code paintComponent}
 * pour dessiner une image en arrière-plan. Elle permet de charger une image à partir
 * d'un fichier et de l'afficher en l'ajustant à la taille du panneau.
 * </p>
 *
 * <p>
 * L'image de fond est redimensionnée dynamiquement en fonction des dimensions du panneau.
 * </p>
 *
 * @author tran
 * @version 1.0
 */
class BackgroundPanel extends JPanel {
    /** L'image de fond à afficher dans le panneau. */
    private Image backgroundImage;

    /**
     * Constructeur de {@code BackgroundPanel}.
     *
     * <p>
     * Charge l'image spécifiée par le chemin d'accès fourni en paramètre et l'assigne
     * comme arrière-plan du panneau.
     * </p>
     *
     * @param filePath Le chemin d'accès du fichier image à charger.
     */
    public BackgroundPanel(String filePath) {
        try {
            backgroundImage = ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur : Impossible de charger l'image " + filePath);
        }
        setLayout(null); // Permet un positionnement personnalisé des composants enfants
    }

    /**
     * Surcharge de la méthode {@code paintComponent} pour dessiner l'image de fond.
     *
     * <p>
     * Cette méthode est appelée automatiquement lors du rafraîchissement de l'interface
     * et assure que l'image est redimensionnée pour correspondre aux dimensions du panneau.
     * </p>
     *
     * @param g L'objet {@link Graphics} utilisé pour dessiner l'image.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
