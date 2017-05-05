package com.example.day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * ss
 *
 * @author:lh
 */
public class test2 {

    public static void main(String[] args) throws IOException {
        t1();

    }

    /**
     * 4.在歌星大奖赛中，有10个评委为参赛的选手打分，分数为1-100分。
     * 选手最后得分为：去掉一个最高分和一个最低分后其余8个分数的平均值。
     * 请编写一个程序实现。
     */
    public static void t1() throws IOException {
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        int i;
        List<Integer> ll = new ArrayList<>();
        for (i = 0; i < 10; i++) {
            System.out.print("请输入第" + (i + 1) + "个分数:");
            ll.add(Integer.parseInt(buf.readLine()));
        }
        int sum = ll.stream().reduce((l1, l2) -> l1 + l2).get();
        int max = ll.stream().mapToInt(l -> l).max().getAsInt();
        int min = ll.stream().mapToInt(l -> l).min().getAsInt();
        System.out.println("去掉一个最高分：" + max
                + "\n去掉一个最低分：" + min
                + "\n最后得分：" + (float) (Math.round(((sum - max - min) / 8) * 100)) / 100);
    }

}
