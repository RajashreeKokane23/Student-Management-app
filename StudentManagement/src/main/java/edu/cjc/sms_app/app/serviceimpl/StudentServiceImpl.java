package edu.cjc.sms_app.app.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.cjc.sms_app.app.Model.Student;
import edu.cjc.sms_app.app.repositary.StudentRepositary;
import edu.cjc.sms_app.app.serviceI.StudentServiceI;

@Service
public class StudentServiceImpl implements StudentServiceI {
	@Autowired
StudentRepositary sr;
	public void saveStudentDetails(Student s) {
		
		sr.save(s);
		
	}
	@Override
	public List<Student> getAllStudents() {
		return (List<Student>) sr.findAll();
	}
	@Override
	public List<Student> searchStudentsByBatch(String batchNumber) {
		List<Student>batchStudent=sr.findAllByBatchNumber(batchNumber);
		return batchStudent;
	}
	@Override
	public Student getSingleStudent(int studentId) {
		Optional<Student>opStudent=sr.findById(studentId);
		return opStudent.get();
	}
	@Override
	public void updateStudentFees(int studentId, float ammount) {
		Optional<Student>opStudent=sr.findById(studentId);
		Student st=opStudent.get();
		
		st.setFeesPaid(st.getFeesPaid()+ammount);
		sr.save(st);
		
	}
	@Override
	public void removeStudent(int studentId) {
		sr.deleteById(studentId);
		
	}
	
	@Override
	public void updateBatch(int studentId, String batchNumber) {
		Student s=sr.findById(studentId).get();
		s.setBatchNumber(batchNumber);
		sr.save(s);
		
	}

}
