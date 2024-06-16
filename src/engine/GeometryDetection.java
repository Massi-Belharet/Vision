package engine;

import java.awt.Color;
import java.awt.image.BufferedImage;

import config.AppConfiguration;
/**
 * Detect the Geometry of the elements
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class GeometryDetection {
	/**
	 * Element detector 
	 */
	ElementDetector elemDetec = new ElementDetector();
	private static final int ELEMENT_X=AppConfiguration.ELEMENT_SIZE/2;
    private static final int ELEMENT_Y=AppConfiguration.ELEMENT_SIZE/2;
    
    private int radius= AppConfiguration.POTHOLE_RADIUS;
    
    /**
     * Detecting the geometry of a circle
     * @param image
     * @param zone_X
     * @param zone_Y
     * @param element_posX
     * @param element_posY
     * @return boolean
     */
	public boolean circleDetect(BufferedImage image, int zone_X, int zone_Y, int element_posX, int element_posY) {
		//if the color doesn't exist then no need for the geometry check
		if(!elemDetec.detectElement(image, zone_X, zone_Y, element_posX, element_posY, AppConfiguration.BLACK_COLOR)) {
			
			return false;
		}
		
		int centerX= zone_X+element_posX+ELEMENT_X;
    	int centerY=zone_Y+element_posY+ELEMENT_Y;
		   	
    	for(int angle=0; angle<360; angle+=45) {
    		double angleInRadians = Math.toRadians(angle);
    		int x=(int) (radius*Math.cos(angleInRadians))+centerX;
    		int y=(int) (radius*Math.sin(angleInRadians))+centerY;
    		
    		Color pixelColor = new Color(image.getRGB(x, y));
    		//if we find any unsimilarity we directly return false
    		if(!elemDetec.isSimilarColor(pixelColor, AppConfiguration.BLACK_COLOR)) {
    			return false;
    		}
    		
    	}
    	return true;
		
	}

}
