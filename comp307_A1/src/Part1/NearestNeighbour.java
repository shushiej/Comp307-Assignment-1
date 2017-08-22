package Part1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class NearestNeighbour {
	
	public static String getNeighbours(ArrayList<Instance1> training, Instance1 test, int k){
		
		HashMap<Double, String> distances = new HashMap<Double,String>();
		HashMap<Double, String> n = new HashMap<Double,String>();
		HashMap<String,Integer> Neighbours = new HashMap<String, Integer>();
		
		for(Instance1 item: training){
			//Performs Euclidean calculation between points in Test and Training
			double dist = Calculate.euclidean(test, item);
			//puts them into a new Hashmap with the distances and name of flower(type)
			distances.put(dist, item.getType());
		}
		
		//Create a new ArrayList with the distance values from the euclidean calculation
		ArrayList <Double> list = new ArrayList<Double>(distances.keySet());
		//Sort the values
		Collections.sort(list);
		
		int index = 0;
		while(index < k){
			//Puts the distance from list and the type from distances into a Hashmap 
			n.put(list.get(index), distances.get(list.get(index)));
			index++;
		}

		int i = 0;
		for(Map.Entry<Double, String> entry :n.entrySet()) {	
			while(i<k){
				//put the type into a HashMap of Neighbours
				Neighbours.put(entry.getValue(),1);
				i++;
				break;
			}
		}
		
		String predictions = (String) (Neighbours.keySet().toArray()[0]);
		return predictions;
	}
	
	public static Double getAccuracy(ArrayList<Instance1> test, ArrayList<String> predictions){
		double correct = 0;
		for(int i = 0; i <= test.size()-1; i++){
			if(test.get(i).getType().equals(predictions.get(i))){
				correct ++;
			}
		}
		
		System.out.println("P: " + predictions.size());
		for(int x = 0; x < 75; x++){
			String xxx = predictions.get(x);
			String stat = "-";
			if(test.get(x).getType().equals(xxx)){
			stat = "-";
			}else{
			stat = "Mismatch";
			}
			System.out.println((x + 1 )+ " " + test.get(x).getType() + " " + xxx + " " + stat);
			
		}
		double accuracy = (correct/test.size()) * 100;
		return accuracy;
	}
	
	public static void main(String[] args){
		new NearestNeighbour();
		FileReader.load("iris-training.txt","iris-test.txt");
		ArrayList<String> predictions = new ArrayList<String>();
		for(Instance1 item: FileReader.testList){
			String result = NearestNeighbour.getNeighbours(FileReader.trainingList, item,1);
			predictions.add(result);
		}
		double accuracy = NearestNeighbour.getAccuracy(FileReader.testList, predictions);
		System.out.println("Accuracy: " + accuracy + '%');
	}
}
