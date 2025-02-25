/* BRUTE FORCE (by owen: follows the brute-force approach of
 checking every combination of four points to see if they are collinear) */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private final List<LineSegment> segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException("Points array cannot be null");
        }

        int n = points.length;
        segments = new ArrayList<>();

        Point[] copy = points.clone();
        Arrays.sort(copy);

        for (int i = 0; i < n; i++) {
            if (copy[i] == null) {
                throw new IllegalArgumentException("Point cannot be null");
            }
            if (i > 0 && copy[i].compareTo(copy[i - 1]) == 0) {
                throw new IllegalArgumentException("Duplicate points detected");
            }
        }

        for (int i = 0; i < n - 3; i++) {
            for (int j = i + 1; j < n - 2; j++) {
                for (int k = j + 1; k < n - 1; k++) {
                    for (int l = k + 1; l < n; l++) {
                        Point p = copy[i];
                        Point q = copy[j];
                        Point r = copy[k];
                        Point s = copy[l];

                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(q) == p.slopeTo(s)) {
                            segments.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }
}
