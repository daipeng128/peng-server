package com.peng.itrat.dao.picture;

import com.peng.itrat.model.picture.PictureAlbum;
import com.peng.itrat.core.dao.BaseMapper;
import com.peng.itrat.core.model.Page;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface IPictureAlbumDao extends BaseMapper<PictureAlbum> {

    List<PictureAlbum> listByMember(@Param("memberId") Integer memberId);

    List<PictureAlbum> list(@Param("page") Page page);

    PictureAlbum findWeiboAlbum(@Param("memberId") Integer memberId);

    PictureAlbum findById(@Param("id") Integer id);

    int delete(@Param("id") Integer id);
}