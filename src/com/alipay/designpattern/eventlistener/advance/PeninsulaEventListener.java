/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2012 All Rights Reserved.
 */
package com.alipay.designpattern.eventlistener.advance;

import java.util.EventListener;

/**
 * �뵺���Ƶļ�������
 * 
 * @author hongxin.xu
 * @version $Id: PeninsulaEventListener.java, v 0.1 2012-1-4 ����9:41:54 hongxin.xu Exp $
 */
public interface PeninsulaEventListener<T> extends EventListener {

    /**
     * ����뵺���ݱ仯��Handler��
     * 
     * @param eventData
     *        �뵺�¼�������
     */
    public void handler(EventData<T> eventData);

}
