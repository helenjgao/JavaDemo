/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.alipay.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * ���۲��ߵ�ʵ����
 * <p>
 * ΰ��ı�������Ȩ�������������ע�Ľ��㡣 ��ˣ������ｫ����Ϊһ�����۲��ߵ�ʵ����
 * </p>
 * 
 * @author hongxin.xu
 * @version $Id: ChinaObserver.java, v 0.1 2012-1-2 ����3:14:53 hongxin.xu Exp $
 */
public class NorthKoreaSubject extends Observable {

    /**
     * @see java.util.Observable#addObserver(java.util.Observer)
     */
    @Override
    public synchronized void addObserver(Observer o) {
        super.addObserver(o);
        super.setChanged();
    }

    /**
     * @see java.util.Observable#notifyObservers(java.lang.Object)
     */
    @Override
    public void notifyObservers(Object arg) {
        super.setChanged();
        super.notifyObservers(arg);
    }

    public static void main(String[] args) {
        // ���������۲��ߡ�
        Observer china = new ChinaObserver();
        Observer american = new AmericanObserver();

        // ��ӹ۲���
        Observable northKoreaSubject = new NorthKoreaSubject();
        northKoreaSubject.addObserver(china);
        northKoreaSubject.addObserver(american);

        // �����¼���
        System.out.println("Kim-Jong-II:�����к�������˼�ܴ");
        northKoreaSubject.notifyObservers(PeninsulaEnum.NUCLEAR);

        // �����¼���
        System.out.println("Kim-Jong-II:�ҹ���~~~");
        northKoreaSubject.notifyObservers(PeninsulaEnum.KIMJIONIIDEAD);

    }

}
