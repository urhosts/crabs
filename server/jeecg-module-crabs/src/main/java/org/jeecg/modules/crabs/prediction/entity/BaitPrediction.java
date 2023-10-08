package org.jeecg.modules.crabs.prediction.entity;

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
 * @Description: 投喂量预测
 * @Author: jeecg-boot
 * @Date:   2023-10-08
 * @Version: V1.0
 */
@Data
@TableName("bait_prediction")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="bait_prediction对象", description="投喂量预测")
public class BaitPrediction implements Serializable {
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
	/**溶解氧*/
	@Excel(name = "溶解氧", width = 15)
    @ApiModelProperty(value = "溶解氧")
    private Double dissolvedOxygen;
	/**螃蟹只数*/
	@Excel(name = "螃蟹只数", width = 15)
    @ApiModelProperty(value = "螃蟹只数")
    private Integer crabsCount;
	/**预测明日投喂量(克)*/
	@Excel(name = "预测明日投喂量(克)", width = 15)
    @ApiModelProperty(value = "预测明日投喂量(克)")
    private Double baitPrediction;
}
