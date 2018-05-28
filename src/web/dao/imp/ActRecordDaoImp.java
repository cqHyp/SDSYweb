package web.dao.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import web.dao.ActRecordDao;
import web.model.Activiting;
import web.model.Activity;
import web.model.Students;

public class ActRecordDaoImp extends HibernateDaoSupport implements ActRecordDao {
    @SuppressWarnings({"unchecked", "unused"})
    @Override
    public List<Map<String, Object>> allStuRecord(int id) {
        Session ssn = getSession();
        Query query = ssn.createQuery("from Activiting a where a.id.ARId=" + id);
        List<Activiting> l = query.list();
        ssn.close();
        List<Map<String, Object>> listg = new ArrayList<>();
        if (l != null && l.size() > 0) {
            List<Students> list = null;
            Map<String, Object> map;
            int len = l.size();
            Object[] values = new Object[len];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                values[j] = l.get(j).getId().getASId();
                if (j != len - 1) {
                    sb.append("?,");
                } else {
                    sb.append("?");
                }
            }
            list = getHibernateTemplate().find("FROM Students t WHERE t.id.SStudentId IN (" + sb.toString() + ")",
                    values);
            if (list != null && list.size() > 0) {
                for (Activiting a : l) {
                    for (Students stu : list) {
                        // for (List<Object> a : l) {
                        if (stu.getId().getSStudentId().equals(a.getId().getASId())) {
                            map = new HashMap<String, Object>();
                            // list =
                            // getHibernateTemplate().find("from Students t
                            // where t.id.SStudentId=?",
                            // a.getId().getASId());
                            // stu=list.get(0);
                            map.put("id", stu.getId().getSStudentId());
                            map.put("name", stu.getSName());
                            map.put("college", stu.getSCollege());
                            map.put("clasz", stu.getSClass());
                            map.put("iphone", stu.getSPhone());
                            /*
                             * map.put("id", a.get(0)); map.put("name",
                             * a.get(1)); map.put("college", a.get(2));
                             * map.put("clasz", a.get(3));
                             */
                            if (a.getAGrade() != null) {
                                if (a.getAGrade() == 1)
                                    map.put("grade", "优秀");
                                else if (a.getAGrade() == 2)
                                    map.put("grade", "良好");
                                else if (a.getAGrade() == 3)
                                    map.put("grade", "不合格");
                            } else
                                map.put("grade", "未评价");
                            listg.add(map);
                        }
                    }
                }
            }
        }
        return listg;
    }

    @Override
    public double PageSum(int id) {
        Session session = getSession();
        Query query = session.createQuery("select count(*) from Activiting  a where  a.id.ARId=" + id);
        Long count = (Long) query.uniqueResult();
        double a = count;
        session.close();
        return a;
    }

    @SuppressWarnings({"unchecked", "unused"})
    @Override
    public List<Activiting> JionActStudent(int id) {
        List<Students> stulist = null;
        List<Activiting> list = getHibernateTemplate().find("from Activiting a where a.id.ARId=?", id);
        for (Activiting stuId : list) {
            stulist = this.getHibernateTemplate().find("from Students a where a.id.SStudentId=?",
                    stuId.getId().getASId());
            stuId.setStudent(stulist.get(0));
        }
        if (list != null && list.size() > 0)
            return list;
        else
            return null;
    }

    @SuppressWarnings({"unchecked", "unused"})
    @Override
    public List<Map<String, Object>> ActStudentRecord(int exportType, int i) {
        // int a[]={0};
        String b[] = {"商学院", "信息工程学院", "人文学院", "机械工程学院", "外国语学院", "建筑学院", "设计学院", "理学院", "中旅学院"};
        List<Activity> aclist = null;
        List<Map<String, Object>> l = new ArrayList<>();
        List<Students> s;
        if (i == 0) {
            s = getHibernateTemplate().find("from Students");
        } else {
            s = getHibernateTemplate().find("from Students s where s.SCollege=?", b[i - 1]);
        }

        List<Activiting> list = getHibernateTemplate().find("from Activiting a where a.AGrade=1 or a.AGrade=2");
        int len = list.size();
        Object[] values = new Object[len];
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < len; j++) {
            values[j] = list.get(j).getId().getARId();
            if (j != len - 1)
                sb.append("?,");
            else
                sb.append("?");
        }
        aclist = getHibernateTemplate().find("FROM Activity t WHERE t.RId IN (" + sb.toString() + ")", values);
        if (list.size() != 0) {
            for (Activiting ac : list) {
                for (Activity entity1 : aclist)
                    if (ac.getId().getARId().equals(entity1.getRId())) {
                        ac.setActivity(entity1);
                        break;
                    }
            }
        }
        // System.out.println("添加活动信息"+exportType);
        double record1, record2, record3, record4, record5;
        int acItem;
        double credit;
        Map<String, Object> map;
        if (s.size() != 0) {
            for (Students stu : s) {
                map = new LinkedHashMap<>();
                record1 = 0;
                record2 = 0;
                record3 = 0;
                record4 = 0;
                record5 = 0;
                map.put("id", stu.getId().getSStudentId());
                map.put("name", stu.getSName());
                map.put("college", stu.getSCollege());
                map.put("profession", stu.getSProfession());
                map.put("class", stu.getSClass());
                for (Activiting acr : list) {
                    if (acr.getActivity() != null) {
                        if (acr.getId().getASId().equals(stu.getId().getSStudentId())
                                && acr.getActivity().getRType() == exportType) {
                            acItem = acr.getActivity().getRItem();
                            credit = acr.getActivity().getRCredit();
                            switch (exportType) {
                                case 2: // (博雅读书)
                                    if (acItem == 1) {
                                        record1 += credit;
                                    } else if (acItem == 2) {
                                        record2 += credit;
                                    } else if (acItem == 3) {
                                        record3 += credit;
                                    }
                                    break;
                                case 3:// (博雅心情)
                                    if (acItem == 4) {
                                        record1 += credit;
                                    } else if (acItem == 5) {
                                        record2 += credit;
                                    } else if (acItem == 6) {
                                        record3 += credit;
                                    }
                                    break;
                                case 4:// (博雅实践)
                                    if (acItem == 7) {
                                        record1 += credit;
                                    } else if (acItem == 8) {
                                        record2 += credit;
                                    }
                                    break;
                                case 5:// (博雅讲坛)
                                    if (acItem == 9) {
                                        record1 += credit;
                                    } else if (acItem == 10) {
                                        record2 += credit;
                                    } else if (acItem == 11) {
                                        record3 += credit;
                                    } else if (acItem == 12) {
                                        record4 += credit;
                                    } else if (acItem == 13) {
                                        record5 += credit;
                                    }
                                    break;
                                case 6:// (博雅修身)
                                    if (acItem == 14) {
                                        record1 += credit;
                                    } else if (acItem == 15) {
                                        record2 += credit;
                                    } else if (acItem == 16) {
                                        record3 += credit;
                                    } else if (acItem == 17) {
                                        record4 += credit;
                                    }
                                    break;
                                case 7:// (博雅视野)
                                    if (acItem == 18) {
                                        record1 += credit;
                                    } else if (acItem == 19) {
                                        record2 += credit;
                                    } else if (acItem == 20) {
                                        record3 += credit;
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        break;
                    }
                }
                map.put("record1", record1);
                map.put("record2", record2);
                map.put("record3", record3);
                map.put("record4", record4);
                map.put("record5", record5);
                l.add(map);
            }
        }
        if (l != null)
            return l;
        else
            return null;
    }

    @Override
    public void updateactRecordStu(Activiting activityrecord) {
        this.getHibernateTemplate().update(activityrecord);

    }

    @SuppressWarnings("unchecked")
    @Override
    public Activiting findactRecordStu(int actid, String stuid) {
        Object[] parm = {actid, stuid};
        List<Activiting> list = getHibernateTemplate().find("from Activiting a where a.id.ARId=? and a.id.ASId=?",
                parm);
        if (list != null && list.size() > 0) {
            return list.get(0);
        } else
            return null;
    }

    @SuppressWarnings({"unchecked", "unused"})
    @Override
    public List<Map<String, Object>> findActRecord(int actid) {
        List<Activiting> list = getHibernateTemplate().find("from Activiting a where a.id.ARId=?", actid);
        int len = list.size();
        List<Map<String, Object>> ll = new ArrayList<Map<String, Object>>();
        if (len > 0 && list != null) {
            List<Students> l = null;
            List<Activity> aclist = null;
            Object[] values = new Object[len];
            Object[] values2 = new Object[len];
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (int j = 0; j < len; j++) {
                values[j] = list.get(j).getId().getASId();
                values2[j] = list.get(j).getId().getARId();
                if (j != len - 1) {
                    sb.append("?,");
                    sb2.append("?,");
                } else {
                    sb.append("?");
                    sb2.append("?");
                }
            }
            l = getHibernateTemplate().find("FROM Students t WHERE t.id.SStudentId IN (" + sb.toString() + ")", values);
            aclist = getHibernateTemplate().find("FROM Activity t WHERE t.RId IN (" + sb2.toString() + ")", values2);
            for (Activiting a : list) {
                if (a.getId().getASId().equals("") || a.getId().getARId() == 0 || a.getId().getASId() == null) {
                    this.getHibernateTemplate().delete(a);
                    break;
                }
                for (Students st : l)
                    if (a.getId().getASId().equals(st.getId().getSStudentId())) {
                        a.setStudent(st);
                        break;
                    }
                for (Activity ac : aclist)
                    if (a.getId().getARId().equals(ac.getRId())) {
                        a.setActivity(ac);
                        break;
                    }
            }
            int k = 1;
            Map<String, Object> map;
            for (Activiting a : list) {
                map = new HashMap<String, Object>();
                map.put("id", k);
                if (a.getId().getASId() != null)
                    map.put("xuehao", a.getId().getASId());
                else
                    map.put("xuehao", "空");
                if (a.getStudent() != null) {
                    map.put("name", a.getStudent().getSName());
                    map.put("classleibie", a.getStudent().getSClass());
                    map.put("dianhua", a.getStudent().getSPhone());
                } else {
                    map.put("name", "空");
                    map.put("classleibie", "空");
                    map.put("dianhua", "空");
                }
                if (a.getAFirsttime() == null) {
                    map.put("ischidao", "未签到");
                } else if (a.getAFirsttime().getTime() > a.getActivity().getRStartTime().getTime()) {
                    map.put("ischidao", "是");
                } else {
                    map.put("ischidao", "否");
                }

                if (a.getASecondtime() == null) {
                    map.put("iszaotui", "未签到");
                } else if (a.getASecondtime().getTime() < a.getActivity().getREndTime().getTime()) {
                    map.put("iszaotui", "是");
                } else {
                    map.put("iszaotui", "否");
                }

                if (a.getAThirdtime() == null) {
                    map.put("ischidao2", "未签到");
                } else if (a.getAThirdtime().getTime() > a.getActivity().getRStartTimeTwo().getTime()) {
                    map.put("ischidao2", "是");
                } else {
                    map.put("ischidao2", "否");
                }

                if (a.getAFourtime() == null) {
                    map.put("iszaotui2", "未签到");
                } else if (a.getAFourtime().getTime() < a.getActivity().getREndTimeTwo().getTime()) {
                    map.put("iszaotui2", "是");
                } else {
                    map.put("iszaotui2", "否");
                }

                k++;
                ll.add(map);
                // System.out.println("ll:：" + ll);
            }
        }
        return ll;

    }

    @Override
    public List<Map<String, Object>> allActing(int i, int j) {
        // TODO Auto-generated method stub
        Session session = getSession();
        java.text.NumberFormat format = java.text.NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(2);
        String a[] = {"博雅读书", "博雅心情", "博雅实践", "博雅讲坛", "博雅修身", "博雅视野"};
        String b[] = {"商学院", "信息工程学院", "人文学院", "机械工程学院", "外国语学院", "建筑学院", "设计学院", "理学院", "中旅学院"};
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Students> stuli;
        Map<String, Object> map;
        int allnumber = 0, allnumber0 = 0, allnumber1 = 0;
        String sql, sql2;
        // 新方法
        if (i == 0 && j != 0) {
            int number = getHibernateTemplate().find("from Students s where s.SCollege=?", b[j - 1]).size();
            for (i += 2; i < 8; i++) {
                sql = "select count(*) from (SELECT sum(b.r_credit) as grade from activiting a,activity b,students s where  a.A_R_id=b.r_id"
                        + " and (a.A_grade=1 or a.A_grade=2) and a.A_S_id=s.s_studentId and b.r_type=" + i
                        + " and s.s_college='" + b[j - 1]
                        + "' group by s.s_studentId order by s.s_studentId) t WHERE t.grade>=1 and t.grade<2";
                sql2 = "select count(*) from (SELECT sum(b.r_credit) as grade from activiting a,activity b,students s where  a.A_R_id=b.r_id"
                        + " and (a.A_grade=1 or a.A_grade=2) and a.A_S_id=s.s_studentId and b.r_type=" + i
                        + " and s.s_college='" + b[j - 1]
                        + "' group by s.s_studentId order by s.s_studentId) t WHERE t.grade>=2";
                map = new HashMap<String, Object>();
                int grade2 = 0, grade1 = 0, grade0 = 0;
                grade1 = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
                // getSession().flush();
                grade2 = Integer.valueOf(session.createSQLQuery(sql2).uniqueResult().toString());
                grade0 = number - grade1 - grade2;
                map.put("name", a[i - 2]);
                allnumber += number;
                map.put("number", number);
                allnumber0 += grade0;
                map.put("number0", grade0);
                allnumber1 += grade1;
                map.put("number1", grade1);
                map.put("number2", grade2);
                if (number == 0) {
                    number = 1;
                }
                // System.out.println(grade0/number+","+(double)grade0/number);
                map.put("bnumber0", format.format((double) grade0 / number));
                map.put("bnumber1", format.format((double) grade1 / number));
                map.put("bnumber2", format.format((double) grade2 / number));
                list.add(map);
            }
        } else if (i != 0 && j == 0) {
            for (; j < b.length; j++) {
                sql = "select count(*) from (SELECT sum(b.r_credit) as grade from activiting a,activity b,students s where  a.A_R_id=b.r_id "
                        + "and (a.A_grade=1 or a.A_grade=2) and a.A_S_id=s.s_studentId and b.r_type=" + (i + 1)
                        + " and s.s_college='" + b[j]
                        + "' group by s.s_studentId order by s.s_studentId) t WHERE t.grade>=1 and t.grade<2";
                sql2 = "select count(*) from (SELECT sum(b.r_credit) as grade from activiting a,activity b,students s where  a.A_R_id=b.r_id "
                        + "and (a.A_grade=1 or a.A_grade=2) and a.A_S_id=s.s_studentId and b.r_type=" + (i + 1)
                        + " and s.s_college='" + b[j]
                        + "' group by s.s_studentId order by s.s_studentId) t WHERE t.grade>=2";
                stuli = getHibernateTemplate().find("from Students s where s.SCollege=?", b[j]);
                // System.out.println(sql);
                // System.out.println(sql2);
                map = new HashMap<String, Object>();
                int grade2 = 0, grade1 = 0, number, grade0 = 0;
                number = stuli.size();
                grade1 = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
                // getSession().flush();
                grade2 = Integer.valueOf(session.createSQLQuery(sql2).uniqueResult().toString());
                grade0 = number - grade1 - grade2;
                map.put("name", b[j]);
                allnumber += number;
                map.put("number", number);
                allnumber0 += grade0;
                map.put("number0", grade0);
                allnumber1 += grade1;
                map.put("number1", grade1);
                map.put("number2", grade2);
                if (number == 0) {
                    number = 1;
                }
                // System.out.println(grade0/number+","+(double)grade0/number);
                map.put("bnumber0", format.format((double) grade0 / number));
                map.put("bnumber1", format.format((double) grade1 / number));
                map.put("bnumber2", format.format((double) grade2 / number));
                list.add(map);
            }
        }

        map = new HashMap<String, Object>();
        map.put("name", "全部");
        map.put("number", allnumber);
        map.put("number0", allnumber0);
        map.put("number1", allnumber1);
        map.put("number2", allnumber - allnumber0 - allnumber1);
        if (allnumber == 0) {
            allnumber = 1;
        }
        map.put("bnumber0", format.format((double) allnumber0 / allnumber));
        map.put("bnumber1", format.format((double) allnumber1 / allnumber));
        map.put("bnumber2", format.format((double) (allnumber - allnumber0 - allnumber1) / allnumber));
        list.add(map);
        // 旧方法
        session.close();
        return list;
    }

    @Override
    public List<Activiting> findActStudents(int actid) {
        List<Activiting> list = getHibernateTemplate().find("from Activiting a where a.id.ARId=?", actid);
        return list;
    }

    @Override
    public void addActiviting(Activiting acting) {
        // TODO Auto-generated method stub
        this.getHibernateTemplate().save(acting);

    }

    @Override
    public List<Map<String, Object>> findActqiandao(int id) {
        Session ssn = getSession();
        Query query = ssn.createQuery(
                "from Activiting a where A_devicecode=null and A_secdevicecode=null and A_thirddevicecode=null"
                        + " and A_fourdevicecode=null and A_status=1  and a.id.ARId=" + id);
        List<Activiting> l = query.list();
        ssn.close();
        List<Map<String, Object>> listg = new ArrayList<>();
        if (l != null && l.size() > 0) {
            int len = l.size();

            Map<String, Object> map;
            List<Students> list = null;
            Object[] values = new Object[len];
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                values[j] = l.get(j).getId().getASId();
                if (j != len - 1) {
                    sb.append("?,");
                } else {
                    sb.append("?");
                }
            }
            list = getHibernateTemplate().find("FROM Students t WHERE t.id.SStudentId IN (" + sb.toString() + ")",
                    values);
            if (list != null && list.size() > 0) {
                for (Activiting a : l) {
                    for (Students stu : list) {
                        if (stu.getId().getSStudentId().equals(a.getId().getASId())) {
                            map = new HashMap<String, Object>();
                            map.put("id", stu.getId().getSStudentId());
                            map.put("name", stu.getSName());
                            map.put("college", stu.getSCollege());
                            map.put("clasz", stu.getSClass());
                            map.put("iphone", stu.getSPhone());
                            listg.add(map);
                        }
                    }
                }
            }

        }
        return listg;
    }

    @Override
    public List<Map<String, Object>> allProActing(int i, String sProfession) {
        Session session = getSession();
        java.text.NumberFormat format = java.text.NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(2);
        String a[] = {"博雅读书", "博雅心情", "博雅实践", "博雅讲坛", "博雅修身", "博雅视野"};
        String b[] = {"商学院", "信息工程学院", "人文学院", "机械工程学院", "外国语学院", "建筑学院", "设计学院", "理学院", "中旅学院"};
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Students> stuli;
        Map<String, Object> map;
        int allnumber = 0, allnumber0 = 0, allnumber1 = 0;
        String sql, sql2;
        // 新方法
        int number = getHibernateTemplate().find("from Students s where s.SProfession=?", sProfession).size();
        for (i += 2; i < 8; i++) {
            sql = "select count(*) from (SELECT sum(b.r_credit) as grade from activiting a,activity b,students s where  a.A_R_id=b.r_id"
                    + " and (a.A_grade=1 or a.A_grade=2) and a.A_S_id=s.s_studentId and b.r_type=" + i
                    + " and s.s_profession='" + sProfession
                    + "' group by s.s_studentId order by s.s_studentId) t WHERE t.grade>=1 and t.grade<2";
            sql2 = "select count(*) from (SELECT sum(b.r_credit) as grade from activiting a,activity b,students s where  a.A_R_id=b.r_id"
                    + " and (a.A_grade=1 or a.A_grade=2) and a.A_S_id=s.s_studentId and b.r_type=" + i
                    + " and s.s_profession='" + sProfession
                    + "' group by s.s_studentId order by s.s_studentId) t WHERE t.grade>=2";
            map = new HashMap<String, Object>();
            int grade2 = 0, grade1 = 0, grade0 = 0;
            // super.releaseSession(session);
            grade1 = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
            // getSession().flush();
            grade2 = Integer.valueOf(session.createSQLQuery(sql2).uniqueResult().toString());
            grade0 = number - grade1 - grade2;
            map.put("name", a[i - 2]);
            allnumber += number;
            map.put("number", number);
            allnumber0 += grade0;
            map.put("number0", grade0);
            allnumber1 += grade1;
            map.put("number1", grade1);
            map.put("number2", grade2);
            if (number == 0) {
                number = 1;
            }
            // System.out.println(grade0/number+","+(double)grade0/number);
            map.put("bnumber0", format.format((double) grade0 / number));
            map.put("bnumber1", format.format((double) grade1 / number));
            map.put("bnumber2", format.format((double) grade2 / number));
            list.add(map);
        }
        session.close();
        return list;
    }

    @Override
    public List<Map<String, Object>> ActStudentRecordNew(int exportType, String students, String twocollege,
                                                         String nianji, String profession, String classz) {
        List<Activity> aclist = null;
        List<Map<String, Object>> l = new ArrayList<>();
        List<Students> s;
        if (students.length() > 5) {
            s = getHibernateTemplate().find("from Students s where s.id.SStudentId in (" + students + ")");
        } else {
            if (twocollege.equals("all")) {
                if (nianji.equals("all")) {
                    s = getHibernateTemplate().find("from Students");
                } else {
                    s = getHibernateTemplate().find("from Students s where s.SClass like '%" + nianji + "%'");
                }
            } else {
                if (profession.equals("all")) {
                    if (nianji.equals("all")) {
                        s = getHibernateTemplate().find("from Students s where s.SCollege=?", twocollege);
                    } else {
                        s = getHibernateTemplate().find(
                                "from Students s where s.SCollege=? and s.SClass like '%" + nianji + "%'", twocollege);
                    }
                } else {
                    if (classz.equals("all")) {
                        if (nianji.equals("all")) {
                            s = getHibernateTemplate().find("from Students s where s.SProfession=?", profession);
                        } else {
                            s = getHibernateTemplate().find(
                                    "from Students s where s.SProfession=? and s.SClass like '%" + nianji + "%'",
                                    profession);
                        }
                    } else {
                        s = getHibernateTemplate().find("from Students s where s.SClass=?", classz);
                    }

                }
            }
        }
        // s = getHibernateTemplate().find("from Students s where s.SCollege=?",
        // b[i - 1]);
        List<Activiting> list = getHibernateTemplate().find("from Activiting a where a.AGrade=1 or a.AGrade=2 ");
        List<Activiting> list2 = getHibernateTemplate().find("from Activiting a where a.AGrade=1 or a.AGrade=2 group by a.id.ARId ");
        int len = list2.size();
        Object[] values = new Object[len];
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < len; j++) {
            values[j] = list2.get(j).getId().getARId();
            if (j != len - 1)
                sb.append("?,");
            else
                sb.append("?");
        }
        aclist = getHibernateTemplate().find("FROM Activity t WHERE t.RId IN (" + sb.toString() + ")", values);
        if (list.size() != 0) {
            for (Activiting ac : list) {
                for (Activity entity1 : aclist)
                    if (ac.getId().getARId().equals(entity1.getRId())) {
                        //System.out.println(entity1.getRType());
                        ac.setActivity(entity1);
                        break;
                    }
            }
        }
        //System.out.println("开始"+list.size());
        // System.out.println("添加活动信息"+exportType);
        double record1, record2, record3, record4, record5;
        int acItem;
        double credit;
        Map<String, Object> map;
        if (s.size() != 0) {
            for (Students stu : s) {
                map = new LinkedHashMap<>();
                record1 = 0;
                record2 = 0;
                record3 = 0;
                record4 = 0;
                record5 = 0;
                map.put("id", stu.getId().getSStudentId());
                map.put("name", stu.getSName());
                map.put("college", stu.getSCollege());
                map.put("profession", stu.getSProfession());
                map.put("class", stu.getSClass());
                for (Activiting acr : list) {

                    if (acr.getActivity() != null) {
                        if (acr.getId().getASId().equals(stu.getId().getSStudentId())
                                && acr.getActivity().getRType().equals(exportType)) {

                            acItem = acr.getActivity().getRItem();
                            credit = acr.getActivity().getRCredit();
                            switch (exportType) {
                                case 2: // (博雅读书)
                                    if (acItem == 1) {
                                        record1 += credit;
                                    } else if (acItem == 2) {
                                        record2 += credit;
                                    } else if (acItem == 3) {
                                        record3 += credit;
                                    }
                                    break;
                                case 3:// (博雅心情)
                                    if (acItem == 4) {
                                        record1 += credit;
                                    } else if (acItem == 5) {
                                        record2 += credit;
                                    } else if (acItem == 6) {
                                        record3 += credit;
                                    }
                                    break;
                                case 4:// (博雅实践)
                                    if (acItem == 7) {
                                        record1 += credit;
                                    } else if (acItem == 8) {
                                        record2 += credit;
                                    }
                                    break;
                                case 5:// (博雅讲坛)
                                    //System.out.println(credit);
                                    if (acItem == 9) {
                                        record1 += credit;
                                    } else if (acItem == 10) {
                                        record2 += credit;
                                    } else if (acItem == 11) {
                                        record3 += credit;
                                    } else if (acItem == 12) {
                                        record4 += credit;
                                    } else if (acItem == 13) {
                                        record5 += credit;
                                    }
                                    break;
                                case 6:// (博雅修身)
                                    if (acItem == 14) {
                                        record1 += credit;
                                    } else if (acItem == 15) {
                                        record2 += credit;
                                    } else if (acItem == 16) {
                                        record3 += credit;
                                    } else if (acItem == 17) {
                                        record4 += credit;
                                    }
                                    break;
                                case 7:// (博雅视野)
                                    if (acItem == 18) {
                                        record1 += credit;
                                    } else if (acItem == 19) {
                                        record2 += credit;
                                    } else if (acItem == 20) {
                                        record3 += credit;
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        System.out.println("空" + acr.getId().getARId());
                        continue;
                    }
                }

                map.put("record1", record1);
                map.put("record2", record2);
                map.put("record3", record3);
                map.put("record4", record4);
                map.put("record5", record5);
                l.add(map);
            }
        }
        if (l != null)
            return l;
        else
            return null;
    }

    @Override
    public List<Map<String, Object>> allActingNew(String twocollege, String nianji, String profession, String classz, int type) {
        Session session = getSession();
        java.text.NumberFormat format = java.text.NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(2);
        String hql = "", hqlz = "", hqlj = "", sql = "", sql2 = "", sqlj = "";
        boolean allcollege = false;
        int status = 0;
        if ("all".equals(classz)) {
            if ("all".equals(profession)) {
                if ("all".equals(twocollege)) {
                    allcollege = true;
                    hqlz = "from Students";
                    hqlj = " group by SCollege";
                    status = 1;
                } else {
                    hqlz = "from Students where SCollege='" + twocollege + "'";
                    hqlj = " group by SProfession";
                    status = 2;
                }
            } else {
                hqlz = "from Students where SProfession='" + profession + "'";
                hqlj = " group by SClass";
                status = 3;
            }
        } else {
            hqlz = "from Students where SClass='" + classz + "'";
            hqlj = " group by SClass";
            status = 3;
        }
        if ("all".equals(nianji)) {
            hqlz += hqlj;
        } else {
            status += 10;
            if (allcollege) {
                hqlz += " where SClass like '%" + nianji + "%' " + hqlj;
            } else {
                hqlz += " and SClass like '%" + nianji + "%' " + hqlj;
            }
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Students> stuli;
        Map<String, Object> map;
        int allnumber = 0, allnumber0 = 0, allnumber1 = 0;
        System.out.println(hqlz);
        List<Students> stulist = this.getHibernateTemplate().find(hqlz);

        for (Students s : stulist) {
            hql = "select *from students s where ";
            switch (status) {
                case 1:
                    sqlj = " s.s_college='" + s.getSCollege() + "'";
                    break;
                case 2:
                    sqlj = " s.s_profession='" + s.getSProfession() + "'";
                    break;
                case 3:
                    sqlj = " s.s_class='" + s.getSClass() + "'";
                    break;
                case 11:
                    sqlj = " s.s_college='" + s.getSCollege() + "' and s.s_class like '%" + nianji + "%' ";
                    break;
                case 12:
                    sqlj = " s.s_profession='" + s.getSProfession() + "' and s.s_class like '%" + nianji + "%' ";
                    break;
                case 13:
                    sqlj = " s.s_class='" + s.getSClass() + "' and s.s_class like '%" + nianji + "%' ";
                    break;
            }
            //System.out.println(hql+sqlj);

            List<Students> slist = session.createSQLQuery((hql + sqlj)).list();
            int number = slist.size();
            sql = "select count(*) from (SELECT sum(b.r_credit) as grade from activiting a,activity b,students s where  a.A_R_id=b.r_id"
                    + " and (a.A_grade=1 or a.A_grade=2) and a.A_S_id=s.s_studentId and b.r_type=" + type + " and " + sqlj
                    + " group by s.s_studentId order by s.s_studentId) t WHERE  t.grade>=1 and t.grade<2";
            sql2 = "select count(*) from (SELECT sum(b.r_credit) as grade from activiting a,activity b,students s where  a.A_R_id=b.r_id"
                    + " and (a.A_grade=1 or a.A_grade=2) and a.A_S_id=s.s_studentId and b.r_type=" + type + " and " + sqlj
                    + " group by s.s_studentId order by s.s_studentId) t WHERE t.grade>=2";
            map = new HashMap<String, Object>();
            int grade2 = 0, grade1 = 0, grade0 = 0;
            //System.out.println(sql);
            //System.out.println(sql2);
            grade1 = Integer.valueOf(session.createSQLQuery(sql).uniqueResult().toString());
            // getSession().flush();
            grade2 = Integer.valueOf(session.createSQLQuery(sql2).uniqueResult().toString());
            grade0 = number - grade1 - grade2;
            switch (status) {
                case 1:
                    map.put("name", s.getSCollege());
                    break;
                case 2:
                    map.put("name", s.getSProfession());
                    break;
                case 3:
                    map.put("name", s.getSClass());
                    break;
                case 11:
                    map.put("name", s.getSCollege());
                    break;
                case 12:
                    map.put("name", s.getSProfession());
                    break;
                case 13:
                    map.put("name", s.getSClass());
                    break;
            }

            allnumber += number;
            map.put("number", number);
            allnumber0 += grade0;
            map.put("number0", grade0);
            allnumber1 += grade1;
            map.put("number1", grade1);
            map.put("number2", grade2);
            if (number == 0) {
                number = 1;
            }
            // System.out.println(grade0/number+","+(double)grade0/number);
            map.put("bnumber0", format.format((double) grade0 / number));
            map.put("bnumber1", format.format((double) grade1 / number));
            map.put("bnumber2", format.format((double) grade2 / number));
            list.add(map);

        }
        map = new HashMap<String, Object>();
        map.put("name", "全部");
        map.put("number", allnumber);
        map.put("number0", allnumber0);
        map.put("number1", allnumber1);
        map.put("number2", allnumber - allnumber0 - allnumber1);
        if (allnumber == 0) {
            allnumber = 1;
        }
        map.put("bnumber0", format.format((double) allnumber0 / allnumber));
        map.put("bnumber1", format.format((double) allnumber1 / allnumber));
        map.put("bnumber2", format.format((double) (allnumber - allnumber0 - allnumber1) / allnumber));
        list.add(map);
        session.close();
        return list;
    }

    @Override
    public List<Map<String, Object>> getStudentsGrade(String twocollege, String nianji, String profession, String classz) {
        Session session = getSession();
        java.text.NumberFormat format = java.text.NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(2);
        String hql = "", hqlz = "", hqlj = "", sql = "", sql2 = "", sqlj = "";
        boolean allcollege = false;
        int status = 0;
        if ("all".equals(classz)) {
            if ("all".equals(profession)) {
                if ("all".equals(twocollege)) {
                    allcollege = true;
                    hqlz = "from Students";
//                    hqlj = " group by SCollege";
                    status = 1;
                } else {
                    hqlz = "from Students where SCollege='" + twocollege + "'";
//                    hqlj = " group by SProfession";
                    status = 2;
                }
            } else {
                hqlz = "from Students where SProfession='" + profession + "'";
//                hqlj = " group by SClass";
                status = 3;
            }
        } else {
            hqlz = "from Students where SClass='" + classz + "'";
//            hqlj = " group by SClass";
            status = 3;
        }
        if ("all".equals(nianji)) {
            hqlz += hqlj;
        } else {
            status += 10;
            if (allcollege) {
                hqlz += " where SClass like '%" + nianji + "%' " + hqlj;
            } else {
                hqlz += " and SClass like '%" + nianji + "%' " + hqlj;
            }
        }
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        List<Students> stuli;

        int allnumber = 0, allnumber0 = 0, allnumber1 = 0;
        System.out.println(hqlz);
        List<Students> stulist = session.createQuery(hqlz).list();

        for (Students s : stulist) {
            Map<String, Object> map = new HashMap<>();
            for (int type = 2; type <= 7; type++){
                String gradeSql = "SELECT IFNULL(SUM(a.A_grade),0) FROM activiting a, activity b WHERE a.A_R_id = b.r_id AND a.A_S_id = " + s.getId().getSStudentId() + " AND b.r_type = " + type;
                int grade = Integer.valueOf(session.createSQLQuery(gradeSql).uniqueResult().toString());
                map.put("number" + type, grade);
            }

            map.put("name", s.getSName());
            list.add(map);
        }
//        map = new HashMap<String, Object>();
//        map.put("name", "全部");
//        map.put("number0", 1);
//        map.put("number1", 1);
//        map.put("number2", 1);
//        map.put("number3", 1);
//        map.put("number4", 1);
//        map.put("number5", 1);
//        map.put("bnumber2", format.format((double) (allnumber - allnumber0 - allnumber1) / allnumber));
//        list.add(map);
        session.close();
        return list;
    }


}
