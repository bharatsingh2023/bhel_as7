
package com.bhel.project.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bhel.project.entityImpl.BhelLogin;
import com.bhel.project.jpa.repo.BhelLoginRepository;

@Service
public class BhelLoginService {

	@Autowired
	BhelLoginRepository bhelLoginRepository;

	public String authenticateUser(BhelLogin bhelLogin) throws UsernameNotFoundException {
		Optional<BhelLogin> opBhelLogin = bhelLoginRepository.findById(bhelLogin.getId());
		if (opBhelLogin.isPresent()) {
			BhelLogin dbBhelLogin = opBhelLogin.get();
			if (bhelLogin.getPassword().equals(dbBhelLogin.getPassword()))
				return "Authenticated User";
		} else {
			return "Incorrect Password";
		}

		throw new UsernameNotFoundException("No User is not found for this username!!! ");

	}

	public String addBhelLogin(BhelLogin bhelLogin) {

		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		String encryptedPwd = bcrypt.encode(bhelLogin.getPassword());
		bhelLogin.setPassword(encryptedPwd);
		BhelLogin saveBhelLogin = bhelLoginRepository.save(bhelLogin);
		return saveBhelLogin.getEmail_id() + "added to database successfully";

	}

	public BhelLogin checkUserExist(String email) {
		// TODO Auto-generated method stub
		return bhelLoginRepository.checkEmailCount(email);
	}

	public BhelLogin updateResetPassword(BhelLogin updateLogin) {
		BhelLogin returnLogin = bhelLoginRepository.save(updateLogin);
		return returnLogin;

	}

}
