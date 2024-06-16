package gui;

import org.apache.log4j.Logger;

import config.AppConfiguration;
import data.DataHolder;
import log.LoggerUtility;
/**
 * Road Scenario 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class RoadScenario extends Scenario{

	private static final Logger logger = LoggerUtility.getLogger(RoadScenario.class,"html");
	private static final long serialVersionUID = 1L;
	private int i=0;
	private int count=1;
    private float statPrt=0;
    private String zonePast="x-y";
	public RoadScenario(String title, String imagePath) {
		super(title, imagePath);
		 for(int j=0;j<=3;j++) {
			    for(int i=0;i<=4;i++) {
			    	info.statContent.append("Poucentage of potholes in Zone ( " + i + "," + j + "):"+info.stat+" \n");
			    }
		    }
		 info.statContent.append("la zone la plus endomager est : x-y \n");
		 info.statContent.append("le pourcentage totale est : 0% \n");
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
				
				// change the stuff here 
				info.potholesScenarioInfoDisplay(dashboard.getZoneX(), dashboard.getZoneY());
				
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
				
				stop=true;
				System.out.println("la zone la plus endomager est : "+stat.getTheMostDamagedZone());
				logger.info("la zone la plus endomager est : "+stat.getTheMostDamagedZone());
				
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
					logger.error(e.getMessage());
				}
				
				// we call a method that changes the image 
		          //  paintStrategy.addRandomFire(dashboard);
				
			
		       
				this.dashboard.repaint();
				info.statContent.setText("");
				
				for(int j=0;j<=3;j++) {
				    for(int i=0;i<=4;i++) {
				    	info.statContent.append("Poucentage of potholes in Zone ( " + i + "," + j + "):"+info.stat+" \n");
				    }
			    }
				info.statContent.append("la zone la plus endomager est : "+zonePast+" \n");
                info.statContent.append("le pourcentage totale est : "+statPrt+"%  ");
				count++;
				if(count<5) {
					this.dashboard.setImage("src/resource/road"+count+".png");
					stop=false;
				}
				
				i=0;
				//continue the simulation from the beginning

				// we clear the data from the data holder each evolution
				DataHolder.getInstance().clearData();
				
			}
		}else {
			// change this
			info.potholesScenarioInfoDisplay(dashboard.getZoneX(), dashboard.getZoneY());
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
