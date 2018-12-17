package com.peng.itrat.service.picture;

import com.peng.itrat.model.picture.PictureAlbum;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.model.Page;

/**
 * Created by zchuanzhao on 2017/11/03.
 */
public interface IPictureAlbumService {

    ResultModel<PictureAlbum> listByMember(Integer memberId);

    ResultModel<PictureAlbum> listByPage(Page page);

    boolean delete(Integer id);

    boolean save(PictureAlbum pictureAlbum);

    boolean update(PictureAlbum pictureAlbum);

    PictureAlbum findWeiboAlbum(Integer memberId);

    PictureAlbum findById(Integer id);
}