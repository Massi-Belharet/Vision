package gui;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import config.AppConfiguration;
import data.Coordinates;
import data.DataHolder;
import data.Element;
import data.Person;
import engine.ElementDetector;
import log.LoggerUtility;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
/**
 * Paint strategy 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class PaintStrategy{
	
    private BufferedImage dashboardImage;
    private DataHolder dataholder = DataHolder.getInstance();
    private BufferedImage fireImage;
    private BufferedImage personIcon;
    private BufferedImage terrainImage;
    private Random random = new Random();
    private static final Logger logger = LoggerUtility.getLogger(PaintStrategy.class,"html");
    
    public PaintStrategy(BufferedImage dashboardImage) {
        this.dashboardImage = dashboardImage;
        // Charger l'image de feu
        try {
            fireImage = ImageIO.read(new File("src/resource/Fire.png"));
            personIcon = ImageIO.read(new File("src/resource/person.png"));
            terrainImage = ImageIO.read(new File("src/resource/terrain.png"));
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
/**
 * Add Fire element in a random position 
 * @param dashboard
 */
    public void addRandomFire(Dashboard dashboard) {
        // Ajouter de 12 Ã  1 images de feu Ã  chaque fois
        int numFires = random.nextInt(16) + 12;
        for (int i = 0; i < numFires; i++) {
            // Calculer les coordonnÃ©es rÃ©elles dans l'image principale
        	
            int X = random.nextInt(AppConfiguration.DASHBOARD_WIDTH/ AppConfiguration.ELEMENT_SIZE);
            int Y = random.nextInt(AppConfiguration.DASHBOARD_HEIGHT/ AppConfiguration.ELEMENT_SIZE);
           int actualX=X*AppConfiguration.ELEMENT_SIZE;
            int actualY=Y*AppConfiguration.ELEMENT_SIZE;
            
            // Dessiner l'image de feu dans cette case
            Graphics2D g = dashboardImage.createGraphics();
            g.drawImage(fireImage, actualX, actualY, null);
            g.dispose();

            // Mettre Ã  jour l'image dans le composant Dashboard
            dashboard.setImage(dashboardImage);
            System.out.println("\nFire Added !!!!"+actualX+" , "+actualY);
            logger.info("\nFire Added !!!!"+actualX+" , "+actualY);
        }
    }
    
    /**
     * Add Person element to a random available position
     * @param dashboard
     */
    
    public void addPeople(Dashboard dashboard) {
        // Ajouter de personnes
        int numPeople = 7;
        for (int i = 0; i < numPeople; i++) {
            // Générer des coordonnées aléatoires
            int x = random.nextInt(AppConfiguration.DASHBOARD_WIDTH / AppConfiguration.ELEMENT_SIZE);
            int y = random.nextInt(AppConfiguration.DASHBOARD_HEIGHT / AppConfiguration.ELEMENT_SIZE);
            int actualX = x * AppConfiguration.ELEMENT_SIZE;
            int actualY = y * AppConfiguration.ELEMENT_SIZE;
            // Vérifier si la cellule ne contient pas de feu
            if (!isCellOccupied(actualX, actualY, dashboard.getImage())) {
                // Dessiner l'image de la personne dans cette cellule
                Graphics2D g = dashboardImage.createGraphics();
               
                g.drawImage(personIcon, actualX, actualY, null);
                g.dispose();
                
                Coordinates coordinates = new Coordinates(actualX, actualY, x, y);
                Element person = new Person(coordinates);
                // wrong
                dataholder.addData(person);
                dashboard.setImage(dashboardImage);
            }
        }

        // Mettre à jour l'image dans le composant Dashboard
        
    }
    
    /**
     * Move Person element
     * @param dashboard
     */
    public void movePeople(Dashboard dashboard) {
        // Récupérer l'image principale du tableau de bord
    	
        BufferedImage dashboardImage = dashboard.getImage();

        // Récupérer la liste des personnes du DataHolder
        ArrayList<Element> peopleList = dataholder.getData();

        // Parcourir toutes les personnes
        for (Element person : peopleList) {
        	if(person instanceof Person ) {
        		int currentX = person.getCoordinates().getX();
                int currentY = person.getCoordinates().getY();
                //why do we get 0 0 
                // Tant que la personne n'est pas déplacée dans une direction sans feu, continuez à choisir une nouvelle direction
                
                boolean moved = false;
                while (!moved) {
                    // Générer une direction de déplacement aléatoire
                    int direction = random.nextInt(4); // 0: haut, 1: bas, 2: gauche, 3: droite

                    // Calculer les nouvelles coordonnées en fonction de la direction choisie
                    int newX = currentX;
                    int newY = currentY;
                    
                    
                    switch (direction) {
                        case 0: // Haut
                            newY = Math.max(0, currentY - AppConfiguration.ELEMENT_SIZE );
                            break;
                        case 1: // Bas
                            newY = Math.min(AppConfiguration.DASHBOARD_HEIGHT - AppConfiguration.ELEMENT_SIZE , currentY + AppConfiguration.ELEMENT_SIZE);
                            break;
                        case 2: // Gauche
                            newX = Math.max(0, currentX - AppConfiguration.ELEMENT_SIZE);
                            break;
                        case 3: // Droite
                            newX = Math.min(AppConfiguration.DASHBOARD_WIDTH - AppConfiguration.ELEMENT_SIZE , currentX + AppConfiguration.ELEMENT_SIZE);
                            break;
                    }
                    
                    
                    // Vérifier si la cellule adjacente dans la direction choisie contient du feu
                    if (!isCellOccupied(newX, newY, dashboard.getImage())) {
                        // Supprimer l'image de la personne de son emplacement actuel en la remplaçant par l'image de terrain
                       
                        Graphics2D g = dashboardImage.createGraphics();
                        g.drawImage(terrainImage, currentX, currentY, null); // Remplacer par l'image de terrain
                        g.dispose();

                        // Dessiner l'image de la personne dans la nouvelle cellule
                      
                        g = dashboardImage.createGraphics();
                        g.drawImage(personIcon, newX, newY, null);
                        g.dispose();
                      
                        // Mettre à jour les coordonnées de la personne dans le DataHolder
                        person.getCoordinates().setX(newX);
                        person.getCoordinates().setY(newY);

                        moved = true; // Indiquer que la personne a été déplacée avec succès
                    }
                    // Sinon, continuez à choisir une nouvelle direction
                }
            }

            // Mettre à jour l'image dans le composant Dashboard
            dashboard.setImage(dashboardImage);
        	}
            
    }

    /**
     * Verify if a grid is occupied
     * @param x
     * @param y
     * @param image
     * @return boolean
     */
    
    private boolean isCellOccupied(int x, int y, BufferedImage image) {

        ElementDetector elementDetector = new ElementDetector();

        // Vérifier si la cellule contient du feu        System.out.println(" the x and y in the color test : "+x+" "+y+"  "+elementDetector.detectFire(image, x, y, AppConfiguration.ORANGE_COLOR));
        return elementDetector.detectFire(image, x, y, AppConfiguration.ORANGE_COLOR)|| elementDetector.detectFire(image, x, y, AppConfiguration.PERSON_COLOR);
    }
    
    
    
    
    
}
