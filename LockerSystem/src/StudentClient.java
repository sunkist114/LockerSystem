import java.util.Scanner;

public class StudentClient
{
    public static void run() {
        Scanner sc = new Scanner(System.in);
        LockerManager lockerManager = AppContext.lockerManager;

        System.out.println("====================================");
        System.out.println("       CSE 사물함 신청 시스템       ");
        System.out.println("====================================\n");

        System.out.println("안녕하세요! CSE 사물함 신청 프로그램입니다.");
        System.out.println("사물함은 총 50개이며, 선착순으로 배정됩니다.\n");

        System.out.println("사물함 신청을 위해 아래의 정보가 필요합니다.");
        System.out.println(" - 학번");
        System.out.println(" - 이름");
        System.out.println(" - 전화번호");
        System.out.println(" - 원하는 사물함 번호 (배치도 참고)\n");

        System.out.println("※ 이미 신청한 경우, 기존 신청 정보를 확인할 수 있습니다.");
        System.out.println("※ 중복 신청은 불가능하며, 사물함은 승인 후 사용할 수 있습니다.\n");

        System.out.println("------------------------------------");

        while (true) {
            System.out.print("학번을 입력하여주세요: ");
            String studentID = sc.nextLine().trim();

            if (lockerManager.studentIdExists(studentID)) {
                Student existing = lockerManager.findStudent(studentID);
                System.out.println("\n이미 신청 정보가 있습니다.");
                System.out.println(existing);
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            System.out.print("성함을 입력하여주세요: ");
            String name = sc.nextLine().trim();

            System.out.print("전화번호를 입력하여주세요: ");
            String phone = sc.nextLine().trim();

            System.out.println("\n현재 사물함 현황:");
            lockerManager.printLockerStatus();

            int lockerNumber;

            while (true) {
                System.out.println("\n사용하고 싶은 사물함 번호를 입력하세요 (1-50): ");
                try
                {
                    lockerNumber = Integer.parseInt(sc.nextLine().trim());
                    if (lockerNumber < 1 || lockerNumber > 50)
                    {
                        System.out.println("1~50 사이의 숫자를 입력해주세요.");
                        System.out.println("1~50 사이의 숫자를 입력해주세요.");
                        continue;
                    }
                    if (lockerManager.isLockerNumberInUse(lockerNumber))
                    {
                        System.out.println(lockerNumber + "번은 이미 사용 중입니다.");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("숫자를 입력해주세요.");
                }
            }

            Student student = new Student(studentID, name, phone, "대기");
            student.lockerNumber = lockerNumber;
            lockerManager.requestLocker(student);

            System.out.println("신청이 완료되었습니다. 승인 후 사물함을 사용하실 수 있습니다.");
            return;
        }
    }
}
