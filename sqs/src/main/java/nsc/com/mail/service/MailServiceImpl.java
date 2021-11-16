package nsc.com.mail.service;

import java.util.List;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import nsc.com.mail.entity.OMail;
import nsc.com.mail.reservorio.MailReservorio;
import nsc.com.mail.service.MailService;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{
	private final MailReservorio mailReservorio;
	
	@Override
	public List<OMail> listAllMail() {
		return mailReservorio.findAll();
	}

	@Override
	public OMail getMail(Long id) {
		return mailReservorio.findById(id).orElse(null);
	}

	@Override
	public OMail createMail(OMail mail) {
		return mailReservorio.save(mail) ;
	}

	@Override
	public OMail updateMail(OMail mail) {
		OMail mailDB = getMail(mail.getId());
		if (mailDB == null) {
			return null;
		} else {
			return mailReservorio.save(mail);
		}
	}

	@Override
	public OMail deleteMail(Long id) {
		OMail mailDB = getMail(id);
		if (mailDB == null) {
			return null;
		} else {
			mailReservorio.deleteById(id);
			return mailDB;
		}
	}


}

