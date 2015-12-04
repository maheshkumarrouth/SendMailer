package com.userAdmin.dao.model.UserAdministration;
// Generated 14 Nov, 2015 3:14:36 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * VerificationToken generated by hbm2java
 */
public class VerificationToken  implements java.io.Serializable {


     private Integer id;
     private UserDetails userDetails;
     private String token;
     private Date expireDate;

    public VerificationToken() {
    }

	
    public VerificationToken(Date expireDate) {
        this.expireDate = expireDate;
    }
    public VerificationToken(UserDetails userDetails,String token, Date expireDate) {
       this.userDetails = userDetails;
       this.token = token;
       this.expireDate = expireDate;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public UserDetails getUserDetails() {
        return this.userDetails;
    }
    
    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    public Date getExpireDate() {
        return this.expireDate;
    }
    
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }


	@Override
	public String toString() {
		return "VerificationToken [id=" + id + ", userDetails=" + userDetails
				+ ", token=" + token + ", expireDate=" + expireDate + "]";
	}

    


}

