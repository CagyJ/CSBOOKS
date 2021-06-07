package com.cagyj.books.task;

import com.cagyj.books.service.BookService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 完成图书评分任务
 */
@Component  // 不能确定是controller，repository,...
public class ComputeTask {

    @Resource
    private BookService bookService;

    // 定时任务(任务调度)
    @Scheduled(cron = "0 0 0 * * ?")  // 每天凌晨两点
    public void updateEvaluation() {
        bookService.updateEvaluation();
        System.out.println("更新评分了！！！");
    }

}
