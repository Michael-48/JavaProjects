public class Line {
    // Line Segment
    
    private Point _p1;
    private Point _p2;
    // p1 is a direct coordinate
    // Point p2 is an offset of p1
    
    static class Point {
        private double _x;
        private double _y;
        
        public Point(double x, double y) {
            this._x = x;
            this._y = y;
        }
        
        public double GetX() {
            return this._x;
        }   
        
        public double GetY() {
            return this._y;
        }

        // Operations
        public double cross(Point b) {
            return (this._x*b.GetY())-(this._y*b.GetX());
        }
        
        public double length() {
            return Math.sqrt((Math.pow(this._x,2))+Math.pow(this._y,2));
        }
         
        public Point add(Point b) {
            return new Point(this._x+b.GetX(), this._y+b.GetY());
        }
        
        public Point sub(Point b) {
            return new Point(this._x-b.GetX(), this._y-b.GetY());
        }
        
        public Point mul(double x) {
            return new Point(this._x*x,this._y*x);
        }
        
        public Point rot(double x) {
            double theta = Math.toRadians(x);
            return new Point(
                this._x * Math.cos(theta) - this._y * Math.sin(theta),
                this._x * Math.sin(theta) + this._y * Math.cos(theta)
            );
        }
        
        public void log() {
            System.out.println("Point Coordinates: X = "+this._x+", Y = "+this._y);
        }
    }
    
    public Line(Point p1, Point p2) {
        this._p1 = p1;
        this._p2 = p2;
    }
    
    public Point GetP1() {
        return this._p1;
    }
    public Point GetP2() {
        return this._p2;
    }
    
    public double GetAngle(Line b) {
        return Math.atan2(this._p2._y-b.GetP1().GetY(), this._p2._x-b.GetP1().GetY());
    }
    
    public double GetIntersection(Line viewRay, Line b) { 
        // this = camera ray, viewRay = foward camera direction b = wall line segment
        Point base = this._p1.sub(b.GetP1());
        double cross1 = this._p2.cross(b.GetP2());
        
        double t = -base.cross(b.GetP2())/cross1;
        double u = -base.cross(this._p2)/cross1;
        
        double intersectionDist = b.GetP2().mul(t).length();
        double fixedProjection = Math.cos(GetAngle(viewRay)/2);
        
        return ((1>t && 0<t) && (1>u && 0<u))? intersectionDist*fixedProjection: -1d;
    }
}