package web.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.StudentDao;
import web.model.Students;
import web.util.MD5Util;


public class StudentDaoImp extends HibernateDaoSupport implements StudentDao {

    @Override
    public double pageSum(int j) {
        Session session = getSession();
        Query query;
        String b[] = {"商学院", "信息工程学院", "人文学院", "机械工程学院", "外国语学院", "建筑学院", "设计学院", "理学院", "中旅学院"};
        if (j == 0) {
            query = session.createQuery("select count(*) from Students");
        } else {
            query = session.createQuery("select count(*) from Students where SCollege=?");
            query.setParameter(0, b[j - 1]);
        }
        Long c = (Long) query.uniqueResult();
        double a = c;
        session.close();
        return a;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> allStudent(int j) {
        Session ssn = getSession();
        Query query;
        String b[] = {"商学院", "信息工程学院", "人文学院", "机械工程学院", "外国语学院", "建筑学院", "设计学院", "理学院", "中旅学院", "之江学院"};
        if (j == 0) {
            query = ssn.createQuery("from Students");
        } else {
            query = ssn.createQuery("from Students where SCollege=?");
            query.setParameter(0, b[j - 1]);
        }
        List<Students> l = query.list();
        Map<String, Object> map;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

        for (Students stu : l) {
            map = new HashMap<String, Object>();
            map.put("id", stu.getId().getSStudentId());
            map.put("name", stu.getSName());
            if (stu.getSSex())
                map.put("sex", "女");
            else
                map.put("sex", "男");
            map.put("college", stu.getSCollege());
            map.put("pr", stu.getSProfession());
            map.put("clasz", stu.getSClass());
            map.put("type", stu.getSClasstype());
            if (stu.getSJobstatus() != null)
                map.put("job", stu.getSJobstatus());
            else
                map.put("job", "学生");
            map.put("te", stu.getSTutor());
            if (stu.getSPhone() != null)
                map.put("phone", stu.getSPhone());
            else
                map.put("phone", "未填写");
            if (stu.getSIdcard() != null)
                map.put("scard", stu.getSIdcard());
            else
                map.put("scard", "未填写");
            list.add(map);
        }
        ssn.close();
        return list;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void updateStudentPsd(String updateId, String psdMd5) {
        //System.out.print(updateId+", "+psdMd5);
        List<Students> list = getHibernateTemplate().find("from Students t where t.id.SStudentId=?", updateId);

        list.get(0).setSPassword(MD5Util.MD5(psdMd5));
        this.getHibernateTemplate().update(list.get(0));

    }

    @SuppressWarnings("unchecked")
    @Override
    public Students isexist(String stuId) {
        // TODO Auto-generated method stub
        List<Students> list = getHibernateTemplate().find(
                "from Students s where s.id.SStudentId=?", stuId);
        if (list.size() > 0) {
            System.out.println("存在" + list.get(0).getSName());
            return list.get(0);
        } else
            return null;
    }

    @Override
    public void updatestudent(Students stu) {
        this.getHibernateTemplate().update(stu);

    }

    @Override
    public void addstudent(Students stu) {
        this.getHibernateTemplate().save(stu);

    }

    @Override
    public void deleteStudents(int j) {
        // TODO Auto-generated method stub
        String b[] = {"商学院", "信息工程学院", "人文学院", "机械工程学院", "外国语学院", "建筑学院", "设计学院", "理学院", "中旅学院"};
        List<Students> s = getHibernateTemplate().find("from Students s where s.SCollege=?", b[j - 1]);
        for (Students stu : s) {
            this.getHibernateTemplate().delete(stu);
        }
    }

    @Override
    public List<Students> findPro(int i) {
        // TODO Auto-generated method stub
        String b[] = {"商学院", "信息工程学院", "人文学院", "机械工程学院", "外国语学院", "建筑学院", "设计学院", "理学院", "中旅学院"};
        List<Students> s = getHibernateTemplate().find("from Students s where s.SCollege=? GROUP BY s.SProfession", b[i]);
        return s;
    }

    @Override
    public List<Students> getAllcollege() {
        List<Students> s = getHibernateTemplate().find("from Students s  GROUP BY s.SCollege");
        return s;
    }

    @Override
    public List<Students> getAllprofession() {
        List<Students> s = getHibernateTemplate().find("from Students s  GROUP BY s.SProfession");
        return s;
    }

    @Override
    public List<Students> getAllclass() {
        List<Students> s = getHibernateTemplate().find("from Students s  GROUP BY s.SClass");
        return s;
    }

    @Override
    public List<Map<String, Object>> findStudents(String twocollege, String nianji, String profession, String classz) {
        // TODO Auto-generated method stub
        List<Students> s;
        if (twocollege.equals("all")) {
            if (nianji.equals("all")) {
                s = getHibernateTemplate().find("from Students");
            } else {
                s = getHibernateTemplate().find("from Students s where s.SClass like '%" + nianji + "%'");
            }
            //s = getHibernateTemplate().find("from Students");
        } else {
            if (profession.equals("all")) {
                if (nianji.equals("all")) {
                    s = getHibernateTemplate().find("from Students s where s.SCollege=?", twocollege);
                } else {
                    s = getHibernateTemplate().find("from Students s where s.SCollege=? and s.SClass like '%" + nianji + "%'", twocollege);
                }
            } else {
                if (classz.equals("all")) {
                    if (nianji.equals("all")) {
                        s = getHibernateTemplate().find("from Students s where s.SProfession=?", profession);
                    } else {
                        s = getHibernateTemplate().find("from Students s where s.SProfession=? and s.SClass like '%" + nianji + "%'", profession);
                    }
                } else {
                    s = getHibernateTemplate().find("from Students s where s.SClass=?", classz);
                }

            }
        }
        Map<String, Object> map;
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (s != null & s.size() > 0) {
            for (Students stu : s) {
                map = new HashMap<String, Object>();
                map.put("id", stu.getId().getSStudentId());
                map.put("name", stu.getSName());
                if (stu.getSSex())
                    map.put("sex", "女");
                else
                    map.put("sex", "男");
                map.put("college", stu.getSCollege());
                map.put("pr", stu.getSProfession());
                map.put("clasz", stu.getSClass());
                map.put("type", stu.getSClasstype());
                if (stu.getSJobstatus() != null)
                    map.put("job", stu.getSJobstatus());
                else
                    map.put("job", "学生");
                map.put("te", stu.getSTutor());
                if (stu.getSPhone() != null)
                    map.put("phone", stu.getSPhone());
                else
                    map.put("phone", "未填写");
                if (stu.getSIdcard() != null)
                    map.put("scard", stu.getSIdcard());
                else
                    map.put("scard", "未填写");
                list.add(map);
            }
        }
        return list;
    }

}
