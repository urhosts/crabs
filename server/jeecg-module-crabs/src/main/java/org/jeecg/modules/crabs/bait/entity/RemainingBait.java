package org.jeecg.modules.crabs.bait.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 残饵计数识别
 * @Author: jeecg-boot
 * @Date:   2023-09-03
 * @Version: V1.0
 */
@Data
@TableName("remaining_bait")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="remaining_bait对象", description="残饵计数识别")
public class RemainingBait implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**原始图片*/
	@Excel(name = "原始图片", width = 15)
    @ApiModelProperty(value = "原始图片")
    private String baitPic;
	/**残饵计数*/
	@Excel(name = "残饵计数", width = 15)
    @ApiModelProperty(value = "残饵计数")
    private Integer baitCount;
	/**识别结果*/
	@Excel(name = "识别结果", width = 15)
    @ApiModelProperty(value = "识别结果")
    private String recognitionPic;
    /**投喂量*/
    @Excel(name = "投喂量", width = 15)
    @ApiModelProperty(value = "投喂量")
    private java.lang.Integer baitInput;

    /**螃蟹只数*/
    @Excel(name = "螃蟹只数", width = 15)
    @ApiModelProperty(value = "螃蟹只数")
    private java.lang.Integer crabsCount;

    /**溶解氧*/
    @Excel(name = "溶解氧", width = 15)
    @ApiModelProperty(value = "溶解氧")
    private java.lang.Double dissolvedOxygen;
}
