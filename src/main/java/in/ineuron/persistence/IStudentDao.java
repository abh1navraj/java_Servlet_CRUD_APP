package in.ineuron.persistence;

import in.ineuron.dto.Student;

public interface IStudentDao {
	
	//Operations to be implemented
	public String addStudent(Student student);
	
	public Student searchStudent(Integer sid);
	
	public String updateStudent(Student student);
	
	public String deleteStudent(Integer sid);
	
}
