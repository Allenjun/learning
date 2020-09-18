package com.allen.learningbootjpa.pojo.DO;

import lombok.Data;

import javax.persistence.*;

/**
 * @author JUN @Description TODO
 * @createTime 16:28
 */
@Data
@Entity
@Table(name = "student_course", schema = "test")
@IdClass(StudentCourseDOPK.class)
public class StudentCourseDO {

    @Id
    @Column(name = "sid")
    private int sid;

    @Id
    @Column(name = "cid")
    private int cid;

    @Basic
    @Column(name = "score")
    private int score;
}
