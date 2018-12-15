package com.peng.itrat.service.picture;

import com.peng.itrat.model.member.Member;
import com.lxinet.jeesns.core.dto.ResultModel;
import com.lxinet.jeesns.core.model.Page;
import com.peng.itrat.model.picture.PictureComment;


/**
 * Created by zchuanzhao on 2017/11/14.
 */
public interface IPictureCommentService {

    PictureComment findById(int id);

    boolean save(Member loginMember, String content, Integer pictureId);

    boolean delete(Member loginMember, int id);

    ResultModel listByPicture(Page page, int pictureId);

    void deleteByPicture(Integer pictureId);
}
