package com.zjz.code.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjz.code.entity.dto.*;
import com.zjz.code.entity.po.Label;
import com.zjz.code.entity.po.LeaveWord;
import com.zjz.code.entity.vo.PageVO;
import com.zjz.code.service.AdminService;
import com.zjz.code.service.LabelService;
import com.zjz.code.service.LeaveWordService;
import com.zjz.code.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author zjz
 * @description 管理模块
 * @date 2021-06-06 15:48
 */
@Api(tags = "管理模块")
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Value("${user.ip}")
    private String ip;

    @Value("${user.virtualpath}")
    private String virtualPath;

    /**
     * 注入配置中图片保存路径
     */
    @Value("${user.filepath}")
    private String filePath;
    /**
     * 注入配置中的端口号
     */
    @Value("${server.port}")
    private String port;

    @Resource
    AdminService adminService;

    @Resource
    LabelService labelService;

    @Resource
    LeaveWordService leaveWordService;

    @ApiOperation(value = "上传图片")
    @PostMapping("/upload")
    public Result upload(@RequestPart("file") MultipartFile multipartFile) {
        // 生成一个随机的名称，避免文件名重复
        UUID uuid = UUID.randomUUID();
        // 获取原文件名称
        String originalFileName = multipartFile.getOriginalFilename();
        // 获取原文件的后缀
        String fileSuffix = originalFileName.substring(originalFileName.lastIndexOf('.'));
        // 保存文件
        File file = new File(filePath + uuid + fileSuffix);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return new Result().result403("上传失败", "/admin/upload");
        }
        String url = "http://"+ ip + ":"+ port + virtualPath + uuid + fileSuffix;
        return new Result().result200(url, "/admin/upload");
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "查询为空")
    })
    @ApiOperation("分页查询留言")
    @GetMapping("/leaveWord/get")
    public Result getLeaveWord(@RequestParam("PageNow") @Parameter(example = "1") Integer pageNow,
                               @RequestParam("PageSize") @Parameter(example = "5") Integer pageSize) {
        if (pageNow <= 0 ) {
            pageNow = 1;
        }
        Page<LeaveWord> page = leaveWordService.page(new Page<>(pageNow, pageSize));
        if (page.getRecords().size() == 0) {
            return new Result().result404(new ArrayList<>(), "/admin/leaveWord/get");
        }
        int pageTotal = (int) (page.getTotal() / pageSize);
        if (page.getTotal() % pageSize != 0) {
            pageTotal++;
        }
        return new Result().result200(new PageVO<>(pageNow, pageTotal, pageSize, (int) page.getTotal(), page.getRecords()), "/admin/leaveWord/get");
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "删除成功"),
        @ApiResponse(code = 500, message = "删除失败")
    })
    @ApiOperation("批量删除留言")
    @DeleteMapping("/leaveWord/remove")
    public Result removeLeaveWord(@RequestBody List<String> ids) {
        boolean b = leaveWordService.removeByIds(ids);
        if (!b) {
            return new Result().result500("失败,", "/admin/leaveWord/remove");
        }
        return new Result().result200("成功,", "/admin/leaveWord/remove");
    }

    @ApiResponse(code = 200, message = "成功")
    @ApiOperation("分页模糊查询文章")
    @GetMapping("/article/get")
    public Result getArticle(@RequestParam(value = "Name", required = false) String name,
                             @RequestParam("PageNow") @Parameter(example = "1") Integer pageNow,
                             @RequestParam("PageSize") @Parameter(example = "5") Integer pageSize) {
        return adminService.getArticle(name, pageNow, pageSize);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("增加文章")
    @PostMapping("/article/save")
    public Result saveArticle(@RequestBody ArticleSaveDTO articleSaveDTO, HttpServletRequest httpServletRequest) {
        try {
            return adminService.saveArticle(articleSaveDTO, httpServletRequest);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Result().result500("插入失败", "/admin/article/save");
        }
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "修改成功"),
        @ApiResponse(code = 500, message = "修改失败")
    })
    @ApiOperation("修改文章")
    @PutMapping("/article/update")
    public Result updateArticle(@RequestBody ArticleUpdateDTO articleUpdateDTO) {
        return adminService.updateArticle(articleUpdateDTO);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "删除成功"),
        @ApiResponse(code = 500, message = "删除失败")
    })
    @ApiOperation("批量删除文章")
    @DeleteMapping("/article/remove")
    public Result removeArticle(@RequestBody List<String> ids) {
        return adminService.removeArticle(ids);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("增加专题")
    @PostMapping("/label/save")
    public Result saveLabel(@RequestBody LabelSaveDTO labelSaveDTO) {
        Label label = new Label(labelSaveDTO.getName());
        label.setId(UUID.randomUUID().toString());
        boolean save = false;
        try {
            save = labelService.save(label);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result().result500("增加失败", "/admin/label/save");
        }
        if (!save) {
            return new Result().result500("增加失败", "/admin/label/save");
        }
        return new Result().result200("增加成功", "/admin/label/save");
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "查询结果为空")
    })
    @ApiOperation("查询专题")
    @GetMapping("/label/get")
    public Result getLabel(@RequestParam(value = "Name", required = false) String name,
                           @RequestParam("PageNow") @Parameter(example = "1") Integer pageNow,
                           @RequestParam("PageSize") @Parameter(example = "5") Integer pageSize) {
        return adminService.getLabel(name, pageNow, pageSize);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("修改专题")
    @PutMapping("/label/update")
    public Result updateLabel(@RequestBody LabelUpdateDTO labelUpdateDTO) {
        Label label = new Label();
        label.setId(labelUpdateDTO.getId());
        boolean update = labelService.update(new Label(labelUpdateDTO.getName()),new UpdateWrapper<>(label));
        if (!update) {
            return new Result().result500("修改失败", "/admin/label/update");
        }
        return new Result().result200("修改成功", "/admin/label/update");
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("删除专题")
    @DeleteMapping("/label/remove")
    public Result removeLabel(@RequestBody List<String> ids) {
        return adminService.removeLabel(ids);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "查询结果为空")
    })
    @ApiOperation("查询试卷")
    @GetMapping("/paper/get")
    public Result getPaper(@RequestParam(value = "Name", required = false) String name,
                           @RequestParam("PageNow") @Parameter(example = "1") Integer pageNow,
                           @RequestParam("PageSize") @Parameter(example = "5") Integer pageSize) {
        return adminService.getPaper(name, pageNow, pageSize);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("增加试卷")
    @PostMapping("/paper/save")
    public Result savePaper(@RequestBody LabelSaveDTO labelSaveDTO) {
        return adminService.savePaper(labelSaveDTO.getName());
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("修改试卷")
    @PutMapping("/paper/update")
    public Result updatePaper(@RequestBody LabelUpdateDTO labelUpdateDTO) {
        return adminService.updatePaper(labelUpdateDTO);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("删除试卷")
    @DeleteMapping("/paper/remove")
    public Result removePaper(@RequestBody List<String> ids) {
        try {
            return adminService.removePaper(ids);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Result().result500("删除失败", "/admin/paper/remove");
        }
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "查询结果为空")
    })
    @ApiOperation("查询试卷的题目")
    @GetMapping("/topic/get")
    public Result getTopic(@RequestParam(value = "Id") String id,
                           @RequestParam("PageNow") @Parameter(example = "1") Integer pageNow,
                           @RequestParam("PageSize") @Parameter(example = "5") Integer pageSize) {
        return adminService.getTopic(id, pageNow, pageSize);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("增加指定试卷的题目")
    @PostMapping("/topic/save")
    public Result saveTopic(@RequestBody TopicSaveDTO topicSaveDTO) {
        return adminService.saveTopic(topicSaveDTO);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("修改指定试卷的题目")
    @PutMapping("/topic/update")
    public Result updateTopic(@RequestBody TopicUpdateDTO topicUpdateDTO) {
        return adminService.updateTopic(topicUpdateDTO);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("删除指定试卷的题目")
    @DeleteMapping("/topic/remove")
    public Result removeTopic(@RequestBody TopicRemoveDTO topicRemoveDTO) {
        try {
            return adminService.removeTopic(topicRemoveDTO);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Result().result500("删除失败", "admin/topic/remove");
        }
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "查询结果为空")
    })
    @ApiOperation("查询指定试卷得分排行榜")
    @GetMapping("/ranking/get")
    public Result getRanking(@RequestParam(value = "Id") String id,
                             @RequestParam("PageNow") @Parameter(example = "1") Integer pageNow,
                             @RequestParam("PageSize") @Parameter(example = "5") Integer pageSize) {
        return adminService.getRanking(id, pageNow, pageSize);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("增加用户做题记录")
    @PostMapping("/ranking/save")
    public Result saveRanking(@RequestBody RankingSaveDTO rankingSaveDTO) {
        return adminService.saveRanking(rankingSaveDTO);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("修改指定试卷的题目")
    @PutMapping("/ranking/update")
    public Result updateRanking(@RequestBody RankingUpdateDTO rankingUpdateDTO) {
        return adminService.updateRanking(rankingUpdateDTO);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("删除指定试卷的排行记录")
    @DeleteMapping("/ranking/remove")
    public Result removeRanking(@RequestBody List<String> ids) {
        try {
            return adminService.removeRanking(ids);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Result().result500("删除失败", "admin/ranking/remove");
        }
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 404, message = "查询结果为空")
    })
    @ApiOperation("查询文章类型")
    @GetMapping("/type/get")
    public Result getType(@RequestParam("PageNow") @Parameter(example = "1") Integer pageNow,
                          @RequestParam("PageSize") @Parameter(example = "5") Integer pageSize) {
        return adminService.getType(pageNow, pageSize);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("增加文章类型")
    @PostMapping("/type/save")
    public Result saveType(@RequestBody LabelSaveDTO labelSaveDTO) {
        return adminService.saveType(labelSaveDTO);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("修改文章类型")
    @PutMapping("/type/update")
    public Result updateType(@RequestBody LabelUpdateDTO labelUpdateDTO) {
        return adminService.updateType(labelUpdateDTO);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "成功"),
        @ApiResponse(code = 500, message = "失败")
    })
    @ApiOperation("删除文章类型")
    @DeleteMapping("/type/remove")
    public Result removeType(@RequestBody List<String> ids) {
        try {
            return adminService.removeType(ids);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Result().result500("删除失败", "admin/type/remove");
        }
    }
}
