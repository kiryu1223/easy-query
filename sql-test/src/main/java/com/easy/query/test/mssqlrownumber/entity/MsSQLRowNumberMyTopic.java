package com.easy.query.test.mssqlrownumber.entity;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.Table;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * create time 2023/7/27 17:32
 * 文件说明
 *
 * @author xuejiaming
 */
@Data
@Table("MyTopic")
public class MsSQLRowNumberMyTopic {

    @Column(primaryKey = true)
    private String id;
    private Integer stars;
    private String title;
    private LocalDateTime createTime;
}