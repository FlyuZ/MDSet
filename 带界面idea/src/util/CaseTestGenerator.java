//package util;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.concurrent.ThreadLocalRandom;
//
///**
// * @author Saraiva - 2017
// * MIT License
// */
//
//public class CaseTestGenerator {
//    /**
//     * @variables size = 节点数
//     * dense = Generate dense graph ( V^2/2 Edges), if false generates esparse graph稠密图或稀疏图
//     * weight = 是否生成权重
//     * selfLoop = True if selfLoop is allowed
//     * weightLowerLimit = Lower bound of random weight generated
//     * weightUperLimit = Upper bound of random weight generated
//     * separator = 文件分割符
//     * file = 文件名，  默认为txt
//     */
//    public static void main(String args[]) {
//        int size = 20;
//        boolean dense = false;
//        boolean weight = false;
//        boolean selfLoop = false;
//        int weightLowerLimit = 1;
//        int weightUperLimit = 99;
//        char separator = ' ';
//        String filename = "Data";
//        generateCaseTest(size, weightLowerLimit, weightUperLimit, dense, weight, separator, filename, selfLoop);
//
//    }
//
//    public static void generateCaseTest(int size, int weightLowerLimit, int weightUperLimit,
//                                        boolean dense, boolean weight, char separator, String filename, boolean selfLoop) {
//
//        int column1[], column2[];
//
//        column1 = new int[size];
//        column2 = new int[size];
//
//        for (int i = 0; i < size; i++) {
//            column1[i] = i;
//            column2[i] = i + 1;
//        }
//
//        PrintWriter writer;
//        try {
//            writer = new PrintWriter(filename + ".txt", "UTF-8");
//            writer.println(size);
//            if (dense) {
//                for (int i = 0; i < size; i++) {
//                    for (int j = i + 1; j < size - 1; j++) {
//                        int pos1 = ThreadLocalRandom.current().nextInt(0, size - 1);
//                        int pos2 = ThreadLocalRandom.current().nextInt(0, size - 1);
//
//                        while (column1[pos1] == column2[pos2] && !selfLoop) {
//                            pos1 = ThreadLocalRandom.current().nextInt(0, size - 1);
//                            pos2 = ThreadLocalRandom.current().nextInt(0, size - 1);
//                        }
//                        if (weight)
//                            writer.println(column1[pos1] + "" + separator + "" + column2[pos2] + "" + separator
//                                    + "" + ThreadLocalRandom.current().nextInt(weightLowerLimit, weightUperLimit + 1));
//                        else {
//                            writer.println(column1[pos1] + "" + separator + "" + column2[pos2]);
//                        }
//                    }
//                }
//            } else {
//                for (int j = 0; j < size / 4; j++)
//                    for (int i = j; i < size; i++) {
//                        int pos1 = ThreadLocalRandom.current().nextInt(0, size - 1);
//                        int pos2 = ThreadLocalRandom.current().nextInt(0, size - 1);
//                        while (column1[pos1] == column2[pos2] && !selfLoop) {
//                            pos1 = ThreadLocalRandom.current().nextInt(0, size - 1);
//                            pos2 = ThreadLocalRandom.current().nextInt(0, size - 1);
//                        }
//                        if (weight)
//                            writer.println(column1[pos1] + "" + separator + "" + column2[pos2] + "" + separator
//                                    + "" + ThreadLocalRandom.current().nextInt(weightLowerLimit, weightUperLimit + 1));
//                        else
//                            writer.println(column1[pos1] + "" + separator + "" + column2[pos2]);
//                    }
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("数据生成成功");
//    }
//}
