/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imageoverlay;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Marius
 */
public class ImageOverlay {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
// load source images
BufferedImage image = ImageIO.read(new File("image.png"));
BufferedImage overlay = ImageIO.read(new File("overlay.png"));

// create the new image, canvas size is the max. of both image sizes
int w = Math.max(image.getWidth(), overlay.getWidth());
int h = Math.max(image.getHeight(), overlay.getHeight());
BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

// paint both images, preserving the alpha channels

Graphics2D g = combined.createGraphics();
float opacity = .4f;
g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
g.drawImage(image, 0, 0, null);
//g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity+0.3f));
g.drawImage(overlay, 0, 0, null);


// Save as new image
ImageIO.write(combined, "PNG", new File( "combined.png"));
        } catch (IOException ex) {
            Logger.getLogger(ImageOverlay.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
