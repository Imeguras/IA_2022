package gui;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.LinkedList;

public class PointDimension<T extends java.lang.Number> implements Comparable<PointDimension<T>>, Cloneable{

	public T line;
	public T col; 
	public PointDimension(T line, T col){
		
		this.line=line; 
		this.col=col; 
		
	}
	public void setPos(T NewLine, T NewColum){
		line=NewLine;
		col=NewColum;
	}

	public PointDimension<Integer> sum(PointDimension<Integer> toSum){
		PointDimension<Integer> collector = new PointDimension<Integer>(0, 0);
		collector.col= toSum.col+ this.col.intValue();
		collector.line= toSum.line+ this.line.intValue();
		return collector; 
	}
	
	// @summary: Checks if the Point is equal to the other object, returns 0 if so, else it will return the ceiled vector distance between this object and o, the value will be negative if the line of o is bigger than the one of this object, also this is not a geometric function it will parse the integer value of the points
	@Override
	public int compareTo(PointDimension<T> o) {
		return (int)Math.ceil(geometricCompareTo(o));
	}
	public double geometricCompareTo(PointDimension<T> o){
		int x= this.col.intValue()-o.col.intValue();
		int y= this.line.intValue()-o.line.intValue();
		double result= Math.sqrt((x*x)+(y*y)); 
		if(y<0){
			result*=-1; 
		}
		return result;
	}

	// @summary: Checks if this and the point provided are geometrically at the same place
	public boolean equals(PointDimension<T> o){
		return (this.col==o.col && this.line==o.line);
	} 

	public char getValueInPos_RelativeTo(char[][] matrix, int lin, int cl){
		
		return matrix[line.intValue()+lin][col.intValue()+cl];
	}
	@Override
	public String toString(){
		return "Pos: ["+line.doubleValue()+" (line/y), "+ col.doubleValue()+" (column/x)]";
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return new PointDimension<T>(line, col);
	}
	
	public PointDimension<T> getClone(){
		return new PointDimension<T>(line, col);
	}

}