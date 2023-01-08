package net.huray.project.common.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 컨텐트 타입 열거형 클래스
 * @author jindev
 */
@AllArgsConstructor
public enum ContentSubType {

    /** 게시글형 컨텐츠 */
    POST(Values.POST, "게시글형 컨텐츠"),
    /** 카드형 컨텐츠 */
    CARD(Values.CARD, "카드형 컨텐츠");

    @Getter
    private String value;

    @Getter
    private String label;

    public static class Values {
        public static final String POST = "POST";
        public static final String CARD = "CARD";
    }
}
