package com.example.day1;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
//        t3();
        t5();
    }

    public static void t1() {
        List<String> list = Arrays.asList("hello welcome", "world hello", "hello world hello", "hello welcome");
        List<String> collect = list.stream().map(l -> l.split(" ")).flatMap(s -> Arrays.stream(s)).distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    public static void t2() {
        List<String> list1 = Arrays.asList("hello", "hi", "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu");

        List<String> collect = list1.stream().flatMap(l1 -> list2.stream().map(l2 -> l1 + ": " + l2)).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    public static void t3() {
//        Student s1 = new Student("zhangsan", 100, 20);
//        Student s2 = new Student("lisi", 3, 23);
//        Student s3= new Student("wangwu", 1, 45);
//        Student s4 = new Student("zhangsan", 82, 23);
//        Student s5 = new Student("zhangsan", 44, 44);
//        List<Student> students = Arrays.asList(s1, s2, s3, s4, s5);
//        //按照姓名分组
////        Map<String, List<Student>> listMap = students.stream().collect(Collectors.groupingBy(Student::getName));
////        System.out.println(listMap);
//        //按照分数分组
//        Map<Integer, List<Student>> collect = students.stream().collect(Collectors.groupingBy(Student::getScore));


        List<Object> objects = Arrays.asList(null, null);

        System.out.println("");


    }

    public static void t4() {

        Ste ste1 = new Ste("聚信立运营商", "2017-04", "2000", "2000");
        Ste ste2 = new Ste("91贷款1", "2017-03", "210", "3000");
        Ste ste5 = new Ste("91贷款1", "2017-03", "210", "3000");
        Ste ste3 = new Ste("91贷款1", "2017-01", "3100", "2300");
        Ste ste4 = new Ste("聚信立运营商1", "2017-03", "3100", "2300");

        List<Ste> stes = Arrays.asList(ste1, ste2, ste3, ste4, ste5);

//        Map<String, List<Ste>> collect = stes.stream().collect(Collectors.groupingBy(Ste::getMonth));


        List<Ste> collect = stes.stream().sorted(Comparator.comparing(Ste::getMonth)).collect(Collectors.toList());

        Ste ste = collect.stream().findFirst().get();
        List<String> collect2 = collect.stream().map(s -> s.getMonth()).distinct().collect(Collectors.toList());


        String month = ste.getMonth();
        Map<String, List<Ste>> collect1 = collect.stream().collect(Collectors.groupingBy(Ste::getMonth));


        Map<String, List<Ste>> test = getTest(collect2, collect1);


//        Map<String, List<Ste>> collect1 = collect.stream().collect(Collectors.groupingBy(Ste::getMonth).);
//        Map<String, List<Ste>> collect = stes.stream()
//                .map(s->{
//
//                })
//                .collect(Collectors.groupingBy(Ste::getMonth));

        System.out.println();
    }

    public static Map<String, List<Ste>> getTest(List<String> mounths, Map<String, List<Ste>> map) {

        String lastMonth = mounths.get(mounths.size() - 1);


        //最后月份的数据  向前边的月份添加数据
        List<Ste> steList = map.get(lastMonth);

//
//        List<Stream<Stream<Ste>>> collect = mounths.stream().filter(a -> !lastMonth.equals(a)).map(s -> {
//            List<Ste> stes = map.get(lastMonth);
//            return map.get(s).stream().map(b -> {
//                return stes.stream().filter(c -> !c.getName().equals(b.getName())).collect(Collectors.)
//            })
//        }).collect(Collectors.toList());


//        mounths.stream().filter(s->{})


        //last的日期
//        map.forEach((k,v)->{
//
//            List<Ste> collect = v.stream().map(s -> s).collect(Collectors.toList());
//            if (k.equals(lastMonth)) {
//                collect.stream().map(s->{
//                    if (!s.getMonth().equals(lastMonth)) {
//                        List<Ste> stes = map.get(s.getMonth());
//
//
//                    }
//                })
//            }
//
//
//
//        });

        return null;

    }


    public static void t5() {

        Ste ste3 = new Ste("产品a", "2017-01", "3100", "2300");
        Ste ste2 = new Ste("产品b", "2017-03", "210", "3000");
        Ste ste5 = new Ste("产品c", "2017-03", "210", "3000");
        Ste ste4 = new Ste("产品a", "2017-03", "3100", "2300");
        Ste ste1 = new Ste("产品f", "2017-04", "2000", "2000");
        Ste ste6 = new Ste("产品a", "2017-04", "2000", "2000");
        Ste ste7 = new Ste("产品b", "2017-04", "2000", "2000");
        Ste ste8 = new Ste("产品c", "2017-04", "2000", "2000");


        //list数据
        List<Ste> stes = Arrays.asList(ste1, ste2, ste3, ste4, ste5, ste6, ste7, ste8);


        Map<String, List<Ste>> groupByMonth = stes.stream().collect(Collectors.groupingBy(Ste::getMonth));


        List<String> months = stes.stream().map(s -> s.getMonth()).distinct().collect(Collectors.toList());

        Arrays.sort(months.toArray());

        String lastMonth = months.get(0);

        List<Ste> lastMonthStes = groupByMonth.get(lastMonth);

        List<Ste> all = new ArrayList<>();
        for (int i = 0; i < months.size(); i++) {
            String month = months.get(i);
            List<Ste> steList = groupByMonth.get(month);
            if (lastMonthStes.size() > steList.size()) {
                for (Ste ste : steList) {
                    for (Ste lastMonthSte : lastMonthStes) {
                        if (!ste.getName().equals(lastMonthSte.getName())
                                && !ste.getMonth().equals(lastMonthSte.getMonth())) {
                            Ste stesss = new Ste(lastMonthSte.getName(), ste.getMonth(), "0", "0");
                            if (ste.getName().equals(lastMonthSte.getName())) {
                                continue;
                            } else {
                                all.add(stesss);
                            }
                        }
                    }
                }
            }
        }


//        List<Ste> collect1 = stes.stream().sorted(Comparator.comparing(Ste::getMonth)).map(ste -> {
//            if (!lastMonth.equals(ste.getMonth())) {
//                List<Ste> lastMonths = groupByMonth.get(lastMonth);
//                List<Ste> collect = lastMonths.stream().filter(s -> !s.getName().equals(ste.getName())).collect(Collectors.toList());
//                System.out.println(collect);
//                if (collect.isEmpty()) {
////                    Ste newSte = collect.get(0);
//                    Ste ste6 = new Ste(lastMonths.get(0).getName(), ste.getMonth(), "0", "0");
//                    return ste6;
//                }
//                return null;
//            } else {
//                return ste;
//            }
//        }).collect(Collectors.toList());


//        List<Ste> collect = stes.stream().sorted(Comparator.comparing(Ste::getMonth)).flatMap(ste -> {
//
//            if (!lastMonth.equals(ste.getMonth())) {
//
//                List<Ste> lastMonths = groupByMonth.get(lastMonth);
//
//                lastMonths.stream().filter(last->{
//                  return ste.getName().equals(last.getName());
//                }).map(ss->{
//                    return new Ste(ss.getName(),ste.getMonth(),"0","0");
//                }).collect(Collectors.toList());
//            }
//
//
//            return Stream.of(ste);
//        }).collect(Collectors.toList());


        System.out.println();
    }

    /**
     * 计算List<String>时间集合中最大日期
     *
     * @param ins 时间集合(格式：yyyy-MM-dd)
     * @return 最大时间
     */
    public static String getMaxDate(List<String> ins) {

        String a = null;
        for (int i = 0; i < ins.size(); i++) {
            if (i == 0) {
                a = ins.get(i);
            }
            if (ins.get(i).compareTo(a) > 0) {
                a = ins.get(i);
            }
        }
        return a;
    }

}
