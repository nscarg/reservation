package nsc.com.mail.service;


import nsc.com.mail.dto.*;
import nsc.com.mail.infraestructure.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;


@Service
@RequiredArgsConstructor
public class SqsSenderServiceImpl implements SqsSenderService {
    private final QueueInfraestructure queueInfraestructure;

    @Value("${queue.sqsTest}")
    private String urlQueue;

    @Override
    public String senderService(List<QMail_AWS> message) {
    	
    	final Gson gson = new Gson();
    	final String serializado = gson.toJson(message);
        return queueInfraestructure.sendMessage(urlQueue, serializado);
       
    }
}