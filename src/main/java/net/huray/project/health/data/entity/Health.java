package net.huray.project.health.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.project.common.data.entity.BaseEntity;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "health")
public class Health extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @Column(name = "name", length = 32, nullable = false)
  private String name;

}
