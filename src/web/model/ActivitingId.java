package web.model;



/**
 * ActivitingId entity. @author MyEclipse Persistence Tools
 */

public class ActivitingId  implements java.io.Serializable {


    // Fields    

     private Integer ARId;
     private String ASId;


    // Constructors

    /** default constructor */
    public ActivitingId() {
    }

    
    /** full constructor */
    public ActivitingId(Integer ARId, String ASId) {
        this.ARId = ARId;
        this.ASId = ASId;
    }

   
    // Property accessors

    public Integer getARId() {
        return this.ARId;
    }
    
    public void setARId(Integer ARId) {
        this.ARId = ARId;
    }

    public String getASId() {
        return this.ASId;
    }
    
    public void setASId(String ASId) {
        this.ASId = ASId;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ActivitingId) ) return false;
		 ActivitingId castOther = ( ActivitingId ) other; 
         
		 return ( (this.getARId()==castOther.getARId()) || ( this.getARId()!=null && castOther.getARId()!=null && this.getARId().equals(castOther.getARId()) ) )
 && ( (this.getASId()==castOther.getASId()) || ( this.getASId()!=null && castOther.getASId()!=null && this.getASId().equals(castOther.getASId()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getARId() == null ? 0 : this.getARId().hashCode() );
         result = 37 * result + ( getASId() == null ? 0 : this.getASId().hashCode() );
         return result;
   }   





}