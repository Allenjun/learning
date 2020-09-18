package com.allen.learningbootjpa.pojo.DO;

import lombok.Data;

import javax.persistence.*;

/**
 * @author JUN @Description TODO
 * @createTime 16:28
 */
@Data
@Entity
@Table(name = "student", schema = "test")
public class StudentDO {

    @Id
    @Column(name = "id")
    private int id;

    @Basic
    @Column(name = "name")
    private String name;
}
