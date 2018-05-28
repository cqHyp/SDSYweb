package web.dao;

import java.util.List;
import java.util.Map;

import web.model.Students;

public interface StudentDao {
	public double pageSum(int j);
	public List<Map<String, Object>> allStudent(int i);
	public void updateStudentPsd(String updateId, String psdMd5);
	public Students isexist(String stuId);
	public void updatestudent(Students stu);
	public void addstudent(Students stu);
	public void deleteStudents(int j);
	public List<Students> findPro(int i);
	public List<Students> getAllcollege();
	public List<Students> getAllprofession();
	public List<Students> getAllclass();
	public List<Map<String, Object>> findStudents(String twocollege, String nianji, String profession, String classz);

}
