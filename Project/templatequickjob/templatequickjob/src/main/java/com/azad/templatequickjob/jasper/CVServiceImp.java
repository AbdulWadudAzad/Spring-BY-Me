package com.azad.templatequickjob.jasper;

import com.azad.templatequickjob.entity.JobPost;
import com.azad.templatequickjob.entity.User;
import com.azad.templatequickjob.repo.JobPostRepo;
import com.azad.templatequickjob.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("cvservice")
public class CVServiceImp implements CVService {
   @Autowired
    private UserRepo repo;

    @Override
    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
        for(User user:repo.findAll()){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", user.getId());
            item.put("firstName", user.getFirstName());
            item.put("lastName", user.getLastName());
            item.put("email", user.getEmail());
            item.put("userName", user.getUserName());


            result.add(item);
        }
        return result;

    }
}
