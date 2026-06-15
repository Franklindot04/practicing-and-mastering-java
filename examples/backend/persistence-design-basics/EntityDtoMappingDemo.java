public class EntityDtoMappingDemo {
    public static void main(String[] args) {
        TaskEntity entity = new TaskEntity(1, "Map entities", "internal-review-note", false);
        TaskResponse response = TaskMapper.toResponse(entity);

        System.out.println(response);
    }

    record TaskEntity(long id, String title, String internalNote, boolean completed) {
    }

    record TaskResponse(long id, String title, boolean completed) {
    }

    static class TaskMapper {
        static TaskResponse toResponse(TaskEntity entity) {
            return new TaskResponse(entity.id(), entity.title(), entity.completed());
        }
    }
}
