public class RollbackDecisionDemo {
    public static void main(String[] args) {
        DeploymentSignals signals = new DeploymentSignals(false, false, 12);
        System.out.println(decide(signals));
    }

    static String decide(DeploymentSignals signals) {
        if (!signals.healthOk() || !signals.smokeTestsPassed()) {
            return "ROLLBACK: critical deployment checks failed.";
        }
        if (signals.errorCount() > 10) {
            return "PAUSE: investigate elevated errors before continuing.";
        }
        return "CONTINUE: deployment checks look acceptable.";
    }

    record DeploymentSignals(boolean healthOk, boolean smokeTestsPassed, int errorCount) {
    }
}
