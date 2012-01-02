/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2011 All Rights Reserved.
 */
package com.alipay.demo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * cardbinϵͳ�����ݳ�ʼ�����롣
 * 
 * @author hongxin.xu
 * @version $Id: CardbinDataInit.java, v 0.1 2011-8-31 ����10:37:16 hongxin.xu Exp $
 */
public class CardbinDataInit {

    /** Ŀ��·��(�������ɵ����ݳ�ʼ���ű�)�� */
    private final static String DEST_PATH = "e://convert//poscore.csv";

    /** ����cardbinϵͳ�����ݳ�ʼ���ű��� */
    private final static String SRC_PATH  = "e://convert//poscore.sql";

    /** ����Ҫ��ȡ���б�ǡ� */
    private final static String FLAG      = "ERROR_LINE";

    /** ���б�ǡ� */
    private final static String EMPTY     = "";

    /** ���š� */
    private final static String COMM      = ",";

    /** ��Ҫ�����sql��䡣 */
    private final static String INSERT    = "insert into sharedata.card_bin(card_bin_id, card_bin_value, card_len, card_type, card_alias, inst_id, domain, version, gmt_create, gmt_modified, operator, memo) values";

    /** ������ �� */
    private final static String LEFT      = "(";

    /** �����š� */
    private final static String RIGHT     = ")";

    /** ת��������š� */
    private final static String SQ        = "\'";

    /** ת���˫���š� */
    private final static String DQ        = "\"";

    /** ���С� */
    private final static String ENTER     = "\n";

    /** ����Ϣ�� */
    private final static String DOMAIN    = "FINANCE";

    /** ϵͳʱ�䡣 */
    private final static String TIME      = "SYSDATE";

    /** ����Ա�� */
    private final static String OPERATOR  = "admin";

    /** �ֺš� */
    private final static String SEMI      = ";";

    /** �汾��Ϣ�� */
    private final static String VERSION   = "1";

    /** �ո���Ϣ�� */
    private final static String BLANK     = " ";

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            int startId = 2966;
            List<String> list = new ArrayList<String>();
            List<String> strList = readLine();
            System.out.println(strList.size());
            for (String str : strList) {
                List<String> splitResult = parseRecord(str, COMM);
                String result = constructor(splitResult, startId++);
                list.add(result);
            }
            writeFile(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * ��ȡ�����е����ݡ�
     * 
     * @return ���ؼ�¼��Ϣ���ϡ�
     * @throws Exception
     */
    public static List<String> readLine() throws Exception {
        // ���淵�ؽ����
        List<String> record = new ArrayList<String>();

        // ��ȡԴ�ļ�����
        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(DEST_PATH));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        // ����������Լ���ʱ������
        String line = null;
        int totalCount = 0;

        // ��ȡ���ݡ�
        while ((line = bufferedReader.readLine()) != null) {
            // ���������е��롣
            // if (totalCount++ % 2 == 0) {
            // record.add(line);
            // } else {
            // continue;
            // }

            // poscore����
            record.add(line);
        }

        bufferedReader.close();

        // ���ؽ����
        return record;
    }

    /**
     * ����������ַ�����
     * <p>
     * ���صĽ��˳��
     * <ul>
     * <li>��BINֵ(0)��
     * <li>���˺�(1)��
     * <li>������(2)��
     * <li>������(3)��
     * <li>������(4)��
     * <li>��������(5)��
     * </ul>
     * </p>
     * 
     * @param record
     *        �ַ�����
     * @param split
     *        �ָ�����
     * @return �ָ�֮��Ľ����
     */
    public static List<String> parseRecord(String record, String split) {
        List<String> cardbinList = new ArrayList<String>();
        String[] splitResult = record.split(COMM);
        for (String str : splitResult) {
            cardbinList.add(str);
        }
        return cardbinList;
    }

    /**
     * ����ָ���Ĳ�������sql��䡣
     * 
     * @param params
     */
    public static String constructor(List<String> params, int startid) {
        StringBuilder builder = new StringBuilder();
        builder.append(INSERT);
        builder.append(LEFT);
        builder.append(Integer.toString(startid));
        builder.append(COMM);
        builder.append(BLANK + SQ + params.get(0) + SQ);
        builder.append(COMM);
        builder.append(BLANK + SQ + params.get(2) + SQ);
        builder.append(COMM);
        builder.append(BLANK + SQ + params.get(4) + SQ);
        builder.append(COMM);
        builder.append(BLANK + SQ + params.get(3) + SQ);
        builder.append(COMM);

        // ԭ����bin�������ֱ��ת�������󣬻���ð�š�
        String[] inst_arr = params.get(5).split(DQ);
        if (inst_arr.length != 0) {
            builder.append(BLANK + SQ + inst_arr[0] + SQ);
        }
        builder.append(COMM);
        builder.append(BLANK + SQ + DOMAIN + SQ);
        builder.append(COMM);
        builder.append(BLANK + VERSION);
        builder.append(COMM);
        builder.append(BLANK + TIME);
        builder.append(COMM);
        builder.append(BLANK + TIME);
        builder.append(COMM);
        builder.append(BLANK + SQ + OPERATOR + SQ);
        builder.append(COMM);
        builder.append(BLANK + SQ + EMPTY + SQ);
        builder.append(RIGHT);
        builder.append(SEMI);
        return builder.toString();
    }

    /**
     * д���ļ����ݡ�
     * 
     * @param content
     *        �ļ����ݡ�
     * @throws IOException
     */
    public static void writeFile(List<String> contents) throws IOException {
        FileWriter fileWriter = new FileWriter(SRC_PATH, true);
        for (String str : contents) {
            fileWriter.write(str);
            fileWriter.write(ENTER);
        }
        fileWriter.close();
    }
}
