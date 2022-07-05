import com.kuang.dao.BookMapper;
import com.kuang.pojo.Books;
import com.kuang.service.BookServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookServiceImpl mapper = applicationContext.getBean("BookServiceImpl", BookServiceImpl.class);

        List<Books> list = mapper.queryAllBook();

        for (Books books : list) {
            System.out.println(books);
        }
    }
}
