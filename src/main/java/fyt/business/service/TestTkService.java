package fyt.business.service;

import fyt.business.pojo.TestTk;

import java.util.List;
import java.util.Map;


public interface TestTkService {

    public void add(TestTk testTk);

    public int update(TestTk testTk);

    public TestTk selectOne(TestTk testTk);

    public List<TestTk> selectAll(TestTk testTk);

    public void testTransaction() throws Exception;

    void insertTestAdd(Map<String,Object> paraMap);
}
