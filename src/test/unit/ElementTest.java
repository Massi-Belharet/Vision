package test.unit;

import org.junit.Test;
import static org.junit.Assert.*;


import data.Element;
import data.Coordinates;

/**
 * Elements test 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */

public class ElementTest {
    
	@Test
    public void testGetX() {
        Element element = new Element(new Coordinates(0, 0, 0, 0), "fire");
        assertTrue(element.getCoordinates().getX() == 0);
    }

    @Test
    public void testSetX() {
        Element element = new Element(new Coordinates(0, 0, 0, 0), "fire");
        element.getCoordinates().setX(10);
        assertTrue(element.getCoordinates().getX() == 10);
    }

    @Test
    public void testGetY() {
        Element element = new Element(new Coordinates(0, 0, 0, 0), "fire");
        assertTrue(element.getCoordinates().getY() == 0);
    }

    @Test
    public void testSetY() {
        Element element = new Element(new Coordinates(0, 0, 0, 0), "fire");
        element.getCoordinates().setY(20);
        assertTrue(element.getCoordinates().getY() == 20);
    }

    @Test
    public void testGetType() {
        Element element = new Element(new Coordinates(0, 0, 0, 0), "fire");
        assertTrue(element.getType().equals("fire"));
    }

    @Test
    public void testSetType() {
        Element element = new Element(new Coordinates(0, 0, 0, 0), "fire");
        element.setType("newFire");
        assertTrue(element.getType().equals("newFire"));
    }
    

}