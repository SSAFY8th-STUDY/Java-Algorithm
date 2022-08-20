package hw.p17135;

import java.io.*;
import java.util.*;

public class Main_BJ_17135_캐슬디펜스_김영빈 {
    static final int G = 3;
    static int N, M, D;
    static int eCount, answer;
    static People[] Goongsoo;
    static List<People> enemies;

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src/hw/p17135/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        enemies = new LinkedList<>();
        Goongsoo = new People[G];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                if (Integer.parseInt(st.nextToken()) == 1) {
                    enemies.add(new People(N - r, c, ++eCount));
                }
            }
        }

        Collections.sort(enemies);
        fight(0, 0);
        System.out.println(answer);
    }

    public static void goFight(People[] enemies) {
        int total = 0;
        int enemySize = enemies.length;
        int enemyAlive = enemySize;

        while (enemyAlive > 0) {
            PriorityQueue<Integer[]>[] pq = new PriorityQueue[3];
            for (int i = 0; i < 3; i++) {
                pq[i] = new PriorityQueue<>((a, b) -> {
                    int comp = a[0] - b[0];
                    return comp == 0 ? a[1] - b[1] : comp;
                });
            }

            for (int i = 0; i < G; i++) {
                int idx = 0;
                while (idx < enemySize) {
                    if (enemies[idx].alive && Goongsoo[i].attackable(enemies[idx], D)) {
                        People target = enemies[idx];
                        pq[i].add(new Integer[] {Goongsoo[i].getDistance(target), target.c, idx});
                    }
                    idx++;
                }
            }

            // 한 마리씩 죽임
            for (int i = 0; i < G; i++) {
                if (pq[i].isEmpty()) continue;
                Integer[] target = pq[i].peek();
                if (enemies[target[2]].alive) {
                    enemies[target[2]].alive = false;
                    enemyAlive--; total++;
                }
            }

            // 한칸씩 앞으로 이동
            for (int i = 0; i < enemySize; i++) {
                People target = enemies[i];
                if (target.alive) {
                    if (target.r == 1) {
                        target.alive = false;
                        enemyAlive--;
                    } else target.r -= 1;
                }
            }
        }

        answer = Math.max(answer, total);
        for (int i = 0; i < enemySize; i++) {
            enemies[i].reset();
        }
    }

    public static void fight(int idx, int start) {
        if (idx == G) {
            // 궁수의 자리를 뽑음
            People[] e = new People[eCount];
            goFight(enemies.toArray(e));
            return;
        }

        for (int i = start ; i < M ; i++) {
            Goongsoo[idx] = new People(0, i, 0);
            fight(idx + 1, i + 1);
        }
    }
}

class People implements Comparable<People> {
    int r, c;
    int newR, newC;
    int id;
    boolean alive;

    People(int r, int c, int id) {
        this.r = r;
        this.c = c;
        this.id = id;
        this.newR = r;
        this.newC = c;
        alive = true;
    }

    @Override
    public int compareTo(People p) {
        int comp = Integer.compare(r, p.r);
        return comp == 0 ? Integer.compare(c, p.c) : comp;
    }

    public int getDistance(People p) {
        return Math.abs(r - p.r) + Math.abs(c - p.c);
    }

    public Boolean attackable(People p, int D) {
        if (!alive) return false;
        return getDistance(p) <= D;
    }

    public void reset() {
        this.r = this.newR;
        this.c = this.newC;
        this.alive = true;
    }
}