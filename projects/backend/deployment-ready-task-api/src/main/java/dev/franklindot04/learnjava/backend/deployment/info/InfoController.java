package dev.franklindot04.learnjava.backend.deployment.info;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class InfoController {
    private final String applicationName;
    private final String demoMode;
    private final String activeProfile;
    private final String releaseVersion;
    private final String commitSha;

    public InfoController(
            @Value("${spring.application.name}") String applicationName,
            @Value("${app.demo-mode:true}") String demoMode,
            @Value("${app.runtime-profile:local}") String activeProfile,
            @Value("${app.release-version:0.0.0-local}") String releaseVersion,
            @Value("${app.commit-sha:unknown}") String commitSha) {
        this.applicationName = applicationName;
        this.demoMode = demoMode;
        this.activeProfile = activeProfile;
        this.releaseVersion = releaseVersion;
        this.commitSha = commitSha;
    }

    @GetMapping("/info")
    Map<String, String> info() {
        return Map.of(
                "application", applicationName,
                "demoMode", demoMode,
                "runtimeProfile", activeProfile,
                "message", "Educational deployment-readiness skeleton");
    }

    @GetMapping("/version")
    Map<String, String> version() {
        return Map.of(
                "version", releaseVersion,
                "commit", commitSha,
                "application", applicationName);
    }

    @GetMapping("/smoke")
    Map<String, String> smoke() {
        return Map.of(
                "status", "UP",
                "profile", activeProfile,
                "version", releaseVersion);
    }

    @GetMapping("/admin/readiness-notes")
    Map<String, String> adminNotes() {
        return Map.of(
                "note", "Admin-only demo route. Real deployment needs stronger account, audit, and rollback design.");
    }
}
