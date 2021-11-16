package nsc.com.mail.service;

import java.util.List;
import nsc.com.mail.entity.OMail;

public interface MailService {
	
	public List<OMail> listAllMail();
	public OMail   	  getMail(Long id);
	public OMail 	  createMail(OMail mail);
	public OMail      updateMail(OMail mail);
	public OMail      deleteMail(Long id);
	
}


