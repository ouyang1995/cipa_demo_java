package com.szbf.constant;

public enum CommandEnum {
    //Fill in according to the instructions in your interface document
    Plate_INIT2A("","初始化plate"),
    Plate_INIT2B("","初始化plate"),

    Plate_INIT1A("","初始化plate"),
    Plate_INIT1B("","初始化plate"),
    OPEN_COMMUNICATION("","打开连接"),
    CLOSE_COMMUNICATION("","关闭连接"),
    RESET("","重置"),
    SOFT_RESET("","软件重置"),
    INITIALIZE("","初始化"),
    ERROR_FLAG("","错误标志"),
    READ_READY("","读准备"),
    WHILE_STATUS("","打开连接"),
    STATUS("","读状态"),
    OPEN_GATE("","打开盖子"),
    CLOSE_GATE("","关闭盖子"),
    TERMINATE_ACCESS("",""),
    COMMAND_A("",""),
    COMMAND_B("",""),
    COMMAND_C("",""),
    COMMAAND_D("",""),
    COMMAND_E("",""),
    COMMAND_F("",""),
    COMMAND_G("",""),
    COMMAND_H("",""),
    READ_SWAP_STATION("",""),
    SWAP_STATION_180("",""),
    SWAP_STATION_BACK("",""),

    ReadTransferStationPlateSensor("",""),
    READ_SHAKER_STATUS("",""),
    ACTIVATE_SHAKER("",""),
    STOP_SHAKER("",""),
    READ_ACTUAL_TEMPERATURE("",""),
    READ_SET_TEMPERATURE("",""),
    SET_TEMPERATURE("",""),
    READ_LEVEL("",""),
    READ_POSITION("",""),
    SET_SHAKER_SPEED("",""),
    READ_SHAKER_SPEED("",""),
    READ_ACTUAL_HUMIDITY("",""),
    READ_SET_HUMIDITY("",""),
    SET_HUMIDITY("",""),
    READ_ACTUAL_CO2("",""),
    READ_SET_CO2("",""),
    SET_CO2("",""),
    READ_ACTUAL_N2("",""),
    READ_SET_N2("",""),
    SET_N2("",""),
    READ_ACTUAL_O2("",""),
    READ_SET_O2("",""),
    SET_O2("",""),
    Plate_SET1("","" ),
    Plate_SET2("","" );

    CommandEnum(String instruction, String desc) {
        this.instruction = instruction;
        this.desc = desc;
    }

    private String instruction;

    private String desc;

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
