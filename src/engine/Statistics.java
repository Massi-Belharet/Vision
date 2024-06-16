package engine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.DataHolder;
import data.Element;
import config.Utils;
/**
 * Calculating multiple percentages and statistics
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class Statistics {
/**
 * the total number of grids in the dashboard
 */
	private final static int nbTotalBoxes=320;
	/**
	 * Total number of grids in a zone
	 */
	private final static int nbBoxes=16;
	/**
	 * The data about the Elements
	 */
	private ArrayList<Element> data =DataHolder.getInstance().getData();
	private HashMap<String, List<Element>> zoneData= DataHolder.getInstance().getZoneData();
	/**
	 * Percentage of an element in a zone 
	 * @param zoneX
	 * @param zoneY
	 * @return percentage
	 */
	public int getPrtZone(int zoneX, int zoneY) {
		int nbElements =0;
		
		String zoneKey=Utils.zoneKeyGenarator(zoneX, zoneY);
		if(zoneData.containsKey(zoneKey)) {
			nbElements=zoneData.get(Utils.zoneKeyGenarator(zoneX, zoneY)).size();
		}
		float percentage = ((float) nbElements / nbBoxes) * 100; 
	    return (int) percentage;
		
	}
	/**
	 * the percentage of elements in one evolution of the simulation
	 * @return  percentage
	 */
	public int getPrtEvolution() {
		float percentage = ((float) data.size()/nbTotalBoxes)*100;; 
		return (int)percentage;
	}
/**
 * 
 * @return the key(coordinates) of the most damaged zone 
 */
	
	public String getTheMostDamagedZone() {
		
		String Key="0-0";
		int max=0;
		
		for(String zoneKey : zoneData.keySet()) {
			int nbElements =zoneData.get(zoneKey).size();
			if(nbElements>=max) {
				Key=zoneKey;
				max=nbElements;
			}
		}
		return Key;
	}
	
	
	

}
