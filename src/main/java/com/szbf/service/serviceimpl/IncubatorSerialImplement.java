package com.szbf.service.serviceimpl;

import com.szbf.constant.CommandEnum;
import com.szbf.pojo.StoreConst;
import com.szbf.service.StorexService;
import com.szbf.util.HextoByte;
import com.szbf.util.TCPClient;
import com.szbf.util.TxtReadline;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IncubatorSerialImplement implements StorexService {

    public static final String COMMAND_SUFFIX= "0D";
    public static String dataForTcp="";
    //串口
/*    @Autowired
    SzbfStructurePositionService szbfStructurePositionService;*/


/*    @PostConstruct
    public void init(){
        serialPort = TCPRunner.serialPort;
    }*/

    public String commonOperation(CommandEnum commandEnum) throws Exception {
        String command = commandEnum.getInstruction() + COMMAND_SUFFIX;
        return commonOperation(command);
    }

    public String commonOperation(String data,CommandEnum commandEnum) throws Exception {
        String command = commandEnum.getInstruction() +  HextoByte.stringToHexString(data) + COMMAND_SUFFIX;
        return commonOperation(command);
    }

    @Override
    public String commonOperation(String command) throws Exception {
        Thread.sleep(1000);
        byte[] Data = HextoByte.hexToByteArray(command);
        TCPClient.Write(Data);
       // SerialListener.dataPort= "";
      //  SerialTool.sendToPort(TCPRunner.serialPort,Data);
        String read = TCPClient.Read();
        return read;
    }

    @Override
    public void whileStatus() throws Exception {
        int l=0;
        Thread.sleep(300);
        while (true) {
            //SerialListener.dataPort = "";
            byte[] data = HextoByte.hexToByteArray(CommandEnum.STATUS.getInstruction() + COMMAND_SUFFIX);
            TCPClient.Write(data);
            //SerialTool.sendToPort(TCPRunner.serialPort, data);
            Thread.sleep(1000);
            String read = TCPClient.Read();
            if (read.contains("1")) {
                break;
            }else {
                log.info("运行中，正在等待返回完成："+read);
                Thread.sleep(500);
            }
        }
    }


    @Override
    public void importPlate(String Position, String Level) throws Exception {
        String s = HextoByte.stringToHexString(Position);
        String s1 = HextoByte.stringToHexString(Level);
        List<String> commands = Arrays.asList(CommandEnum.COMMAND_A.getInstruction() + s + COMMAND_SUFFIX, CommandEnum.COMMAND_B.getInstruction() + s1 + COMMAND_SUFFIX, CommandEnum.COMMAND_C.getInstruction() + COMMAND_SUFFIX);
        for (String command : commands) {
            commonOperation(command);
        }
    }

    @Override
    public void exportPlate(String Position, String Level) throws Exception {
        String s = HextoByte.stringToHexString(Position);
        String s1 = HextoByte.stringToHexString(Level);
        List<String> commands = Arrays.asList(CommandEnum.COMMAND_A.getInstruction() + s + COMMAND_SUFFIX, CommandEnum.COMMAND_B.getInstruction() + s1 + COMMAND_SUFFIX, CommandEnum.COMMAAND_D.getInstruction() + COMMAND_SUFFIX);
        for (String command : commands) {
            commonOperation(command);
        }
    }

    @Override
    public void setXFerPlate(String Position, String Level) throws Exception {
        String s = HextoByte.stringToHexString(Position);
        String s1 = HextoByte.stringToHexString(Level);
        List<String> commands = Arrays.asList(CommandEnum.COMMAND_A + s + COMMAND_SUFFIX, CommandEnum.COMMAND_B + s1 + COMMAND_SUFFIX, CommandEnum.COMMAND_E + COMMAND_SUFFIX);
        for (String command : commands) {
            commonOperation(command);
        }
    }

    @Override
    public void getXFerPlate(String Position, String Level) throws Exception {
        String s = HextoByte.stringToHexString(Position);
        String s1 = HextoByte.stringToHexString(Level);
        List<String> commands = Arrays.asList(CommandEnum.COMMAND_A + s + COMMAND_SUFFIX, CommandEnum.COMMAND_B + s1 + COMMAND_SUFFIX, CommandEnum.COMMAND_F + COMMAND_SUFFIX);
        for (String command : commands) {
            commonOperation(command);
        }
    }

    @Override
    public void SwapPlate(String Position_1, String Level_1, String Position_2, String Level_2) throws Exception {
        String s = HextoByte.stringToHexString(Position_1);
        String s1 = HextoByte.stringToHexString(Level_1);
        List<String> commandsPre = Arrays.asList(CommandEnum.COMMAND_A + s + COMMAND_SUFFIX, CommandEnum.COMMAND_B + s1 + COMMAND_SUFFIX, CommandEnum.COMMAND_G + COMMAND_SUFFIX);
        for (String command : commandsPre) {
            commonOperation(command);
        }
        whileStatus();

        String s2 = HextoByte.stringToHexString(Position_2);
        String s3 = HextoByte.stringToHexString(Level_2);
        List<String> commandsSuf = Arrays.asList(CommandEnum.COMMAND_A+s2+COMMAND_SUFFIX, CommandEnum.COMMAND_B+s3+COMMAND_SUFFIX, CommandEnum.COMMAND_H + COMMAND_SUFFIX);
        for (String command : commandsSuf) {
            commonOperation(command);
        }
    }

    @Override
    public void AutoShaker() throws Exception {
        Map<String, String> sample_status = TxtReadline.sample_status;
        for (String c:
        StoreConst.Const) {
            if (sample_status.get(c).equals("1")){
                ShakerTrue();
                break;
            }
        }
    }


    @Override
    public void ShakerTrue() throws Exception {
        commonOperation(CommandEnum.ACTIVATE_SHAKER);
        int i=0;
        while (true){
            String status = commonOperation(CommandEnum.READ_SHAKER_STATUS);
            if (status.contains("1")){
                log.info("启动成功");
                break;
            }if (i==30){
                throw new Exception("震动开启超时");
            }
            else {
                log.info("正在验证是否启动震动");
                Thread.sleep(1000);
                i++;
            }
        }
    }
}