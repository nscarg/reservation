package nsc.com.mail.controller;


import nsc.com.mail.service.*;  
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.JsonObjectSerializer;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


import nsc.com.mail.dto.*;
import nsc.com.mail.listener.SqsListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping(value = "v1/sqs", method = RequestMethod.POST,consumes="application/json",
produces="application/json")
@RequiredArgsConstructor
public class SqsSender {
	
    private final SqsSenderService sqsSenderService;
   
    @SuppressWarnings("unchecked")
	@CrossOrigin(origins="*")
    @PostMapping(value = "/enviosMails")
    public void envios(@RequestBody String msjes )  {

    	try {
            List<QMail_AWS> lista = new ObjectMapper().readValue(msjes, new TypeReference<List<QMail_AWS>>() { });
            log.info("Received {}", lista);
            sqsSenderService.senderService(new ObjectMapper().readValue(msjes, new TypeReference<List<QMail_AWS>>() { }));    		
        } catch (Exception e) {
        	  log.info("Received Error{}",e);
        }

    }
}
