package com.bingo.customer.view.gridview;

import java.io.Serializable;

/**
 * ================================
 *
 * @author: zcb
 * @email: zhang-cb@foxmail.com
 * @time: 2018/5/18 10:10
 * @version: 1.0
 * @description: =================================
 */
public class ItemEntity implements Serializable {

    public ItemEntity() {
    }

    public ItemEntity(String name, int id) {
        this.name = name;
        this.id = id;
    }

    private String name;
    private int id;

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
