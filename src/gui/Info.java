package gui;
import javax.swing.*;

import org.apache.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import config.AppConfiguration;
import config.Utils;
import data.Coordinates;
import data.DataHolder;
import data.Element;
import data.Fire;
import data.Pothole;
import engine.ElementDetector;
import engine.GeometryDetection;
import log.LoggerUtility;
/**
 * The panel Info 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */

public class Info extends JPanel implements ActionListener{

private static final long serialVersionUID = 1L;
private DataHolder dataholder = DataHolder.getInstance();
private JLabel statisticsLabel;
private ElementDetector elementDetector;
private GeometryDetection geoDetector;
private static final Logger logger = LoggerUtility.getLogger(Info.class,"html");
private Dashboard dashboard;
private JPanel statPan;
private JMenuItem recherche, quitter ,Help ;
private JTextArea scrollContent;
public JTextArea statContent;
public double stat=0;
public Info(Dashboard dashboard) {
	
    this.elementDetector = new ElementDetector();
    this.geoDetector= new GeometryDetection();
    this.dashboard = dashboard;
    setLayout(new BorderLayout());
 
    // Menu bar
    JMenuBar menuBar = new JMenuBar();
    menuBar.setBackground(Color.lightGray);

    JMenu Fichier = new JMenu("File");
    menuBar.add(Fichier);
    

    Help = new JMenuItem("Help ?");
    menuBar.add(Help);
    Help.setPreferredSize(new Dimension(45, 10));
    Help.setHorizontalAlignment(SwingConstants.CENTER);
    Help.setBackground(Color.LIGHT_GRAY);
    ActionListener actionListener = this;
    Help.addActionListener(actionListener);

	recherche = new JMenuItem("Nouvelle Recherche");
	quitter = new JMenuItem("Quitter !");

    Fichier.add(recherche);
    Fichier.add(quitter);
    quitter.addActionListener(this);
    recherche.addActionListener(this);
    Fichier.addActionListener(actionListener);
    

    // End of menu bar

    add(dashboard, BorderLayout.CENTER);
    statisticsLabel = new JLabel("Statistics Results");
    statisticsLabel.setFont(AppConfiguration.TILTLE_FONT);
    statisticsLabel.setForeground(AppConfiguration.TEXT_COLOR);
    
    setPreferredSize(new Dimension(AppConfiguration.INFO_PANEL_WIDTH, AppConfiguration.WINDOW_HEIGHT));
    setBackground(AppConfiguration.BACKGROUND_COLOR);
    statContent = new JTextArea(10, 30);
    statContent.setEditable(false);
    statContent.setBackground(AppConfiguration.BACKGROUND_COLOR);
    statContent.setForeground(AppConfiguration.TEXT_COLOR);
   
    statPan = new JPanel(new BorderLayout());
    statPan.setBackground(AppConfiguration.BACKGROUND_COLOR);
    statPan.add(statisticsLabel, BorderLayout.NORTH);
    statPan.add(statContent, BorderLayout.CENTER);
    scrollContent = new JTextArea(10, 30);
    scrollContent.append("RESULTS");
    scrollContent.setEditable(false);
    JScrollPane coordinatesScrollPane = new JScrollPane(scrollContent);
    coordinatesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    coordinatesScrollPane.setBackground(AppConfiguration.BACKGROUND_COLOR);
    coordinatesScrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove the border
    
    JViewport viewport = coordinatesScrollPane.getViewport();
    viewport.setBackground(AppConfiguration.BACKGROUND_COLOR);
    
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, coordinatesScrollPane, statPan);
    splitPane.setResizeWeight(0.5);
    splitPane.setDividerLocation(300);
    splitPane.setBorder(BorderFactory.createEmptyBorder()); 
    add(menuBar, BorderLayout.NORTH); 
    add(splitPane, BorderLayout.CENTER);

}
/**
 * Handles the quit and new window actions 
 */
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()== quitter)	{
		System.exit(0); // Quit the application
    }
    	
    if(e.getSource()==recherche) {
    	// Hide the current window
		 	 //currentWindow.setVisible(false);
    	 HomePage homePage = new HomePage();//Come Back to the beginning 
		 homePage.setVisible(true);
		 JFrame frame=(JFrame) SwingUtilities.getWindowAncestor(Info.this);
		 frame.dispose();
    		
    	}
    if(e.getSource()==Help) {//help dialog
		JOptionPane.showMessageDialog(this, "Welcome to DroneVision Simulation! \n\n DroneVision Simulation is designed to simulate the vision of a flying drone. The drone is on a specific mission to continuously detect and process images of the terrain in order to take necessary actions.\n\nNeed Help?\r\n"
				+ "If you have any questions or need assistance understanding how the application works, feel free to reach out to us. We're here to help you make the most of your experience with DroneVision Simulation.\r\n"
				+ "\r\n"
				+ "Enjoy Exploring the World of Drone Technology!", "Aide", JOptionPane.INFORMATION_MESSAGE);
		
	}
}	

