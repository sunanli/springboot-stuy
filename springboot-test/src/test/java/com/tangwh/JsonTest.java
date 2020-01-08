package com.tangwh;

import com.tangwh.pojo.Book;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@RunWith(SpringRunner.class)
//@SpringBootTest
@org.springframework.boot.test.autoconfigure.json.JsonTest
public class JsonTest {

    // 需要mavan 打包的时候不要忽略掉book.json文件 去pom.xml文件修改

    @Autowired
    JacksonTester<Book> jacksonTester;

    // 序列化
    @Test
    public void contextLoads() throws IOException {
        Book book = new Book();
        book.setId(99);
        book.setName("红楼梦");
        book.setAuthor("曹雪芹");
        // 序列化后是否和我的文件的jsOn是否相等
        Assertions.assertThat(jacksonTester.write(book)).isEqualToJson("book.json");
        // 看是否有没有一个key 是name
        Assertions.assertThat(jacksonTester.write(book)).hasJsonPathStringValue("@.name");

        // 看某个值的key 是否对应的某个value
        Assertions.assertThat(jacksonTester.write(book)).extractingJsonPathStringValue("@.author").isEqualTo("曹雪芹");
    }

    // 反序列化
    @Test
    public void test() throws IOException {

        String content = "{\"id\": 99, \"name\":\"红楼梦\", \"author\":\"曹雪芹\"}";

        Assertions.assertThat(jacksonTester.parseObject(content).getName()).isEqualTo("红楼梦");
    }
}
