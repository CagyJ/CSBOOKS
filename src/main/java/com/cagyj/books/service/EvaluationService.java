package com.cagyj.books.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cagyj.books.entity.Evaluation;

import java.util.List;

public interface EvaluationService {

    /**
     * 按图书id选择评论
     * @param bookId
     * @return
     */
    public List<Evaluation> selectEvalByBookId(Long bookId);

    /**
     * 选取所有评论
     * @return
     */
    public IPage<Evaluation> paging(Integer page, Integer rows);

    /**
     * 更新评论状态
     * @param evaluationId
     * @param reason
     * @return
     */
    public Evaluation updateById(Long evaluationId, String status, String reason);
}
