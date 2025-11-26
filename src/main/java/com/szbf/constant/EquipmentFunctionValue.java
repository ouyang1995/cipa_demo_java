package com.szbf.constant;

import com.szbf.pojo.method.equipmentFunction;
import com.szbf.util.ImageUtil;

import java.io.IOException;

/**
 * @author 刘荣
 * @version 1.0.1
 * @ClassName EquipmentFunctionValue.java
 * @Description TODO
 * @createTime 2022年04月14日 15:26:00
 */
public class EquipmentFunctionValue {
    public static final equipmentFunction Incubate;

    static {
        try {
            Incubate = new equipmentFunction("shaking_culture","震荡培养","ShakingCulture","培养箱的培养功能,用于样品培养","3600","培养类","Culturing","data:image/png;base64,"+ ImageUtil.imagePngToBase64String("Incubate_black.png"),"data:image/png;base64,"+ ImageUtil.imagePngToBase64String("Incubate_white.png"),"{\"nest\":[{\"key\":\"equipment\",\"value\":null,\"description\":{\"label\":\"设备名称\",\"label_english\":\"equipment\",\"name\":\"equipment\",\"placeholder\":\"请选择\",\"placeholder_english\":\"Please choose\",\"optionType\":\"equipment\",\"required\":1,\"type\":\"select\"}},{\"key\":\"inputs\",\"value\":null,\"description\":{\"label\":\"输入\",\"label_english\":\"input\",\"name\":\"inputs\",\"nestOptionType\":\"inputs\",\"type\":\"single_nest\",\"required\":0},\"nest\":[{\"key\":\"material_name\",\"value\":null,\"description\":{\"label\":\"物料名称\",\"label_english\":\"material name\",\"name\":\"material_name\",\"placeholder\":\"请选择\",\"placeholder_english\":\"Please choose\",\"optionType\":\"materiel\",\"required\":1,\"type\":\"select\"}},{\"key\":\"position\",\"value\":null,\"description\":{\"label\":\"选择移动位置\",\"label_english\":\"choose move location\",\"name\":\"position\",\"placeholder\":\"请选择\",\"placeholder_english\":\"Please choose\",\"optionType\":\"position\",\"required\":1,\"type\":\"select\"}},{\"description\":{\"name\":\"position_classification\",\"label\":\"位置分类\",\"label_english\":\"Nest classes\",\"type\":\"disabled\",\"value\":null,\"optionType\":\"disabled\",\"required\":0},\"key\":\"position_classification\",\"value\":null}]},{\"key\":\"training_time\",\"value\":\"\",\"description\":{\"label\":\"培养时间（s）\",\"label_english\":\"training time\",\"name\":\"training_time\",\"required\":1,\"type\":\"seconds\",\"isExpression\":0}},{\"key\":\"set_param\",\"value\":\"0\",\"description\":{\"label\":\"设置参数\",\"label_english\":\"setParam\",\"name\":\"set_param\",\"value\":\"0\",\"optionType\":\"radio\",\"required\":0,\"isAssociation\":true,\"type\":\"select\",\"selectionList\":[{\"label\":\"是\",\"label_english\":\"yes\",\"value\":\"1\"},{\"label\":\"否\",\"label_english\":\"no\",\"value\":\"0\"}],\"association\":[{\"association_name\":\"temperature\",\"source_value\":\"1\",\"action\":\"visible\",\"actionValue\":\"\",\"actionFilterableOption\":null},{\"association_name\":\"speed\",\"source_value\":\"1\",\"action\":\"visible\",\"actionValue\":null,\"actionFilterableOption\":null}]}},{\"key\":\"temperature\",\"value\":\"30\",\"description\":{\"label\":\"温度\",\"label_english\":\"Temperature\",\"name\":\"temperature\",\"value\":\"30\",\"minValue\":4,\"maxValue\":50,\"precision\":1,\"required\":1,\"type\":\"float\",\"isExpression\":0}},{\"key\":\"speed\",\"value\":\"400\",\"description\":{\"label\":\"转速\",\"label_english\":\"Speed\",\"name\":\"speed\",\"value\":\"400\",\"minValue\":1,\"maxValue\":1000,\"precision\":0,\"required\":1,\"type\":\"int\",\"isExpression\":0}},{\"key\":\"remark\",\"value\":null,\"description\":{\"label\":\"备注\",\"label_english\":\"remark\",\"name\":\"remark\",\"placeholder\":\"请输入\",\"placeholder_english\":\"Please enter\",\"isExpression\":0,\"required\":0,\"type\":\"input\"}}]}");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
