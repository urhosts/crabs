package org.jeecg.modules.crabs.bait.entity;

import lombok.Data;

@Data
public class StatResultBait {
    private static final long serialVersionUID = 1L;

    private String createTime;

    private Integer baitCount;

    private Integer baitInput;

    private double baitIntake;

    private String name;

    private double value;

}
