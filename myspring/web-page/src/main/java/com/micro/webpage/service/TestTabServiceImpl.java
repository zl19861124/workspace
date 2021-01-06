package com.micro.webpage.service;

import com.micro.webpage.mapper.TestTabDao;
import com.micro.webpage.model.TestTab;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestTabServiceImpl implements  TestTabService {
    @Autowired
     private TestTabDao testTabDao;

    @Override
    public List<TestTab> getAll() {
        return testTabDao.getAll();
    }
}
