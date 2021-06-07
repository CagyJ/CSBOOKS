package com.cagyj.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cagyj.books.entity.Book;
import com.cagyj.books.entity.Evaluation;
import com.cagyj.books.mapper.EvaluationMapper;
import com.cagyj.books.service.EvaluationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("evaluationService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class EvaluationServiceImpl implements EvaluationService {

    @Resource
    private EvaluationMapper evaluationMapper;

    @Override
    public List<Evaluation> selectEvalByBookId(Long bookId) {
        QueryWrapper<Evaluation> wrapper = new QueryWrapper<>();
        wrapper.eq("book_id", bookId);
        // enable comments
        wrapper.eq("state", "enable");
        wrapper.orderByDesc("create_time");
        List<Evaluation> list = evaluationMapper.selectList(wrapper);
        return list;
    }

    @Override
    public IPage<Evaluation> paging(Integer page, Integer rows) {
        Page<Evaluation> p = new Page<Evaluation>(page, rows);
        QueryWrapper<Evaluation> queryWrapper = new QueryWrapper<>();
        // 第一个参数是Page对象，第二个是QueryWrapper
        Page<Evaluation> evaluationPage = evaluationMapper.selectPage(p, queryWrapper);
        return evaluationPage;
    }

    @Override
    public Evaluation updateById(Long evaluationId, String status, String reason) {
        Evaluation ori = evaluationMapper.selectById(evaluationId);
        ori.setState(status);
        ori.setDisableReason(reason);
        ori.setDisableTime(new Date());
        evaluationMapper.updateById(ori);
        return ori;
    }

}
