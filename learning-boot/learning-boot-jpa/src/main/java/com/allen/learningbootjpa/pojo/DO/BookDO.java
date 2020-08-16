package com.allen.learningbootjpa.pojo.DO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author JUN
 * @Description TODO
 * @createTime 12:21
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "book", schema = "test")
public class BookDO extends IdAbstractDO {
    
    @Column
    private String price;
    
}
