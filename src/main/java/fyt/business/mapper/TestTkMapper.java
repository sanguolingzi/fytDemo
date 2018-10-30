package fyt.business.mapper;

import fyt.business.pojo.TestTk;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface TestTkMapper extends Mapper<TestTk> {

    void insertTestAdd(@Param("paraMap") Map<String,Object> paraMap);

}