package dev.franklindot04.learnjava.backend.production.info;

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

    public InfoController(
            @Value("${spring.application.name}") String applicationName,
            @Value("${app.demo-mode:true}") String demoMode) {
        this.applicationName = applicationName;
        this.demoMode = demoMode;
    }

    @GetMapping("/info")
    Map<String, String> info() {
        return Map.of(
                "application", applicationName,
                "demoMode", demoMode,
                "message", "Educational production-readiness skeleton");
    }

    @GetMapping("/admin/readiness-notes")
    Map<String, String> adminNotes() {
        return Map.of(
                "note", "Admin-only demo route. Real production systems need stronger account and audit design.");
    }
}
