package net.huray.project.health;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.huray.project.health.service.HealthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HealthController {

  private final HealthService healthService;

  @GetMapping(value = "/healths")
  public List<String> getHealths() {
    return healthService.list();
  }

}
