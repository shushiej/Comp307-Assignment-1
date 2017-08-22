package Part1;

import java.util.ArrayList;

public class Calculate {

	public static double range(ArrayList<Instance1> list, String type){

		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
	    
		 if (!list.isEmpty()){

    		for (final Instance1 i : list) {
    			if(type == "sepalLength"){
    				max = Math.max(max, i.getSepalLength());
    				min = Math.min(min, i.getSepalLength());
    			}
    			if(type == "sepalWidth"){
    				max = Math.max(max, i.getSepalWidth());
    				min = Math.min(min, i.getSepalWidth());
    			}
    			if(type == "petalLength"){
    				max = Math.max(max, i.getPetalLength());
    				min = Math.min(min, i.getPetalLength());
    			}
    			if(type == "petalWidth"){
    				max = Math.max(max, i.getPetalWidth());
    				min = Math.min(min, i.getPetalWidth());
    			}
    		}
 
	    }
	    return max - min;
		}
	
	public static double euclidean(Instance1 instance1, Instance1 instance2){
		
		double sepalLengthRange = range(FileReader.trainingList, "sepalLength");
		double sepalWidthRange = range(FileReader.trainingList, "sepalWidth");
		double petalLengthRange = range(FileReader.trainingList, "petalLength");
		double petalWidthRange = range(FileReader.trainingList, "petalWidth");

		double petLength = Math.pow(((instance1.getPetalLength() - instance2.getPetalLength())/petalLengthRange),2);
		double petWidth = Math.pow(((instance1.getPetalWidth() - instance2.getPetalWidth())/petalWidthRange),2);
		double sepLength = Math.pow(((instance1.getSepalLength() - instance2.getSepalLength())/sepalLengthRange),2);
		double sepWidth = Math.pow(((instance1.getSepalWidth() - instance2.getSepalWidth())/sepalWidthRange),2);

		return Math.sqrt(petLength + petWidth  + sepLength + sepWidth);
	}
	
}
