package gui;


import java.awt.Color;
import java.awt.Dimension;
 
import java.awt.Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import org.apache.log4j.Logger;

import config.AppConfiguration;
import log.LoggerUtility;

/**
 * Dash board of the GUI
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */

public class Dashboard extends JPanel{

	
	private static final long serialVersionUID = 1L;
	private Dimension preferedSize= new Dimension(AppConfiguration.DASHBOARD_WIDTH, AppConfiguration.DASHBOARD_HEIGHT);
	/**
	 * Image displayed in the dash board
	 */
	private BufferedImage image;
	/**
	 * Looger
	 */
	private static final Logger logger = LoggerUtility.getLogger(Dashboard.class,"html");
	private int zoneX=0;
	private int zoneY=0;
	private Color red= new Color(255, 0, 0, 90);
	/**
	 * Constructor of the Dashboard class
	 * 
	 * @param imagePath
	 */
    public Dashboard(String imagePath) {
        this.setPreferredSize(preferedSize);
        try {
        	image=ImageIO.read(new File(imagePath));
        }catch(Exception e) {
        	e.printStackTrace();
        	logger.error(e.getMessage());
        }
    }
/**
 * Painting the Dashboard
 * 
 * painting the image and the zone indication (with red) and dividing the dash board into grids
 * @param g
 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// draw the image if it's not null
		
		if(image!=null) {
			g.drawImage(image, 0, 0, AppConfiguration.DASHBOARD_WIDTH, AppConfiguration.DASHBOARD_HEIGHT, this);
		}
		g.setColor(new Color(245, 245, 245, 64));
		// vertical lines 
		
		for(int i=0; i<AppConfiguration.DASHBOARD_WIDTH; i+=AppConfiguration.ELEMENT_SIZE) {
			g.drawLine(i, 0, i, AppConfiguration.DASHBOARD_HEIGHT);
		}
		
		for(int i=0; i<AppConfiguration.DASHBOARD_HEIGHT; i+=AppConfiguration.ELEMENT_SIZE) {
			g.drawLine(0, i, AppConfiguration.DASHBOARD_WIDTH, i);
		}
		
		g.setColor(red);
		
		// we put the g in as a para in a method from the main
		g.fillRect(zoneX,zoneY,AppConfiguration.ZONE_SIZE,AppConfiguration.ZONE_SIZE);
		
	}

	/**
	 * Set a new image in the dash board
	 * @param imagePath
	 */
	public void setImage(String imagePath){
		 try {
	            // load the image from file
	            this.image = ImageIO.read(new File(imagePath));
	           
	            
	            // repaint the panel to update the displayed image
	            repaint();
	        } catch (IOException e) {
	            e.printStackTrace();
	            logger.error(e.getMessage());

	        }
	}
	// getters and setters
	public int getZoneX() {
		return zoneX;
	}

	public void setZoneX(int posX) {
		this.zoneX = posX;
	}
	public int getZoneY() {
		return zoneY;
	}

	public void setZoneY(int posY) {
		this.zoneY = posY;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
