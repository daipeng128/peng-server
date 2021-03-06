package com.peng.itrat.service.picture.impl;

import com.peng.itrat.model.member.Member;
import com.peng.itrat.model.picture.PictureAlbum;
import com.peng.itrat.core.utils.ValidUtill;
import com.peng.itrat.core.dto.ResultModel;
import com.peng.itrat.core.enums.Messages;
import com.peng.itrat.core.model.Page;
import com.peng.itrat.dao.picture.IPictureAlbumCommentDao;
import com.peng.itrat.model.picture.PictureAlbumComment;
import com.peng.itrat.service.member.IMemberService;
import com.peng.itrat.service.picture.IPictureAlbumCommentService;
import com.peng.itrat.service.picture.IPictureAlbumService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author zchuanzhao
 * @date 2017/11/17
 */
@Service("pictureAlbumCommentService")
public class PictureAlbumCommentServiceImpl implements IPictureAlbumCommentService {
    @Resource
    private IPictureAlbumCommentDao pictureAlbumCommentDao;
    @Resource
    private IPictureAlbumService pictureAlbumService;
    @Resource
    private IMemberService memberService;

    @Override
    public PictureAlbumComment findById(int id) {
        PictureAlbumComment pictureAlbumComment = pictureAlbumCommentDao.findById(id);
        atFormat(pictureAlbumComment);
        return pictureAlbumComment;
    }

    @Override
    public boolean save(Member loginMember, String content, Integer pictureAlbumId) {
        PictureAlbum pictureAlbum = pictureAlbumService.findById(pictureAlbumId);
        ValidUtill.checkIsNull(pictureAlbum, Messages.PICTURE_ALBUM_NOT_EXISTS);
        PictureAlbumComment pictureAlbumComment = new PictureAlbumComment();
        pictureAlbumComment.setMemberId(loginMember.getId());
        pictureAlbumComment.setPictureAlbumId(pictureAlbumId);
        pictureAlbumComment.setContent(content);
        int result = pictureAlbumCommentDao.saveObj(pictureAlbumComment);
        return result == 1;
    }

    @Override
    public ResultModel listByPictureAlbum(Page page, int pictureAlbumId) {
        List<PictureAlbumComment> list = pictureAlbumCommentDao.listByPictureAlbum(page, pictureAlbumId);
        atFormat(list);
        ResultModel model = new ResultModel(0,page);
        model.setData(list);
        return model;
    }

    @Override
    public void deleteByPictureAlbum(Integer pictureAlbumId) {
        pictureAlbumCommentDao.deleteByPictureAlbum(pictureAlbumId);
    }

    @Override
    public boolean delete(Member loginMember, int id) {
        PictureAlbumComment pictureAlbumComment = this.findById(id);
        ValidUtill.checkIsNull(pictureAlbumComment, "评论不存在");
        int result = pictureAlbumCommentDao.deleteById(id, PictureAlbumComment.class);
        return result == 1;
    }

    public PictureAlbumComment atFormat(PictureAlbumComment pictureAlbumComment){
        pictureAlbumComment.setContent(memberService.atFormat(pictureAlbumComment.getContent()));
        return pictureAlbumComment;
    }

    public List<PictureAlbumComment> atFormat(List<PictureAlbumComment> pictureAlbumCommentList){
        for (PictureAlbumComment pictureAlbumComment : pictureAlbumCommentList){
            atFormat(pictureAlbumComment);
        }
        return pictureAlbumCommentList;
    }
}
