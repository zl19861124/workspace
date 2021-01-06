package com.micro.webpage.mapper;

import com.micro.webpage.model.TestTab;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface TestTabDao {
     //@Select("select * from testtab")
      List<TestTab> getAll();
}
