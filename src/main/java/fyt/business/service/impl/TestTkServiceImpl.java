package fyt.business.service.impl;

import fyt.business.mapper.TestTkMapper;
import fyt.business.pojo.TestTk;
import fyt.business.service.TestService;
import fyt.business.service.TestTkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(rollbackFor = Exception.class)
public class TestTkServiceImpl implements TestTkService {

    @Autowired
    private TestTkMapper testTkMapper;

    @Autowired
    private TestService testServiceImpl;

    @Override
    public void add(TestTk testTk) {
        testTkMapper.insertSelective(testTk);
    }

    @Override
    public int update(TestTk testTk) {
        return testTkMapper.updateByPrimaryKeySelective(testTk);
    }

    @Override
    public TestTk selectOne(TestTk testTk) {
        return testTkMapper.selectOne(testTk);
    }

    @Override
    public List<TestTk> selectAll(TestTk testTk) {
        return testTkMapper.select(testTk);
    }

    @Override
    public void testTransaction() throws Exception{
        TestTk testTk = new TestTk();
        testTk.setDescription("测试事务");
        this.add(testTk);

        if(true)
            throw new Exception("测试事务是否正确!");

        testServiceImpl.testInsert("ak");
    }
}
