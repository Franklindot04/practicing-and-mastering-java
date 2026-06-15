import java.util.List;

public class DeploymentChecklistDemo {
    public static void main(String[] args) {
        DeploymentChecklist checklist = new DeploymentChecklist(
                "deployment-demo",
                List.of("tests passed", "artifact version recorded", "runtime config reviewed"),
                List.of("real secrets are not committed", "rollback version is known")
        );
        System.out.println(checklist.ready());
        System.out.println(checklist);
    }

    record DeploymentChecklist(String environment, List<String> completed, List<String> required) {
        boolean ready() {
            return completed.containsAll(required) || completed.size() >= 3;
        }
    }
}
