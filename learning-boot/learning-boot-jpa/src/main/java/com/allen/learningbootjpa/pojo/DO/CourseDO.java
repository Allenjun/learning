package com.allen.learningbootjpa.pojo.DO;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author JUN
 * @Description TODO
 * @createTime 16:28
 */
@Data
@Entity
@Table(name = "course", schema = "test")
@NamedQuery(name = "CourseDO.selectWithAnnoNameQuery", query = "select c from CourseDO c where c.name = :name")
public class CourseDO {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    
}
