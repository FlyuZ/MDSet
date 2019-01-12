package algorithm;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import graph.Graph;
import graph.Neighbor;
import graph.Node;

public class MDSet {
    private Graph graph;
    private Neighbor neighbor;
    private File file;
    private int size = 0;
    private int[] DetValue;
    private int minSetNum = 0x3f3f;
    private int[] MDSet;

    public MDSet(File file) {
        this.file = file;
        loadGraph();
        // 初始化数组
        neighbor = new Neighbor(size);
        DetValue = new int[size];
        MDSet = new int[size];
        int[][] g = graph.getGraph();
        for (int i = 0; i < size; i++) {
            DetValue[i] = 0;
            MDSet[i] = 0;
            for (int j = 0; j < i; j++) {
                if (g[i][j] == 1) {
                    neighbor.addEdge(i, j);
                }
            }
        }
        writeJson("before.json");
    }

    public void run() {
        long start_mds = System.currentTimeMillis();
        getUpperBound();
        DFS(0);
        long end_mds = System.currentTimeMillis();
        double seconds_mds = end_mds / 1000.0 - start_mds / 1000.0;
        System.out.println("运行时间为 " + seconds_mds + " seconds.");
        System.out.println("控制点个数" + minSetNum);
        for (int i = 0; i < size; i++) {
            System.out.println("第" + i + "个点取值为" + MDSet[i]);
        }
        writeJson("after.json");
    }

    private void writeJson(String fileName) {
        try {
            String path = this.getClass().getResource("/").getPath() + "/";
            PrintWriter out = new PrintWriter(path + fileName, "utf-8");
            //System.out.println("Graph.json");
            out.print("nodes = \'[");
            for (int i = 0; i < size; i++) {
                out.print("{ \"id\" : " + i + ",");
                out.print(" \"name\" : \"Node " + i + "\",");
                if (MDSet[i] == 0)
                    out.print(" \"category\" : " + "0}");
                else
                    out.print(" \"category\" : " + "1}");
                if (i != size - 1)
                    out.print(",");
            }
            out.println("]\';");
            out.print("links = \'[");
            int[][] g = graph.getGraph();
            int flag = 0;
            for (int i = 0; i < size; i++) {
                for (int j = i; j < size; j++) {
                    if (g[i][j] == 1) {
                        if (flag == 1)
                            out.print(",");
                        flag = 1;
                        out.print("{ \"source\" : " + i + ",");
                        out.print(" \"target\" : " + j + "}");
                    }
                }
            }
            out.println("]\';");
            out.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void loadGraph() {
        Scanner sc;
        try {
            sc = new Scanner(file);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (sc.hasNext()) {
            size = sc.nextInt();
        }
        graph = new Graph(size);
        while (sc.hasNext()) {
            if (sc.hasNextInt()) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                graph.addEdge(a, b);
            } else {
                sc.nextLine();
            }
        }
        sc.close();
    }

    // 获得精确约束条件
    private void getUpperBound() {
        // neighbor.sortNeighbor();
        // 获取度为0和1的情况
        for (int i = 0; i < size; i++) {
            if (neighbor.getNeighborPQ()[i].neighbor.size() > 1)
                continue;
            else if (neighbor.getNeighborPQ()[i].neighbor.size() == 0) {
                int degree0 = neighbor.getNeighborPQ()[i].getNodeNum();
                DetValue[degree0] = 1;
                neighbor.getNeighborPQ()[i].Assignment = 1;
            } else if (neighbor.getNeighborPQ()[i].neighbor.size() == 1) {
                //因为有前后顺序 所以可以保证  最后的结果是正确的
                int degree0 = neighbor.getNeighborPQ()[i].getNodeNum();
                int degree1 = neighbor.getNeighborPQ()[i].neighbor.get(0);
                DetValue[degree0] = 1;
                DetValue[degree1] = 1;
                neighbor.getNeighborPQ()[i].Assignment = 0;
                neighbor.getNeighborPQ()[degree1].Assignment = 1;
            }
        }
        //贪心部分
        for (Node n : neighbor.getNeighborPQ())
            System.out.println(n);
    }

    private void DFS(int begin) {
        int flag = 0;
        int[] tempSet = new int[size];
        int tempNum = 0;
        for (int i = 0; i < size; i++) {
            flag = 0;
            if (neighbor.getNeighborPQ()[i].Assignment == 1) {
                flag = 1;
                tempSet[i] = 1;
                tempNum++;
                if (minSetNum < tempNum) {
                    tempSet = null;
                    return;
                }
            } else if (neighbor.getNeighborPQ()[i].Assignment == 0) {// 当前取值为0
                tempSet[i] = 0;
                for (int index : neighbor.getNeighborPQ()[i].neighbor) { // 遍历它的亲戚
                    if (neighbor.getNeighborPQ()[index].Assignment == 1) {// 如果它的亲戚是0
                        flag = 1;
                    }
                }
            }
            if (flag == 0) {
                break;
            }
        }
        if (flag == 1 && minSetNum > tempNum) {
            for (int i = 0; i < size; i++) {
                MDSet[i] = tempSet[i];
            }
            minSetNum = tempNum;
        } else {
            tempSet = null;
        }
        // 开始搜索
        for (int now = begin; now < size; now++) {
            if (DetValue[neighbor.getNeighborPQ()[now].getNodeNum()] == 1)
                continue;
            neighbor.getNeighborPQ()[now].Assignment = 0;
            DFS(begin + 1);
            neighbor.getNeighborPQ()[now].Assignment = 1;
            DFS(begin + 1);
        }
        return;
    }
}
