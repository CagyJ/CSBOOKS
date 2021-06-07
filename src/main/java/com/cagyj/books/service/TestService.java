package com.cagyj.books.service;

import com.cagyj.books.mapper.TestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    @Transactional // 添加事务
    public void batchImport() {
        for (int i = 0; i < 5; i++) {
            testMapper.insertSample();
        }
    }

    public void selectAll() {
        List<Map> list = testMapper.selectAll();
        for (Map map :
                list) {
            System.out.println(map);
        }
    }
}
