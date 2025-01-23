package in.ineuron.servicefactory;

import in.ineuron.Sevice.IStudentService;
import in.ineuron.Sevice.StudentSeviceImpl;

public class StudentServiceFactory {
	
//	make constructor private to avoid object creation of this class
	private StudentServiceFactory(){
		
	}
	
	private static IStudentService studentService = null;
	
	public static IStudentService getStudentService() {
		if (studentService == null) {
			studentService = new StudentSeviceImpl();

		}
		return studentService;

	}

}
