package cn.alauncher.dbcdmo.entity;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Description：
 * @Author： leo.xiong
 * @CreateDate： 2019/01/09 13:58
 * @Email： leo.xiong@suyun360.com
 * @Version：
 */
public interface StringConstants {
    String DEFAULT_DOT = ".";
    String DEFAULT_SYMBOL = ",";
    String DEFAULT_EQUAL_SIGN = "=";
    String VERTICAL_LINE = "|";
    String STRIPING_LINE = "_";
    String DEFAULT_COLON = ":";
    String DEFAULT_EMPTY = "";
    String EMPTY_BLANK = " ";
    String DEFAULT_A = "@";
    String DEFAULT_SEMICOLON = ";";
    String DEFAULT_LEFT_BRACKET = "[";
    String DEFAULT_RIGHT_BRACKET = "]";
    String DEFAULT_PLUS = "+";
    String DEFAULT_MINUX = "-";
    String DEFAULT_LEFT_PARENTHESES = "(";
    String DEFAULT_RIGHT_PARENTHESES = ")";
    String DEFAULT_QUOTATION = "\"";
    /**
     * 当使用.切割字符串时，需要转移
     */
    String SPLIT_DOT = "\\.";

    String DEFAULT_ZERO = "0";

    /**
     * 短横线
     */
    String SPILTSTR_TRANSVERSE_LINE = "-";
    /**
     * 是/否
     */
    String YES = "YES";
    String NO = "NO";

    /**
     * 解析DBC文件字段
     */
    String BO = "BO_ ";
    String SG = " SG_ ";
    String CM = "CM_ SG_ ";
    String BA = "BA_ ";
    String BAD = "BA_DEF_ ";
    String BADD = "BA_DEF_DEF_  ";
    String VAL = "VAL_ ";

    List<String> REPLACE_STR = Lists.newArrayList(DEFAULT_SYMBOL, DEFAULT_COLON, VERTICAL_LINE, DEFAULT_A, DEFAULT_LEFT_BRACKET, DEFAULT_RIGHT_BRACKET, DEFAULT_SEMICOLON, DEFAULT_PLUS,DEFAULT_MINUX, DEFAULT_LEFT_PARENTHESES, DEFAULT_RIGHT_PARENTHESES, DEFAULT_QUOTATION);

    String TYPE_STRING = "STRING";
    String TYPE_INT = "INT";
    String TYPE_HEX = "HEX";
    String TYPE_ENUM = "ENUM";
}
