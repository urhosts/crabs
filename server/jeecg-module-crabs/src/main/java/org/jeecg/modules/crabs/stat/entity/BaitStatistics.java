package org.jeecg.modules.crabs.stat.entity;

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
 * @Description: 摄食量可视化
 * @Author: jeecg-boot
 * @Date:   2023-09-03
 * @Version: V1.0
 */
@Data
@TableName("bait_statistics")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bait_statistics对象", description="摄食量可视化")
public class BaitStatistics implements Serializable {
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
	/**当日投喂量*/
	@Excel(name = "当日投喂量", width = 15)
    @ApiModelProperty(value = "当日投喂量")
    private Integer feeding;
	/**残饵量*/
	@Excel(name = "残饵量", width = 15)
    @ApiModelProperty(value = "残饵量")
    private Integer remaining;
	/**柱状图*/
	@Excel(name = "柱状图", width = 15)
    @ApiModelProperty(value = "柱状图")
    private String barChart;
}
