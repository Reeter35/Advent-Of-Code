package fr.reeter.advent2020.wires;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class WiresIntersectionFinder {
    @Autowired
    private Logger logger;



    /**
     * Gets all intersections from a  list of wires
     * @param wire1 the first wire
     * @param wire2 the second wire
     * @return all the common intersections found
     */
    public List<Point> findAllIntersections(List<Point> wire1, List<Point> wire2) {
        logger.info("Find all intersections");
        List<Point> intersections = new ArrayList<>();

        Point previousPoint1 = new Point(0,0);


        for(Point point1: wire1) {
            logger.debug("Current point on first segment: " + wire1);
            Point previousPoint2 = new Point(0,0);
            for (Point point2 : wire2) {
                logger.debug("Current point on segment segment: " + wire2);

                Point intersection = intersect(previousPoint1, point1, previousPoint2, point2);
                if (intersection != null && (intersection.x != 0 || intersection.y != 0)) {
                    if(!intersections.contains(intersection)) {
                        logger.info("New intersection found: " + intersection);
                        intersections.add(intersection);
                    }
                }

                previousPoint2 = point2;
            }

            previousPoint1 = point1;
        }

        return intersections;
    }



    /**
     * Gets minimal steps to crossing
     * @param wire1 the first wire
     * @param wire2 the second wire
     * @return the minimal number of steps to crossing
     */
    public int getMinimalSteps(List<Point> wire1, List<Point> wire2) {
        logger.info("Find all intersections and get the minimal number of steps");

        Point previousPoint1 = new Point(0,0);

        int minimalNumberOfSteps = Integer.MAX_VALUE;

        int wire1Steps=0;
        for(Point point1: wire1) {
            logger.debug("Current point on first segment: " + wire1);
            int wire2Steps=0;
            Point previousPoint2 = new Point(0,0);
            for (Point point2 : wire2) {
                logger.debug("Current point on segment segment: " + wire2);

                Point intersection = intersect(previousPoint1, point1, previousPoint2, point2);
                if (intersection != null && (intersection.x != 0 || intersection.y != 0)) {
                    logger.info("New intersection found: " + intersection);

                    // get steps from previousPoint1 to intersection
                    int stepsToIntersection1;
                    if(previousPoint1.x == intersection.x) {
                        stepsToIntersection1 = wire1Steps+Math.abs(intersection.y-previousPoint1.y);
                    }
                    else {
                        stepsToIntersection1 = wire1Steps+Math.abs(intersection.x-previousPoint1.x);
                    }
                    int stepsToIntersection2;
                    if(previousPoint2.x == intersection.x) {
                        stepsToIntersection2 = wire2Steps+Math.abs(intersection.y-previousPoint2.y);
                    }
                    else {
                        stepsToIntersection2 = wire2Steps+Math.abs(intersection.x-previousPoint2.x);
                    }
                    logger.info("Steps: " + stepsToIntersection1 + " , " + stepsToIntersection2);

                    if(minimalNumberOfSteps > stepsToIntersection1+stepsToIntersection2) {
                        minimalNumberOfSteps = stepsToIntersection1 + stepsToIntersection2;
                    }
                }

                // increase number of steps
                if(point2.x != previousPoint2.x) {
                    wire2Steps+=Math.abs(point2.x-previousPoint2.x);
                }
                else {
                    wire2Steps+=Math.abs(point2.y-previousPoint2.y);
                }
                previousPoint2 = point2;
            }
            // increase number of steps
            if(point1.x != previousPoint1.x) {
                wire1Steps+=Math.abs(point1.x-previousPoint1.x);
            }
            else {
                wire1Steps+=Math.abs(point1.y-previousPoint1.y);
            }
            previousPoint1 = point1;
        }

        return minimalNumberOfSteps;
    }

    /**
     * Compute if there is an intersection between [AB] and [CD]
     * @param A
     * @param B
     * @param C
     * @param D
     * @return true if the segments intersects
     */
    private Point intersect(Point A, Point B, Point C, Point D) {
        if(A.x == B.x) {
            // [AB] is a vertical edge
            if(C.x == D.x) {
                // [CD] is also vertical ==> no intersection
                return null;
            }

            // intersection if C.y contained between A and B
            if(((C.y >= Math.min(A.y, B.y)) && (C.y <= Math.max(A.y, B.y))) &&
                (A.x >= Math.min(C.x, D.x)) && (A.x <= Math.max(C.x, D.x))){

                return new Point(A.x, C.y);
            }

        }
        else {
            // [AB] is an horizontal edge
            if(C.y == D.y) {
                // [CD] is also horizontal  ==> no intersection
                return null;
            }

            // intersection if C.y contained between A and B
            if((C.x >= Math.min(A.x, B.x) && C.x <= Math.max(A.x, B.x)) &&
                A.y >= Math.min(C.y, D.y) && A.y <= Math.max(C.y, D.y)){
                return new Point(C.x, A.y);
            }
        }
        return null;
    }

    /**
     * Gets the closest point to central
     * @param points
     * @return
     */
    public Point findClosestToCentral(List<Point> points){
        Point closest = null;
        int minDistance = Integer.MAX_VALUE;
        for(Point point: points) {
            int dist = manhattanDistanceToCentral(point);
            if(dist < minDistance && dist != 0) {
                closest=point;
                minDistance=dist;
            }
        }
        return closest;
    }

    /**
     * Compute manhattan distance from central to given point
     * @param point
     * @return the manhattan distance from this point to central
     */
    public int manhattanDistanceToCentral(Point point) {
        return Math.abs(point.x) +Math.abs(point.y);
    }

}
