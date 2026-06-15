import java.util.ArrayList;
import java.util.List;

public class TransactionBoundaryConceptDemo {
    public static void main(String[] args) {
        TaskWorkflow workflow = new TaskWorkflow();
        workflow.createTaskAndAuditEntry("Learn transaction boundaries");
        workflow.printState();
    }

    static class TaskWorkflow {
        private final List<String> tasks = new ArrayList<>();
        private final List<String> auditLog = new ArrayList<>();

        void createTaskAndAuditEntry(String title) {
            // In a real database-backed service, this whole method should usually be one transaction.
            // If audit creation fails after the task is saved, a transaction can roll back both steps.
            tasks.add(title);
            auditLog.add("Created task: " + title);
        }

        void printState() {
            System.out.println("tasks=" + tasks);
            System.out.println("auditLog=" + auditLog);
        }
    }
}
