package com.peng.itrat.service.picture.impl;

import com.peng.itrat.dao.picture.IPictureFavorDao;
import com.peng.itrat.model.picture.PictureFavor;
import com.peng.itrat.service.picture.IPictureFavorService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * Created by zchuanzhao on 2017/11/16.
 */
@Service("pictureFavorService")
public class PictureFavorServiceImpl implements IPictureFavorService {
    @Resource
    private IPictureFavorDao pictureFavorDao;


    @Override
    public PictureFavor find(Integer pictureId, Integer memberId) {
        return pictureFavorDao.find(pictureId,memberId);
    }

    @Override
    public void save(Integer pictureId, Integer memberId) {
        pictureFavorDao.save(pictureId,memberId);
    }

    @Override
    public void delete(Integer pictureId, Integer memberId) {
        pictureFavorDao.delete(pictureId,memberId);
    }
}
