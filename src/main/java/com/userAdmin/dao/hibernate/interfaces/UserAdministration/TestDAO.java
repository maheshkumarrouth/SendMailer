package com.userAdmin.dao.hibernate.interfaces.UserAdministration;

import com.userAdmin.dao.model.UserAdministration.UserDetails;
import com.userAdmin.dao.model.UserAdministration.VerificationToken;

public interface TestDAO {
	public void save(UserDetails userDetails);
	public void save(VerificationToken ver);
}
