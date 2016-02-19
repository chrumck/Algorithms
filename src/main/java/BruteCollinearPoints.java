import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private static final int K = 4;     //subset length
    private int[] sIdxs;                // current subset indices
    private final int pointsLength;
    private ArrayList<LineSegment> segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new NullPointerException("points array is null");
        }
        if (points.length < K) {
            throw new IllegalArgumentException("point.length < " + K );
        }
        pointsLength = points.length;
        segments = new ArrayList<LineSegment>();
        Point[] subArray = new Point[K];
        while(nextCombination()){
            for (int i = 0; i < K ; i++) {
                subArray[i] = points[sIdxs[i]];
            }
            if (subArray[3].compareTo(subArray[0]) == 0) {
                throw new IllegalArgumentException("points same");
            }
            if (subArray[0].slopeOrder().compare(subArray[1], subArray[2]) == 0 &&
                    subArray[1].slopeOrder().compare(subArray[2], subArray[3]) == 0 &&
                    subArray[2].slopeOrder().compare(subArray[3], subArray[0]) == 0) {
                Arrays.sort(subArray);
                segments.add(new LineSegment(subArray[0], subArray[3]));
            }
        }
    }

    //iterates over all possible K combinations of points array
    private boolean nextCombination(){
        //return 0,1, 2..K-1 for the first time
        if (sIdxs == null) {
            sIdxs = new int[K];
            for (int i = 0; (sIdxs[i] = i) < K - 1; i++) ;
            return true;
        }
        int i;
        // find position of item that can be incremented
        for (i = K - 1; i >= 0 && sIdxs[i] == pointsLength - K + i; i--) ;
        // return false if all combinations tried
        if (i < 0) return false;
        // increment this item if it can be incremented
        sIdxs[i]++;
        // fill up remaining items
        for (++i; i < K; i++) {
            sIdxs[i] = sIdxs[i - 1] + 1;
        }
        //return new combination
        return true;
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
