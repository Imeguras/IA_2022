package gui;

public class PointDimension<T extends java.lang.Number> implements Comparable<PointDimension<T>>{

	public T line;
	public T col; 
	public PointDimension(T line, T col){
		
		this.line=line; 
		this.col=col; 
	}
	// @summary: Checks if the Point is equal to the other object, returns 0 if so, else it will return 1 in case the objects sum of decimal places is bigger than the one provided in o
	@Override
	public int compareTo(PointDimension<T> o) {
		
		return (this.col.toString()+this.line.toString()).compareTo(o.col.toString()+o.line.toString());
	}

	public char getValueInPos_RelativeTo(char[][] matrix, int lin, int cl){
		
		return matrix[line.intValue()+lin][col.intValue()+cl];
	}
	

}