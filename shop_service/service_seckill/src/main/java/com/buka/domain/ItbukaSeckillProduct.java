package com.buka.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
 * 秒杀商品表
 * @TableName itbuka_seckill_product
 */
@TableName(value ="itbuka_seckill_product")
@Data
public class ItbukaSeckillProduct {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 秒杀id
     */
    private Long seckillId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 商品价格
     */
    private Integer productMoney;

    /**
     * 库存
     */
    private Integer num;

    /**
     * 活动价格
     */
    private Integer seckillMoney;

    /**
     * 商家名称
     */
    private String merchantName;

    /**
     * 活动场次
     */
    private String activityTimes;

    /**
     * 删除标识
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

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
        ItbukaSeckillProduct other = (ItbukaSeckillProduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSeckillId() == null ? other.getSeckillId() == null : this.getSeckillId().equals(other.getSeckillId()))
            && (this.getProductName() == null ? other.getProductName() == null : this.getProductName().equals(other.getProductName()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getProductMoney() == null ? other.getProductMoney() == null : this.getProductMoney().equals(other.getProductMoney()))
            && (this.getNum() == null ? other.getNum() == null : this.getNum().equals(other.getNum()))
            && (this.getSeckillMoney() == null ? other.getSeckillMoney() == null : this.getSeckillMoney().equals(other.getSeckillMoney()))
            && (this.getMerchantName() == null ? other.getMerchantName() == null : this.getMerchantName().equals(other.getMerchantName()))
            && (this.getActivityTimes() == null ? other.getActivityTimes() == null : this.getActivityTimes().equals(other.getActivityTimes()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSeckillId() == null) ? 0 : getSeckillId().hashCode());
        result = prime * result + ((getProductName() == null) ? 0 : getProductName().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProductMoney() == null) ? 0 : getProductMoney().hashCode());
        result = prime * result + ((getNum() == null) ? 0 : getNum().hashCode());
        result = prime * result + ((getSeckillMoney() == null) ? 0 : getSeckillMoney().hashCode());
        result = prime * result + ((getMerchantName() == null) ? 0 : getMerchantName().hashCode());
        result = prime * result + ((getActivityTimes() == null) ? 0 : getActivityTimes().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", seckillId=").append(seckillId);
        sb.append(", productName=").append(productName);
        sb.append(", productId=").append(productId);
        sb.append(", productMoney=").append(productMoney);
        sb.append(", num=").append(num);
        sb.append(", seckillMoney=").append(seckillMoney);
        sb.append(", merchantName=").append(merchantName);
        sb.append(", activityTimes=").append(activityTimes);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}