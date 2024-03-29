package com.peng.itrat.dao.picture;

import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.model.picture.PictureAlbumComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IPictureAlbumCommentDao extends BaseMapper<PictureAlbumComment> {
    List<PictureAlbumComment> listByPictureAlbum(@Param("page") Page page, @Param("pictureAlbumId") Integer pictureAlbumId);

    int deleteByPictureAlbum(@Param("pictureAlbumId") Integer pictureAlbumId);

    PictureAlbumComment findById(@Param("id") Integer id);
}