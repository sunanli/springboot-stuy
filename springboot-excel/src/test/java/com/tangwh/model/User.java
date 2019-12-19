package com.tangwh.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Tangweihao(唐维豪)
 * @date 2019/12/9
 * @time 下午2:07
 * @discription 写入Excel模型对象
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private String name;

    private String password;

    private Integer age;
}
