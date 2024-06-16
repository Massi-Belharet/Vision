package config;
/**
 * Utils and Methods used in multiple classes 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class Utils {
		/**
		 * 
		 * @param zoneX
		 * @param zoneY
		 * @return the generated key
		 */
		public static String zoneKeyGenarator(int zoneX, int zoneY) {
			return zoneX+"-"+zoneY;
		}
		
}
