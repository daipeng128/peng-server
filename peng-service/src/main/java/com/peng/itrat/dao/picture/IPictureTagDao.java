package com.peng.itrat.dao.picture;

import com.peng.itrat.model.picture.PictureTag;

public interface IPictureTagDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PictureTag record);

    int insertSelective(PictureTag record);

    PictureTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PictureTag record);

    int updateByPrimaryKey(PictureTag record);
}