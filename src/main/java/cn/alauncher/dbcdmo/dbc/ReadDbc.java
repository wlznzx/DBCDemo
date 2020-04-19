package cn.alauncher.dbcdmo.dbc;

import cn.alauncher.dbcdmo.entity.StringConstants;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author： leo.xiong
 * @CreateDate： 2019/04/15 11:01
 * @Email： leo.xiong@suyun360.com
 * @Version：
 */
public class ReadDbc {
    /**
     * 读取dbc文件数据信息
     *
     * @param dbcFile
     * @return
     */
    public Map<String, List<String[]>> read(File dbcFile) {
        Map<String, List<String[]>> dataMapListStrList = new HashMap<>(16);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(dbcFile));
            String lineStr;
            String id = null;
            while ((lineStr = reader.readLine()) != null) {
                if (StringUtils.isEmpty(lineStr)) {
                    continue;
                }
                lineStr = lineStr.replace(StringConstants.DEFAULT_SEMICOLON, StringConstants.DEFAULT_EMPTY);
                id = buildMapInfo(lineStr, dataMapListStrList, id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataMapListStrList;
    }

    /**
     * 获取有效数据信息
     *
     * @param lineStr
     * @param dataMapListStrList
     */
    private String buildMapInfo(String lineStr, Map<String, List<String[]>> dataMapListStrList, String id) {
        if (lineStr.startsWith(StringConstants.BO)) {
            id = getBO(lineStr, dataMapListStrList);
        } else if (lineStr.startsWith(StringConstants.SG)) {
            getSG(lineStr, dataMapListStrList, id);
        } else if (lineStr.startsWith(StringConstants.CM)) {
            getCM(lineStr, dataMapListStrList, StringConstants.CM);
        } else if (lineStr.startsWith(StringConstants.BA)) {
            getBA(lineStr, dataMapListStrList);
        } else if (lineStr.startsWith(StringConstants.BAD)) {
            getBAD(lineStr, dataMapListStrList);
        } else if (lineStr.startsWith(StringConstants.BADD)) {
            getBADD(lineStr, dataMapListStrList, StringConstants.BADD);
        } else if (lineStr.startsWith(StringConstants.VAL)) {
            getVAL(lineStr, dataMapListStrList);
        }
        return id;
    }

    /**
     * 消息初始默认值 如：BA_DEF_ SG_  "GenSigInactiveValue" INT 0 100000;
     * BA_DEF_起始标识
     *
     * @param lineStr
     */
    private void getBAD(String lineStr, Map<String, List<String[]>> dataMapListStrList) {
        if (dataMapListStrList.get(StringConstants.BAD) == null) {
            dataMapListStrList.put(StringConstants.BAD, new ArrayList<>());
        }
        String splitStr = "";
        if (lineStr.contains(StringConstants.TYPE_STRING)) {
            splitStr = StringConstants.TYPE_STRING;
        } else if (lineStr.contains(StringConstants.TYPE_ENUM)) {
            splitStr = StringConstants.TYPE_ENUM;
        } else if (lineStr.contains(StringConstants.TYPE_HEX)) {
            splitStr = StringConstants.TYPE_HEX;
        } else if (lineStr.contains(StringConstants.TYPE_INT)) {
            splitStr = StringConstants.TYPE_INT;
        } else {
            return;
        }
        String[] dataList = lineStr.split(splitStr);
        String[] dataStrList = dataList[0].trim().split(StringConstants.EMPTY_BLANK);
        if (dataStrList.length != 4) {
            return;
        }
        String[] newDataStrList = new String[dataStrList.length + 2];
        System.arraycopy(dataStrList, 0, newDataStrList, 0, dataStrList.length);
        newDataStrList[dataStrList.length] = splitStr;
        newDataStrList[dataStrList.length + 1] = dataList[dataList.length - 1].trim();
        dataMapListStrList.get(StringConstants.BAD).add(newDataStrList);
    }

    /**
     * 消息发送周期 如：BA_ "GenSigStartValue" SG_ 853 ThmMng_PCURTemp 40;
     * BA_起始标识，描述消息与信号更详尽的信息,单位ms
     * 853 消息ID
     * ThmMng_PCURTemp 信号名称
     * 40 发送周期
     *
     * @param lineStr
     */
    private void getBA(String lineStr, Map<String, List<String[]>> dataMapListStrList) {
        String[] dataStrList;
        if (dataMapListStrList.get(StringConstants.BA) == null) {
            dataMapListStrList.put(StringConstants.BA, new ArrayList<>());
        }
        dataStrList = lineStr.split(StringConstants.EMPTY_BLANK);
        if (dataStrList.length < 5) {
            return;
        }
        if (dataStrList.length == 5) {
            String[] newDataStrList = new String[dataStrList.length + 1];
            System.arraycopy(dataStrList, 0, newDataStrList, 0, dataStrList.length - 1);
            newDataStrList[4] = "";
            newDataStrList[5] = dataStrList[4];
            dataMapListStrList.get(StringConstants.BA).add(newDataStrList);
        } else {
            dataMapListStrList.get(StringConstants.BA).add(dataStrList);
        }
    }

    /**
     * 值枚举或特殊值列举或取值范围描述 如：VAL_ 16 DCF_1_Warning 1 "Off" 0 "Off" ;
     * VAL起始标识
     * 16 消息ID
     * DCF_1_Warning 信号名称
     * 1 "Off" 0 "Off"  数据映射信息
     *
     * @param lineStr
     */
    private void getVAL(String lineStr, Map<String, List<String[]>> dataMapListStrList) {
        String[] dataStrList;
        if (dataMapListStrList.get(StringConstants.VAL) == null) {
            dataMapListStrList.put(StringConstants.VAL, new ArrayList<>());
        }
        String[] dataList = lineStr.trim().split(StringConstants.DEFAULT_QUOTATION);
        dataStrList = dataList[0].split(StringConstants.EMPTY_BLANK);
        String[] newDataStrList = new String[4];
        System.arraycopy(dataStrList, 0, newDataStrList, 0, 3);
        dataList[0] = dataStrList[dataStrList.length - 1];
        Map<String, String> jsonMap = new HashMap<>(16);
        for (int i = 0; i < dataList.length; i += 2) {
            jsonMap.put(dataList[i].trim(), dataList[i + 1].trim());
        }
        if (!jsonMap.isEmpty()) {
            newDataStrList[3] = JSON.toJSONString(jsonMap);
        } else {
            newDataStrList[3] = "{}";
        }
        dataMapListStrList.get(StringConstants.VAL).add(newDataStrList);
    }

    /**
     * 消息与信号详细描述 如：CM_ SG_ 512 BR1_ABS_active "123";
     * CM_起始标识
     * 512 消息ID
     * BR1_ABS_active 信号名称
     * 123 注释信息
     *
     * @param lineStr
     */
    private void getCM(String lineStr, Map<String, List<String[]>> dataMapListStrList, String cm) {
        String[] dataStrList;
        if (dataMapListStrList.get(cm) == null) {
            dataMapListStrList.put(cm, new ArrayList<>());
        }
        String[] dataList = lineStr.trim().split(StringConstants.DEFAULT_QUOTATION);
        dataStrList = dataList[0].trim().split(StringConstants.EMPTY_BLANK);
        String[] newDataStrList = new String[dataStrList.length + 1];
        System.arraycopy(dataStrList, 0, newDataStrList, 0, dataStrList.length);
        newDataStrList[dataStrList.length] = dataList[dataList.length - 1];
        dataMapListStrList.get(cm).add(newDataStrList);
    }

    /**
     * 消息初始默认值 如：BA_DEF_DEF_  "messageSendType" "event";
     * BA_DEF_起始标识
     *
     * @param lineStr
     */
    private void getBADD(String lineStr, Map<String, List<String[]>> dataMapListStrList, String cm) {
        String[] dataStrList;
        if (dataMapListStrList.get(cm) == null) {
            dataMapListStrList.put(cm, new ArrayList<>());
        }
        dataStrList = lineStr.split(StringConstants.EMPTY_BLANK);
        dataMapListStrList.get(cm).add(dataStrList);
    }

    /**
     * 信号信息数据 如：SG_ ThmMng_PCURTemp : 16|8@1+ (1,-40) [-40|210] "degC" TBOX
     * SG_代表一个信号信息的起始标识
     * ThmMng_PCURTemp信号名，分长名与短名，此处是短名。长名非必须存在，可以不定义
     * :分割符号
     * 16信号起始bit
     * |分割符号
     * 8信号总长度
     *
     * @0+ @0表示是Motorola格式（Intel格式是1），+表示是无符号数据
     * (1,-40)（精度值，偏移值）
     * [-40|210][最小值|最大值]， 物理意义的最小与最大
     * "degC""单位"
     * TBOX接收处理此信号的节点，同样可以不指明，写为Vector__XXX
     * 信号计算公式 信号值=（物理值-偏移量）/精度值
     */
    private void getSG(String lineStr, Map<String, List<String[]>> dataMapListStrList, String id) {
        String[] dataList = lineStr.split(StringConstants.DEFAULT_QUOTATION);
        String dataType = "1";
        if (lineStr.contains(StringConstants.DEFAULT_PLUS) || lineStr.contains(StringConstants.DEFAULT_MINUX)) {
            dataType = "0";
        }
        if (dataMapListStrList.get(StringConstants.SG) == null) {
            dataMapListStrList.put(StringConstants.SG, new ArrayList<>());
        }
        String[] dataStrList = replaceStr(dataList[0]).trim().split(StringConstants.EMPTY_BLANK);
        String[] newDataStrList = new String[dataStrList.length + 4];
        System.arraycopy(dataStrList, 0, newDataStrList, 1, dataStrList.length);
        if (Integer.parseInt(newDataStrList[7]) == 0) {
            newDataStrList[5] = String.valueOf(getMsbByLsbAndSize(Integer.valueOf(newDataStrList[5]), Integer.valueOf(newDataStrList[6])));
        }
        newDataStrList[18] = String.valueOf(Integer.parseInt(newDataStrList[5]) + Integer.parseInt(newDataStrList[6]) - 1);
        newDataStrList[0] = id;
        newDataStrList[dataStrList.length + 1] = dataList[1];
        newDataStrList[dataStrList.length + 2] = dataList[2].trim();
        newDataStrList[8] = dataType;
        dataMapListStrList.get(StringConstants.SG).add(newDataStrList);
    }

    /**
     * 报文消息数据 如：BO_ 853 VCU_ThmMngMsg: 8 VCU
     * BO_代表一条消息的起始标识
     * 853 消息ID的十进制形式，=0x355
     * VCU_ThmMngMsg消息名
     * :分割符号
     * 8消息报文长度，帧字节数
     * VCU 发出该消息的网络节点
     *
     * @param lineStr
     * @param dataMapListStrList
     */
    private String getBO(String lineStr, Map<String, List<String[]>> dataMapListStrList) {
        String[] dataStrList;
        String id;
        lineStr = replaceStr(lineStr);
        if (dataMapListStrList.get(StringConstants.BO) == null) {
            dataMapListStrList.put(StringConstants.BO, new ArrayList<>());
        }
        dataStrList = lineStr.split(StringConstants.EMPTY_BLANK);
        id = dataStrList[1];
        dataMapListStrList.get(StringConstants.BO).add(lineStr.split(StringConstants.EMPTY_BLANK));
        return id;
    }

    /**
     * 根据lsb和siz运算开始的msb
     *
     * @param lsb
     * @param size
     * @return
     */
    public Integer getMsbByLsbAndSize(Integer lsb, Integer size) {
        int column = lsb % 8 + 1;
        if (column >= size) {
            return lsb + 1 - size;
        }
        size = size - column;
        if (size > 8) {
            return lsb + (8 - column) + ((size / 8) - 1) * 8 + (8 - ((size - 1) % 8));
        } else {
            return lsb + (8 - column) + (8 - ((size - 1) % 8));
        }
    }

    /**
     * 替换无效的分隔符为空格字符串 " "
     *
     * @param lineStr
     * @return
     */
    private String replaceStr(String lineStr) {
        for (String str : StringConstants.REPLACE_STR) {
            lineStr = lineStr.replace(str, StringConstants.EMPTY_BLANK);
        }
        return lineStr;
    }
}
