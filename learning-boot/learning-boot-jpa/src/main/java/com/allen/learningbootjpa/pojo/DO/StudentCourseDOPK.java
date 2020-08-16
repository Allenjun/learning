package com.allen.learningbootjpa.pojo.DO;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Id;
import lombok.Data;

/**
 * @author JUN
 * @Description TODO
 * @createTime 16:28
 */
@Data
public class StudentCourseDOPK implements Serializable {
    
    @Id
    @Column(name = "sid")
    private int sid;
    @Id
    @Column(name = "cid")
    private int cid;
}
