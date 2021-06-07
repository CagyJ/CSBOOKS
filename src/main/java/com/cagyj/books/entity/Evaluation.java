package com.cagyj.books.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("evaluation")
public class Evaluation {

    @TableId(type = IdType.AUTO)
    private Long evaluationId;
    private String content;
    private int score;
    private Date createTime;
    private Long memberId;
    private Long bookId;
    private int enjoy;
    private String state;
    private String disableReason;
    private Date disableTime;

    @TableField(exist = false) //member属性没有对应字段, 表示会在后期注入
    private Member member;

    @TableField(exist = false)
    private Book book;


    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Evaluation(Long evaluationId, String content, int score, Date date, Long memberId, Long bookId, int enjoy, String state, String disableReason, Date disableTime) {
        this.evaluationId = evaluationId;
        this.content = content;
        this.score = score;
        this.createTime = date;
        this.memberId = memberId;
        this.bookId = bookId;
        this.enjoy = enjoy;
        this.state = state;
        this.disableReason = disableReason;
        this.disableTime = disableTime;
    }

    public Evaluation() {
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "evaluationId=" + evaluationId +
                ", content='" + content + '\'' +
                ", score=" + score +
                ", date=" + createTime +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", enjoy=" + enjoy +
                ", state='" + state + '\'' +
                ", disableReason='" + disableReason + '\'' +
                ", disableDate=" + disableTime +
                '}';
    }

    public Long getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Long evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime = date;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public int getEnjoy() {
        return enjoy;
    }

    public void setEnjoy(int enjoy) {
        this.enjoy = enjoy;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDisableReason() {
        return disableReason;
    }

    public void setDisableReason(String disableReason) {
        this.disableReason = disableReason;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }
}
