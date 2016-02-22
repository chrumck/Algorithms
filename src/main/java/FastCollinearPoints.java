import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    //extented point to hold pre-calculated slopeFromP
    private class PointExt implements Comparable<PointExt> {
        public double slopeFromP;
        public Point point;

        public int compareTo(PointExt that) {
            return this.point.compareTo(that.point);
        }
    }

    //comparator to be used to sort PointExt by pre-calculated slopeFromP
    private static class SlopeOrderFast implements Comparator<PointExt> {
        public int compare(PointExt q1, PointExt q2) {
            if (q1.slopeFromP - q2.slopeFromP < 0) return -1;
            if (q1.slopeFromP - q2.slopeFromP > 0) return 1;
            return 0;
        }
    }

    //extended LineSegment exposing constituting points
    private class LineSegmentExt extends LineSegment {
        public Point FirstP;
        public Point SecondP;

        public LineSegmentExt(Point p, Point q) {
            super(p, q);
            FirstP = p;
            SecondP = q;
        }
    }

    //input array of points
    private Point[] input;

    //Auxiliary Array used to sort by slopeFromP
    private PointExt[] workingA;

    //output Segments
    private ArrayList<LineSegmentExt> segments;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException("points array is null");

        input = points;
        segments = new ArrayList<LineSegmentExt>();
        workingA = new PointExt[points.length];

        copyPointsToWorkingA();

        findCollinearPoints();
    }

    private void copyPointsToWorkingA() {
        for (int i = 0; i < input.length; i++) {
            workingA[i] = new PointExt();
            workingA[i].point = input[i];
        }
    }

    //finds all collinear points
    private void findCollinearPoints() {
        for (Point point : input) {
            sortWorkingA(point);
            checkForDuplicates();
            int j = 1;
            int iMax = workingA.length - 1;
            for (int i = 2; i < workingA.length; i++) {
                if (workingA[i - 1].slopeFromP == workingA[i].slopeFromP) j++;
                if (i != iMax && workingA[i].slopeFromP == workingA[i + 1].slopeFromP) {
                    continue;
                }
                if (j > 2) addSegment(point, i - j + 1, i);
                j = 1;
            }
        }
    }

    //calculates new slopeFromP's and sorts working array by calculated slopes
    private void sortWorkingA(Point p) {
        for (PointExt workingPt : workingA) {
            workingPt.slopeFromP = p.slopeTo(workingPt.point);
        }
        Arrays.sort(workingA, new SlopeOrderFast());
    }

    //checks for duplicate points in input
    private void checkForDuplicates() {
        if (workingA.length < 2) return;
        if (workingA[0].slopeFromP == workingA[1].slopeFromP) {
            throw new IllegalArgumentException("duplicate points");
        }
    }

    //checks if segment already added
    private boolean segmentExists(LineSegmentExt newSegment) {
        for (LineSegmentExt segment : segments) {
            if (segment.FirstP.compareTo(newSegment.FirstP) == 0 &&
                    segment.SecondP.compareTo(newSegment.SecondP) == 0) {
                return true;
            }
        }
        return false;
    }

    //sorts workingA section and adds segment to segments if not yet added
    private void addSegment(Point p, int from, int to) {
        Arrays.sort(workingA, from, to + 1);
        Point pStart = (p.compareTo(workingA[from].point) < 0) ? p : workingA[from].point;
        Point pEnd = (p.compareTo(workingA[to].point) > 0) ? p : workingA[to].point;
        LineSegmentExt newSegment = new LineSegmentExt(pStart, pEnd);
        if (!segmentExists(newSegment)) segments.add(newSegment);
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[segments.size()]);
    }
}
