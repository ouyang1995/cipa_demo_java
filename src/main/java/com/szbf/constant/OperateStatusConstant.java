package com.szbf.constant;

public enum OperateStatusConstant {
    FINISH(1,"finsh","结束"),
    FREE(2,"free","空闲"),
    COMPLETED(3,"completed","完成");


    private int key;

    private String value;

    private String desc;

    OperateStatusConstant(int key, String value, String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
