package com.customlog.customlog;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CustomLogController {

    @Value("${logfile.path}")
    private String path;
    
    @Autowired
    private CustomLogService service;

    @GetMapping("/savelogs")
    public Map<String, Object> parseLog(final HttpServletResponse response) throws Exception,CustomException{
    	List<Events> logs=service.parseLog(path);
    	return RestServiceTemplateUtils.getRecordSuccessResponse(logs, response);
    }
    
    @GetMapping("/getlogs")
    public Map<String, Object> getLogs(final HttpServletResponse response) throws Exception{
    	List<Events> logs=service.getLogs();
    	return RestServiceTemplateUtils.getRecordSuccessResponse(logs, response);        
    }
}
