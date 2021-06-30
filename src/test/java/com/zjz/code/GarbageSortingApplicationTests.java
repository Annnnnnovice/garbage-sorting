package com.zjz.code;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjz.code.entity.po.Article;
import com.zjz.code.mapper.ArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.UUID;

@SpringBootTest
class GarbageSortingApplicationTests {

    @Test
    void contextLoads() {
        /*try {
            throw new Exception("自定义异常");
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        /*for (int i = 0; i <= 6; i++) {
            System.out.println(UUID.randomUUID().toString());
        }*/
        String s = "浙江";
        String[] arr = s.split(",");
        System.out.println(arr[0]);
        System.out.println(arr[1]);
    }

}
