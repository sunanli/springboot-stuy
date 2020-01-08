package com.tangwh;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tangwh.pojo.Book;
import com.tangwh.service.HelloService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootTestApplicationTests {

    /***
     * 测试Service
     */

    @Autowired
    HelloService helloService;

    /**
     * Controller测试
     */
    @Autowired
    WebApplicationContext wac;
    //MVC需要初始化
    MockMvc mockMvc;

    @Test
    public void contextLoads() {
        System.out.println();
        helloService.sayHello("唐维豪");
    }

    //前置增强  初始化MVC
    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }

    /**
     * 测试Hello接口 传递的值 是Key -value 形式
     * @throws Exception
     */

    @Test
    public void testControllerHello() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                // 测试接口
                MockMvcRequestBuilders.get("/hello")
                        //Key -- value 形式传递参数
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                         //参数固定值 key             value
                        .param("name", "javaBoy"))
                // 期待你的状态是ok
                .andExpect(MockMvcResultMatchers.status().isOk())
                // 并且把结果显示出来
                .andDo(MockMvcResultHandlers.print())
                // 有返回值
                .andReturn();
        // 将结果以String打印出来
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    /**
     * 测试接口 /book
     * @throws Exception
     */

    @Test
    public void testControllerBook() throws Exception {

        Book book = new Book();
        book.setId(99);
        book.setName("西游记");
        book.setAuthor("吴承恩");

        // 将book 对象转化成字符串
        String s = new ObjectMapper().writeValueAsString(book);
        System.out.println("book对象原来的样子:"+book.toString());
        System.out.println("book现在的样子:"+s);


        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(s))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();


        System.out.println("book后来的样子:"+mvcResult.getResponse().getContentAsString());


    }

}
