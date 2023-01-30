package duke.commands.taskCommands;

import duke.commands.Command;
import duke.commands.InvalidTaskException;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import static duke.constants.Constants.LINEBREAK;

public class ToDoCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList taskList){
        int markIndex;
        int indexCount = Task.getIndexCount();
        String description;
        try {
            markIndex = line.indexOf(' ');
            if (markIndex == -1) {
                throw new InvalidTaskException();
            }

            description = line.substring(markIndex + 1).trim();
            ToDo newTask = new ToDo(description);
            taskList.addTask(indexCount, newTask);
        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        }
    }
}
