package com.peng.itrat.model.picture;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peng.itrat.model.member.Member;
import com.peng.itrat.core.annotation.Column;
import com.peng.itrat.core.annotation.Id;
import com.peng.itrat.core.annotation.Table;
import com.peng.itrat.core.enums.FillTime;
import com.peng.itrat.core.enums.IdType;

import java.util.Date;

@Table("tbl_picture_comment")
public class PictureComment {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column("member_id")
    private Integer memberId;

    private Member member;

    @Column("picture_id")
    private Integer pictureId;

    private Picture picture;
    @Column("content")
    private String content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}