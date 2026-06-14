public class Task {
    private final String description;
    private boolean complete;

    public Task(String description) {
        this.description = description;
        this.complete = false;
    }

    public String description() {
        return description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void markComplete() {
        complete = true;
    }

    public String displayText(int number) {
        String status = complete ? "[x]" : "[ ]";
        return number + ". " + status + " " + description;
    }
}
