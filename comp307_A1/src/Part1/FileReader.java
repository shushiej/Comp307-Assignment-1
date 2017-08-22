package Part1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReader{
  
static ArrayList<Instance1>	trainingList = new ArrayList<Instance1>();
static ArrayList<Instance1>	testList = new ArrayList<Instance1>();
static ArrayList<String> types = new ArrayList<String>();

public static void load(String trainingFile, String testFile){
	
try{
	
	Scanner sc = new Scanner(new File(trainingFile));
	Scanner sc2 = new Scanner(new File(testFile));
	
	while(sc.hasNext()){
		double sL = sc.nextDouble();
		double sW = sc.nextDouble();
		double pL = sc.nextDouble();
		double pW = sc.nextDouble();
		String t = sc.next();
		
		trainingList.add(new Instance1(sL,sW,pL,pW,t));
		
	}
	while(sc2.hasNext()){
		double sL = sc2.nextDouble();
		double sW = sc2.nextDouble();
		double pL = sc2.nextDouble();
		double pW = sc2.nextDouble();
		String t = sc2.next();
		
		testList.add(new Instance1(sL,sW,pL,pW,t));
		
	}
	sc.close();
	sc2.close();
}catch (FileNotFoundException e){}
}

}
