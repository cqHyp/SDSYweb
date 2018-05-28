package web.dao;

import java.util.List;
import java.util.Map;

import web.model.Activiting;
import web.model.Students;

public interface ActRecordDao {
	//�����ѧ���ɼ�(ҳ��)
	 List<Map<String, Object>> allStuRecord(int i);
	 double PageSum(int id);
	 List<Activiting> JionActStudent(int id);
	 List<Map<String, Object>> ActStudentRecord(int exportType, int j);
	 void updateactRecordStu(Activiting activityrecord);
	 Activiting findactRecordStu(int actid, String stuid);
	 List<Map<String, Object>> findActRecord(int actid);
	 List<Map<String, Object>> allActing(int i, int j);
	 List<Activiting> findActStudents(int actid);
	 void addActiviting(Activiting acting);
	 List<Map<String, Object>> findActqiandao(int actid);
	 List<Map<String, Object>> allProActing(int i, String sProfession);
	List<Map<String,Object>> ActStudentRecordNew(int exportType, String students, String twocollege, String nianji, String profession, String classz);

    List<Map<String,Object>> allActingNew(String twocollege, String nianji, String profession, String classz, int type);

    List<Map<String, Object>> getStudentsGrade(String twocollege, String nianji, String profession, String classz);
}
