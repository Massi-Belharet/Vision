package engine;


import java.awt.Color;
import java.awt.image.BufferedImage;

import config.AppConfiguration;
/**
 * Detect multiple elements in the terrain
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class ElementDetector {
    /**
     * Tolerance of the color detection
     */
    private static final int TOLERANCE = 50; 
   /**
    * X coordinate of the pixel in the middle
    */
    private static final int ELEMENT_X=AppConfiguration.ELEMENT_SIZE/2;
    /**
     * Y coordinate of the pixel in the middle
     */
    private static final int ELEMENT_Y=AppConfiguration.ELEMENT_SIZE/2;
/**
 * Detect an element based on their pixels Color in an image in certain position
 * @param image
 * @param zone_X
 * @param zone_Y
 * @param element_posX
 * @param element_posY
 * @param color
 * @return boolean 
 */
    public boolean detectElement(BufferedImage image, int zone_X, int zone_Y, int element_posX, int element_posY, Color color){
    	int x= zone_X+element_posX+ELEMENT_X;
    	int y=zone_Y+element_posY+ELEMENT_Y;
    	
    		Color pixelColor = new Color(image.getRGB(x, y));
    		
    		return isSimilarColor(pixelColor, color);
    	
    }

    /**
     *  Method to check if two colors are similar within a tolerance range
     * @param c1
     * @param c2
     * @return boolean
     */
    protected boolean isSimilarColor(Color c1, Color c2) {
        int deltaRed = Math.abs(c1.getRed() - c2.getRed());
        int deltaGreen = Math.abs(c1.getGreen() - c2.getGreen());
        int deltaBlue = Math.abs(c1.getBlue() - c2.getBlue());
        return deltaRed <= TOLERANCE && deltaGreen <= TOLERANCE && deltaBlue <= TOLERANCE;
    }
    /**
     * Detect the Fire element
     * @param image
     * @param x
     * @param y
     * @param color
     * @return boolean
     */
    public boolean detectFire(BufferedImage image, int x, int y, Color color) {
    	x+=ELEMENT_X;
    	y+=ELEMENT_Y;
    	Color pixelColor = new Color(image.getRGB(x, y));
		
		return isSimilarColor(pixelColor, color);
    }
}