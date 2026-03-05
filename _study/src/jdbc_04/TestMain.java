package jdbc_04;

import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
//        int h = 4;
//        int w = 2;
//        int a = 999999999;
//        int b = 0;

        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int w = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.printf("(%9d, %9d)", a, b);
                if (j != w - 1) System.out.print(" | ");
            }

            if (i != h - 1) {
                int cnt = (22 * w) + (3 * (w - 1));

                System.out.print("\n");

                for (int k = 0; k < cnt; k++) {
                    System.out.print("=");
                }

                System.out.print("\n");
            }
        }

        sc.close();
    }
}
