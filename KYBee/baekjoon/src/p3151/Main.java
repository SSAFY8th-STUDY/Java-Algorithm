package p3151;

import java.util.*;
import java.io.*;

public class Main {

    static int N;
    static Student[] people;
    static long answer;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("src/p3151/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        people = new Student[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            people[i] = new Student(Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(people);

        for (int current = 0; current < N; current++) {

            for (int front = current + 1; front < N; front++) {
                long teamAbility = -1 * (people[current].ability + people[front].ability);

                int cnt = upperBound(front + 1, N, teamAbility) - lowerBound(front + 1, N, teamAbility);
                answer += cnt;
            }
        }
        System.out.println(answer);
    }

    public static int lowerBound(int left, int right, long target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (target <= people[mid].ability) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static int upperBound(int left, int right, long target) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (target < people[mid].ability) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}

class Student implements Comparable<Student> {
    long ability;

    Student(long ability) {
        this.ability = ability;
    }

    @Override
    public int compareTo(Student st) {
        return Long.compare(ability, st.ability);
    }
}