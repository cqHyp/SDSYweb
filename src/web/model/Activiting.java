package web.model;

import java.sql.Timestamp;


/**
 * Activiting entity. @author MyEclipse Persistence Tools
 */

public class Activiting  implements java.io.Serializable {


    // Fields    

     private ActivitingId id;
     private Integer AStatus;
     private Integer AGrade;
     private String ADevicecode;
     private Integer ALevel;
     private Timestamp AFirsttime;
     private String ASecdevicecode;
     private Timestamp ASecondtime;
     private String AThirddevicecode;
     private Timestamp AThirdtime;
     private String AFourdevicecode;
     private Timestamp AFourtime;


    // Constructors

    /** default constructor */
     private Students student;
 	private Activity activity;
 	
    public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public Activiting() {
    }

	/** minimal constructor */
    public Activiting(ActivitingId id) {
        this.id = id;
    }
    
    /** full constructor */
    public Activiting(ActivitingId id, Integer AStatus, Integer AGrade, String ADevicecode, Integer ALevel, Timestamp AFirsttime, String ASecdevicecode, Timestamp ASecondtime, String AThirddevicecode, Timestamp AThirdtime, String AFourdevicecode, Timestamp AFourtime) {
        this.id = id;
        this.AStatus = AStatus;
        this.AGrade = AGrade;
        this.ADevicecode = ADevicecode;
        this.ALevel = ALevel;
        this.AFirsttime = AFirsttime;
        this.ASecdevicecode = ASecdevicecode;
        this.ASecondtime = ASecondtime;
        this.AThirddevicecode = AThirddevicecode;
        this.AThirdtime = AThirdtime;
        this.AFourdevicecode = AFourdevicecode;
        this.AFourtime = AFourtime;
    }

   
    // Property accessors

    public ActivitingId getId() {
        return this.id;
    }
    
    public void setId(ActivitingId id) {
        this.id = id;
    }

    public Integer getAStatus() {
        return this.AStatus;
    }
    
    public void setAStatus(Integer AStatus) {
        this.AStatus = AStatus;
    }

    public Integer getAGrade() {
        return this.AGrade;
    }
    
    public void setAGrade(Integer AGrade) {
        this.AGrade = AGrade;
    }

    public String getADevicecode() {
        return this.ADevicecode;
    }
    
    public void setADevicecode(String ADevicecode) {
        this.ADevicecode = ADevicecode;
    }

    public Integer getALevel() {
        return this.ALevel;
    }
    
    public void setALevel(Integer ALevel) {
        this.ALevel = ALevel;
    }

    public Timestamp getAFirsttime() {
        return this.AFirsttime;
    }
    
    public void setAFirsttime(Timestamp AFirsttime) {
        this.AFirsttime = AFirsttime;
    }

    public String getASecdevicecode() {
        return this.ASecdevicecode;
    }
    
    public void setASecdevicecode(String ASecdevicecode) {
        this.ASecdevicecode = ASecdevicecode;
    }

    public Timestamp getASecondtime() {
        return this.ASecondtime;
    }
    
    public void setASecondtime(Timestamp ASecondtime) {
        this.ASecondtime = ASecondtime;
    }

    public String getAThirddevicecode() {
        return this.AThirddevicecode;
    }
    
    public void setAThirddevicecode(String AThirddevicecode) {
        this.AThirddevicecode = AThirddevicecode;
    }

    public Timestamp getAThirdtime() {
        return this.AThirdtime;
    }
    
    public void setAThirdtime(Timestamp AThirdtime) {
        this.AThirdtime = AThirdtime;
    }

    public String getAFourdevicecode() {
        return this.AFourdevicecode;
    }
    
    public void setAFourdevicecode(String AFourdevicecode) {
        this.AFourdevicecode = AFourdevicecode;
    }

    public Timestamp getAFourtime() {
        return this.AFourtime;
    }
    
    public void setAFourtime(Timestamp AFourtime) {
        this.AFourtime = AFourtime;
    }
   








}