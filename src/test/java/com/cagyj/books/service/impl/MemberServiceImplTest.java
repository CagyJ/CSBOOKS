package com.cagyj.books.service.impl;

import com.cagyj.books.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MemberServiceImplTest {

    @Resource
    private MemberService memberService;

    @Test
    public void createMember() {
        memberService.createMember("cagyjiao", "cagyjiao", "cagyjiao");
    }
}