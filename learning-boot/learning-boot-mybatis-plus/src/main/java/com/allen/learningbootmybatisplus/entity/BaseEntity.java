package com.allen.learningbootmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    @Version
    private Integer revision;
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private LocalDateTime createdTime;
    @TableField(value = "created_by", fill = FieldFill.INSERT)
    private String createdBy;
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private LocalDateTime updatedTime;
    @TableField(value = "updated_by", fill = FieldFill.UPDATE)
    private String updatedBy;
    @TableLogic(value = "0", delval = "1")
    @TableField(fill = FieldFill.INSERT, select = false)
    private Integer deleted;
}
