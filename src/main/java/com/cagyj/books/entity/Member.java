package com.cagyj.books.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("`member`") // 避免与保留字段冲突
public class Member {

    @TableId(type = IdType.AUTO)
    private Long memberId;
    private String username;
    private String password;
    private int salt;
    private Date createTime;
    private String nickname;

    @Override
    public String toString() {
        return "Member{" +
                "memberId=" + memberId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", salt=" + salt +
                ", createTime=" + createTime +
                ", nickName='" + nickname + '\'' +
                '}';
    }

    public Member() {
    }

    public Member(Long memberId, String username, String password, int salt, Date createTime, String nickname) {
        this.memberId = memberId;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.createTime = createTime;
        this.nickname = nickname;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSalt() {
        return salt;
    }

    public void setSalt(int salt) {
        this.salt = salt;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
