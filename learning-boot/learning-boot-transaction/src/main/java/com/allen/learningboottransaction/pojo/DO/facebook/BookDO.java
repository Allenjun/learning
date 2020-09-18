package com.allen.learningboottransaction.pojo.DO.facebook;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author admin
 * @version 1.0.0 @Description TODO
 * @createTime 2019/07/17 11:46:00
 */
@Entity
@Table(name = "book", schema = "exp01", catalog = "")
public class BookDO {

    private int id;
    private String description;
    private String title;

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
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, title);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BookDO bookDO = (BookDO) o;
        return id == bookDO.id
                && Objects.equals(description, bookDO.description)
                && Objects.equals(title, bookDO.title);
    }
}
