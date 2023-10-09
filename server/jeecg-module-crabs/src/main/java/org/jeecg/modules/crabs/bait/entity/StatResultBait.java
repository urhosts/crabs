package org.jeecg.modules.crabs.bait.entity;

import lombok.Data;

@Data
public class StatResultBait {
    private static final long serialVersionUID = 1L;

    // 创建时间
    private String createTime;

    // 饵料个数
    private Integer baitCount;

    // 投喂量
    private Integer baitInput;

    // 摄食量
    private double baitIntake;

    // 识别表溶解氧
    private Double recoDissolvedOxygen;

    // 预测表溶解氧
    private Double antiDissolvedOxygen;

    // 识别表螃蟹只数
    private Integer recoCrabsCount;

    // 预测表螃蟹只数
    private Integer antiCrabsCount;

    // 预测表明日投喂量
    private Double baitPrediction;

    private String name;

    private double value;

}
