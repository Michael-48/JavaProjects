public class Config extends ConsoleProgram {
    
    public int[] PromptSize() {
        String[] prompts = {
            "horizonal size of the grid: ",
            "vertical size of the grid: "
        };
        int[] sizeLimits = {32,25};
        
        int[] size = {0,0};
        
        for (int i = 0; i < sizeLimits.length; i++) {
            int currentSize = readInt(prompts[i]);
            currentSize = Math.max(currentSize,3);
            
            if (currentSize > sizeLimits[i]) {
                currentSize = sizeLimits[i];
                System.out.println("Using maximum size of "+String.valueOf(sizeLimits[i]));
            }
            
            size[i] = currentSize;
        }
        return size;
    }
    
    static protected class editCommands {
        
        static public void _help() {
            String[] cellEdit = {"help", "inv", "rand", "play"};
            String[] cellEditDescription = {
                "Displays all commands",
                "Inverts a cell at specificed location",
                "randomizes the grid",
                "Finishes editing the grid, starts simulation"
            };
            
            for (int i = 0; i < cellEdit.length; i++) {
                System.out.println("\t"+cellEdit[i]+" - "+cellEditDescription[i]);
            }
        }
        
        static public void _inv(Grid grid, String _Coordinate) {
            String Coordinate = _Coordinate;
            if (Coordinate == "") {
                Coordinate = grid.readLine("Coordinate: ");
            }
            Coordinate = Coordinate.toUpperCase();
            
            try {
                int y = ((byte)Coordinate.charAt(0))-65;
                int x = Integer.parseInt(Coordinate.substring(1))-1;
                grid.setCellState(x, y, !grid.getCellState(x,y));
                grid.renderGrid();
            } catch (Exception e) {
                System.out.println("Invalid Format; Use Format Ex. \"C8\".");
            }
        }
        
        static public void _rand(Grid grid, String _Threshold) {
            try {
                double Threshold = 0;
                
                if (_Threshold == "") {
                    Threshold = grid.readDouble("Threshold: ");
                } else {
                    Threshold = Double.parseDouble(_Threshold);
                }
                
                int[] Size = grid.getSize();
                
                for (int x = 0; x < Size[0]; x++) {
                    for (int y = 0; y < Size[1]; y++) {
                        grid.setCellState(x,y,Math.random() < Threshold);
                    }
                }
                grid.renderGrid();
            } catch (Exception e) {
                System.out.println("Use a decimal between 0-1.");
            }
        }
        
    }
    
    public void PromptCells(Grid grid) {
        System.out.println("Edit the starting cells:");
        
        editCommands._help();
        
        commandLoop: while (true) {
            String[] currentInput = readLine("Enter command: ").split(" ");
            String ExtraParams = currentInput.length>1? currentInput[1]: "";
            
            switch (currentInput[0].toLowerCase()) {
                case "help": 
                    editCommands._help();
                    break;
                case "inv": 
                    editCommands._inv(grid, ExtraParams);
                    break;
                case "rand": 
                    editCommands._rand(grid, ExtraParams);
                    break;
                case "play": 
                    break commandLoop;
                default:
                    System.out.println("What?");
                    editCommands._help();
                    break;
            }
        }
    }
}