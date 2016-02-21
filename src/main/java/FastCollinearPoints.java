import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private static final int K = 4;     //subset length
    private Point[] orgPoints;
    private Point[] sortedPts;
    private ArrayList<LineSegmentExt> segments = new ArrayList<LineSegmentExt>();

    private class LineSegmentExt extends LineSegment {
        private Point firstP;
        private Point secondP;

        public LineSegmentExt(Point p, Point q) {
            super(p, q);
            firstP = p;
            secondP = q;
        }

        public Point getFirstP() {
            return firstP;
        }

        public Point getSecondP() {
            return secondP;
        }
    }

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("points array is null");
        }

        orgPoints = Arrays.copyOf(points, points.length);
        sortedPts = Arrays.copyOf(points, points.length);
        Arrays.sort(orgPoints);
        for (int i = 0; i < orgPoints.length; i++) {
            if (i < orgPoints.length - 1 && orgPoints[i].compareTo(orgPoints[i + 1]) == 0) {
                throw new IllegalArgumentException("orgPoints same");
            }
            sortedPts[i] = orgPoints[i];
        }

        for (int i = 0; i < orgPoints.length; i++) {
            Arrays.sort(sortedPts, orgPoints[i].slopeOrder());
            for (int j = 1; j < sortedPts.length - 2; j++) {
                double slopeIToJ = orgPoints[i].slopeTo(sortedPts[j]);
                int k = j;
                while (k < sortedPts.length - 1 &&
                        slopeIToJ == orgPoints[i].slopeTo(sortedPts[k + 1])) k++;
                if (k > j + 1) {
                    Point[] colPts = new Point[k - j + 2];
                    for (int l = 0; l < colPts.length - 1; l++) {
                        colPts[l] = sortedPts[j + l];
                    }
                    colPts[colPts.length - 1] = orgPoints[i];
                    Arrays.sort(colPts);
                    LineSegmentExt newSegment = new LineSegmentExt(colPts[0], colPts[colPts.length - 1]);
                    if (!segmentExists(newSegment)) segments.add(newSegment);
                }
                j = k;
            }
        }


    }

    private final boolean segmentExists(LineSegmentExt newSegment) {
        for (LineSegmentExt segment : segments) {
            if (segment.getFirstP().compareTo(newSegment.getFirstP()) == 0 &&
                    segment.getSecondP().compareTo(newSegment.getSecondP()) == 0) {
                return true;
            }
        }
        return false;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}
