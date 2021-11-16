package nsc.com.mail.service;


import java.util.List;

import nsc.com.mail.dto.*;

public interface SqsSenderService {
   String senderService(List<QMail_AWS> message);
}



