package com.allen.learningbootjpa.pojo.DO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author JUN
 * @Description TODO
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
