package com.bigdata.redis;

import com.oracle.tools.packager.mac.MacAppBundler;
import redis.clients.jedis.Jedis;

import java.util.*;

public class RedisTest {
    private static Jedis jedis =new Jedis("localhost");
    public static  void main(String args[]){

        System.out.println(jedis.ping());
        stringTypeTest();
        hashTypeTest();
        listTypeTest();
        setTypeTest();
        listAllKey();

    }

    private static void listAllKey() {
        Set<String> set = jedis.keys("*");

        for(Iterator<String> it = set.iterator();it.hasNext();) {
            String key = it.next();
            System.out.println("List of stored keys:: "+key);
        }
    }

    private static void hashTypeTest() {
        jedis.hset("car:3","name","GreatWall");
        Map map = new HashMap<String,String>();
        map.put("color","white");
        map.put("price","10000");
        jedis.hmset("car:3",map);

        Map allMap= jedis.hgetAll("car:3");
        for(Iterator<String> it = allMap.keySet().iterator();it.hasNext();){
           String key= it.next();
           System.out.println(allMap.get(key));
        }
    }

    private static void stringTypeTest() {
        jedis.set("tutorial-name", "Redis tutorial");
        // Get the stored data and print it
        System.out.println("Stored stringType in redis:: "+ jedis.get("tutorial-name"));

    }

    private static void listTypeTest() {
        //store data in redis list
        jedis.lpush("tutorial-list", "Redis");
        jedis.lpush("tutorial-list", "Mongodb");
        jedis.lpush("tutorial-list", "Mysql");
        // Get the stored data and print it
        List<String> list = jedis.lrange("tutorial-list", 0 ,5);

        for(int i = 0; i<list.size(); i++) {
            System.out.println("Stored listType in redis:: "+list.get(i));
        }
    }

    private static void setTypeTest() {
        jedis.sadd("seta","a","b","c","d");
        jedis.sadd("setb","c","d","e","f");
        Set<String> set = jedis.smembers("seta");
        for(Iterator<String> it = set.iterator();it.hasNext();) {
            String key = it.next();
            System.out.println("Stored setType in redis :: "+key);
        }


        Set<String> diffSet =  jedis.sdiff("seta","setb");//集合a和集合b 取差
        for(Iterator<String> it = diffSet.iterator();it.hasNext();) {
            String diffString = it.next();
            System.out.println("diff of :: "+diffString);
        }

        Set<String> uinonSet =  jedis.sunion("seta","setb");//集合a和集合b 取并集
        for(Iterator<String> it = uinonSet.iterator();it.hasNext();) {
            String unionString = it.next();
            System.out.println("union of :: "+unionString);
        }

        Set<String> interSet =  jedis.sinter("seta","setb");//集合a和集合b 取交集
        for(Iterator<String> it = interSet.iterator();it.hasNext();) {
            String interString = it.next();
            System.out.println("inter of :: "+interString);
        }
    }
}
