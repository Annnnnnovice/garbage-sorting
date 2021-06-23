package com.zjz.code.entity.vo;

import java.util.List;

/**
 * @author zjz
 * @description
 * @date 2021-06-10 18:04
 */
public class GameVO {

    private String paperId;

    private List<TopicVO> topicVOList;

    public GameVO() {
    }

    public GameVO(String paperId, List<TopicVO> topicVOList) {
        this.paperId = paperId;
        this.topicVOList = topicVOList;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public List<TopicVO> getTopicVOList() {
        return topicVOList;
    }

    public void setTopicVOList(List<TopicVO> topicVOList) {
        this.topicVOList = topicVOList;
    }

    @Override
    public String toString() {
        return "GameVO{" +
            "paperId='" + paperId + '\'' +
            ", topicVOList=" + topicVOList +
            '}';
    }
}
