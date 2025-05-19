import java.util.Scanner;
import java.util.List;

public class AdminClient
{
    private static final String ADMIN_PASSWORD = "cse2025";

    public static void run() {
        Scanner sc = new Scanner(System.in);
        LockerManager lockerManager = AppContext.lockerManager;

        System.out.print("관리자 비밀번호를 입력하세요: ");
        String inputPassword = sc.nextLine().trim();

        if (!ADMIN_PASSWORD.equals(inputPassword)) {
            System.out.println("비밀번호가 틀렸습니다. 프로그램을 종료합니다.");
            return;
        }

        System.out.println("====================================");
        System.out.println("       CSE 사물함 관리자 모드       ");
        System.out.println("====================================");

        while (true) {
            System.out.println("\n[ 관리자 메뉴 ]");
            System.out.println("1. 신청자 목록 보기");
            System.out.println("2. 사물함 배정 승인하기");
            System.out.println("3. 사물함 상태 확인");
            System.out.println("4. 특정 사물함 비우기");
            System.out.println("5. 전체 초기화");
            System.out.println("6. 신청 거절하기");
            System.out.println("0. 종료");
            System.out.print("메뉴 선택: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    List<Student> pending = lockerManager.getPendingRequests();
                    if (pending.isEmpty()) {
                        System.out.println("승인 대기 중인 신청자가 없습니다.");
                    } else {
                        System.out.println("\n[승인 대기 목록]");
                        for (Student s : pending) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 2:
                    System.out.print("승인할 학번을 입력하세요: ");
                    String approveID = sc.nextLine().trim();
                    lockerManager.approveRequest(approveID);
                    break;

                case 3:
                    lockerManager.printLockerStatus();
                    break;

                case 4:
                    System.out.print("비울 학번을 입력하세요: ");
                    String clearID = sc.nextLine().trim();
                    lockerManager.clearLocker(clearID);
                    break;

                case 5:
                    System.out.print("정말 초기화하시겠습니까? (yes/no): ");
                    String confirm = sc.nextLine().trim().toLowerCase();
                    if (confirm.equals("yes")) {
                        lockerManager.clearAllLockers();
                    } else {
                        System.out.println("초기화를 취소했습니다.");
                    }
                    break;

                case 6:
                    System.out.print("승인 거절할 학번을 입력하세요: ");
                    String rejectID = sc.nextLine().trim();
                    lockerManager.rejectRequest(rejectID);
                    break;

                case 0:
                    System.out.println("관리자 모드를 종료합니다.");
                    return;

                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}