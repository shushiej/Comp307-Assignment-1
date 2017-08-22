package Part2;


import java.util.ArrayList;
import java.util.List;
import Part2.FileReader.Instance1;

public class DecisionTree {

	List<String> categoryNames;
	List<String> attributeNames;
	List<Instance1> trainingInstances;
	List<Instance1> testInstances;
	double baseLineProb;
	String baseLineCat;
	Node tree;

	public DecisionTree(List<Instance1> trainingSet, List<Instance1> testSet,
			List<String> categoryNames, List<String> attributeNames) {
		this.trainingInstances = trainingSet;
		this.testInstances = testSet;
		this.categoryNames = categoryNames;
		this.attributeNames = attributeNames;

		baseLineCat = getMajorityClass(trainingInstances);
		baseLineProb = calculateProbability(trainingInstances);

		tree = buildTree(trainingInstances, attributeNames);
		System.out.println("\n============= Tree =============\n");
		tree.report("");
		System.out.println("\n================================\n");
		System.out.println("BP: " + baseLineProb);
		classify();

	}


	public Node buildTree(List<Instance1> instances, List<String> attributes){

		List<Instance1> bestInstTrue = null;
		List<Instance1> bestInstFalse = null;
		double lowestImpurity = 1;
		int bestAttIndex = 0;
		String bestAtt = "";

		//if instances is empty
		if(instances == null||instances.isEmpty()){
			//return a leaf node containing the "baseline" predictor)
			return new Leaf(baseLineCat, baseLineProb);
		}
		//if instances are pure
		if((int)calculateProbability(instances) == 1){
			//return a leaf node containing the name of the class of the instances
			//in the node and probability 1
			return new Leaf(instances.get(0).getCategory(), 1);
		}
		//if attributes is empty
		if(attributeNames.isEmpty()){
			//return a leaf node containing the name and probability 
			//of the majority class of the instances in the node
			double probability = calculateProbability(instances);
			String majorityCategory = getMajorityClass(instances);;
			return new Leaf(majorityCategory, probability);
		}
		//else find best attribute:
		else{
			//for each attribute
			for(int i = 0; i < attributes.size(); i++){
				//seperate instances into two sets:
				List<Instance1> listTrue = new ArrayList<Instance1>();
				List<Instance1> listFalse = new ArrayList<Instance1>();

				for(Instance1 instance: instances){
					//instances for which the attribute is true
					if(instance.getAtt(i) == true){
						listTrue.add(instance);
					}
					//instances for which the attribute is false
					else{
						listFalse.add(instance);
					}
				}
				//compute purity of each set.
				double purityOfTrue = calculatePurity(listTrue);
				double purityOfFalse = calculatePurity(listFalse);
				double averageOfTrue = ((listTrue.size()/((double)instances.size())) * purityOfTrue);
				double averageOfFalse = ((listFalse.size()/((double)instances.size())) * purityOfFalse);
				double weightedImpurity = averageOfTrue + averageOfFalse;//initialize and the calculate 
				// if weighted average purity of these sets is best so far
				if(weightedImpurity < lowestImpurity){
					// bestAtt = this attribute
					bestAtt = attributes.get(i);
					lowestImpurity = weightedImpurity;
					bestAttIndex = i;
					// bestInstsTrue = set of true instances 
					bestInstTrue = listTrue;
					// bestInstsFalse = set of false instances
					bestInstFalse = listFalse;
				}
			}
			//build subtrees using the remaining attributes:
			attributeNames.remove(bestAttIndex);
			//left = BuildTree(bestInstsTrue, attributes - bestAtt)
			Node left = buildTree(bestInstTrue, attributeNames);
			//right = BuildTree(bestInstsFalse, attributes - bestAttr)
			Node right = buildTree(bestInstFalse, attributeNames);
			//return Node containing (bestAtt, left, right)
			return new Node(bestAtt, bestAttIndex, left, right);
		}
	}

	private double calculatePurity(List<Instance1> instances){
		double x = 0;
		double y = 0;
		for(Instance1 i : instances){
			String category = i.getCategory();
			if(category.equals(categoryNames.get(0))){
				x++;
			}
			else if(category.equals(categoryNames.get(1))){
				y++;
			}
		}
		double purity = (x*y) / Math.pow((x+y),2);
		return purity;
	}

	private String getMajorityClass(List<Instance1> instances){
		double x = 0;
		double y = 0;
		for(Instance1 i : instances){
			if(i.getCategory() == categoryNames.get(0)){
				x++;
			}
			else{
				y++;
			}
		}
		if(x > y){
			return categoryNames.get(0);
		}
		else{
			return categoryNames.get(1);
		}
	}

	private double calculateProbability(List<Instance1> instances){
		double x = 0;
		double y = 0;
		double total = instances.size();
		for(Instance1 i : instances){
			if(i.getCategory() == categoryNames.get(0)){
				x++;
			}
			else{
				y++;
			}
		}
		if(x > y){
			return x/total;
		}
		else{
			return y/total;
		}
	}

	private void classify(){
		System.out.println("\nClassifying Test Data Set:");
		int correct = 0;
		for (Instance1 instance : testInstances){
			if (predictResult(tree, instance).equals(instance.getCategory())){
				correct++;
			}
		}
		System.out.format("Correctly Predicted: %d\n", correct);
		System.out.format("Total Tested: %d\n", testInstances.size());
		System.out.format("Classification Accuracy: %f\n", correct*100.00/testInstances.size());
	}

	private String predictResult(Node node, Instance1 testInstance){
		if (node instanceof Leaf){
			return ((Leaf) node).getAttributeName();
		}
		else{
			Node nonLeafNode = (Node) node;
			if (testInstance.getAtt(nonLeafNode.getAttributeIndex()) == true){
				return predictResult(nonLeafNode.getLeft(), testInstance);
			}
			else{
				return predictResult(nonLeafNode.getRight(), testInstance);
			}
		}
	}
}