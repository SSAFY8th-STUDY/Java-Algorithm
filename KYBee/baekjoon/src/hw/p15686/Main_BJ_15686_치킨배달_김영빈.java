package hw.p15686;

import java.util.*;
import java.io.*;

public class Main_BJ_15686_치킨배달_김영빈 {
    static int N, M;
    static List<Building> chickenStore = new ArrayList<>();
    static List<Building> home = new ArrayList<>();
    static Building[] output;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("src/hw/p15686/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int option = Integer.parseInt(st.nextToken());
                if (option == 1) {
                    home.add(new Building(i, j));
                } else if (option == 2) {
                    chickenStore.add(new Building(i, j));
                }
            }
        }

        output = new Building[M];
        combination(0, 0);

        System.out.println(answer);
    }

    public static void combination(int idx, int start) {
        if (idx == M) {
            int totalChickenDistance = 0;
            for (Building h : home) {
                int minDistance = Integer.MAX_VALUE;
                for (int i = 0; i < M; i++) {
                    minDistance = Math.min(minDistance, getChicken(h, output[i]));
                }
                totalChickenDistance += minDistance;
            }
            answer = Math.min(answer, totalChickenDistance);
            return;
        }

        for (int i = start; i < chickenStore.size(); i++) {
            output[idx] = chickenStore.get(i);
            combination(idx + 1, i + 1);
        }
    }

    public static int getChicken(Building b1, Building b2) {
        return Math.abs(b1.r - b2.r) + Math.abs(b1.c - b2.c);
    }
}

class Building {
    int r;
    int c;

    Building(int r, int c) {
        this.r = r;
        this.c = c;
    }
}