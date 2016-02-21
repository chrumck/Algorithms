import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private static final int K = 4;     //subset length
    private Point[] orgPoints;
    private ArrayList<LineSegment> segments = new ArrayList<LineSegment>();

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("points array is null");
        }
        orgPoints = Arrays.copyOf(points, points.length);
        Arrays.sort(orgPoints);

        for (int i = 0; i < orgPoints.length - 1; i++) {
            if (orgPoints[i].compareTo(orgPoints[i + 1]) == 0) {
                throw new IllegalArgumentException("orgPoints same");
            }
            if (i >= orgPoints.length - 3) continue;

            for (int j = i + 1; j < orgPoints.length - 2; j++)
                for (int k = j + 1; k < orgPoints.length - 1; k++)
                    for (int l = k + 1; l < orgPoints.length; l++) {
                        if (orgPoints[i].slopeOrder().compare(orgPoints[j], orgPoints[k]) == 0 &&
                                orgPoints[j].slopeOrder().compare(orgPoints[k], orgPoints[l]) == 0) {
                            segments.add(new LineSegment(orgPoints[i], orgPoints[l]));
                        }
                    }
        }
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
