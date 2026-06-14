import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks = new ArrayList<>();

    public void add(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Task description cannot be blank.");
        }
        tasks.add(new Task(description.trim()));
    }

    public List<Task> all() {
        return tasks;
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public void markComplete(int taskNumber) {
        taskAt(taskNumber).markComplete();
    }

    public void delete(int taskNumber) {
        tasks.remove(indexFor(taskNumber));
    }

    private Task taskAt(int taskNumber) {
        return tasks.get(indexFor(taskNumber));
    }

    private int indexFor(int taskNumber) {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new IllegalArgumentException("Task number does not exist.");
        }
        return taskNumber - 1;
    }
}
