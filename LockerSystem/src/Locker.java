import java.util.List;

public interface Locker
{
    // 학생 요청 기능
    void requestLocker(Student student);            // 사물함 신청
    boolean isLockerAvailable();                    // 사물함 이용 가능 여부 확인

    // 관리자 처리 기능
    void approveRequest(String studentID);          // 신청 승인
    void rejectRequest(String studentID);
    void clearLocker(String studentID);             // 특정 사물함 비우기
    void clearAllLockers();                         // 모든 사물함 초기화

    // 조회 기능
    List<Student> getApprovedStudents();            // 사물함 이용 중인 학생 목록
    List<Student> getPendingRequests();             // 승인 대기 중인 학생 목록

    // 공유 기능
    boolean studentIdExists(String studentID);      // 학번 중복 확인
    boolean hasLocker(String studentID);            // 사물함 중복 이용 여부
    boolean isLockerNumberInUse(int lockerNumber);  // 이미 배정된 사물함인지 확인
    Student findStudent(String studentID);          // 학번으로 학생 객체 검색

}
