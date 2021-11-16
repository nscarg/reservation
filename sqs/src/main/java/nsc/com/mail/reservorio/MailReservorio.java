package nsc.com.mail.reservorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import nsc.com.mail.entity.OMail;

public interface MailReservorio extends JpaRepository<OMail, Long> {
			
}
