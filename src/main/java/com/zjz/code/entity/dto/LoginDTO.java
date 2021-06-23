package com.zjz.code.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author zjz
 * @description 登录的数据传输类
 * @date 2021-06-06 19:07
 */
@ApiModel("登录的数据传输类")
public class LoginDTO {

    @ApiModelProperty(value = "账号", example = "admin")
    private String account;

    @ApiModelProperty(value = "密码", example = "admin")
    private String cipher;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    @Override
    public String toString() {
        return "LoginDTO{" +
            "account='" + account + '\'' +
            ", cipher='" + cipher + '\'' +
            '}';
    }
}
