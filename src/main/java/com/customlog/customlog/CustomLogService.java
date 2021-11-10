package com.customlog.customlog;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Time;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomLogService {
	
	@Autowired
	private CustomLogRepository repository;

	public List<Events> parseLog(String path) throws CustomException, Exception {

        JSONParser parser = new JSONParser();
        String state,host,type,id;
        long timestamp;
        CustomLog cl;
        Map<String, CustomLog> map=new TreeMap<>();
        try { 
		JSONArray a = (JSONArray) parser.parse(new FileReader(path));
		  for (Object o : a)
		  {
		    JSONObject data = (JSONObject) o;
		    id= (String) data.get("id");
		    state = (String) data.get("state");
		    host = (String) data.get("host");
		    timestamp = (long) data.get("timestamp");
		    type = (String) data.get("type");
		    log.info("data->"+data);
		    if(map.get(id) != null) {
		    	cl=map.get(id);
		    	if(state.equals("FINISHED")) 
		    		cl.setFinished(timestamp);
		    	if(state.equals("STARTED"))
		    		cl.setStarted(timestamp);
		    	cl.setDuration(cl.getFinished()-cl.getStarted());
	    		if(cl.getDuration()>4)cl.setAlert(true);
		    }
		    else {
		    	cl=new CustomLog(id, type, host);
		    	if(state.equals("STARTED"))
		    		cl.setStarted(timestamp);
		    	if(state.equals("FINISHED"))
		    		cl.setFinished(timestamp);
		    	map.put(id, cl);
		    }
		  }
		  saveLog(map.values());
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e) {
        	throw e;
        } catch (ParseException e) {
        	throw e;
        } catch (Exception e) {
			// TODO Auto-generated catch block
        	throw e;
		}
        return getLogs();
	}

	private List<CustomLog> saveLog(Collection<CustomLog> values) throws CustomException {
		// TODO Auto-generated method stub
		if(values.size()>0) return repository.saveAll(values);
		throw new CustomException("NO Record Found");
	}

	public List<Events> getLogs() {
		// TODO Auto-generated method stub
		return repository.findAll().stream()
                .map(log -> new Events(log))
                .collect(Collectors.toList());
	}
	
}
