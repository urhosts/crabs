package org.jeecg.modules.crabs.bait.entity;

import lombok.Data;

@Data
public class StatResultBait {
    private static final long serialVersionUID = 1L;

    // 创建时间
    private String createTime;

    // 饵料个数
    private Integer baitCount = 0;

    // 投喂量
    private Integer baitInput = 0;

    // 摄食量
    private double baitIntake = 0;

    // 识别表溶解氧
    private Double recoDissolvedOxygen = 0.0;

    // 预测表溶解氧
    private Double antiDissolvedOxygen = 0.0;

    // 识别表螃蟹只数
    private Integer recoCrabsCount = 0;

    // 预测表螃蟹只数
    private Integer antiCrabsCount = 0;

    // 预测表明日投喂量
    private Double baitPrediction = 0.0;

    private String name;

    private double value;

}
