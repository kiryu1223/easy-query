package com.easy.query.dto;

import com.easy.query.core.annotation.Column;
import lombok.Data;
import lombok.ToString;

/**
 * create time 2023/3/25 21:43
 * 文件说明
 *
 * @author xuejiaming
 */
@ToString
public class TopicGroupTestDTO {

    private String id;

    private Integer idCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIdCount() {
        return idCount;
    }

    public void setIdCount(Integer idCount) {
        this.idCount = idCount;
    }
}
