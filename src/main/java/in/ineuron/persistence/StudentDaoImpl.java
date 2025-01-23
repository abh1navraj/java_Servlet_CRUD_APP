package in.ineuron.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import in.ineuron.dto.Student;
import in.ineuron.util.JDBCUtil;

public class StudentDaoImpl implements IStudentDao {

	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet resultSet = null;
	Student student = null;

	@Override
	public String addStudent(Student student) {
		try {
			connection = JDBCUtil.getJDBCConnection();
			String selectQuery = "INSERT INTO STUDENT(SNAME, SAGE, SADDRESS) VALUES(?, ?, ?);";

			if (connection != null)
				pstmt = connection.prepareStatement(selectQuery, Statement.RETURN_GENERATED_KEYS);

			if (pstmt != null) {

				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddress());

				int rowAffected = pstmt.executeUpdate();

				if (rowAffected == 1) {
					ResultSet generatedKeys = pstmt.getGeneratedKeys();
					if (generatedKeys.next()) 
						return String.valueOf(generatedKeys.getInt(1));
				}

			}
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return "failure";
	}

	@Override
	public Student searchStudent(Integer sid) {
		try {
			connection = JDBCUtil.getJDBCConnection();
			String selectQuery = "select sid, sname, sage, saddress from student where sid = ?";

			if (connection != null)
				pstmt = connection.prepareStatement(selectQuery);

			if (pstmt != null)
				pstmt.setInt(1, sid);

			if (pstmt != null)
				resultSet = pstmt.executeQuery();

			if (resultSet != null) {

				if (resultSet.next()) {
					student = new Student();
					student.setSid(resultSet.getInt(1));
					student.setSname(resultSet.getString(2));
					student.setSage(resultSet.getInt(3));
					student.setSaddress(resultSet.getString(4));

					return student;
				}
			}

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		return null;
	}

	@Override
	public String updateStudent(Student student) {
		try {
			connection = JDBCUtil.getJDBCConnection();
			String updateQuery = "update student set sname = ?, sage = ?, saddress =? where sid =?";
			int rowAffected = 0;
			if (connection != null)
				pstmt = connection.prepareStatement(updateQuery);

			if (pstmt != null) {
				pstmt.setString(1, student.getSname());
				pstmt.setInt(2, student.getSage());
				pstmt.setString(3, student.getSaddress());
				pstmt.setInt(4, student.getSid());
				
			}

			if (pstmt != null)
				rowAffected = pstmt.executeUpdate();

			if (rowAffected != 0) {
					return "success";
				}
			else {
					return "Not found";
			}
			
	

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		return "failure";
		
	}

	@Override
	public String deleteStudent(Integer sid) {
		try {
			connection = JDBCUtil.getJDBCConnection();
			String deleteQuery = "delete from student where sid = ?";

			if (connection != null)
				pstmt = connection.prepareStatement(deleteQuery);

			if (pstmt != null)
				pstmt.setInt(1, sid);

			if (pstmt != null) {
				int rowAffected = pstmt.executeUpdate();
				if (rowAffected == 1)
					return "Success";
				else
					return "Not Found";

			}
		} catch (SQLException | IOException e) {

			e.printStackTrace();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		return "failure";

	}

}
