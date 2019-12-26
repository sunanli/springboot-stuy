package com.tangwh.pojo;

//import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private Integer id;

    private String name;

    private String address;

    //格式化
    //@JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
}
