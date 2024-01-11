package com.dy.wowojcodesandbox.model;

import lombok.Data;

/**
 * 判题信息
 */
@Data
public class JudgeInfo {
    /**
     * 程序执行信息
     */
    private String message;
    /**
     * 内存(KB)
     */
    private Long memory;
    /**
     * 运行时间(ms)
     */
    private Long time;
}
