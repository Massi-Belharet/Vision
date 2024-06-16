package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Store Elements and their coordinates
 * 
 * this class is a Singleton
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class DataHolder {

	private ArrayList<Element> data;
	// or it can be a hash map where the key is the zone and the values are a list of elements
	
	private  HashMap<String, List<Element>> zoneData;
	/**
	 * The only instance of this class
	 */
	private static DataHolder instance=null;
	
	/**
	 * the private constructor of DataHolder
	 */
	private DataHolder() {
		data=new ArrayList<>();
		zoneData=new HashMap<>();
	}
	/**
	 * Add an element to the ArrayList data
	 * @param element
	 */
	public void addData(Element element) {
		this.data.add(element);
	
	}
	/**
	 * Add elements to the HashMap ZoneData 
	 * @param zoneKey
	 * @param element
	 */
	public void addZoneData(String zoneKey, Element element){
		
		if (zoneData.containsKey(zoneKey)) {
            
            List<Element> elements = zoneData.get(zoneKey);
            elements.add(element);
        } else {
            
            List<Element> elements = new ArrayList<>();
            elements.add(element);
            zoneData.put(zoneKey, elements);
        }
	}
	/**
	 * 
	 * @return data
	 */
	public ArrayList<Element> getData() {
		return data;
	}
	/**
	 * 
	 * @return zoneData
	 */
	public HashMap<String, List<Element>> getZoneData(){
		return zoneData;
	}
	/**
	 * Display the content of data array
	 */
	public void displayData(){
	    System.out.println("Contenu de la liste de donn�es : ");
	    for (int i = 0; i < data.size(); i++) {
	        Element element = data.get(i);
	        System.out.println("Index : " + i + ", Type : " + element.getType());
	    }
	}
	/**
	 * 
	 * @return instance
	 */
	public static DataHolder getInstance(){
		if(instance == null) {
			instance=new DataHolder(); 
		}
		return instance;
	}
	/**
	 * Clear data and zoneData
	 */
	public void clearData() {
		
		 Iterator<Element> iterator = data.iterator();
	        while (iterator.hasNext()) {
	            Element element = iterator.next();
	            if (element instanceof Fire) {
	                iterator.remove();
	            }
	        }
	   
		this.zoneData.clear();
	}
	
	       
}
