package com.corti.scribdic;

import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;

public class ScrabbleDictionary {
	
	private int finalScore;
	private Scanner keybd;
	private Scanner input;
	private FileReader reader;
	private ArrayList<String> dic;
	private ArrayList<String> winWord;
	private ArrayList<String> dicTest;
	private ArrayList<String> winTest;
	private String path;
	
	public ScrabbleDictionary()
	{
		keybd = new Scanner(System.in);
		path = "scribDict.txt";
		finalScore = 0;
		dic = new ArrayList<String>();
		winWord = new ArrayList<String>();
		dicTest = new ArrayList<String>();
		winTest = new ArrayList<String>();
	}
	
	public void setDiction()
	{
		String temp = "";
		File file = new File(System.getProperty("user.dir") + "/" + path);
		
		try{
			reader = new FileReader(file);
			input = new Scanner(reader);
			
			while(input.hasNext()){
				temp = input.next();
				dic.add(temp);
			}
		}catch(FileNotFoundException ex){
			ex.printStackTrace();
		}catch(IOException e){
			System.err.println();
		}
	}
	
	public void start()
	{
		String newWord = "";
		String newInput = "";
		DialogMenue dm = new DialogMenue();
		dm.welcome();
		dm.startLne();
		newInput = keybd.next();
		keybd.nextLine();
		for(int index = 0; index < dic.size(); index++){
			newWord = dic.get(index);
			wordCompare(newInput, newWord);
		}
	}
	
	public void testPrint()
	{
		for(int index = 0; index < dic.size(); index++){
			System.out.println(dic.get(index));
		}
	}
	
	public void wordCompare(String tempOne, String tempTwo)
	{		
	    
		ArrayList <Character> wordOne =new ArrayList<>();
		ArrayList <Character> wordTwo = new ArrayList<>();
		for(int i = 0;i < tempOne.length(); i++){
			wordOne.add(tempOne.charAt(i));
		}
		for(int i = 0;i < tempTwo.length(); i++){
			wordTwo.add(tempTwo.charAt(i));
		} 
		
		int sIndex = 0;
		String winning = "";
		finalScore = 0;
		for(int index = 0; index < wordTwo.size(); ){
		
			String tempO = "" + wordOne.get(sIndex);
			String tempT = "" + wordTwo.get(index);
			if(!(tempT.equalsIgnoreCase(tempO)) ){
				
			sIndex++;
			}else{
				finalScore = score(tempT) + finalScore;	
				winning = winning + tempT;
				wordOne.remove(sIndex ); 
				sIndex = 0;
				index++;
				if(winning.equals(tempTwo)){
					winning = winning + finalScore;
					winWord.add(winning); 
					System.out.println(winning);
					break;
				}
			}
			if(sIndex + 1 == wordOne.size() ){
				index++;
				sIndex =0;
			}
			
		}
		
	}
	
	public int score(String letter)
	{
		int score = 0;
		
		switch (letter) {
		case "a" :	case "e" :		case "i" :		case "o" :		case "u" :
		case "l" :	case "n" :		case "s" :		case "t" :		case "r" :
			score += 1;
			break;
		case "d" :	case "g" :	
			score += 2;
			break;
		case "b" :	case "c" :		case "m" :		case "p" :	
			score += 3;
			break;
		case "f" :	case "h" :		case "v" :		case "w" :		case "y" :		
			score += 4;
			break;
		case "k" :		
			score += 5;
			break;
		case "j" :	case "x" :
			score += 8;
			break;
		case "q" :	case "z" :
			score += 10;
			break;
		}
		
		return score;
	}
}
