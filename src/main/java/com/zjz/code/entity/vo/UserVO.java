package com.zjz.code.entity.vo;

/**
 * @author zjz
 * @description 用户信息的显示类
 * @date 2021-06-06 21:27
 */
public class UserVO {

    /**
     * 用户名
     */
    private String name;

    /**
     * 头像url
     */
    private String headUrl;

    public UserVO() {
    }

    public UserVO(String name, String headUrl) {
        this.name = name;
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    @Override
    public String toString() {
        return "UserVO{" +
            "name='" + name + '\'' +
            ", headUrl='" + headUrl + '\'' +
            '}';
    }
}
