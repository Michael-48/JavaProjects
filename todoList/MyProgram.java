public class MyProgram extends ConsoleProgram
{
    Todo mainTodo = new Todo();
    boolean iterate = true;
    
    public void run()
    {
        eventLoop();
    }
    
    private void eventLoop()
    {
        while (iterate)
        {
            String str = readLine("enter command:\n");
            processInput(str);
        }
    }
    
    private void processInput(String str)
    {
        switch (str.toLowerCase())
        {
            case "list":
                mainTodo.list();
                break;
            case "todo":
                mainTodo.add();
                break;
            case "clear":
                mainTodo.clear();
                break;
            case "end":
                iterate = false;
                break;
            default:
                System.out.println("unrecognized command");
        }
    }
}