package config;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.border.EmptyBorder;

/**
 * this class contains Constant attributes for the application
 *  
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class AppConfiguration {
		public static final int WINDOW_HEIGHT=620;
		
		public static final int INFO_PANEL_WIDTH=260;
		
		public static final int DASHBOARD_HEIGHT=800;
		public static final int DASHBOARD_WIDTH=1000;
		public static final int ELEMENT_SIZE=50;
		public static final int ZONE_SIZE=200;
		
		public static int ANALYZE_SPEED=2000;
		
		public static final String FIRE_SCENARIO_IMAGE="src/resource/image1.png";
		public static final String ROAD_SCENARIO_IMAGE="src/resource/road1.png";
		
		public static final int POTHOLE_RADIUS=18;
		public static final Color ORANGE_COLOR = new Color(255,90,0);
		public static final Color BLACK_COLOR = new Color(0,0,0); 
		public static final Color PERSON_COLOR= new Color(232, 195, 158);
		public static final Color TEXT_COLOR = new Color(255, 255, 255);
	    public static final Color BACKGROUND_COLOR = new Color(48, 48, 48);
	    public static final Color BUTTON_COLOR = new Color(216, 110, 5); 
	    public static final Color LINE_COLOR = new Color(34, 34, 34);
	    
	    
	    public static final Font TILTLE_FONT = new Font("Futura Hv BT", 0, 24);
	    public static final Font TEXT_FONT = new Font("Futura Md BT", 0, 15);
	    public static final Font BUTTON_FONT = new Font("Futura BdCn BT", 0, 18);
	    
	    public static final Dimension BUTTON_DIMENSION= new Dimension(120,30);
	    public static final EmptyBorder PANEL_BORDER = new EmptyBorder(7, 5, 0, 5);
		public static final Color BORDER_COLOR = null;

		public static final Color BEIGE_COLOR =  new Color(232,195,158);
}
