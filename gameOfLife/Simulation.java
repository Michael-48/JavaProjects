public class Simulation {
    public void Step(Grid grid) {
        int[] size = grid.getSize();
        boolean[][] newState = new boolean[size[0]][size[1]];
        
        for (int x = 0; x < size[0]; x++) {
            for (int y = 0; y < size[1]; y++) {
                int population = getCellBorderState(grid, x, y);
                
                newState[x][y] = false;
                
                if (grid.getCellState(x,y) && ((population == 2 || population == 3))) {
                    newState[x][y] = true;
                } else if (population == 3) {
                    newState[x][y] = true;
                }
            }
        }
        
        for (int x = 0; x < size[0]; x++) {
            for (int y = 0; y < size[1]; y++) {
                grid.setCellState(x,y,newState[x][y]);
            }
        }
    }
    
    private int getCellBorderState(Grid grid, int x, int y) {
        int[] size = grid.getSize();
        int sum = 0;
        
        int[][] borders = {
            {-1,1 }, {0 ,1 }, {1 ,1 },
            {-1,0 },          {1 ,0 },
            {-1,-1}, {0 ,-1}, {1 ,-1}
        };
        for (int[] i: borders) {
            if ((x+i[0] >= 0 && x+i[0] < size[0]) && (y+i[1] >= 0 && y+i[1] < size[1])) {
                sum += (grid.getCellState(x+i[0], y+i[1])? 1: 0);
            }
        }
        return sum;
    }
}