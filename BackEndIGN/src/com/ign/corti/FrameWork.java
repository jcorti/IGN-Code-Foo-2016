package com.ign.corti;


import javax.swing.*;
import javax.swing.text.DefaultCaret;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import com.ign.corti.KeybordSetup;
/**
 * Thisa is where the gui is created 
 * @author corti
 *
 */
public class FrameWork{
	
	private JFrame frame;
    
	private KeybordSetup keybd;
	/**
	 * constructor
	 */
	public FrameWork()
    {
		//this pretends that an alien virus has been found on your computer it asks you if you wanna proceed if you say no the program closses if you say yes the program runs
        int buttonInt= JOptionPane.showConfirmDialog(null,"Your computer is infected with an \"Alien Program\" from Omicron Persei 8 \nplease run the Diag program to fix your computer","Warning", JOptionPane.YES_NO_OPTION);
        if(buttonInt == JOptionPane.YES_OPTION){
        	JOptionPane.showMessageDialog(null,"AZERTY virus detected:QWERTY vacine stated","Fortune",JOptionPane.INFORMATION_MESSAGE);
        	jFrameSetup();
        }else{
        	System.exit(0);        
        }
    }
    /**
     * The start of the JFrame 
     */
    public void jFrameSetup()
    {

		frame = new JFrame("Alien Program"); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setOpaque(true);
		
		JTextArea textA = new JTextArea(15,50);
		textA.setWrapStyleWord(true);
		textA.setEditable(false);
		textA.setFont(Font.getFont(Font.SANS_SERIF));
		
		JScrollPane scro = new JScrollPane();
		scro.setPreferredSize(new Dimension(250,80)); 
		
		JPanel inputP = new JPanel();
		inputP.setLayout(new FlowLayout());
		JTextArea input = new JTextArea(1,20);
		
		keybd = new KeybordSetup(input);
		input.addKeyListener(keybd);
		inputP.add(input);
		
		JButton revertButton = new JButton("Revert"); 
		DefaultCaret car = (DefaultCaret) textA.getCaret();
		car.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		panel.add(scro);
		inputP.add(revertButton); 
		panel.add(inputP);

		frame.getContentPane().add(BorderLayout.CENTER,panel);
		frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(true);
        input.requestFocus();
        revertButton.addActionListener(new ActionListener() {
			//I made a boolean variable global in the KeybordSetup class so i can call it here and change the keybord with a click of the button
			@Override
			public void actionPerformed(ActionEvent e) {
				KeybordSetup.qwerty = !KeybordSetup.qwerty;
			}
		});
    }
    
    
}