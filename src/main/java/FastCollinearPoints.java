//Written by Nick Wong
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.*;

public class FastCollinearPoints {

    private final List<LineSegment> lineSegments = new ArrayList<>();

    public FastCollinearPoints(Point[] points) {

        for (Point p : points) {
            Point[] otherPoints = new Point[points.length - 1];
            int index = 0;
            for (Point q : points) {
                if (!q.equals(p)) {
                    otherPoints[index++] = q;
                }
            }

            sort(otherPoints, p.slopeOrder());

            int j = 0;
            while (j < otherPoints.length) {
                List<Point> collinear = new ArrayList<>();
                collinear.add(p);

                double slope = p.slopeTo(otherPoints[j]);
                while (j < otherPoints.length && p.slopeTo(otherPoints[j]) == slope) {
                    collinear.add(otherPoints[j]);
                    j++;
                }


                if (collinear.size() >= 4) {
                    collinear.sort(null);
                    if (p.compareTo(collinear.get(0)) == 0) {
                        lineSegments.add(new LineSegment(collinear.get(0), collinear.get(collinear.size() - 1)));
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[0]);
    }
}
