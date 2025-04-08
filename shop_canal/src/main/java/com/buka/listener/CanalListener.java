package com.buka.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.buka.entity.MqConstant;
import com.xpand.starter.canal.annotation.CanalEventListener;
import com.xpand.starter.canal.annotation.ListenPoint;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@CanalEventListener
public class CanalListener {
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@ListenPoint(schema = "itbuka_goods",table = "goods_product")
	public void listenProduct(CanalEntry.RowData rowData,CanalEntry.EventType eventType){
		if(eventType==CanalEntry.EventType.INSERT){
			List<CanalEntry.Column> afterColumn=rowData.getAfterColumnsList();
			for (CanalEntry.Column column:afterColumn){
				if("id".equals(column.getName())){
					String value=column.getValue();
					rabbitTemplate.convertAndSend(MqConstant.PRODDUCT_EXCHANGE,MqConstant.PRODDUCT_ADD_ROUKEY,value);

				}

			}
		} else if (eventType==CanalEntry.EventType.UPDATE)
		{
			List<CanalEntry.Column> afterColumn= rowData.getAfterColumnsList();
			List<CanalEntry.Column> beforeCoulumn=rowData.getBeforeColumnsList();
			String valuebefore=null;
			for(CanalEntry.Column column:beforeCoulumn){
				if("id".equals(column.getName())) {
					 valuebefore = column.getValue();
				}
			}
			for (CanalEntry.Column column: afterColumn){
				if ("id".equals(column.getName())){
					String value = column.getValue()+","+valuebefore;
					rabbitTemplate.convertAndSend(MqConstant.PRODDUCT_EXCHANGE,MqConstant.PRODDUCT_UPDATE_ROUKEY,value);
				}
			}
		}
	}
}

