package com.allen.learningboottransaction.pojo.DO.facebook;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/07/17 11:46:00
 */
@Entity
@Table(name = "user", schema = "exp01", catalog = "")
public class UserDO {

    private int id;
    private String name;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDO userDO = (UserDO) o;
        return id == userDO.id && Objects.equals(name, userDO.name);
    }
}
