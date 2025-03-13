package edu.cjc.sms_app.app.serviceI;

import java.util.List;

import edu.cjc.sms_app.app.Model.Student;

public interface StudentServiceI {
public void saveStudentDetails(Student s);
public List<Student>getAllStudents();
public List<Student>searchStudentsByBatch(String batchNumber);
public Student getSingleStudent(int studentId);
public void updateStudentFees(int studentId, float ammount);
public void removeStudent(int studentId);
public void updateBatch(int studentId,String batchNumber );
}
