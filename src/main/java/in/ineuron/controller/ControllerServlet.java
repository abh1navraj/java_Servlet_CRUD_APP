package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.ineuron.Sevice.IStudentService;
import in.ineuron.dto.Student;
import in.ineuron.servicefactory.StudentServiceFactory;

@WebServlet("/controller/*")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);

	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
		IStudentService studentService = StudentServiceFactory.getStudentService();

		System.out.println("Request URI :: " + request.getRequestURI());
		System.out.println("Path Info :: " + request.getPathInfo());

		if (request.getRequestURI().endsWith("addform")) {
			String operation = "Insert";
			try {
				String sname = request.getParameter("username");
				String sage = request.getParameter("age");
				String saddress = request.getParameter("address");

				Student student = new Student();
				student.setSname(sname);
				student.setSage(Integer.parseInt(sage));
				student.setSaddress(saddress);

				String status = studentService.addStudent(student);

				if (status.equals("failure")) {
					String message = "failed";
					response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
							+ operation + "&sid=");
				} else {
					String message = "Success";
					response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
							+ operation + "&sid=" + status);

				} 
			} catch (Exception e) {
				String message = "failed";
				response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
						+ operation);
			}
		}

		if (request.getRequestURI().endsWith("searchform")) {
			String operation = "Search";
			String sid = null;
			try {
				sid = request.getParameter("userid");

				Student student = studentService.searchStudent(Integer.parseInt(sid));

				if (student != null) {
					response.sendRedirect(request.getContextPath() + "/searchResult.html?userid=" + student.getSid() + "&username="
							+ student.getSname() + "&age=" + student.getSage() + "&address=" + student.getSaddress());

				} else {
					String message = "not found";

					response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
							+ operation + "&sid=" + sid);

				}

			} catch (Exception e) {
				String message = "failed";
				response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
						+ operation + "&sid=" + sid);

			}

		}

		if (request.getRequestURI().endsWith("deleteform")) {
			String operation = "Delete";
			String sid = null;
			try {
				sid = request.getParameter("username");

				String result = studentService.deleteStudent(Integer.parseInt(sid));

				PrintWriter out = response.getWriter();

				if (result.equals("Success")) {
					String message = "success";

					response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
							+ operation + "&sid=" + sid);

				} else if (result.equals("failure")) {
					String message = "failure";

					response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
							+ operation + "&sid=" + sid);

				} else {
					String message = "not found";

					response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
							+ operation + "&sid=" + sid);

				}
				out.close();
			} catch (Exception e) {
				String message = "failed";

				response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
						+ operation + "&sid=" + sid);
			}

		}

		if (request.getRequestURI().endsWith("editform")) {
			String sid = null;
			try {
				sid = request.getParameter("username");

				Student student = studentService.searchStudent(Integer.parseInt(sid));

				PrintWriter out = response.getWriter();

				if (student != null) {
					Integer id = student.getSid();
					String name = student.getSname();
					Integer age = student.getSage();
					String address = student.getSaddress();

					response.sendRedirect(request.getContextPath() + "/updateDetail.html?userid=" + id + "&username="
							+ name + "&age=" + age + "&address=" + address);

				} else {
					String operation = "Update";
					String message = "Not found";

					response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
							+ operation + "&sid=" + sid);

				}
				out.close();
			} catch (Exception e) {
				String message = "failed";
				String operation = "Update";
				response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
						+ operation + "&sid=" + sid);
			}

		}

		if (request.getRequestURI().endsWith("updateform")) {
			String sid = null;
			try {
				sid = request.getParameter("userid");
				String sname = request.getParameter("name");
				String sage = request.getParameter("age");
				String saddress = request.getParameter("address");

				Student student = new Student();
				student.setSid(Integer.parseInt(sid));
				student.setSname(sname);
				student.setSage(Integer.parseInt(sage));
				student.setSaddress(saddress);

				String result = studentService.updateStudent(student);

				PrintWriter out = response.getWriter();
				String operation = "Update";
				if (result.equals("success")) {
					String message = "success";
					response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
							+ operation + "&sid=" + sid);

				} else if (result.equals("Not found")) {
					String message = "not found";

					response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
							+ operation + "&sid=" + sid);
				} else {
					String message = "failed";
					response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
							+ operation + "&sid=" + sid);
				}
				out.close();
			} catch (Exception e) {
				String message = "failed";
				String operation = "Update";
				response.sendRedirect(request.getContextPath() + "/success.html?message=" + message + "&operation="
						+ operation + "&sid=" + sid);
			}

		}

	}

}
