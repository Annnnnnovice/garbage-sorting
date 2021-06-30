package com.zjz.code.controller;

import com.zjz.code.entity.dto.GameDTO;
import com.zjz.code.entity.po.LeaveWord;
import com.zjz.code.service.*;
import com.zjz.code.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.UUID;

/**
 * @author zjz
 * @description
 * @date 2021-06-16 16:58
 */
@Api(tags = "前台展示模块")
@CrossOrigin
@RestController
@RequestMapping("/default")
public class RevealController {

    @Resource
    GameService gameService;

    @Resource
    ArticleService articleService;

    @Resource
    AdminService adminService;

    @Resource
    LabelService labelService;

    @Resource
    LeaveWordService leaveWordService;

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("保存留言")
    @PostMapping("/leaveWord/save")
    public Result saveLeaveWord(@RequestBody @Parameter(example = "{\"content\":\"网站不错\"}") Map<String, Object> map) {
        boolean content = leaveWordService.save(new LeaveWord(UUID.randomUUID().toString() ,(String) map.get("content")));
        if (!content) {
            return new Result().result500("失败", "/default/leaveWord/save");
        }
        return new Result().result200("成功", "/default/leaveWord/save");
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "查询结果为空")
    })
    @ApiOperation("查询专题")
    @GetMapping("/label/get")
    public Result getLabel(@RequestParam(value = "PageNow") @Parameter(example = "1") Integer pageNow,
                           @RequestParam(value = "PageSize") @Parameter(example = "5") Integer pageSize) {
        return adminService.getLabel(null, pageNow, pageSize);
    }

    @ApiOperation("根据专题查询文章")
    @GetMapping("/label/get/article")
    public Result getLabelArticle(@RequestParam(value = "LabelName") @Parameter(example = "浙江") String labelName,
                                  @RequestParam(value = "PageNow") @Parameter(example = "1") Integer pageNow,
                                  @RequestParam(value = "PageSize") @Parameter(example = "5") Integer pageSize) {


        return labelService.getLabelArticle(labelName, pageNow, pageSize);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("根据类别名称获取文章")
    @GetMapping("/article/type/get")
    public Result getArticlesByType(@RequestParam("TypeName") @Parameter(example = "垃圾分类知识") String typeName,
                                    @RequestParam("PageNow") @Parameter(example = "1") Integer pageNow,
                                    @RequestParam("PageSize") @Parameter(example = "5") Integer pageSize) {
        return articleService.getArticlesByType(typeName, pageNow, pageSize);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("文章模糊查询")
    @GetMapping("/article/get")
    public Result getArticles(@RequestParam(value = "Name", required = false) @Parameter(example = "前端") String name,
                              @RequestParam("PageNow") @Parameter(example = "1") Integer pageNow,
                              @RequestParam("PageSize") @Parameter(example = "5") Integer pageSize) {
        return adminService.getArticle(name, pageNow, pageSize);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "查询结果为空")
    })
    @ApiOperation("获得游戏题目")
    @GetMapping("/game/get")
    public Result getGame() {
        return gameService.getGame();
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("保存游戏记录")
    @PostMapping("/game/save")
    public Result saveGameRecords(@RequestBody GameDTO gameDTO) {
        return gameService.saveGameRecords(gameDTO);
    }


    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "查询结果为空")
    })
    @ApiOperation("获得游戏排行")
    @GetMapping("/game/ranking/get")
    public Result getGameRanking(@RequestParam("PageSize") @Parameter(example = "5") Integer pageSize,
                                 @RequestParam("PaperId") String paperId) {
        return adminService.getRanking(paperId, 1, pageSize);
    }
}
