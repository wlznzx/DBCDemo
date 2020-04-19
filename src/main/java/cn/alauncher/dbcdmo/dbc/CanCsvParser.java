package cn.alauncher.dbcdmo.dbc;

import cn.alauncher.dbcdmo.entity.CanDetailExcel;
import cn.alauncher.dbcdmo.entity.StringConstants;
import com.google.common.collect.Lists;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CanCsvParser {

    public static void main(String[] args) {
        String path = "D:/IDEAWORKSPACES/vehicle-dbc/src/dbc/Pegas_CANMap_V02.0_20170607.dbc";
        new CanCsvParser().convertData(new File(path));
    }


    public List<CanDetailExcel> convertData(File file) {
        Map<String, List<String[]>> dataMapListStrList = new ReadDbc().read(file);
        List<CanDetailExcel> canDetailExcelList = new ArrayList<>(dataMapListStrList.size());
        if (dataMapListStrList.isEmpty()) {
            return canDetailExcelList;
        }
        List<String[]> transMiterNodeLists = Optional.ofNullable(dataMapListStrList.get(StringConstants.BO)).orElse(new ArrayList<>());
        Map<String, String[]> transMiterNodeListMap = new HashMap<>(transMiterNodeLists.size());
        for (String[] strings : transMiterNodeLists) {
            transMiterNodeListMap.put(strings[1], strings);
        }
        List<String[]> signalList = Optional.ofNullable(dataMapListStrList.get(StringConstants.SG)).orElse(new ArrayList<>());
        Map<String, CanDetailExcel> canDetailExcelMap = new HashMap<>(signalList.size());
        int i = 0;
        for (String[] strings : signalList) {
            CanDetailExcel canDetailExcel = new CanDetailExcel();
            canDetailExcel.setSignalName(strings[2]);
            canDetailExcel.setLsb(strings[5]);
            canDetailExcel.setMsb(strings[18]);
            canDetailExcel.setSize(strings[6]);
            canDetailExcel.setByteOrder(strings[7]);
            canDetailExcel.setDataType(strings[8]);
            canDetailExcel.setFactor(strings[10]);
            canDetailExcel.setOffset(strings[11]);
            canDetailExcel.setpMinimum(strings[14]);
            canDetailExcel.setpMaximum(strings[15]);
            canDetailExcel.setReceiver(strings[17]);
            canDetailExcel.setUnit(strings[16]);
            canDetailExcel.setMinImum(canDetailExcel.getpMinimum());
            canDetailExcel.setMaxImum(canDetailExcel.getpMaximum());
            canDetailExcel.setId(String.valueOf(++i));
            String[] transMiterNodeList = transMiterNodeListMap.get(strings[0]);
            canDetailExcel.setTransMitterNode(transMiterNodeList[5]);
            canDetailExcel.setMessageName(transMiterNodeList[2]);
            canDetailExcel.setDlc(transMiterNodeList[4]);
            canDetailExcel.setCanId(Integer.toHexString(Integer.parseInt(transMiterNodeList[1])));
            canDetailExcelMap.put(strings[0] + strings[2], canDetailExcel);
        }
        List<String[]> cmList = Optional.ofNullable(dataMapListStrList.get(StringConstants.CM)).orElse(new ArrayList<>());
        for (String[] strings : cmList) {
            CanDetailExcel canDetailExcel = canDetailExcelMap.get(strings[2] + strings[3]);
            if (canDetailExcel != null) {
                canDetailExcel.setSignalDescription(strings[4]);
            }
        }
        List<String[]> baList = Optional.ofNullable(dataMapListStrList.get(StringConstants.BA)).orElse(new ArrayList<>());
        for (String[] strings : baList) {
            CanDetailExcel canDetailExcel = canDetailExcelMap.get(strings[3] + strings[4]);
            if (canDetailExcel != null) {
                canDetailExcel.setPeriod(strings[5]);
            }
        }
        List<String[]> badList = Optional.ofNullable(dataMapListStrList.get(StringConstants.BAD)).orElse(new ArrayList<>());
        for (String[] strings : badList) {
//            System.out.println(strings);
        }
        List<String[]> baddList = Optional.ofNullable(dataMapListStrList.get(StringConstants.BADD)).orElse(new ArrayList<>());
        for (String[] strings : baddList) {
//            System.out.println(strings);
        }
        List<String[]> valList = Optional.ofNullable(dataMapListStrList.get(StringConstants.VAL)).orElse(new ArrayList<>());
        for (String[] strings : valList) {
            CanDetailExcel canDetailExcel = canDetailExcelMap.get(strings[1] + strings[2]);
            if (canDetailExcel != null) {
                canDetailExcel.setSignalValueTable(strings[3]);
            }
        }
        return Lists.newArrayList(Optional.ofNullable(canDetailExcelMap.values()).orElse(new HashSet<>()));
    }
}