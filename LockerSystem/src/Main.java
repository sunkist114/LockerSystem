import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("===== CSE 사물함 시스템 =====");
            System.out.println("1. 사물함 신청");
            System.out.println("2. 관리자 모드");
            System.out.println("0. 종료");
            System.out.print("선택: ");
            int mode = sc.nextInt();
            sc.nextLine();

            switch (mode) {
                case 1:
                    StudentClient.run();
                    break;
                case 2:
                    AdminClient.run();
                    break;
                case 0:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}