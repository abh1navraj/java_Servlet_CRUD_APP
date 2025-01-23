package in.ineuron.Sevice;

import in.ineuron.daofactory.StudentDaoFactory;
import in.ineuron.dto.Student;
import in.ineuron.persistence.IStudentDao;

public class StudentSeviceImpl implements IStudentService {
	IStudentDao iStudentDao = null;
	@Override
	public String addStudent(Student student) {
		
		iStudentDao = StudentDaoFactory.getStudentDao();
		if(iStudentDao!=null)
			return iStudentDao.addStudent(student);
		else
			return "failure";
		
	}

	@Override
	public Student searchStudent(Integer sid) {
		iStudentDao = StudentDaoFactory.getStudentDao();
		if(iStudentDao!=null)
			return iStudentDao.searchStudent(sid);
		else
			return null;
	}

	@Override
	public String updateStudent(Student student) {
		iStudentDao = StudentDaoFactory.getStudentDao();
		return iStudentDao.updateStudent(student);
	}

	@Override
	public String deleteStudent(Integer sid) {
		iStudentDao = StudentDaoFactory.getStudentDao();
		return iStudentDao.deleteStudent(sid);
		
	}

}
