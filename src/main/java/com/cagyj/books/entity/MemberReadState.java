package com.cagyj.books.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("member_read_state")
public class MemberReadState {

    @TableId(type= IdType.AUTO)
    private long rsId;
    private long bookId;
    private long memberId;
    private int readState;
    private Date createTime;


    @Override
    public String toString() {
        return "MemberReadState{" +
                "rsId=" + rsId +
                ", bookId=" + bookId +
                ", memberId=" + memberId +
                ", readState=" + readState +
                ", createTime=" + createTime +
                '}';
    }

    public MemberReadState() {
    }

    public MemberReadState(long rsId, long bookId, long memberId, int readState, Date createTime) {
        this.rsId = rsId;
        this.bookId = bookId;
        this.memberId = memberId;
        this.readState = readState;
        this.createTime = createTime;
    }

    public long getRsId() {
        return rsId;
    }

    public void setRsId(long rsId) {
        this.rsId = rsId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }

    public int getReadState() {
        return readState;
    }

    public void setReadState(int readState) {
        this.readState = readState;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
