package com.allen.learningbootjpa.pojo.DO;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author JUN @Description TODO
 * @createTime 12:08
 */
@Data
@MappedSuperclass
public class IdAbstractDO {

    @Id
    @Column
    @GenericGenerator(name = "snowFlake", strategy = "com.allen.learningbootjpa.pojo.DO.IdGenerator")
    @GeneratedValue(generator = "snowFlake")
    private Long id;
}
