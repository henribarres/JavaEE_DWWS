package br.ufes.inf.nemo.javahostel.control;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.ufes.inf.nemo.javahostel.application.RegistrationService;
import br.ufes.inf.nemo.javahostel.application.UnderAgeException;
import br.ufes.inf.nemo.javahostel.domain.Guest;

@Named @SessionScoped
public class RegistrationController implements Serializable {
	@EJB
	private RegistrationService registrationService;
	
	private Guest guest = new Guest();
	private int age;
	
	public Guest getGuest() {
		return guest;
	}
	
	public int getAge() {
		return age;
	}
	
	public String register() {
		try {
			registrationService.register(guest);
		}
		catch (UnderAgeException e) {
			age = e.getAge();
			return "/registration/underage.xhtml";
		}
		
		return "/registration/success.xhtml";
	}
}
