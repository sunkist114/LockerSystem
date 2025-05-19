public class Student
{
    String studentID;
    String name;
    String phoneNumber;
    String status;
    int lockerNumber;

    public Student(String studentID, String name, String phoneNumber, String status) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.studentID = studentID;
        this.status = status;
        this.lockerNumber = -1;
    }

    @Override
    public String toString() {
        String lockerInfo = (lockerNumber == -1) ? "미배정" : lockerNumber + "번";
        return "[" + studentID + "] " + name + " / " + phoneNumber + " / 상태: " + status + " / 사물함: " + lockerInfo;
    }
}
