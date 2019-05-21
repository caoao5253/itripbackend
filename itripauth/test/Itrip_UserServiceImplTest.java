import cn.itrip.auth.service.user.Itrip_UserService;
import cn.itrip.beans.pojo.ItripUser;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

public class Itrip_UserServiceImplTest {

    @Test
    public void insertItripUser() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
        Itrip_UserService userService = (Itrip_UserService)ctx.getBean("Itrip_UserService");
        ItripUser user = new ItripUser();
        user.setUsercode("caoao5253@aliyun.com");
        user.setUsername("小明");
        try {
            userService.insertItripUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void dd(){
        String [] list=new String[]{"www","homolo","com"};
        StringBuffer str=new StringBuffer();
        for(int i=0;i<list.length;i++){
            str.append(list[i]+".");

        }
        System.out.print(str.substring(0,str.lastIndexOf(".")).toString());
    }
}