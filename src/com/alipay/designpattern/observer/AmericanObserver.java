/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.alipay.designpattern.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * ����˰�����������Ҳʱ�̹�ע�ı����ʡ�
 * 
 * @author hongxin.xu
 * @version $Id: AmericanObserver.java, v 0.1 2012-1-2 ����3:24:05 hongxin.xu Exp $
 */
public class AmericanObserver implements Observer {

    /**
     * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
     */
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof PeninsulaEnum) {
            PeninsulaEnum msg = (PeninsulaEnum) arg;
            if (msg == PeninsulaEnum.KIMJIONIIDEAD) {
                System.out.println("  --�׹������ص�");
            }
            if (msg == PeninsulaEnum.NUCLEAR) {
                System.out.println("  --�׹������������������");
            }
        }
    }

}
