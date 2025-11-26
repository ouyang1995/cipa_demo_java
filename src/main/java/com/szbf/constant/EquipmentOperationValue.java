package com.szbf.constant;

import com.szbf.pojo.method.equipmentOperation;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName EquipmentOperationValue.java
 * @Description TODO
 * @createTime 2022年04月14日 15:43:00
 */
public class EquipmentOperationValue {
    public static final equipmentOperation OpenCommunication = new equipmentOperation("OpenCommunication","打开通讯","OpenCommunication","培养箱的设备连接",null);
    public static final equipmentOperation CloseCommunication = new equipmentOperation("CloseCommunication","断开连接设备","CloseCommunication","培养箱的设备断开连接",null);
    public static final equipmentOperation ResetInit = new equipmentOperation("ResetAndInit","初始化","Init","培养箱设备软件初始化,用于发生错误使用,可能恢复",null);
    public static final equipmentOperation OpenGate = new equipmentOperation("OpenGate","开仓","OpenGate","打开培养箱舱门",null);
    public static final equipmentOperation In =new equipmentOperation("in","进仓","ImportPlate","进入培养箱","{  \"nest\": [    {      \"description\": {        \"name\": \"nestColumnCo\",        \"label\": \"nestColumnCo\",        \"label_english\": \"nestColumnCo\",        \"type\": \"input\",        \"value\": null,        \"placeholder\": \"请输入\",        \"placeholder_english\": \"Please enter\",        \"required\": 1,        \"message\": \"不能为空\",        \"message_english\": \"not null\"      },      \"key\": \"nestColumnCo\",      \"value\": null    },    {      \"description\": {        \"name\": \"nestLayerCo\",        \"label\": \"nestLayerCo\",        \"label_english\": \"nestLayerCo\",        \"type\": \"input\",        \"value\": null,        \"placeholder\": \"请输入\",        \"placeholder_english\": \"Please enter\",        \"required\": 1,        \"message\": \"不能为空\",        \"message_english\": \"not null\"      },      \"key\": \"nestLayerCo\",      \"value\": null    }  ]}");

    public static final equipmentOperation Out =new equipmentOperation("out","出仓","ExportPlate","退出培养箱","{  \"nest\": [    {      \"description\": {        \"name\": \"nestColumnCo\",        \"label\": \"nestColumnCo\",        \"label_english\": \"nestColumnCo\",        \"type\": \"input\",        \"value\": null,        \"placeholder\": \"请输入\",        \"placeholder_english\": \"Please enter\",        \"required\": 1,        \"message\": \"不能为空\",        \"message_english\": \"not null\"      },      \"key\": \"nestColumnCo\",      \"value\": null    },    {      \"description\": {        \"name\": \"nestLayerCo\",        \"label\": \"nestLayerCo\",        \"label_english\": \"nestLayerCo\",        \"type\": \"input\",        \"value\": null,        \"placeholder\": \"请输入\",        \"placeholder_english\": \"Please enter\",        \"required\": 1,        \"message\": \"不能为空\",        \"message_english\": \"not null\"      },      \"key\": \"nestLayerCo\",      \"value\": null    }  ]}");
    public static final equipmentOperation CloseGate = new equipmentOperation("CloseGate","关仓","CloseGate","关闭培养箱舱门",null);
    public static final equipmentOperation ActivateShaker = new equipmentOperation("ActivateShaker","开启关闭震动","Activate Shaker","培养箱的震动开启关闭","{\n" +
            "    \"nest\": [\n" +
            "        {\n" +
            "            \"description\": {\n" +
            "                \"name\": \"Shaker\",\n" +
            "                \"label\": \"是否震动\",\n" +
            "                \"label_english\": \"mode\",\n" +
            "                \"type\": \"select\",\n" +
            "                \"value\": null,\n" +
            "                \"defaultValue\": \"\",\n" +
            "                \"placeholder\": \"请选择\",\n" +
            "                \"placeholder_english\": \"Please choose\",\n" +
            "                \"optionType\": \"radio\",\n" +
            "                \"selectionList\": [\n" +
            "                    {\n" +
            "                        \"name\": \"true\",\n" +
            "                        \"label\": \"是\"\n" +
            "                    },\n" +
            "                    {\n" +
            "                        \"name\": \"false\",\n" +
            "                        \"label\": \"否\"\n" +
            "                    }"+
            "                ],\n" +
            "                \"required\": 1,\n" +
            "                \"message\": \"是否震动不可为空\",\n" +
            "                \"message_english\": \"The Shaker cannot be empty\",\n" +
            "                \"trigger\": [\n" +
            "                    \"change\",\n" +
            "                    \"blur\",\n" +
            "                    \"submit\"\n" +
            "                ]\n" +
            "            },\n" +
            "            \"key\": \"Shaker\",\n" +
            "            \"value\": null\n" +
            "        }"+
            "    ]\n" +
            "}");
}
