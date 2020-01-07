package com.tangwh.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

/**
 * @author Tangweihao
 * @version 1.0
 * @date 2020/1/7 11:27
 */
@RestController
public class HelloController {

    @PostMapping("/info")
    public String getInfo(@RequestParam("name")  String info){

        System.out.println(info);
        return info;
    }
    /**
     *    @GetMapping("/data/reqparam/{id}")
     *     // @RequestParam 可以设置参数是否为必须传，以及默认值
     *     // URL:xxxx/shop/data/reqparam/1?id=2
     *     public String dataTest(@RequestParam(value="id",defaultValue = "2",required = true) String id) {
     *
     *         return id;
     *     }
     *
     *     @GetMapping("/data/pathparam/{param}")
     *     // 获取参数同@RequestParam
     *     // URL:xxxx/shop/data/pathparam/1?param=2
     *     public String dateTest3(@PathParam(value="param") String param) {
     *         return param;
     *     }
     *
     *     @GetMapping("/data/pathvar/{name}")
     *     // URL:xxxx/shop/data/pathvar/1
     *     public String dateTest2(@PathVariable(value="name",required = true) String name) {
     *         return name;
     *     }
     *
     *2.@RequestBody获取参数
     *
     * 参考文章：https://juejin.im/post/5b5efff0e51d45198469acea
     *
     * 2.1使用@RequestBody的时候，api的接口大多是post方式，这时候处理的headers中content-type，不是默认的application/x-www-form-urlencoded请求方式，而是application/json或者是application/xml等请求方式。
     *
     * 当使用application/x-www-form-urlencoded时：
     * ————————————————
     *
     *
     *
     *
     */
}
