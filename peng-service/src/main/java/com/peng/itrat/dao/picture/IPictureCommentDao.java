package com.peng.itrat.dao.picture;

import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.model.picture.PictureComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPictureCommentDao extends BaseMapper<PictureComment> {

    List<PictureComment> listByPicture(@Param("page") Page page, @Param("pictureId") Integer pictureId);

    int deleteByPicture(@Param("pictureId") Integer pictureId);

    PictureComment findById(@Param("pictureId") Integer pictureId);
}