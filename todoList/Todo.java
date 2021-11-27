import java.util.ArrayList;

final class Todo extends ConsoleProgram {
    
    private ArrayList<String> todoList = new ArrayList<String>();
    
    public void add()
    {
        String newTodo = readLine("enter text:\n");
        todoList.add(newTodo);
        System.out.println("todo entered!");
    }
    
    public void clear()
    {
        todoList = new ArrayList<String>();
    }
    
    public void list()
    {
        if (todoList.size() != 0) {
            for (int i = 0; i < todoList.size(); i++)
            {
                System.out.println(String.valueOf(i+1)+": "+todoList.get(i));
            }
        } else {
            System.out.println("nothing todo");
        }
    }
}