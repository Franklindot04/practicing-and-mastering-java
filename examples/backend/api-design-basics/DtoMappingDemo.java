public class DtoMappingDemo {
    public static void main(String[] args) {
        Task task = new Task(10, "Practice DTO mapping", "MEDIUM", false, "internal-audit-note");
        TaskResponse response = TaskMapper.toResponse(task);

        System.out.println(response);
    }

    record Task(long id, String title, String priority, boolean completed, String internalNote) {
    }

    record TaskResponse(long id, String title, String priority, boolean completed) {
    }

    static class TaskMapper {
        static TaskResponse toResponse(Task task) {
            return new TaskResponse(task.id(), task.title(), task.priority(), task.completed());
        }
    }
}
