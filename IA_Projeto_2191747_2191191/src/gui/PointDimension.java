package gui;

import java.math.BigInteger;
import java.util.Comparator;
import java.util.LinkedList;

public class PointDimension<T extends java.lang.Number> implements Comparable<PointDimension<T>>{

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
	
	// @summary: Checks if the Point is equal to the other object, returns 0 if so, else it will return 1 in case the objects sum of decimal places is bigger than the one provided in o
	@Override
	public int compareTo(PointDimension<T> o) {
		
		return (this.col.toString()+"C"+this.line.toString()+"L").compareTo(o.col.toString()+"C"+o.line.toString()+"L");
	}

	public char getValueInPos_RelativeTo(char[][] matrix, int lin, int cl){
		
		return matrix[line.intValue()+lin][col.intValue()+cl];
	}
	@Override
	public String toString(){
		return "Pos: ["+line.doubleValue()+" (line/y), "+ col.doubleValue()+" (column/x)]";
	}
	

}