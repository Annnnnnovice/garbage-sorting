package com.zjz.code.service;

import com.zjz.code.entity.dto.GameDTO;
import com.zjz.code.utils.Result;

/**
 * @author zjz
 * @description
 * @date 2021-06-06 15:50
 */
public interface GameService {

    /**
     * 获得游戏的题目
     * @return Result类
     */
    Result getGame();

    /**
     * 保存游戏记录
     * @param gameDTO 游戏记录的数据传输类
     * @return Result类
     */
    Result saveGameRecords(GameDTO gameDTO);

}
