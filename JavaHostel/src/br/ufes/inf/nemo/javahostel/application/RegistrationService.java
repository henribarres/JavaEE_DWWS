package br.ufes.inf.nemo.javahostel.application;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.ufes.inf.nemo.javahostel.domain.Guest;

@Stateless @LocalBean
public class RegistrationService implements Serializable {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void register(Guest guest) throws UnderAgeException {
		int age = calculateAge(guest.getBirthDate());
		if (age < 18) throw new UnderAgeException(age);
		entityManager.persist(guest);
	}

	private int calculateAge(Date birthDate) {
		if (birthDate == null) return 0;
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthDate);
		Calendar today = Calendar.getInstance();
		today.setTime(new Date(System.currentTimeMillis()));
		int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
		birth.add(Calendar.YEAR, age);
		if (birth.after(today)) age--;
		return age;
	}
}
