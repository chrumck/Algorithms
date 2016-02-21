import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private static final int K = 4;     //subset length
    private Point[] sortedPts;
    private ArrayList<LineSegment> segments;

    // finds all line segments containing 4 points
    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("points array is null");
        }
        if (points.length < K) {
            throw new IllegalArgumentException("point.length < " + K );
        }
        sortedPts = new Point[points.length];
        Arrays.sort(points);
        for (int i = 0; i < points.length; i++) {
            if (i < points.length - 1 && points[i].compareTo(points[i +1]) == 0){
                throw new IllegalArgumentException("points same");
            }
            sortedPts[i] = points[i];
        }

        for (int i = 0; i < points.length; i++) {
            Arrays.sort(sortedPts, points[i].slopeOrder());

            for (int j = 0; j < sortedPts.length - 3; j++) {
                double slpIToJ= points[i].slopeTo(sortedPts[j]);
                int k = j;
                while(slpIToJ == points[i].slopeTo(sortedPts[k + 1]) &&
                        k < sortedPts.length - 1) k++;
                if (k > j + 2){
                    Arrays.sort(sortedPts, j, k);
                    segments.add(new LineSegment(sortedPts[j], sortedPts[k]));
                }
            }
        }
    }

    // the number of line segments
    public int numberOfSegments() {
        return  segments.size();
    }

    // the line segments
    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}
