package com.allen.learningboottransaction.pojo.DO.google;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/07/17 16:44:00
 */
@Entity
@Table(name = "teacher", schema = "google", catalog = "")
public class TeacherDO {

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
        TeacherDO teacherDO = (TeacherDO) o;
        return id == teacherDO.id && Objects.equals(name, teacherDO.name);
    }
}
