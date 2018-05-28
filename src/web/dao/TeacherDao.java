package web.dao;

import java.util.List;
import java.util.Map;

import web.model.Teacher;

public interface TeacherDao {
	public Teacher login(String name,String psd);
	public double pageSum(int j);
	public List<Teacher> allTeacher();
	public List<Map<String, Object>> allTeacher(int j);
	public void updateTeacherPsd(String updateId, String psdMd5);
	public Teacher isExist(String tId);
	public void updatepsd(Teacher teacher);
	public void addTeacher(Teacher teacher);
	public boolean findTeacher(String fkTeacherId, String changepsd);
	public Teacher oneTeahcer(String tId);
	public void deleteTeachers(int j);
}
