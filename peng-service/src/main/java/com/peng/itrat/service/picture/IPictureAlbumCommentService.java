package com.peng.itrat.service.picture;

import com.peng.itrat.model.member.Member;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.peng.itrat.model.picture.PictureAlbumComment;


/**
 *
 * @author zchuanzhao
 * @date 2017/11/17
 */
public interface IPictureAlbumCommentService {

    PictureAlbumComment findById(int id);

    boolean save(Member loginMember, String content, Integer pictureAlbumId);

    boolean delete(Member loginMember, int id);

    ResultModel listByPictureAlbum(Page page, int pictureAlbumId);

    void deleteByPictureAlbum(Integer pictureAlbumId);
}
