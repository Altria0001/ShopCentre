package com.buka.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 商品表
 * @TableName goods_product
 */
@TableName(value ="goods_product")
@Data
public class GoodsProduct implements Serializable {
    /**
     * 商品编号
     */
    @TableId
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 销售模式
     */
    private String salesModel;

    /**
     * 商品类型
     */
    private String type;

    /**
     * 上下架状态(0上架1下架 )
     */
    private Integer status;

    /**
     * 审核状态(0未审核 1通过 2拒绝)
     */
    private Integer audit;

    /**
     * 店铺名称
     */
    private String shopName;

    /**
     * 商品分类id
     */
    private Long classifyId;

    /**
     * 商品卖点
     */
    private String selling;

    /**
     * 商品参数
     */
    private String parameter;

    /**
     * 计量单位
     */
    private String unitMeasure;

    /**
     * 商品图片
     */
    private String img;

    /**
     * 商品视频
     */
    private String video;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 删除标识(0未删 1已删)
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 修改时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 品牌id
     */
    private Long brandId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        GoodsProduct other = (GoodsProduct) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPrice() == null ? other.getPrice() == null : this.getPrice().equals(other.getPrice()))
            && (this.getSalesModel() == null ? other.getSalesModel() == null : this.getSalesModel().equals(other.getSalesModel()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAudit() == null ? other.getAudit() == null : this.getAudit().equals(other.getAudit()))
            && (this.getShopName() == null ? other.getShopName() == null : this.getShopName().equals(other.getShopName()))
            && (this.getClassifyId() == null ? other.getClassifyId() == null : this.getClassifyId().equals(other.getClassifyId()))
            && (this.getSelling() == null ? other.getSelling() == null : this.getSelling().equals(other.getSelling()))
            && (this.getParameter() == null ? other.getParameter() == null : this.getParameter().equals(other.getParameter()))
            && (this.getUnitMeasure() == null ? other.getUnitMeasure() == null : this.getUnitMeasure().equals(other.getUnitMeasure()))
            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
            && (this.getVideo() == null ? other.getVideo() == null : this.getVideo().equals(other.getVideo()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getIsDelete() == null ? other.getIsDelete() == null : this.getIsDelete().equals(other.getIsDelete()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getBrandId() == null ? other.getBrandId() == null : this.getBrandId().equals(other.getBrandId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPrice() == null) ? 0 : getPrice().hashCode());
        result = prime * result + ((getSalesModel() == null) ? 0 : getSalesModel().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAudit() == null) ? 0 : getAudit().hashCode());
        result = prime * result + ((getShopName() == null) ? 0 : getShopName().hashCode());
        result = prime * result + ((getClassifyId() == null) ? 0 : getClassifyId().hashCode());
        result = prime * result + ((getSelling() == null) ? 0 : getSelling().hashCode());
        result = prime * result + ((getParameter() == null) ? 0 : getParameter().hashCode());
        result = prime * result + ((getUnitMeasure() == null) ? 0 : getUnitMeasure().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        result = prime * result + ((getVideo() == null) ? 0 : getVideo().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getIsDelete() == null) ? 0 : getIsDelete().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getBrandId() == null) ? 0 : getBrandId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", salesModel=").append(salesModel);
        sb.append(", type=").append(type);
        sb.append(", status=").append(status);
        sb.append(", audit=").append(audit);
        sb.append(", shopName=").append(shopName);
        sb.append(", classifyId=").append(classifyId);
        sb.append(", selling=").append(selling);
        sb.append(", parameter=").append(parameter);
        sb.append(", unitMeasure=").append(unitMeasure);
        sb.append(", img=").append(img);
        sb.append(", video=").append(video);
        sb.append(", description=").append(description);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", brandId=").append(brandId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}