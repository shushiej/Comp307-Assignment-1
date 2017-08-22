package Part3;

public class Image {

	private String className;
	private int[] features;
	
	public Image(String className, int[] features){
		this.className = className;
		this.features = features;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int[] getFeatures() {
		return features;
	}

	public void setFeatures(int[] features) {
		this.features = features;
	}
	
}
