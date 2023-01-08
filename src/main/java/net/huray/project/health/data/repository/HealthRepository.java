package net.huray.project.health.data.repository;

import net.huray.project.health.data.entity.Health;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthRepository extends JpaRepository<Health, Long> {

}
