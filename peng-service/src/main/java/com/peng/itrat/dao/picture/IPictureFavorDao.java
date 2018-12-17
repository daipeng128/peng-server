package com.peng.itrat.dao.picture;

import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.model.picture.PictureFavor;
import org.apache.ibatis.annotations.Param;

public interface IPictureFavorDao extends BaseMapper<PictureFavor> {

    PictureFavor find(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer save(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);

    Integer delete(@Param("pictureId") Integer pictureId, @Param("memberId") Integer memberId);
}