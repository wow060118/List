package com.example.yfr.demo.alleumn;

public enum AdviceEnum {
    NONE(-1, "none"),BUG(-1, "bug"),ADVICE(-1, "demo_advice"),USER(-1, "user"),OTHER(-1, "other");
    private Integer code;
    private String desc;

    private AdviceEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static AdviceEnum fromName(String name) {
        for (AdviceEnum e : AdviceEnum.values()) {
            if (e.name().equals(name)) {
                return e;
            }
        }
        return AdviceEnum.NONE;
    }

    public static AdviceEnum fromCode(Integer code) {
        for (AdviceEnum e : AdviceEnum.values()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return AdviceEnum.NONE;
    }

    public static AdviceEnum fromDesc(String desc) {
        for (AdviceEnum e : AdviceEnum.values()) {
            if (e.getDesc().equals(desc)) {
                return e;
            }
        }
        return AdviceEnum.NONE;
    }
}