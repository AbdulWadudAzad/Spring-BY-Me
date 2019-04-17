package com.azad.templatequickjob.jasper;

import com.azad.templatequickjob.entity.JobPost;
import com.azad.templatequickjob.repo.JobPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("jobPostService")
public class JobPostServiceImp implements JobPostService {
   @Autowired
    private JobPostRepo repo;

    @Override
    public List<Map<String, Object>> report() {
        List<Map<String, Object>> result=new ArrayList<Map<String, Object>>();
        for(JobPost jobPost:repo.findAll()){
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("id", jobPost.getId());
            item.put("companyName", jobPost.getCompanyName());
            item.put("description", jobPost.getJobDescription());
            item.put("fullAddress", jobPost.getFullAddress());
            result.add(item);
        }
        return result;

    }
}
