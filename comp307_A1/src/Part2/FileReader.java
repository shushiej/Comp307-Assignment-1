package Part2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader{


	List<String> categoryNames;
	List<String> attNames;
	List<Instance1> allInstances;
	
	int numCategories;
	int numAtts;
	
	String fileName;

	public FileReader(String fileName){
		this.fileName = fileName;
		categoryNames = new ArrayList<String>();		
		attNames = new ArrayList<String>();	
		allInstances = new ArrayList<Instance1>();
		readDataFile(fileName);
	}	

	private void readDataFile(String fname){
		/* format of names file:
		 * names of categories, separated by spaces
		 * names of attributes
		 * category followed by true's and false's for each instance
		 */
		System.out.println("Reading data from file "+fname);
		try {
			Scanner scan = new Scanner(new File(fname));

			categoryNames = new ArrayList<String>();
			for (Scanner s = new Scanner(scan.nextLine());s.hasNext();) 
			categoryNames.add(s.next());
			numCategories=categoryNames.size();
			
			System.out.println(numCategories +" categories");
			System.out.println(categoryNames);

			attNames = new ArrayList<String>();
			for (Scanner s = new Scanner(scan.nextLine()); s.hasNext();) 
			attNames.add(s.next());
			numAtts = attNames.size();
			
			System.out.println(numAtts +" attributes");
			System.out.println(attNames);

			allInstances = readInstances(scan);
			scan.close();
		}
		catch (IOException e) {
			throw new RuntimeException("Data File caused IO exception");
		}
	}


	private List<Instance1> readInstances(Scanner scan){
		/* instance = classname and space separated attribute values */
		List<Instance1> instances = new ArrayList<Instance1>();
		while (scan.hasNext()){ 
			Scanner line = new Scanner(scan.nextLine());
			
			int categoryID = categoryNames.indexOf(line.next());
			String category = categoryNames.get(categoryID);	
			List<Boolean> attrValues = new ArrayList<Boolean>();
			
			while (line.hasNextBoolean()){
				attrValues.add(line.nextBoolean());	
			}
			instances.add(new Instance1(category, attrValues));
		}
		System.out.println("Read " + instances.size()+" instances");
		System.out.println("*****************************************");	
		return instances;
	}

	public List<String> getCategoryNames() {
		return categoryNames;
	}

	public List<String> getAttributeNames() {
		return attNames;
	}

	public List<Instance1> getInstances(){
		return allInstances;
	}

//--------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------//
//--------------------------------------------------------------------------------------------//	
	
	class Instance1 {

		private String category;
		private List<Boolean> vals;

		public Instance1(String cat, List<Boolean> instanceData){
			this.category = cat;
			this.vals = instanceData;
		}

		public boolean getAtt(int index){
			return vals.get(index);
		}

		public String getCategory(){
			return category;
		}

		public String toString(){
			StringBuilder ans = new StringBuilder(categoryNames.get(numCategories));
			ans.append(" ");
			for (Boolean val : vals)
				ans.append(val?"true  ":"false ");
			return ans.toString();
		}
	}
}
