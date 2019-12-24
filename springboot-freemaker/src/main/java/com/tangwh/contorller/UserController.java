package com.tangwh.contorller;

import com.tangwh.pojo.User;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Controller
public class UserController {


    @GetMapping("/user")
    public String user(Model model) {

        Random random = new Random();

        List<User> users = new ArrayList<>();
        for (int i = 0; i <= 10; i++) {
                                                                             // 0 表示男 1 表示女  其他表示未知
            users.add(new User(i, "小明" + i, "西安" + i,random.nextInt(3)));
        }
        model.addAttribute("users", users);

        return "user";
    }
}
