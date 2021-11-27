public class Grid extends ConsoleProgram {
    
    private int[] size = {0,0};
    
    private class Cell {
        private int[] position = {0,0};
        
        public boolean state = false;
        
        public Cell(int x, int y) {
            position[0] = x;
            position[1] = y;
        }
    }
    
    private Cell[][] cells;
    
    public int[] getSize() {
        return size;
    }
    
    public void InitGridCells(int[] _size) {
        size = _size;

        cells = new Cell[size[0]][size[1]];

        for (int x = 0; x < size[0]; x++) {
            for (int y = 0; y < size[1]; y++) {
                cells[x][y] = new Cell(x,y);
            }
        }
    }
    
    public void setCellState(int x, int y, boolean state) {
        cells[x][y].state = state;
    }
    
    public boolean getCellState(int x, int y) {
        return cells[x][y].state;
    }
    
    public void renderGrid() {
        String ColumnLabel = "  ";
        for (int y = 0; y < size[1]; y++) {
            ColumnLabel += ((char)(y+65))+" ";
        }
        System.out.println(ColumnLabel);
        
        for (int x = 0; x < size[0]; x++) {
            String currentRow = String.format("%1$2s", x+1);
            
            for (int y = 0; y < size[1]; y++) {
                currentRow = currentRow+(cells[x][y].state? "\u2588\u2588" : "__");
            }
            System.out.println(currentRow);
        }
    }
}