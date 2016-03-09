import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;

import java.util.ArrayList;

public class PointSET {
    private static final double MAX_DISTANCE = 2;
    private SET<Point2D> points;

    // construct an empty set of points
    public PointSET() {
        points = new SET<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return points.size() == 0;
    }

    // number of points in the set
    public int size() {
        return points.size();
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        points.add(p);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    // draw all points to standard draw
    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException("rect");
        ArrayList<Point2D> rectPoints = new ArrayList<Point2D>();
        for (Point2D point : points) {
            if (rect.contains(point)) rectPoints.add(point);
        }
        return rectPoints;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException("point");
        Point2D nearestPoint = null;
        double nearestDist = MAX_DISTANCE;
        for (Point2D currPoint : points) {
            double currDist = currPoint.distanceTo(p);
            if (currDist < nearestDist) {
                nearestDist = currDist;
                nearestPoint = currPoint;
            }
        }
        return nearestPoint;
    }
}
