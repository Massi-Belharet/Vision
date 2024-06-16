package engine;
/**
 * Chronometer 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class Chronometer {
	
	 int s=0;
	private int m=0;
	private int h=0;
	/**
	 * Increment the chronometer
	 */
	public void increament() {
		if(this.s<60){
			this.s++;
		}else {
			this.s=0;
			if(this.m<60){
				this.m++;
			}else{
				this.m=0;
				if(this.h<24){
					this.h++;
				}else {
					reset();
				}
			}
		}
	}
	/**
	 * Reset
	 */
	public void reset() {
		this.s=0;
		this.m=0;
		this.h=0;
	}
	/**
	 * Formulate hours/minutes/seconds
	 * @param i
	 * @return transformation
	 */
	public String transform(int i) {
		if(i<10) {
			return "0"+i;
		}
		return ""+i;
	}
	/**
	 * toString 
	 */
	
	@Override
	public String toString(){
		return transform(this.h)+":"+transform(this.m)+":"+transform(this.s);
	}

}
