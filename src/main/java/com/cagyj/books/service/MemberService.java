package com.cagyj.books.service;

import com.cagyj.books.entity.Evaluation;
import com.cagyj.books.entity.Member;
import com.cagyj.books.entity.MemberReadState;

public interface MemberService {
    public Member selectById(Long id);

    public Member createMember(String username, String password, String nickname);

    public Member checkLogin(String username, String password);

    public MemberReadState selectMemberReadState(Long memberId, Long bookId);

    public MemberReadState updateMemberReadState(Long memberId, Long bookId, Integer readState);

    public Evaluation evaluate(Long memberId, Long bookId, Integer score, String contents);

    public Evaluation enjoy(Long evaluationId);
}
