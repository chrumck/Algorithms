import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private static final double LOWER_X = 0, LOWER_Y = 0, UPPER_X = 1, UPPER_Y = 1;
    private static final double MAX_DISTANCE_SQ = 2;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect) {
            this.p = p;
            this.rect = rect;
        }
    }

    private Node root;

    private int N;

    private double rXmin, rYmin, rXmax, rYmax;

    private ArrayList<Point2D> rangeArray;
    private RectHV queryRect;

    private Point2D nearestP;
    private double nearestDSq;
    private Point2D queryP;

    public KdTree() {
        this.N = 0;
        this.rangeArray = new ArrayList<Point2D>();
    }

    // is the set empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // number of points in the set
    public int size() {
        return N;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p) {
        if (p == null) throw new NullPointerException("point");
        rXmin = LOWER_X;
        rYmin = LOWER_Y;
        rXmax = UPPER_X;
        rYmax = UPPER_Y;
        root = insert(root, p, true);
    }

    // does the set contain point p?
    public boolean contains(Point2D p) {
        if (p == null) throw new NullPointerException("point");
        return contains(root, p, true);
    }

    // draw all points to standard draw
    public void draw() {
        draw(root, true);
    }

    // all points that are inside the rectangle
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new NullPointerException("rect");
        rangeArray.clear();
        queryRect = rect;
        fillRangeArray(root);
        return rangeArray;
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p) {
        if (p == null) throw new NullPointerException("point");
        if (root == null) return null;
        nearestP = null;
        nearestDSq = MAX_DISTANCE_SQ;
        queryP = p;
        findNearest(root);
        return nearestP;
    }

    //private methods ---------------------------------------------------------------

    private Node insert(Node node, Point2D point, boolean sortByX) {
        if (node == null) {
            N++;
            return new Node(point, new RectHV(rXmin, rYmin, rXmax, rYmax));
        }

        if (point.x() == node.p.x() && point.y() == node.p.y()) return node;

        boolean isLB = sortByX ? point.x() < node.p.x() : point.y() < node.p.y();
        if (sortByX && isLB) rXmax = node.p.x();
        else if (sortByX) rXmin = node.p.x();
        else if (isLB) rYmax = node.p.y();
        else rYmin = node.p.y();

        if (isLB) node.lb = insert(node.lb, point, !sortByX);
        else node.rt = insert(node.rt, point, !sortByX);

        return node;
    }

    private boolean contains(Node node, Point2D point, boolean sortByX) {
        if (node == null) return false;
        if (point.x() == node.p.x() && point.y() == node.p.y()) return true;

        boolean isLB = sortByX ? point.x() < node.p.x() : point.y() < node.p.y();

        if (isLB) return contains(node.lb, point, !sortByX);
        else return contains(node.rt, point, !sortByX);
    }

    private void draw(Node node, boolean sortByX) {
        if (node == null) return;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(.01);
        node.p.draw();

        StdDraw.setPenRadius();
        if (sortByX) {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }
        draw(node.lb, !sortByX);
        draw(node.rt, !sortByX);
    }

    private void fillRangeArray(Node node) {
        if (node == null) return;

        if (queryRect.contains(node.p)) {
            rangeArray.add(node.p);
            fillRangeArray(node.lb);
            fillRangeArray(node.rt);
        } else {
            if (node.lb != null && node.lb.rect.intersects(queryRect)) fillRangeArray(node.lb);
            if (node.rt != null && node.rt.rect.intersects(queryRect)) fillRangeArray(node.rt);
        }
    }

    private void findNearest(Node node) {
        double currDSq = queryP.distanceSquaredTo(node.p);
        if (currDSq < nearestDSq) {
            nearestDSq = currDSq;
            nearestP = node.p;
        }

        if (node.rt != null && node.rt.rect.contains(queryP)) {
            findNearest(node.rt);
            if (node.lb != null && node.lb.rect.distanceSquaredTo(queryP) < nearestDSq)
                findNearest(node.lb);
        }
        else if (node.lb != null) {
            findNearest(node.lb);
            if (node.rt != null && node.rt.rect.distanceSquaredTo(queryP) < nearestDSq)
                findNearest(node.rt);
        }
        else if (node.rt != null && node.rt.rect.distanceSquaredTo(queryP) < nearestDSq) {
            findNearest(node.rt);
        }
    }

}
