package fyt.business.service.impl;

import fyt.business.mapper.TestTkMapper;
import fyt.business.pojo.TestTk;
import fyt.business.service.TestTkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestTkServiceImpl implements TestTkService {

    @Autowired
    private TestTkMapper testTkMapper;

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
}
