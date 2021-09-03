import processing.core.PApplet;

public class Line {

	private double x1, y1, x2, y2;
	
	// Constructs a line from (x1, y1) to (x2, y2) 
	public Line(double x1, double y1, double x2, double y2) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	 
	// Sets this line�s second point (x2, y2) to a new coordinate 
	 public void setPoint2(double x2, double y2)  {
		 this.x2 = x2;
		 this.y2 = y2;
	 }
	 
	// Draws this line using the PApplet passed as an argument 
	 public void draw(PApplet drawer) {
		 drawer.line((float)x1, (float)y1, (float)x2, (float)y2);
	 }



	 // Returns the x coordinate of the intersection point of this line and the other line (assuming they continue forever)
	 public double getIntersectionX(Line other) {
		 double denom = (x1-x2)*(other.y1-other.y2) - (y1-y2)*(other.x1-other.x2);

		 double ix = (x1*y2 - y1*x2)*(other.x1-other.x2) - (x1 - x2)*(other.x1*other.y2-other.y1*other.x2);

		 ix /= denom;
		 return ix;
	 }

	 // Returns the y coordinate of the intersection point of this line and the other line (assuming they continue forever)
	 public double getIntersectionY(Line other) {
		 double denom = (x1-x2)*(other.y1-other.y2) - (y1-y2)*(other.x1-other.x2);

		 double iy = (x1*y2 - y1*x2)*(other.y1-other.y2) - (y1 - y2)*(other.x1*other.y2-other.y1*other.x2);

		 iy /= denom;
		 return iy;
	 }




	// Returns true if this line segment and the segment other intersect each other. Returns false if they do not intersect.
	 public boolean intersects(Line other) {

		 double denom = (x1-x2)*(other.y1-other.y2) - (y1-y2)*(other.x1-other.x2);

		 if (Math.abs(denom) < 0.0001) {  // If the Lines are parallel, check the end points using point strategy
			 return pointIntersection(x1,y1,other) ||
					 pointIntersection(x2,y2,other) ||
					 pointIntersection(other.x1,other.y1,this) ||
					 pointIntersection(other.x2,other.y2,this);
		 }

		 double ix = (x1*y2 - y1*x2)*(other.x1-other.x2) - (x1 - x2)*(other.x1*other.y2-other.y1*other.x2);
		 double iy = (x1*y2 - y1*x2)*(other.y1-other.y2) - (y1 - y2)*(other.x1*other.y2-other.y1*other.x2);
		 ix /= denom;
		 iy /= denom;

		 return rangeCheck(ix,other.x1,other.x2) &&
				 rangeCheck(iy,other.y1,other.y2) &&
				 rangeCheck(ix,x1,x2) &&
				 rangeCheck(iy,y1,y2);

	 }




	 public boolean pointIntersection(double x, double y) {
		 return pointIntersection(x,y,this);
	 }





	 private boolean pointIntersection(double x, double y, Line other) {
		 double newY = (other.y2-other.y1)/(other.x2-other.x1)*(x - other.x1)+other.y1;
		 if (Math.abs(y-newY) < 0.0001) {
			 if (rangeCheck(x,other.x1,other.x2) && rangeCheck(y,other.y1,other.y2))
				 return true;
			 else
				 return false;
		 } else
			 return false;
	 }



	 private boolean rangeCheck(double x, double o1, double o2) {
		 if (x >= Math.min(o1, o2) - 0.0001 && x <= Math.max(o1, o2) + 0.0001)
			 return true;
		 else
			 return false;

	 }
	 
	
	
}
