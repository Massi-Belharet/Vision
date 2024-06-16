package gui;


import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;

import config.AppConfiguration;
import data.DataHolder;

/**
 * Home page 
 * 
 * @author Feryel BOUBEKER, Massinissa BELHARET, Dyhia Tasseadit BOUDJAMEA
 */
public class HomePage extends JFrame implements ActionListener{
	
   
	private static final long serialVersionUID = 1L;
	
	
	private JButton fireDetect;
	private JButton potholesDetect;
	/**
	 * Constructor
	 */
    public HomePage(){
         
        JPanel panel=new JPanel();
        
        panel.setBorder(BorderFactory.createEmptyBorder(200,200,100,200));
        panel.setLayout(new GridBagLayout());
        panel.setBackground(AppConfiguration.BACKGROUND_COLOR); 

        // Create a GridBagConstraints object to manage button layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0; 
        gbc.gridy = 1; 
        gbc.insets = new Insets(10, 0, 0, 0); 
        
        // the fire and garbage button
        fireDetect=new JButton("Fire Detection");
        potholesDetect= new JButton("Road's state");
        
        Dimension buttonDimension= new Dimension(200, 30); 
        fireDetect.setPreferredSize(buttonDimension);
        fireDetect.setBackground(AppConfiguration.BUTTON_COLOR); 
        fireDetect.setForeground(Color.WHITE); 
        fireDetect.setBorderPainted(true);
        fireDetect.addActionListener(this);
        
        potholesDetect.setPreferredSize(buttonDimension);
        potholesDetect.setBackground(AppConfiguration.BUTTON_COLOR); 
        potholesDetect.setForeground(Color.WHITE); 
        potholesDetect.setBorderPainted(true);
        potholesDetect.addActionListener(this);
        
        
        
        // main title
        JLabel titleLabel = new JLabel("VISION");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Futura Hv BT", Font.BOLD, 150));
        titleLabel.setForeground(AppConfiguration.TEXT_COLOR);
    
        panel.add(titleLabel);
        panel.add(fireDetect, gbc);
        gbc.gridy++; 
        panel.add(potholesDetect, gbc);

        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("HomePage");
        pack();
        setVisible(true);
      
    }
    /**
     * the main method 
     * @param args
     */
    public static void main(String[] args){
        new HomePage();
    }
    /**
     * Actions performed depending on the button clicked
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		DataHolder.getInstance().clearData();
		
		if(e.getSource()==fireDetect) {
			
			FireScenario main=new FireScenario("Vision", AppConfiguration.FIRE_SCENARIO_IMAGE);
			Thread mainThread= new Thread(main);
			mainThread.start();
	        setVisible(false);
	        
		}
		if(e.getSource()== potholesDetect) {
			
			RoadScenario main=new RoadScenario("Vision", AppConfiguration.ROAD_SCENARIO_IMAGE);
			Thread mainThread= new Thread(main);
			mainThread.start();
	        setVisible(false);
		}
		
	}
    
 
}
