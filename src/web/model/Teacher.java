package web.model;

import java.sql.Timestamp;

/**
 * Teacher entity. @author MyEclipse Persistence Tools
 */

public class Teacher implements java.io.Serializable {

	// Fields

	private TeacherId id;
	private String TPassword;
	private String TName;
	private String TPhone;
	private Integer TDutyphone;
	private String TEmail;
	private String TCollege;
	private String TAddress;
	private String TOpinions;
	private String TPicture;
	private String TToken;
	private String TExpert;
	private String TResearchdirection;
	private Timestamp TChangeTime;
	private Timestamp TCreateTime;
	private Integer TFrequency;
	private Integer TType;
	private Integer TSearchnum;
	private String TIntroduction;
	private Integer TPower;

	// Constructors

	/** default constructor */
	public Teacher() {
	}

	/** minimal constructor */
	public Teacher(TeacherId id) {
		this.id = id;
	}

	/** full constructor */
	public Teacher(TeacherId id, String TPassword, String TName, String TPhone, Integer TDutyphone, String TEmail,
			String TCollege, String TAddress, String TOpinions, String TPicture, String TToken, String TExpert,
			String TResearchdirection, Timestamp TChangeTime, Timestamp TCreateTime, Integer TFrequency, Integer TType,
			Integer TSearchnum, String TIntroduction, Integer TPower) {
		this.id = id;
		this.TPassword = TPassword;
		this.TName = TName;
		this.TPhone = TPhone;
		this.TDutyphone = TDutyphone;
		this.TEmail = TEmail;
		this.TCollege = TCollege;
		this.TAddress = TAddress;
		this.TOpinions = TOpinions;
		this.TPicture = TPicture;
		this.TToken = TToken;
		this.TExpert = TExpert;
		this.TResearchdirection = TResearchdirection;
		this.TChangeTime = TChangeTime;
		this.TCreateTime = TCreateTime;
		this.TFrequency = TFrequency;
		this.TType = TType;
		this.TSearchnum = TSearchnum;
		this.TIntroduction = TIntroduction;
		this.TPower = TPower;
	}
	

	// Property accessors

	public Teacher(TeacherId id, String tPassword, String tName, String tPhone, Integer tDutyphone, String tEmail,
			String tCollege, String tAddress, String tOpinions, String tPicture, String tIntroduction) {
		super();
		this.id = id;
		TPassword = tPassword;
		TName = tName;
		TPhone = tPhone;
		TDutyphone = tDutyphone;
		TEmail = tEmail;
		TCollege = tCollege;
		TAddress = tAddress;
		TOpinions = tOpinions;
		TPicture = tPicture;
		TIntroduction = tIntroduction;
	}

	public TeacherId getId() {
		return this.id;
	}

	public void setId(TeacherId id) {
		this.id = id;
	}

	public String getTPassword() {
		return this.TPassword;
	}

	public void setTPassword(String TPassword) {
		this.TPassword = TPassword;
	}

	public String getTName() {
		return this.TName;
	}

	public void setTName(String TName) {
		this.TName = TName;
	}

	public String getTPhone() {
		return this.TPhone;
	}

	public void setTPhone(String TPhone) {
		this.TPhone = TPhone;
	}

	public Integer getTDutyphone() {
		return this.TDutyphone;
	}

	public void setTDutyphone(Integer TDutyphone) {
		this.TDutyphone = TDutyphone;
	}

	public String getTEmail() {
		return this.TEmail;
	}

	public void setTEmail(String TEmail) {
		this.TEmail = TEmail;
	}

	public String getTCollege() {
		return this.TCollege;
	}

	public void setTCollege(String TCollege) {
		this.TCollege = TCollege;
	}

	public String getTAddress() {
		return this.TAddress;
	}

	public void setTAddress(String TAddress) {
		this.TAddress = TAddress;
	}

	public String getTOpinions() {
		return this.TOpinions;
	}

	public void setTOpinions(String TOpinions) {
		this.TOpinions = TOpinions;
	}

	public String getTPicture() {
		return this.TPicture;
	}

	public void setTPicture(String TPicture) {
		this.TPicture = TPicture;
	}

	public String getTToken() {
		return this.TToken;
	}

	public void setTToken(String TToken) {
		this.TToken = TToken;
	}

	public String getTExpert() {
		return this.TExpert;
	}

	public void setTExpert(String TExpert) {
		this.TExpert = TExpert;
	}

	public String getTResearchdirection() {
		return this.TResearchdirection;
	}

	public void setTResearchdirection(String TResearchdirection) {
		this.TResearchdirection = TResearchdirection;
	}

	public Timestamp getTChangeTime() {
		return this.TChangeTime;
	}

	public void setTChangeTime(Timestamp TChangeTime) {
		this.TChangeTime = TChangeTime;
	}

	public Timestamp getTCreateTime() {
		return this.TCreateTime;
	}

	public void setTCreateTime(Timestamp TCreateTime) {
		this.TCreateTime = TCreateTime;
	}

	public Integer getTFrequency() {
		return this.TFrequency;
	}

	public void setTFrequency(Integer TFrequency) {
		this.TFrequency = TFrequency;
	}

	public Integer getTType() {
		return this.TType;
	}

	public void setTType(Integer TType) {
		this.TType = TType;
	}

	public Integer getTSearchnum() {
		return this.TSearchnum;
	}

	public void setTSearchnum(Integer TSearchnum) {
		this.TSearchnum = TSearchnum;
	}

	public String getTIntroduction() {
		return this.TIntroduction;
	}

	public void setTIntroduction(String TIntroduction) {
		this.TIntroduction = TIntroduction;
	}

	public Integer getTPower() {
		return this.TPower;
	}

	public void setTPower(Integer TPower) {
		this.TPower = TPower;
	}

}