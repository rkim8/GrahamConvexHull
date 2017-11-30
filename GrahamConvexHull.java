/*
   Programmer: Richard Kim
   Date: April 29th, 2014
   Class: ITCS 2215-003
   Purpose: The purpose of this program is to implement the Convex Hull algorithm.
*/

//Below, we add in additional utilities and io's to the original program
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;

public class GrahamConvexHull 
{
	public static void main(String[] args) throws FileNotFoundException 
	{		
		// You will have to replace this section with the file reading one
		Scanner file = new Scanner(new File("input.txt"));  //Program will read from input.txt file
		
		HashMap<String, MyPoint> allPoints = new HashMap<String, MyPoint>();
		Stack<String> stackNode = new Stack<String>();
		
      //The code below will read each coordinate from the input file.
		while (file.hasNextLine())
		{
			String currLine[] = file.nextLine().split(",");
			if (currLine.length == 3)
			{
				MyPoint currPoint = new MyPoint(currLine[0], 
						Integer.parseInt(currLine[1].trim()), 
						Integer.parseInt(currLine[2].trim()));
				allPoints.put(currLine[0], currPoint);	
				stackNode.push(currLine[0]);
			}			
		}
		file.close(); //closes the input.txt file.
      
		String[] allNodes = new String[stackNode.size()];
      for (int i = 0; i < allNodes.length; i++)
		{
			allNodes[i] = stackNode.pop();
		}		
		String leftMost = allNodes[0];		
		
		for (int i = 0; i < allNodes.length; i++)
		{
			String currKey = allNodes[i];
 			if (allPoints.get(currKey).x < allPoints.get(leftMost).x)
				leftMost = currKey;
		}
		
		MyLine allLines[] = new MyLine[allNodes.length - 1];
		
		int border = 0;
		for (int i = 0; i < allNodes.length; i++)
		{
			if (leftMost == allNodes[i])
				continue;
			
			MyPoint fromPoint = allPoints.get(leftMost);
			MyPoint toPoint = allPoints.get(allNodes[i]);
			
			MyLine currLine = new MyLine(fromPoint, toPoint);
			
			allLines[border] = currLine;
			border = border + 1;
			
			System.out.println();
		}
		
		
		Arrays.sort(allLines);
		
		Stack<String> ourStack = new Stack<String>();
		
		ourStack.push(leftMost);
		
		String elementBeforeLast = ourStack.pop();
		ourStack.push(elementBeforeLast);
		
		ourStack.push(allLines[0].point2.name);
		
		for (int i = 0; i < allLines.length-1; i ++)
		{
			String lastElementInStack = ourStack.pop();
			ourStack.push(lastElementInStack);
			
			MyLine line1 = new MyLine(allPoints.get(elementBeforeLast), allPoints.get(lastElementInStack));
			
				
			MyLine line2 = new MyLine(allPoints.get(elementBeforeLast), allPoints.get(allLines[i+1].point2.name));
	
			elementBeforeLast = lastElementInStack;
			if (!line1.isItClockwise(line2))
         {
				ourStack.push(allLines[i+1].point2.name);
			}
         else
			{
				; // Here you will have to add code to pop from the stack then check direction again
				ourStack.pop();
				lastElementInStack = ourStack.pop();
				elementBeforeLast = ourStack.pop();
				ourStack.push(elementBeforeLast);
				ourStack.push(lastElementInStack);
				i--;
			}
		}
      
      System.out.println("\nThe Convex Hull Algorithm is:");
		while (!ourStack.isEmpty())
      {
			System.out.print(ourStack.pop() + " ");
		}
      
	}
}