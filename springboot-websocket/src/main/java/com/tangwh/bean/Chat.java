package com.tangwh.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2019/12/23 10:40
 */
// 单聊
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    // 消息从哪来
    private String from;

    // 消息内容
    private String content;

    //消息去了哪
    private String to;


}
