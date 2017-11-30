
public class MyLine implements Comparable<MyLine>
{
	MyPoint point1;
	MyPoint point2;

	double slope;
	
	public MyLine(MyPoint local_point1, MyPoint local_point2)
	{
		point1 = local_point1;
		point2 = local_point2;
		
		calculateSlope();
	}
	
	public void calculateSlope()
	{
		slope = (point2.y - point1.y) / (point2.x - point1.x);
	}

	@Override
	public int compareTo(MyLine o) {
		// TODO Auto-generated method stub
		
		System.out.println("Measuring from " + slope + " to " + o.slope);
		System.out.println("The Answer is " +  (int) ((slope - o.slope)*100));
		
		return (int) ((slope*100 - o.slope*100));
	}
	
	public Boolean isItClockwise(MyLine o){
		double point1_x_afterTranslation = point2.x - point1.x;
		double point1_y_afterTranslation = point2.y - point1.y;
		
		double point2_x_afterTranslation = o.point2.x - o.point1.x;
		double point2_y_afterTranslation = o.point2.y - o.point1.y;
		
		if (point1_y_afterTranslation * point2_x_afterTranslation < point1_x_afterTranslation * point2_y_afterTranslation)
			return false;	
		else
			return true;
	}
}

