package com.easy.query.test.dto.autodto;

import com.easy.query.core.annotation.Column;
import com.easy.query.core.annotation.Navigate;
import com.easy.query.core.enums.RelationTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * create time 2024/4/12 22:55
 * 文件说明
 *
 * @author xuejiaming
 */
@Data
public class SchoolClassAO {

//    @Column(primaryKey = true)//主键
//    private String id;
    private String name;
    //一对多 一个班级多个学生
    @Navigate(value = RelationTypeEnum.OneToMany)
    //完整配置,property忽略表示对应的主键
//    @Navigate(value = RelationTypeEnum.OneToMany,selfProperty = "id",targetProperty = "classId")
    private List<SchoolStudentAO> schoolStudents;

    @Data
    public static class  SchoolStudentAO{
        @Column(primaryKey = true)
        private String id;
//        private String classId;
        private String name;
    }
}
