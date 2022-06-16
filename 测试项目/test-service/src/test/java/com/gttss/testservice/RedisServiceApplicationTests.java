package com.gttss.testservice;

import com.gttss.entity.PageData;
import com.gttss.testservice.dao.test.TestDao;
import com.gttss.testservice.util.BpDeep;
import com.gttss.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class RedisServiceApplicationTests {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    TestDao testDao;

    @Test
    void contextLoads() {
    }

//    @Test
//    void testf3(){
//        RedisUtil redisUtil=new RedisUtil();
//        redisUtil.setRedisTemplate(redisTemplate);
//        redisUtil.set("name5","555");
//    }
//    @Test
//    void testf4(){
//        RedisUtil redisUtil=new RedisUtil();
//        redisUtil.setRedisTemplate(redisTemplate);
//        redisUtil.del("name5", RedisCacheKey.FIRSTBOOK.toString());
//    }

    @Test
    void testf4(){
        RedisUtil redisUtil=new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        BoundValueOperations keyTest1 = redisTemplate.boundValueOps("keyTest");
        BoundValueOperations keyTest2 = redisTemplate.boundValueOps("keyTest2".toString());
        stringRedisTemplate.opsForValue().set("keyTest3", "valueTest333");
        String keyTest = (String) keyTest1.get();
//        String keyTest = redisUtil.get("keyTest").toString();
        System.out.println(keyTest);
    }

    @Test
    void testTrain() throws IOException, ClassNotFoundException {
        List<PageData> list = testDao.findList(new PageData());
        String path="D:/test/ai.mod";
        BpDeep bpDeep = null;
        try {
            bpDeep = new BpDeep(path);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            bpDeep=new BpDeep(new int[]{3,9,9,81,81,81,9,9,6}, 0.15, 0.8);
            System.out.println("exception");
        }

        System.out.println("start--------");

        double [][] data= new double[list.size()][3];
        double [][] taget= new double[list.size()][6];
        String showTime="";
        int idx=0;
        for(PageData pd:list) {
            showTime=pd.getString("showTime");
            data[idx][0]=Integer.parseInt(showTime.substring(0,4));
            data[idx][1]=Integer.parseInt(showTime.substring(4,6));
            data[idx][2]=Integer.parseInt(showTime.substring(6,8));
//            System.out.println(showTime.substring(0,4)+"-"+showTime.substring(4,6)+"-"+showTime.substring(6,8));
            taget[idx][0]=Integer.parseInt(pd.getString("red1"));
            taget[idx][1]=Integer.parseInt(pd.getString("red2"));
            taget[idx][2]=Integer.parseInt(pd.getString("red3"));
            taget[idx][3]=Integer.parseInt(pd.getString("red4"));
            taget[idx][4]=Integer.parseInt(pd.getString("red5"));
            taget[idx][5]=Integer.parseInt(pd.getString("blue"));
            idx++;
        }
        for(int i=0;i<list.size();i++) {
            bpDeep.train(data[i], taget[i]);
            bpDeep.display();
        }
        bpDeep.save(path);
    }

    @Test
    void testGetResult() throws IOException, ClassNotFoundException {
        String path="D:/test/ai.mod";
        BpDeep bpDeep = new BpDeep(path);
        double data[]=new double[]{2022,6,13};
        double[] result = bpDeep.computeOut(data);
        bpDeep.display();
        System.out.println(result[0]+"-"+result[1]+"-"+result[2]+"-"+result[3]+"-"+result[4]+"-"+result[5]);

    }

    @Test
    void display() throws IOException, ClassNotFoundException {
        String path="D:/test/ai.mod";
        BpDeep bpDeep = new BpDeep(path);
        bpDeep.display();
    }

}
