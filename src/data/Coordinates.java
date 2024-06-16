package data;
/**
 * the coordinates used to locate the Elements 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class Coordinates {
	
	
	private int x;
	
	private int y;
	private int zoneX;
	private int zoneY;
	/**
	 * constructor for the class Coordinates
	 * @param x
	 * @param y
	 * @param zoneX
	 * @param zoneY
	 */
	
	public Coordinates(int x, int y, int zoneX, int zoneY) {
		this.x=x;
		this.y=y;
		this.zoneX=zoneX;
		this.zoneY=zoneY;
	}
	
	/**
	 * 
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * set x
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * 
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * set y
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * 
	 * @return zoneX
	 */
	public int getZoneX() {
		return zoneX;
	}
	/**
	 * set zoneX
	 * @param zoneX
	 */
	public void setZoneX(int zoneX) {
		this.zoneX = zoneX;
	}
	/**
	 * 
	 * @return zoneY
	 */
	public int getZoney() {
		return zoneY;
	}
	/**
	 * set zoneY
	 * @param zoneY
	 */
	public void setZoney(int zoneY) {
		this.zoneY = zoneY;
	}
}
