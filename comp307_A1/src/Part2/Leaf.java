package Part2;

public class Leaf extends Node{
	
	private String attributeName;
	private double probability;
	
	public Leaf(String attributename, double probability) {
		super(attributename, 0, null,null);
		this.attributeName = attributename;
		this.probability = probability;
	}
	
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public void report(String indent){
		System.out.format("%sClass %s, prob=%4.2f\n",
		indent, attributeName, probability);
	}
}
