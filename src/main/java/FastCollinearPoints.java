import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private static final int K = 4;     //subset length
    private Point[] orgPoints;
    private Point[] sortedPts;
    private ArrayList<Point> segments;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("points array is null");
        }
        segments = new ArrayList<Point>();
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

                    if (!segmentExists(colPts[0], colPts[colPts.length - 1])) {
                        segments.add(colPts[0]);
                        segments.add(colPts[colPts.length - 1]);
                    }
                }
                j = k;
            }
        }
    }

    private boolean segmentExists(Point p, Point q) {
        for (int i = 0; i < segments.size() - 1; i += 2) {
            if (segments.get(i).compareTo(p) == 0 &&
                    segments.get(i + 1).compareTo(q) == 0) return true;
        }
        return false;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.size() / 2;
    }

    // the line segments
    public LineSegment[] segments() {
        LineSegment[] segmentsArray = new LineSegment[segments.size() / 2];
        for (int i = 0; i < segments.size() - 1; i += 2) {
            segmentsArray[i/2] = new LineSegment(segments.get(i), segments.get(i + 1));
        }
        return segmentsArray;
    }
}
