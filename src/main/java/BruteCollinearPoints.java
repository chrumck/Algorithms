import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private static final int K = 4;     //subset length
    private ArrayList<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("points array is null");
        }
        if (points.length < K) {
            throw new IllegalArgumentException("point.length < " + K );
        }

        Arrays.sort(points);
        segments = new ArrayList<LineSegment>();

        for (int i = 0; i < points.length - 1; i++){
            if (points[i].compareTo(points[i + 1]) == 0){
                throw new IllegalArgumentException("points same");
            }
            if (i >= points.length - 3) continue;

            for (int j = i + 1; j < points.length - 2; j++)
                for (int k = j + 1; k < points.length - 1; k++)
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeOrder().compare(points[j], points[k]) == 0 &&
                                points[j].slopeOrder().compare(points[k], points[l]) == 0) {
                            segments.add(new LineSegment(points[i], points[l]));
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
