public class Scene {
    private Line.Point _camera;
    private Line.Point _screenSize;
    private int _FOV;
    private double _camAngle;
    private double _viewDistance;
    
    private Line[] walls = new Line[2];
    private Line[] rays;
    
    public Scene(int FOV, double viewDistance, Line.Point screenSize) {
        this._FOV = FOV;
        this._viewDistance = viewDistance;
        this._screenSize = screenSize;
        this._camera = new Line.Point(0,0);
        this._camAngle = 0;
        
        this.rays = new Line[(int)screenSize.GetX()];
        
        walls[0] = new Line(
            new Line.Point(30,0),
            new Line.Point(0,-15)
        );
        walls[1] = new Line(
            new Line.Point(30,-15),
            new Line.Point(-15,0)
        );
    }
    
    public void GenerateRays() {
        int rayCount = (int)_screenSize.GetX();
        
        for (int x = 0; x < rayCount; x++) {
            rays[x] = new Line(
                this._camera,
                new Line.Point(
                    Math.sin(((double)x/(double)rayCount)*Math.toRadians(_FOV)+Math.toRadians(_camAngle))*_viewDistance,
                    Math.cos(((double)x/(double)rayCount)*Math.toRadians(_FOV)+Math.toRadians(_camAngle))*_viewDistance
                )
            );
        }
    }
    
    public double[] Render() {
        double[] renderData = new double[rays.length];
        
        Line viewRay = new Line(
            this._camera,
            (new Line.Point(1,0)).rot(this._camAngle)
        );
        int i = 0;
        for (Line CamLine : rays) {
            double smallestDist = 10e8;
            
            for (Line WallLine : walls) {
                double intersection = CamLine.GetIntersection(viewRay, WallLine);
                if (intersection != -1d) {
                    smallestDist = Math.min(smallestDist,intersection);
                }
            }
            renderData[i] = smallestDist;
            i++;
        }
        return renderData;
    }
    
    public String[] Display(double[] data) {
        double ySize = _screenSize.GetY();
        String[] displayData = new String[(int)ySize];
        
        for (int y = 0; y < _screenSize.GetY(); y++) {
            displayData[y] = "";
             for (int x = 0; x < _screenSize.GetX(); x++) {
                boolean wallValidity = (1/data[x])*ySize > Math.abs(((ySize/2)-y))+1;
                double diGraident = (Math.random()*6+data[x])/6;
                String[] diColor = {" ","\u2591","\u2592","\u2593","\u2588","\u2588"};
                
                displayData[y] += (
                    wallValidity? diColor[(int)diGraident]+diColor[(int)diGraident]: 
                    y>(ySize/2)+4? "\u259A\u259A": 
                    y>ySize/2? "  ": "\u259D\u259D"
                );
             }
        }
        return displayData;
    }
    
    public void Rotate(double angle) {
        //will break if modulo isint implemented
        _camAngle += angle;
    }
    
    public void Move(Line.Point a) {
        _camera = _camera.add(a.rot(_camAngle));
        _camera.log();
    }
}