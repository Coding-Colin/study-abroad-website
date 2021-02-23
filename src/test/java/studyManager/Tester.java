package studyManager;

import java.util.List;

import com.study.manager.bean.User;
import com.study.manager.mapper.UserMapper;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Tester {


    @Test
    public void testUSerMapper() {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("spring-dao.xml");
        UserMapper userMapper = ctx.getBean("userMapper", UserMapper.class);
        List<User> user = userMapper.getAll();
        for (int i=0;i<user.size();i++){
            System.out.println(user.get(i));
        }

        ctx.close();
    }

}



