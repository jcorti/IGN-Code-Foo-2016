package com.ign.corti;

import java.awt.EventQueue; 
/**
 * Main class for the Alien program 
 * @author corti
 *
 */
public class AlienPRun {
	//Main method
	public static void main(String[] args)
	{
		// EventQueue 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//try catch block for the GUI
				try {
					//this calls the constructor for FrameWork
					FrameWork guiStart = new FrameWork();	
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});
	}
}
