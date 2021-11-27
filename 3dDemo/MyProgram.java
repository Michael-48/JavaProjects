public class MyProgram extends ConsoleProgram
{
    // Renders line segments in a 3d envionment
    
    // problems: 
    // the angle calculation for movement is wrong
    // the walls curve when they shouldnt
    // the movement might be centered at the left corner of the screen
    // the projection distortion calculation might be centered at the left corner of the screen
    // no dithering algorithm, replace the rand color thing
    
    public void run()
    {
        int scale = 20;
        Scene scene = new Scene(90, 120, new Line.Point(16*scale,9*scale));
        while (true) {
            switch (readLine("Command: ")) {
                case "m":
                    double speed = readDouble("Distance: ");
                    scene.Move(new Line.Point(speed,0));
                    break;
                case "l":
                    scene.Rotate(45d);
                    break;
                case "r":
                    scene.Rotate(-45d);
                    break;
                default:
                    break;
            }
            scene.GenerateRays();
            double[] imgData = scene.Render();
            String[] displayData = scene.Display(imgData);
            
            for (String x : displayData) {
                System.out.println(x);
            }
        }
    }
}