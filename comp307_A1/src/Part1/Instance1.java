package Part1;

public class Instance1 {

	private double sepalLength;
	private double sepalWidth;
	private double petalLength;
	private double petalWidth;
	private String type;
	
	public Instance1(double sepalLength, double sepalWidth, 
					double petalLength, double petalWidth, String type){
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.type = type;
	}

	public double getSepalLength() {
		return sepalLength;
	}

	public double getSepalWidth() {
		return sepalWidth;
	}

	public double getPetalLength() {
		return petalLength;
	}

	public double getPetalWidth() {
		return petalWidth;
	}

	public String getType() {
		return type;
	}
	
	public String toString() {
		return "Instance [sepalLength=" + sepalLength + ", sepalWidth="
				+ sepalWidth + ", petalLength=" + petalLength + ", petalWidth="
				+ petalWidth + ", type=" + type + "]";
	}
	
}
