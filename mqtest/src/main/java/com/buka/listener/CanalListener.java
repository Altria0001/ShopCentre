package com.buka.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;

@CanalEventListener
public class CanalListener {

    @ListenPoint(schema = "itbuka_goods",table = "goods_product")
    public void listenProduct(CanalEntry.RowData data, CanalEntry.EventType eventType){
        System.out.println(data);
        System.out.println(eventType);
    }


}
