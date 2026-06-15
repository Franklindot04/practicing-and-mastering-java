public class RequestResponseShapeDemo {
    public static void main(String[] args) {
        CreateTaskRequest request = new CreateTaskRequest("Read HTTP notes", "HIGH");
        TaskResponse response = new TaskResponse(1, request.title(), request.priority(), false);

        System.out.println("Request title: " + request.title());
        System.out.println("Response: " + response);
    }

    record CreateTaskRequest(String title, String priority) {
    }

    record TaskResponse(long id, String title, String priority, boolean completed) {
    }
}
