package cn.alauncher.dbcdmo.entity;

import java.util.regex.Pattern;

/**
 * Created by GAVER on 2017/5/4.
 */
public class CanDetailExcel {
    String id;
    String result="OK";
    String channelName;
    String no;
    String signalName;
    String signalDescription;
    String transMitterNode;
    String messageName; // frame_name
    String canId;
    String signalValueTable;
    String messageType="P";
    String period;
    String dlc;
    String msb, lsb; // lsb=start_bit
    String size; // size=length
    String byteOrder;
    String dataType;
    String defaultInitialisedValue;
    String alternativeValue;
    String factor;
    String offset;
    String pMinimum, pMaximum;
    /**
     * 阿里插件反射读取值需要驼峰命名法
     */
    String minImum, maxImum;
    String unit;
    String receiver;
    String code;
    String paramName;

    public CanDetailExcel() {
    }

    public CanDetailExcel(String id, String signalName, String signalDescription, String canId, String code, String paramName, String unit, String transMitterNode, String channelName) {
        this.id = id;
        this.channelName = channelName;
        this.signalName = signalName;
        this.signalDescription = signalDescription;
        this.transMitterNode = transMitterNode;
        this.canId = canId;
        this.code = code;
        this.paramName = paramName;
        this.unit = unit;
    }

    public CanDetailExcel(String id, String channelName, String no, String signalName, String signalDescription, String transMitterNode, String messageName, String canId, String signalValueTable, String messageType, String period, String dlc, String msb, String lsb, String size, String byteOrder, String dataType, String defaultInitialisedValue, String alternativeValue, String factor, String offset, String pMinimum, String pMaximum, String unit, String receiver, String code, String paramName) {
        this.id = id;
        this.channelName = channelName;
        this.no = no;
        this.signalName = signalName;
        this.signalDescription = signalDescription;
        this.transMitterNode = transMitterNode;
        this.messageName = messageName;
        this.canId = canId;
        this.signalValueTable = signalValueTable;
        this.messageType = messageType;
        this.period = period;
        this.dlc = dlc;
        this.msb = msb;
        this.lsb = lsb;
        this.size = size;
        this.byteOrder = byteOrder;
        this.dataType = dataType;
        this.defaultInitialisedValue = defaultInitialisedValue;
        this.alternativeValue = alternativeValue;
        this.factor = factor;
        this.offset = offset;
        this.pMinimum = pMinimum;
        this.pMaximum = pMaximum;
        this.unit = unit;
        this.receiver = receiver;
        this.code = code;
        this.paramName = paramName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSignalName() {
        return signalName;
    }

    public void setSignalName(String signalName) {
        this.signalName = signalName;
    }

    public String getSignalDescription() {
        return signalDescription;
    }

    public void setSignalDescription(String signalDescription) {
        this.signalDescription = signalDescription;
    }

    public String getTransMitterNode() {
        return transMitterNode;
    }

    public void setTransMitterNode(String transMitterNode) {
        this.transMitterNode = transMitterNode;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getCanId() {
        return canId;
    }

    public void setCanId(String canId) {
        this.canId = canId;
    }

    public String getSignalValueTable() {
        return signalValueTable;
    }

    public void setSignalValueTable(String signalValueTable) {
        this.signalValueTable = signalValueTable;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getDlc() {
        return dlc;
    }

    public void setDlc(String dlc) {
        this.dlc = dlc;
    }

    public String getMsb() {
        return msb;
    }

    public void setMsb(String msb) {
        this.msb = msb;
    }

    public String getLsb() {
        return lsb;
    }

    public void setLsb(String lsb) {
        this.lsb = lsb;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getByteOrder() {
        return byteOrder;
    }

    public void setByteOrder(String byteOrder) {
        this.byteOrder = byteOrder;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getDefaultInitialisedValue() {
        return defaultInitialisedValue;
    }

    public void setDefaultInitialisedValue(String defaultInitialisedValue) {
        this.defaultInitialisedValue = defaultInitialisedValue;
    }

    public String getAlternativeValue() {
        return alternativeValue;
    }

    public void setAlternativeValue(String alternativeValue) {
        this.alternativeValue = alternativeValue;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getpMinimum() {
        return pMinimum;
    }

    public void setpMinimum(String pMinimum) {
        this.pMinimum = pMinimum;
    }

    public String getpMaximum() {
        return pMaximum;
    }

    public void setpMaximum(String pMaximum) {
        this.pMaximum = pMaximum;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Override
    public String toString() {
        return "CanDetailExcel{" +
                "id='" + id + '\'' +
                ", channelName='" + channelName + '\'' +
                ", no='" + no + '\'' +
                ", signalName='" + signalName + '\'' +
                ", signalDescription='" + signalDescription + '\'' +
                ", transMitterNode='" + transMitterNode + '\'' +
                ", messageName='" + messageName + '\'' +
                ", canId='" + canId + '\'' +
                ", signalValueTable='" + signalValueTable + '\'' +
                ", messageType='" + messageType + '\'' +
                ", period='" + period + '\'' +
                ", dlc='" + dlc + '\'' +
                ", msb='" + msb + '\'' +
                ", lsb='" + lsb + '\'' +
                ", size='" + size + '\'' +
                ", byteOrder='" + byteOrder + '\'' +
                ", dataType='" + dataType + '\'' +
                ", defaultInitialisedValue='" + defaultInitialisedValue + '\'' +
                ", alternativeValue='" + alternativeValue + '\'' +
                ", factor='" + factor + '\'' +
                ", offset='" + offset + '\'' +
                ", pMinimum='" + pMinimum + '\'' +
                ", pMaximum='" + pMaximum + '\'' +
                ", unit='" + unit + '\'' +
                ", receiver='" + receiver + '\'' +
                ", code='" + code + '\'' +
                ", paramName='" + paramName + '\'' +
                '}';
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMinImum() {
        return minImum;
    }

    public void setMinImum(String minImum) {
        this.minImum = minImum;
    }

    public String getMaxImum() {
        return maxImum;
    }

    public void setMaxImum(String maxImum) {
        this.maxImum = maxImum;
    }
}
