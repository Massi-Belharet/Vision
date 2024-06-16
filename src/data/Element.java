package data;
/**
 * The Elements in the simulation 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class Element {

	
	/**
	 * Coordinates of the elements
	 */
	private Coordinates coordinates;
	
	private String type;
	
	/**
	 * Constructor of the class Element
	 * @param coordinates
	 * @param type
	 */
	public Element(Coordinates coordinates, String type) {
		this.coordinates=coordinates;
		this.type=type;
	}
	
	//setters and getters
	public Coordinates getCoordinates() {
		return coordinates;
	}
	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
