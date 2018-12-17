package com.peng.itrat.dao.picture;

import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.model.picture.PictureAlbumFavor;
import org.apache.ibatis.annotations.Param;

public interface IPictureAlbumFavorDao extends BaseMapper<PictureAlbumFavor> {
    PictureAlbumFavor find(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);

    Integer save(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);

    Integer delete(@Param("pictureAlbumId") Integer pictureAlbumId, @Param("memberId") Integer memberId);
}