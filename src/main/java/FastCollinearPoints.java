import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;

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
            if (q1.slopeFromP < q2.slopeFromP) return -1;
            if (q1.slopeFromP < q2.slopeFromP) return 1;
            return 0;
        }
    }

    //HashTable Key for segments
    private class SegmentKey {
        private double slopeOToP;
        private double slopeXToP;
        private double slopeYToP;
        private double slopePToQ;

        public SegmentKey(double otP, double xToP, double yToP, double ptoQ) {
            slopeOToP = otP;
            slopeXToP = xToP;
            slopeYToP = yToP;
            slopePToQ = ptoQ;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) return false;
            if (!(obj instanceof SegmentKey)) return false;
            SegmentKey that = (SegmentKey) obj;
            return slopeOToP == that.slopeOToP &&
                    slopeXToP == that.slopeXToP &&
                    slopeYToP == that.slopeYToP &&
                    slopePToQ == that.slopePToQ;
        }

        @Override
        public int hashCode() {
            int hash = 17;
            hash = hash * 31 + Double.hashCode(slopeOToP);
            hash = hash * 31 + Double.hashCode(slopeXToP);
            hash = hash * 31 + Double.hashCode(slopeYToP);
            hash = hash * 31 + Double.hashCode(slopePToQ);
            return hash;
        }
    }

    //Origin points
    private static final Point O_POINT = new Point(0, 0);
    private static final Point X_POINT = new Point(1000000, 0);
    private static final Point Y_POINT = new Point(0, 1000000);

    //input array of points
    private Point[] input;

    //Auxiliary Array used to sort by slopeFromP
    private PointExt[] workingA;

    //output Segments
    private Hashtable<SegmentKey, LineSegment> segments;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new NullPointerException("points array is null");

        input = Arrays.copyOf(points, points.length);
        segments = new Hashtable<SegmentKey, LineSegment>();
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

    //sorts workingA section and adds segment to segments if not yet added
    private void addSegment(Point p, int from, int to) {
        Arrays.sort(workingA, from, to + 1);
        Point pStart = (p.compareTo(workingA[from].point) < 0) ? p : workingA[from].point;
        Point pEnd = (p.compareTo(workingA[to].point) > 0) ? p : workingA[to].point;
        SegmentKey newKey = new SegmentKey(O_POINT.slopeTo(pStart), X_POINT.slopeTo(pStart),
                Y_POINT.slopeTo(pStart), pStart.slopeTo(pEnd));
        if (segments.containsKey(newKey)) return;
        segments.put(newKey, new LineSegment(pStart, pEnd));
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.values().toArray(new LineSegment[segments.size()]);
    }
}
