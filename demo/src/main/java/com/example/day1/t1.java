package com.example.day1;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author:lh
 */
public class t1 {
    public static void main(String[] args) {

        //
//        t1();
        //
//        t2();
        //分组
        t3();
    }

    public static void t1(){
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world hello", "hello welcome");
        List<String> collect = list.stream().map(l -> l.split(" ")).flatMap(s -> Arrays.stream(s)).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void t2(){
        List<String> list1 = Arrays.asList("hello", "hi", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu");

        List<String> collect = list1.stream().flatMap(l1 -> list2.stream().map(l2 -> l1 +": "+ l2)).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    public static void t3(){
        Student s1 = new Student("zhangsan", 100, 20);
        Student s2 = new Student("lisi", 3, 23);
        Student s3= new Student("wangwu", 1, 45);
        Student s4 = new Student("zhangsan", 82, 23);
        Student s5 = new Student("zhangsan", 44, 44);
        List<Student> students = Arrays.asList(s1, s2, s3, s4, s5);
        //按照姓名分组
//        Map<String, List<Student>> listMap = students.stream().collect(Collectors.groupingBy(Student::getName));
//        System.out.println(listMap);
        //按照分数分组
        Map<Integer, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getScore));




    }
}
