public class ReleaseVersionInfoDemo {
    public static void main(String[] args) {
        ReleaseVersionInfo info = new ReleaseVersionInfo("1.0.0-demo", "abc1234", "deployment-demo");
        System.out.println(info.safeSummary());
    }

    record ReleaseVersionInfo(String version, String commit, String environment) {
        String safeSummary() {
            return "version=%s commit=%s environment=%s".formatted(version, commit, environment);
        }
    }
}
