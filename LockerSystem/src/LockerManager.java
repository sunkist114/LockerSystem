import java.util.ArrayList;
import java.util.List;

public class LockerManager implements Locker
{
    private List<Student> studentList;
    private List<Student> pendingList;
    private final int maxLockerCount = 50;
    private int assignedCount = 0;


    public LockerManager() {
        studentList = new ArrayList<>();
        pendingList = new ArrayList<>();
        assignedCount = 0;
    }

    @Override
    public void requestLocker(Student student) {
        if (studentIdExists(student.studentID)) {
            System.out.println("이미 신청 정보가 존재합니다.");
            return;
        }
        studentList.add(student);
        pendingList.add(student);
        student.status = "대기";
        System.out.println("사물함 신청이 완료되었습니다.");
    }

    @Override
    public boolean isLockerAvailable() {
        return assignedCount < maxLockerCount;
    }

    @Override
    public void approveRequest(String studentID) {
        Student target = findStudent(studentID);
        if (target == null) {
            System.out.println("학생 정보가 없습니다.");
            return;
        }
        if (!pendingList.contains(target)) {
            System.out.println("승인 대기 중이 아닙니다.");
            return;
        }
        if (!isLockerAvailable()) {
            System.out.println("배정 가능한 사물함이 없습니다.");
            return;
        }

        if (isLockerNumberInUse(target.lockerNumber)) {
            System.out.println(target.lockerNumber + "번 사물함은 이미 사용 중입니다.");
            return;
        }

        target.status = "승인됨";
        assignedCount++;
        pendingList.remove(target);
        System.out.println("승인완료! " + target.lockerNumber + "번 사물함이 배정되었습니다.");
    }

    @Override
    public void rejectRequest(String studentID) {
        Student target = findStudent(studentID);
        if (target == null) {
            System.out.println("해당 학번의 신청 정보가 없습니다.");
            return;
        }

        if (!pendingList.contains(target)) {
            System.out.println("해당 신청은 대기 중이 아닙니다.");
            return;
        }

        target.status = "거절됨";
        pendingList.remove(target);
        System.out.println("거절 완료: " + target.name + " (" + target.studentID + ")");
    }

    @Override
    public boolean studentIdExists(String studentID)
    {
        for (Student s : studentList) {
            if (s.studentID.equals(studentID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Student findStudent(String studentID) {
        for (Student s : studentList) {
            if (s.studentID.equals(studentID)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public boolean hasLocker(String studentID) {
        Student s = findStudent(studentID);
        return s != null && s.lockerNumber != -1;
    }

    @Override
    public boolean isLockerNumberInUse(int lockerNumber) {
        for (Student s : studentList) {
            if (s.lockerNumber == lockerNumber && "승인됨".equals(s.status)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public List<Student> getPendingRequests() {
        return pendingList;
    }

    @Override
    public List<Student> getApprovedStudents() {
        List<Student> result = new ArrayList<>();
        for (Student s : studentList) {
            if ("승인됨".equals(s.status)) {
                result.add(s);
            }
        }
        return result;
    }

    @Override
    public void clearLocker(String studentID) {
        Student s = findStudent(studentID);
        if (s != null && "승인됨".equals(s.status))
        {
            s.status = "빈 사물함";
            s.lockerNumber = -1;
            assignedCount--;
            System.out.println("사물함이 비워졌습니다.");
        } else {
            System.out.println("비울 수 없는 상태입니다.");
        }
    }

    @Override
    public void clearAllLockers() {
        for (Student s : studentList) {
            if ("승인됨".equals(s.status)) {
                s.status = "빈 사물함";
                s.lockerNumber = -1;
            }
        }
        assignedCount = 0;
        System.out.println("초기화 완료.");
    }


    public void printLockerStatus() {
        for (int row=0; row<5; row++) {
            for (int col=0; col<10; col++) {
                int lockerNumber = col * 5 + row + 1;
                System.out.printf("%4d ", lockerNumber);
            }
            System.out.println();

            for (int col = 0; col < 10; col++) {
                int lockerNumber = col * 5 + row + 1;
                if (isLockerNumberInUse(lockerNumber)) {
                    System.out.print(" [X] ");
                } else {
                    System.out.print(" [O] ");
                }
            }
            System.out.println("\n");
        }
    }
}
