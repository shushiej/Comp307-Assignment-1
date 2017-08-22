package Part3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Perceptron {

	private ArrayList<Image> images = new ArrayList<Image>();
	private Feature[] features = new Feature[50];

	private double[]weight = new double[50];
	private int numFeatures = 50;
	private int attempts = 0;

	private void loadFile() {
		boolean[][] newimage = null;
		int rows;
		int cols;
		String category;
		try {
			java.util.regex.Pattern bit = java.util.regex.Pattern.compile("[01]");
			Scanner scan = new Scanner(new File(FileDialog.open()));
			while(scan.hasNextLine()){
				if(!scan.next().equals("P1")){
					System.out.println("Not a P1 PBM File");
				}
				category = scan.next().substring(1);
				rows = scan.nextInt();
				cols = scan.nextInt();

				newimage = new boolean[rows][cols];
				for (int r=0; r<rows; r++){
					for (int c=0; c<cols; c++){
						newimage[r][c] = (scan.findWithinHorizon(bit,0).equals("1"));
					}
				}

				int[] imageValues = new int[50];
				for(int i=0; i<numFeatures; i++){
					imageValues[i] = features[i].value(newimage);
				}

				images.add(new Image(category, imageValues));

				for(int i=0; i<weight.length; i++){
					weight[i] = new java.util.Random().nextDouble();
				}
			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
	} 

	//-------------------------------------------------------------------//	

	private void initialiseFeatures(){

		features[0] = new Dummy();
		System.out.println("Feature "+ (1) + " = 3"
				+ Arrays.toString(features[0].getRow()) 
				+ Arrays.toString(features[0].getCol()) 
				+ Arrays.toString(features[0].getSgn()));

		for(int i=1; i<numFeatures; i++){
			features[i] = new Feature();
			System.out.println("Feature "+ (i+1) + " = "
					+ Arrays.toString(features[i].getRow()) 
					+ Arrays.toString(features[i].getCol()) 
					+ Arrays.toString(features[i].getSgn()));
		}
	}

	//-------------------------------------------------------------------//	

	private void perceptron() {

		double correct = 0;

		for(Image image : images){

			if(!(checkWeight(image.getFeatures()).equals(image.getClassName()))){

				if(image.getClassName().equals("other")){
					for (int i = 1; i < numFeatures; i++){
						weight[i] -= 0.2 * image.getFeatures()[i];
					}
				}
				else if(image.getClassName().equals("Yes")){
					for (int i = 1; i < numFeatures; i++){
						weight[i] += 0.2 * image.getFeatures()[i];
					}
				}
			}
			else{
				correct++;
			}
		}
		attempts++;	
		if(correct == images.size()){
			System.out.println("\nFinal Weights: " + Arrays.toString(weight));
			System.out.println("\nConvergence of " + correct + "% Reached after " + attempts + " attempts");
		}
		else{
			if (attempts == 1000) {
				System.out.println("Perceptron has not converged from 1000 trials");
				System.out.println("Correctly classified images = " + correct);
				return;
			}
			perceptron();
		}
	}

	private String checkWeight(int[] featureImage) {
		double sum = 0;

		for (int i = 0; i < featureImage.length; i++) {
			sum += featureImage[i] * weight[i];
		}
		return (sum > 1)? "Yes" : "other";
	}

	public static void main(String[] args){
		Perceptron p = new Perceptron();
		p.initialiseFeatures();
		p.loadFile();
		p.perceptron();
		System.exit(0);
	}
}
