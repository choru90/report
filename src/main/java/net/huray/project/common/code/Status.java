package net.huray.project.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Status {

  TODO("할일"),
  ACTIVE("진행중"),
  DONE("완료");

  @Getter
  private String label;

}
