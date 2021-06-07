package com.cagyj.books.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("book")
public class Book {

    @TableId(type = IdType.AUTO)
    @TableField("book_id")
    private long bookId;

    @TableField("book_name")
    private String bookName;

    @TableField("sub_title")
    private String subTitle;

    @TableField("author")
    private String author;

    // the cover image of the book
    @TableField("cover")
    private String cover;

    @TableField("description")
    private String description;

    @TableField("category_id")
    private long categoryId;

    @TableField("evaluation_score")
    private float evaluationScore;

    @TableField("evaluation_quantity")
    private int evaluationQuantity;

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", author='" + author + '\'' +
                ", cover='" + cover + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", evaluationScore=" + evaluationScore +
                ", evaluationQuantity=" + evaluationQuantity +
                '}';
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public float getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(float evaluationScore) {
        this.evaluationScore = evaluationScore;
    }

    public int getEvaluationQuantity() {
        return evaluationQuantity;
    }

    public void setEvaluationQuantity(int evaluationQuantity) {
        this.evaluationQuantity = evaluationQuantity;
    }
}