/**
 * Display Fire Scenario related informations
 * @param zoneX
 * @param zoneY
 */

public void fireScenarioInfoDisplay(int zoneX, int zoneY) {
    if (dashboard.getImage() != null){
    	
    	for(int element_y=0; element_y<AppConfiguration.ZONE_SIZE; element_y+=AppConfiguration.ELEMENT_SIZE) {
    		for(int element_x=0; element_x<AppConfiguration.ZONE_SIZE; element_x+=AppConfiguration.ELEMENT_SIZE) {
    			
    			boolean fireDetected = elementDetector.detectElement(dashboard.getImage(), zoneX, zoneY, element_x, element_y, AppConfiguration.ORANGE_COLOR);
    			boolean personDetected = elementDetector.detectElement(dashboard.getImage(), zoneX, zoneY, element_x, element_y, AppConfiguration.BEIGE_COLOR);
    			  if (fireDetected){
    	    	      
    				  scrollContent.append("\n Fire detected! in : "+ element_x/50+","+element_y/50+"\n in the zone : "+zoneX/200+","+zoneY/200);
    	    	      System.out.println("\n Fire detected! in : "+ element_x/50+","+element_y/50+"\n in the zone : "+zoneX/200+","+zoneY/200); 
    	    	      logger.info("\n Fire detected! in : "+ element_x/50+","+element_y/50+"\n in the zone : "+zoneX/200+","+zoneY/200) ;
    	    	      // adding the data to the data holder 
    	    	        Element fire=new Fire(new Coordinates(element_x/50, element_y/50, zoneX/200, zoneY/200));
    	    	      
    	    	        this.dataholder.addData(fire);
    	    	        dataholder.addZoneData(Utils.zoneKeyGenarator(zoneX/200, zoneY/200), fire);
    	    	    }
    			  else if (personDetected){
      	    	    
    				  scrollContent.append("\n Person detected! in : "+ element_x/50+","+element_y/50+"\n in the zone : "+zoneX/200+","+zoneY/200);
    	    	      System.out.println("\n Person detected! in : "+ element_x/50+","+element_y/50+"\n in the zone : "+zoneX/200+","+zoneY/200); 
    	    	        
    	  
    	    	    }
    	    	    else {
    	    	    	System.out.println("\nNothing found !");
    	    	    }
    	    	   
    		}
    		// Scroll Auto
            scrollContent.setCaretPosition(scrollContent.getDocument().getLength());
    	}


    }

}

/**
 * Display Road's state scenario related informations
 * @param zoneX
 * @param zoneY
 */
public void potholesScenarioInfoDisplay(int zoneX, int zoneY) {
    if (dashboard.getImage() != null){
    	
    	for(int element_y=0; element_y<AppConfiguration.ZONE_SIZE; element_y+=AppConfiguration.ELEMENT_SIZE) {
    		for(int element_x=0; element_x<AppConfiguration.ZONE_SIZE; element_x+=AppConfiguration.ELEMENT_SIZE) {
    			
    			boolean potholeDetected = geoDetector.circleDetect(dashboard.getImage(), zoneX, zoneY, element_x, element_y);
    			if(zoneX==0 && zoneY==0) {
    				System.out.print(element_x+" "+element_y);
    			}
    			  if (potholeDetected){
    				  
    				  scrollContent.append("\n pothole detected! in : "+ element_x/50+","+element_y/50+"\n in the zone : "+zoneX/200+","+zoneY/200);
    	    	      System.out.println("\n pothole detected! in : "+ element_x/50+","+element_y/50+"\n in the zone : "+zoneX/200+","+zoneY/200); 
    	    	      logger.info("\n pothole detected! in : "+ element_x/50+","+element_y/50+"\n in the zone : "+zoneX/200+","+zoneY/200);  
    	    	      // adding the data to the data holder 
    	    	        Element pothole=new Pothole(new Coordinates(element_x/50, element_y/50, zoneX/200, zoneY/200));
    	    	      
    	    	        this.dataholder.addData(pothole);
    	    	        dataholder.addZoneData(Utils.zoneKeyGenarator(zoneX/200, zoneY/200), pothole);
    	    	    }
    	    	    else {
    	    	    	System.out.println("\nNothing !!!!");
    	    	    }
    	    	   
    		}
    		// Scroll Auto
            scrollContent.setCaretPosition(scrollContent.getDocument().getLength());
    	}


    }

}
	

}
