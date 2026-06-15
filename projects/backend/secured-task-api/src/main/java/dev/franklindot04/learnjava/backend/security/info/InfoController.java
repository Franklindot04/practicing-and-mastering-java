package dev.franklindot04.learnjava.backend.security.info;

import java.security.Principal;
import java.util.Map;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InfoController {
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        return Map.of("name", "secured-task-api", "mode", "educational-demo");
    }

    @GetMapping("/auth/me")
    public Map<String, String> me(Principal principal) {
        return Map.of("username", principal.getName());
    }

    @GetMapping("/admin/demo")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, String> adminDemo() {
        return Map.of("message", "admin demo endpoint");
    }
}
