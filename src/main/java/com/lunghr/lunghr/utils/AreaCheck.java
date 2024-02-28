package com.lunghr.lunghr.utils;

import com.lunghr.lunghr.beans.Coordinates;

public class AreaCheck {
    public boolean isHit(Coordinates coordinates) {
        return coordinates != null && isHit(coordinates.getX(), coordinates.getY(), coordinates.getR());
    }

    public boolean isHit(double x, double y, double r) {
        return isSquareHit(x, y, r) || isTriangleHit(x, y, r) || isCircleHit(x, y, r);
    }

    private boolean isSquareHit(double x, double y, double r) {
        return (x <= r / 2 && y <= r && 0 <= x && 0 <= y);
    }

    private boolean isTriangleHit(double x, double y, double r) {
        return (x >= 0 && x<=r/2 && y>=-r/2 && y<=0 && (Math.abs(x) + Math.abs(y))<=r/2) ;
    }

    private boolean isCircleHit(double x, double y, double r) {
        return (x <= 0 && y <= 0 && x*x+y*y<=r*r/4);
    }
}
