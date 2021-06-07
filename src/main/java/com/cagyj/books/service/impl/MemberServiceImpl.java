package com.cagyj.books.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cagyj.books.entity.Evaluation;
import com.cagyj.books.entity.Member;
import com.cagyj.books.entity.MemberReadState;
import com.cagyj.books.mapper.EvaluationMapper;
import com.cagyj.books.mapper.MemberMapper;
import com.cagyj.books.mapper.MemberReadStateMapper;
import com.cagyj.books.service.MemberService;
import com.cagyj.books.service.exception.BussinessException;
import com.cagyj.books.utils.MD5Utils;
import com.mysql.cj.result.DefaultValueFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service("memberService")
@Transactional
public class MemberServiceImpl implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private MemberReadStateMapper memberReadStateMapper;

    @Resource
    private EvaluationMapper evaluationMapper;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Member selectById(Long id) {
        Member member = memberMapper.selectById(id);
        return member;
    }

    @Override
    public Member createMember(String username, String password, String nickname) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<Member> list = memberMapper.selectList(queryWrapper);
        if (list.size() >0) {
            throw new BussinessException("M01", "The user exists.");
        }
        Member member = new Member();
        member.setUsername(username);
        member.setNickname(nickname);
        // 1000 - 1999数字
        int salt = new Random().nextInt(1000) + 1000;
        password = MD5Utils.md5Digest(password,salt);
        member.setPassword(password);
        member.setSalt(salt);
        member.setCreateTime(new Date());
        memberMapper.insert(member);
        return member;
    }

    @Override
    public Member checkLogin(String username, String password) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        Member member = memberMapper.selectOne(queryWrapper);
        if (member == null) {
            throw new BussinessException("M02", "This account does not exists!");
        }

        String md5 = MD5Utils.md5Digest(password, member.getSalt());
        if (!md5.equals(member.getPassword())) {
            throw new BussinessException("M03", "The password is not correct.");
        }
        return member;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MemberReadState selectMemberReadState(Long memberId, Long bookId) {
        QueryWrapper<MemberReadState> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id", bookId);
        queryWrapper.eq("member_id", memberId);
        MemberReadState readState = memberReadStateMapper.selectOne(queryWrapper);

        return readState;
    }

    @Override
    public MemberReadState updateMemberReadState(Long memberId, Long bookId, Integer readState) {
        QueryWrapper<MemberReadState> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("book_id", bookId);
        queryWrapper.eq("member_id", memberId);
        MemberReadState memberReadState = memberReadStateMapper.selectOne(queryWrapper);
        if (memberReadState == null) {
            memberReadState = new MemberReadState();
            memberReadState.setBookId(bookId);
            memberReadState.setCreateTime(new Date());
            memberReadState.setMemberId(memberId);
            memberReadState.setReadState(readState);
            memberReadStateMapper.insert(memberReadState);
        } else {
            memberReadState.setReadState(readState);
            memberReadStateMapper.updateById(memberReadState);
        }
        return memberReadState;
    }

    /**
     * 写评论逻辑
     * @param memberId
     * @param bookId
     * @param score
     * @param contents
     * @return
     */
    @Override
    public Evaluation evaluate(Long memberId, Long bookId, Integer score, String contents) {
        Evaluation evaluation = new Evaluation();
        evaluation.setMemberId(memberId);
        evaluation.setBookId(bookId);
        evaluation.setScore(score);
        evaluation.setContent(contents);
        evaluation.setCreateTime(new Date());
        evaluation.setState("enable");
        evaluation.setEnjoy(0);
        evaluationMapper.insert(evaluation);
        return evaluation;
    }

    @Override
    public Evaluation enjoy(Long evaluationId) {
        Evaluation evaluation = evaluationMapper.selectById(evaluationId);
        evaluation.setEnjoy(evaluation.getEnjoy()+1);
        evaluationMapper.updateById(evaluation);
        return evaluation;
    }
}
