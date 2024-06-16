package gui;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import config.AppConfiguration;

import engine.Statistics;
import log.LoggerUtility;
import engine.Chronometer;

import org.apache.log4j.Logger;
/**
 * Scenario 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public abstract class Scenario extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	private Scenario instance= this;
	private static final Logger logger = LoggerUtility.getLogger(Scenario.class,"html");
	
	protected Dashboard dashboard; 
	protected PaintStrategy paintStrategy;
	protected Info info;
	protected Statistics stat=new Statistics();

	private JPanel startButtonPan= new JPanel();
	private JPanel gapPanel=new JPanel();
	protected JButton startPause= new JButton("Start");
	protected boolean stop=true;
	private  JSlider speed ;
	JLabel indicator;
	JLabel simulationTime;
	
	protected Chronometer chrono=new Chronometer();


	    private final long task1Delay = 1000;
	    private long lastTask1Time = 0;
	    private long lastTask2Time = 0;
	
	private int sp =AppConfiguration.ANALYZE_SPEED;
	public Scenario(String title, String imagePath) {
		super(title);
		logger.debug("Debug message");
        logger.warn("Warning message");
        logger.error("Error message");
		init(imagePath);
		
		
	}
	/**
	 * Initialization 
	 * @param imagePath
	 */
	public void init(String imagePath) {
		SpeedCurser slider = new SpeedCurser();
		// the code for initiating the GUI
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		dashboard= new Dashboard(imagePath);
		info= new Info(dashboard);
		paintStrategy=new PaintStrategy(dashboard.getImage());
		//some styling
		startPause.setBackground(AppConfiguration.BUTTON_COLOR);
		startPause.setForeground(AppConfiguration.TEXT_COLOR);
		startPause.setFont(AppConfiguration.BUTTON_FONT);
		startButtonPan.setBackground(AppConfiguration.BACKGROUND_COLOR);
		// added this for styling 
		gapPanel.setPreferredSize(new Dimension(20, 620));
		gapPanel.setBackground(AppConfiguration.BACKGROUND_COLOR);
		
		// adding components
		
		startButtonPan.add(startPause);
	    // Adding speed indicator label
	    indicator = new JLabel();
	    indicator.setHorizontalAlignment(SwingConstants.LEFT);
	    indicator.setPreferredSize(new Dimension(150, 15));
	    indicator.setText(" Simulation Speed: 1 ");
	    indicator.setForeground(AppConfiguration.TEXT_COLOR);
	    startButtonPan.add(indicator);

	    // Adding speed slider
	    speed = new JSlider();
	    speed.setBackground(AppConfiguration.BACKGROUND_COLOR);
	    speed.setMinimum(1);
	    speed.setMaximum(10);
	    speed.setValue(1);
		speed.addChangeListener(slider);
	    startButtonPan.add(speed);
	    
	    simulationTime=new JLabel();
	    simulationTime.setText(chrono.toString());
	    simulationTime.setForeground(AppConfiguration.TEXT_COLOR);
	    Font font = simulationTime.getFont();
	    simulationTime.setFont(new Font(font.getName(), Font.PLAIN, 20));
	    startButtonPan.add(simulationTime);
	    
	    // Adding action listener to the start button
		startPause.addActionListener(new StartPauseAction());
		contentPane.add(dashboard, BorderLayout.CENTER);
		contentPane.add(gapPanel, BorderLayout.EAST);
		contentPane.add(info, BorderLayout.WEST);
		//start button
		contentPane.add(startButtonPan, BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		
		setResizable(false);
		setVisible(true);
		
	}
	
	/**
	 * update values
	 */
	protected abstract void updateValue(); 
	
	
	/**
	 * handle Start and pause actions
	 */
	private class StartPauseAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(stop){
				stop=false;
				Thread mainThread= new Thread(instance);
				mainThread.start();
				//logs
				/*logger.debug("Debug message");
		        logger.info("Info message");
		        logger.warn("Warning message");
		        logger.error("Error message");
				*/
				startPause.setText("Pause");
				
				
			}else {
				startPause.setText("Start");
				stop=true;
				
			}
			
		}
		
	}
	
	/**
	 * Action of the simulation speed
	 */
	 
	private class SpeedCurser implements ChangeListener {
	
		@Override
		public void stateChanged(ChangeEvent e) {
			indicator.setText("Vitesse de simulation : " + speed.getValue());
			switch (speed.getValue()){
				case 1:
					sp = AppConfiguration.ANALYZE_SPEED;
					break;
				case 2:
					sp = AppConfiguration.ANALYZE_SPEED/2;
					break;
				case 3:
					sp = AppConfiguration.ANALYZE_SPEED/3;
					break;
				case 4:
					sp = AppConfiguration.ANALYZE_SPEED/4;
					break;
				case 5:
					sp = AppConfiguration.ANALYZE_SPEED/5;
					break;
				case 6:
					sp = AppConfiguration.ANALYZE_SPEED/6;
					break;
				case 7:
					sp = AppConfiguration.ANALYZE_SPEED/7;
					break;
				case 8:
					sp = AppConfiguration.ANALYZE_SPEED/8;
					break;
				case 9:
					sp = AppConfiguration.ANALYZE_SPEED/9;
					break;
				case 10:
					sp = AppConfiguration.ANALYZE_SPEED/10;
					break;
			}
		}
	}
/**
 * Run method
 */
	@Override
	public void run() {
		while (!stop) {
			
			 while (!stop) {
				 long currentTime = System.currentTimeMillis();

		            if (currentTime - lastTask1Time >= task1Delay) {
		            	chrono.increament();
	                    simulationTime.setText(chrono.toString());
		                lastTask1Time = currentTime;
		            }		           
		            if (currentTime - lastTask2Time >= sp) {
		            	updateValue();
		                lastTask2Time = currentTime;
		            }
		            
		            try {
		                Thread.sleep(100);
		            } catch (InterruptedException e) {
		                e.printStackTrace();
		                logger.error(e.getMessage());
		            }
		        }
		}
		
	}
	
}

	
