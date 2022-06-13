package com.gttss.testservice.dao;

import com.gttss.entity.PageData;

import java.util.List;

public interface Dao {
    public int  findCount(PageData pd);
    public List<PageData> findList(PageData pd);

    public PageData findByUuid(PageData pd);
    public int add(PageData pd);
    public int edit(PageData pd);
    public int delete(PageData pd);

    public List<PageData> dataListPage();
    public List<PageData> listAll();
}
