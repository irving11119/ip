package duke.commands.taskCommands;

import duke.commands.Command;
import duke.exceptions.InvalidTaskException;
import duke.save.FileOperation;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

import java.io.IOException;

public class EventCommand extends Command {

    @Override
    public void handleCommand(String line, TaskList taskList){
        int markIndex;
        int markIndex1;
        int indexCount = Task.getIndexCount();
        String description;

        try {
            String from;
            String to;

            description = line.substring(line.indexOf(' ') + 1, line.indexOf('/')).trim();
            markIndex = line.indexOf("/from");
            markIndex1 = line.indexOf("/to");
            if (markIndex == -1 || markIndex1 == -1) {
                throw new InvalidTaskException();
            }

            from = line.substring(markIndex + 5, markIndex1).trim();
            to = line.substring(markIndex1 + 3).trim();

            if (from.equals("")) {
                throw new InvalidTaskException();
            }

            ToDo newDeadline = new Event(description, from, to);
            taskList.addTask(indexCount, newDeadline);
            FileOperation.updateFile(taskList);

        } catch (InvalidTaskException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }
}
