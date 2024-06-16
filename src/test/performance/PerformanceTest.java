package test.performance;

import org.junit.Test;

import config.AppConfiguration;
import data.DataHolder;
import gui.Info;
import gui.Scenario;
import log.LoggerUtility;
import gui.Dashboard;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
/**
 * Performance Test 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class PerformanceTest {
	private static final Logger logger = LoggerUtility.getLogger(PerformanceTest.class,"html");
	
    @Test(timeout = 60000) // Limite le test � 60 secondes
    
    public void testFireDetectionPerformance() {

        Info info = new Info(new Dashboard(AppConfiguration.FIRE_SCENARIO_IMAGE));


        int zoneSize = AppConfiguration.ZONE_SIZE;
        int dashboardWidth = AppConfiguration.DASHBOARD_WIDTH;
        int dashboardHeight = AppConfiguration.DASHBOARD_HEIGHT;
        int analyzeSpeed = AppConfiguration.ANALYZE_SPEED;

        int initialDataSize = DataHolder.getInstance().getData().size();
        long startTime = System.currentTimeMillis();

        for (int zoneY = 0; zoneY < dashboardHeight; zoneY += zoneSize) {
            for (int zoneX = 0; zoneX < dashboardWidth; zoneX += zoneSize) {

                info.fireScenarioInfoDisplay(zoneX, zoneY);

             // Attendez le temps de l'ANALYZE_SPEED avant de passer � la prochaine zone
                try {
                    Thread.sleep(analyzeSpeed); 
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logger.error(e.getMessage());
                }
            }
        }

        long endTime = System.currentTimeMillis();
        int finalDataSize = DataHolder.getInstance().getData().size();
        assertTrue(finalDataSize > initialDataSize);

        if (finalDataSize > initialDataSize) {
            DataHolder.getInstance().displayData();
        }

        long elapsedTime = endTime - startTime;
        double elapsedTimeMinutes = convertMillisecondsToSeconds(elapsedTime);
        System.out.println("Temps ecoule pour la detection de feu : " + elapsedTimeMinutes + " secondes");
        logger.info("Temps ecoule pour la detection de feu : " + elapsedTimeMinutes + " secondes");
    }
    
    public double convertMillisecondsToSeconds(long milliseconds) {
        return (double) milliseconds / 1000.0; 
    }
}
