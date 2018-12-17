package com.peng.itrat.service.common;

import com.peng.itrat.model.member.Member;
import com.peng.itrat.model.common.Archive;
import com.peng.itrat.core.dto.ResultModel;


/**
 * Created by zchuanzhao on 2016/10/14.
 */
public interface IArchiveService {

    Archive findByArchiveId(int id);

    boolean save(Member member, Archive archive);

    boolean update(Member member, Archive archive);

    boolean delete(int id);

    void updateViewCount(int id);

    ResultModel favor(Member loginMember, int archiveId);
}
