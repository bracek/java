package com.att.uchannels.service;

import com.att.uchannels.AbstractWebTest;
import com.att.uchannels.TestInterface;
import com.att.uchannels.domain.Entity;
import junit.framework.Assert;

import javax.annotation.Resource;

/**
 * Created by miroslav.katrak on 4.9.2014.
 */
public class EntityServiceImplTest extends AbstractWebTest implements TestInterface {
    @Resource
    private EntityService entityService;


    @Override
    public void testInsert() {

    }

    @Override
    public void testUpdate() {

    }

    @Override
    public void testLoad() {
        Entity attr = null;
        try {
            attr = entityService.getEntity(TEST_ATTRIBUTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(attr.getId() > 0);


    }

    @Override
    public void testDelete() {

    }
}
