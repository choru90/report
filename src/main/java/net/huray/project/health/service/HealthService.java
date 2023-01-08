package net.huray.project.health.service;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

  public List<String> list() {
    return List.of("test1", "test2", "test3");
  }

}
