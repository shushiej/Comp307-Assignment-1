package Part2;

public class Node {
	
	private String attributeName;
	private int attributeIndex;
	private Node left;
	private Node right;
	
	
	public Node(String attributeName, int attributeIndex, Node left, Node right){
		this.attributeName = attributeName;
		this.attributeIndex = attributeIndex;
		this.left = left;
		this.right = right;
	}
	
	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public int getAttributeIndex() {
		return attributeIndex;
	}

	public void setAttributeIndex(int attributeIndex) {
		this.attributeIndex = attributeIndex;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public void report(String indent){
		System.out.format("%s%s = True:\n",	indent, attributeName);
				left.report(indent+" ");
				System.out.format("%s%s = False:\n", indent, attributeName);
				right.report(indent+" ");
	}
}
