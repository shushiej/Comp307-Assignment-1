package Part2;

import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		FileReader trainingData = new FileReader("hepatitis-training.dat");//args[0]);
		List<FileReader.Instance1> trainingSet = trainingData.getInstances();
		FileReader testData = new FileReader("hepatitis-test.dat");//args[1]);
		List<FileReader.Instance1> testSet = testData.getInstances();
		
		List<String> categoryNames = trainingData.getCategoryNames();
		List<String> attributeNames = trainingData.getAttributeNames();
		
		new DecisionTree(trainingSet, testSet, categoryNames, attributeNames);		
	}
}
