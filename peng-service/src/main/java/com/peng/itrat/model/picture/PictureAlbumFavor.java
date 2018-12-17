package com.peng.itrat.model.picture;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.peng.itrat.core.annotation.Column;
import com.peng.itrat.core.annotation.Id;
import com.peng.itrat.core.annotation.Table;
import com.peng.itrat.core.enums.FillTime;
import com.peng.itrat.core.enums.IdType;

import java.util.Date;

@Table("tbl_picture_album_favor")
public class PictureAlbumFavor {
    @Id(value = "id", type = IdType.AUTO)
    private Integer id;
    @Column(value = "create_time", currTime = FillTime.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column("picture_album_id")
    private Integer pictureAlbumId;
    @Column("member_id")
    private Integer memberId;

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

    public Integer getPictureAlbumId() {
        return pictureAlbumId;
    }

    public void setPictureAlbumId(Integer pictureAlbumId) {
        this.pictureAlbumId = pictureAlbumId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}