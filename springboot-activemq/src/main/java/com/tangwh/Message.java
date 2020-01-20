package com.tangwh;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Message implements Serializable {
    // 消息的主体
    private String content;

    // 消息的发送日期
    private Date sendDate;

}
