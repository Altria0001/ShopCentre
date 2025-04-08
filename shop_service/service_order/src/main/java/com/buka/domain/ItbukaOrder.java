package com.buka.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName itbuka_order
 */
@TableName(value ="itbuka_order")
@Data
public class ItbukaOrder {
    /**
     * 订单号
     */
    @TableId
    private Long id;

    /**
     * 订单来源（0 移动端 1 pc端 2 小程序）
     */
    private Integer source;

    /**
     * 订单类型（0 普通订单 1 虚拟订单 2秒杀订单）
     */
    private Integer type;

    /**
     * 买家名称
     */
    private String buyerName;

    /**
     * 	订单金额
     */
    private BigDecimal money;

    /**
     * 订单状态（0 未付款 1 已付款 2 待发货 3 已发货 4 待核验 5待自提 6 已完成 7 已关闭）
     */
    private Integer status;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 下单时间
     */
    private Date createTime;

    /**
     * 删除标识（0 未删除 1已删除）
     */
    private Integer isDelete;

    /**
     * 收货地址id
     */
    private Long addressId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 支付宝交易凭证号
     */
    private String tradeNo;

    /**
     * 配送方式
     */
    private String shippingMethod;

    /**
     * 物流单号
     */
    private String trackingNumber;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ItbukaOrder other = (ItbukaOrder) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getBuyerName() == null ? other.getBuyerName() == null : this.getBuyerName().equals(other.getBuyerName()))
            && (this.getMoney() == null ? other.getMoney() == null : this.getMoney().equals(other.getMoney()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getAddressId() == null ? other.getAddressId() == null : this.getAddressId().equals(other.getAddressId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getTradeNo() == null ? other.getTradeNo() == null : this.getTradeNo().equals(other.getTradeNo()))
            && (this.getShippingMethod() == null ? other.getShippingMethod() == null : this.getShippingMethod().equals(other.getShippingMethod()))
            && (this.getTrackingNumber() == null ? other.getTrackingNumber() == null : this.getTrackingNumber().equals(other.getTrackingNumber()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getBuyerName() == null) ? 0 : getBuyerName().hashCode());
        result = prime * result + ((getMoney() == null) ? 0 : getMoney().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getAddressId() == null) ? 0 : getAddressId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getTradeNo() == null) ? 0 : getTradeNo().hashCode());
        result = prime * result + ((getShippingMethod() == null) ? 0 : getShippingMethod().hashCode());
        result = prime * result + ((getTrackingNumber() == null) ? 0 : getTrackingNumber().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", source=").append(source);
        sb.append(", type=").append(type);
        sb.append(", buyerName=").append(buyerName);
        sb.append(", money=").append(money);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", addressId=").append(addressId);
        sb.append(", productId=").append(productId);
        sb.append(", num=").append(num);
        sb.append(", tradeNo=").append(tradeNo);
        sb.append(", shippingMethod=").append(shippingMethod);
        sb.append(", trackingNumber=").append(trackingNumber);
        sb.append("]");
        return sb.toString();
    }
}