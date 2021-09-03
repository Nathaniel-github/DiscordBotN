

public class LineTest {
    public static void main(String[] args) {
        String arg = args[0];
        switch (arg) {
            case "vertical":
                testVerticalLines();
                break;
            case "parallel":
                testParallelLines();
                break;
            case "intersecting":
                testIntersectingLines();
                break;
            case "non_intersecting":
                testNonIntersectingLines();
                break;
            case "collinear":
                testCollinearLines();
        }
    }

    public static void testIntersectingLines() {
        Line l1 = new Line(0,0,10,10);
        Line l2 = new Line(0,10,10,0);
        assert l1.intersects(l2);
        l2 = new Line(0,5,10,5);
        assert l1.intersects(l2);
        l2 = new Line(5,0,6,10);
        assert l1.intersects(l2);
        l2 = new Line(4,5,6,3);
        assert l1.intersects(l2);
        l1.setPoint2(5,6);
        assert l1.intersects(l2);
    }
    public static void testNonIntersectingLines(){
        Line l1 = new Line(0,0,10,10);
        Line l2 = new Line(0,5,5,10);
        assert !l1.intersects(l2);
        l2 = new Line(5,0,10,5);
        assert !l1.intersects(l2);
        l2 = new Line(5,10,15,11);
        assert !l1.intersects(l2);
        l2 = new Line(0,0.01,10.01,10.01);
        assert !l1.intersects(l2);
    }
    public static void testCollinearLines(){
        Line l1 = new Line(0,0,10,10);
        Line l2 = new Line(-5,-5,5,5);
        assert l1.intersects(l2);
        l2 = new Line(-10,-10,-1,-1);
        assert !l1.intersects(l2);
        l2.setPoint2(15,15);
        assert l1.intersects(l2);
        l2 = new Line(0,0,10,10);
        assert l1.intersects(l2);
        l2 = new Line (2,2,7,7);
        assert l1.intersects(l2);
        l2 = new Line(11,11,15,15);
        assert !l1.intersects(l2);
    }
    public static void testParallelLines(){
        Line l1 = new Line(0,0,10,10);
        Line l2 = new Line(0,1,10,11);
        assert !l1.intersects(l2);
        l2 = new Line(0,-1,0,9);
        assert l1.intersects(l2);
        l2 = new Line (-1,-1,9,9);
        assert l1.intersects(l2);
        l2 = new Line (1,1,11,11);
        assert l1.intersects(l2);
    }
    public static void testVerticalLines(){
        Line l1 = new Line(0,0,0,10);
        Line l2 = new Line(1,0,1,10);
        assert !l1.intersects(l2);
        l2 = new Line(-1,0,-1,10);
        assert !l1.intersects(l2);
        l2 = new Line(-1,-1,-1,9);
        assert !l1.intersects(l2);
        l2 = new Line(0,-1,0,9);
        //doesn't work with parallel vertical lines
        assert l1.intersects(l2);
        l2 = new Line(0,1,0,11);
        assert l1.intersects(l2);
        l2 = new Line(1,1,1,11);
        assert !l1.intersects(l2);
    }

}
