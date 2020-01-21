package com.tangwh.controller;

import com.tangwh.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@Api(tags = "用户数据接口")
public class UserController {


    @ApiOperation(value = "查询用户", notes = "根据用户id查询用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99")
    @GetMapping("/user ")
    public User getUserById(Integer id) {
        User user = new User();
        user.setId(1);
        user.setUsername("唐同学");
        user.setAddress("陕西西安");
        return user;
    }


    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable Integer id) {
        System.out.println(id);

    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99"),
            @ApiImplicitParam(name = "username", value = "用户名", required = true, defaultValue = "small-howe")
    })
    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户名")
    @PutMapping("/user")
//    @ApiIgnore  屏蔽接口显示
    public User updateUsernameById(String username, Integer id) {

        User user = new User();

        user.setId(id);
        user.setUsername(username);
        return user;
    }


    @ApiOperation(value = "添加用户", notes = "添加用户接口")
    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return user;
    }

}
