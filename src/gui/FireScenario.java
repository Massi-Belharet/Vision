package gui;

import java.awt.Color;
import javax.swing.Timer;

import org.apache.log4j.Logger;

import config.AppConfiguration;
import data.DataHolder;
import log.LoggerUtility;
/**
 * Fire Scenario 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class FireScenario extends Scenario{

	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerUtility.getLogger(FireScenario.class,"html");
	private int i=0;
    private  Timer timer;
    private float statPrt=0;
    private String zonePast="x-y";
    /**
     * Constructor of Fire Scenario
     * @param title
     * @param imagePath
     */
	public FireScenario(String title, String imagePath) {
		super(title, imagePath);
		 for(int j=0;j<=3;j++) {
			    for(int i=0;i<=4;i++) {
			    	info.statContent.append("Poucentage of fire in Zone ( " + i + "," + j + "):"+info.stat+" \n");
			    }
		    }
		 info.statContent.append("la zone la plus endomager est : x-y \n");
		 info.statContent.append("le pourcentage totale est : 0% \n");
		 paintStrategy.addPeople(dashboard);
		 timer = new Timer(4000, e -> paintStrategy.movePeople(dashboard)); // 4000 ms = 4 secondes
         timer.setRepeats(true);
         timer.start();
         
	}

	/**
	 * updates the values of the GUI
	 * 
	 * display new information and repaint the dash board 
	 */

	@Override
	protected void updateValue() {
		if(dashboard.getZoneX()<AppConfiguration.DASHBOARD_WIDTH-AppConfiguration.ZONE_SIZE) {
			if(dashboard.getZoneY()<AppConfiguration.DASHBOARD_HEIGHT) {
				
				// we detect the fire in the current zone 
				info.fireScenarioInfoDisplay(dashboard.getZoneX(), dashboard.getZoneY());
				//traitement affichage des stats
				String texte = info.statContent.getText();
			    String[] phrases = texte.split("\n");
			    String StatString = String.valueOf(stat.getPrtZone(dashboard.getZoneX()/200, dashboard.getZoneY()/200));
			    phrases[i]=phrases[i].replaceFirst(":" + String.valueOf(info.stat) , ":"+ StatString+"%");
				i++;
				StringBuilder texteModifie = new StringBuilder();
			    for (String phrase : phrases) {
			        texteModifie.append(phrase).append("\n");
			    } 
			    info.statContent.setText(texteModifie.toString());
				System.out.println("le pourcentage dans cette zone "+stat.getPrtZone(dashboard.getZoneX()/200, dashboard.getZoneY()/200)+"%");
				logger.info(texteModifie.toString());
				//update the zone 
				dashboard.setZoneX(dashboard.getZoneX()+AppConfiguration.ZONE_SIZE);
				this.dashboard.repaint();
				
				
			}else {
				int x=0;
				stop=true;
				//affichage console
				System.out.println("la zone la plus endomager est : "+stat.getTheMostDamagedZone());
				//affichage GUI
				String texte = info.statContent.getText();
			    String[] phrases = texte.split("\n");
			    StringBuilder texteModifie = new StringBuilder();
			    phrases[i]=phrases[i].replaceFirst(": "+zonePast,":"+stat.getTheMostDamagedZone());
			    phrases[i+1]=phrases[i+1].replaceFirst(": "+statPrt+"% " , ":"+ stat.getPrtEvolution()+"%");
			    statPrt=stat.getPrtEvolution();
			    zonePast=stat.getTheMostDamagedZone();
			    for (String phrase : phrases) {
			        texteModifie.append(phrase).append("\n");
			    } 
			   
			    info.statContent.setText(texteModifie.toString());
				
				//info.statContent.append("la zone la plus endomager est : "+stat.getTheMostDamagedZone()+"\n");
				logger.info("la zone la plus endomager est : "+stat.getTheMostDamagedZone());
				dashboard.setZoneX(0);
				dashboard.setZoneY(0);
				startPause.setText("Start");
				
				System.out.println("le pourcentage totale est : "+stat.getPrtEvolution()+"%");
				logger.info("le pourcentage totale est : "+stat.getPrtEvolution()+"%");
				//info.statContent.append("le pourcentage totale est : "+stat.getPrtEvolution()+"%");
				// i feel like this delay is important before the next evolution
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					
					System.out.println(e.getMessage());
					logger.info(e.getMessage());
				}
				
				// we call a method that changes the image 
		            paintStrategy.addRandomFire(dashboard);
		           
		            i=0;
		       
				this.dashboard.repaint();
				info.statContent.setText("");
				
				for(int j=0;j<=3;j++) {
				    for(int i=0;i<=4;i++) {
				    	info.statContent.append("Poucentage of fire in Zone ( " + i + "," + j + "):"+info.stat+" \n");
				    }
			    }
				info.statContent.append("la zone la plus endomager est : "+zonePast+" \n");
                info.statContent.append("le pourcentage totale est : "+statPrt+"%  ");
				stop=false;
				//continue the simulation from the beginning
				// we clear the data from the data holder each evolution
				DataHolder.getInstance().clearData();
				
			}
		}else {
			info.fireScenarioInfoDisplay(dashboard.getZoneX(), dashboard.getZoneY());
			//call the stat to print the previous zone's stats 
			String texte = info.statContent.getText();
		   
		    String[] phrases = texte.split("\n");
		    String StatString = String.valueOf(stat.getPrtZone(dashboard.getZoneX()/200, dashboard.getZoneY()/200));
		    phrases[i]=phrases[i].replaceFirst(":" + String.valueOf(info.stat) , ":"+ StatString+"%");
			i++;
			StringBuilder texteModifie = new StringBuilder();
		    for (String phrase : phrases) {
		        texteModifie.append(phrase).append("\n");
		    } 
		    
		    info.statContent.setText(texteModifie.toString());
			System.out.println("le pourcentage dans cette zone "+stat.getPrtZone(dashboard.getZoneX()/200, dashboard.getZoneY()/200)+"%");
			logger.info(texteModifie.toString());
			//update
			dashboard.setZoneX(0);
			dashboard.setZoneY(dashboard.getZoneY()+AppConfiguration.ZONE_SIZE);
			this.dashboard.repaint();
		}
		
	}

}