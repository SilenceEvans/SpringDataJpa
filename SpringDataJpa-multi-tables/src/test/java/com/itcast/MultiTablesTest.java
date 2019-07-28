package com.itcast;

import cn.itchenxi.dao.CustomerDao;
import cn.itchenxi.dao.LinkManDao;
import cn.itchenxi.dao.RoleDao;
import cn.itchenxi.dao.UserDao;
import cn.itchenxi.entity.Customer;
import cn.itchenxi.entity.LinkMan;
import cn.itchenxi.entity.Role;
import cn.itchenxi.entity.User;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class MultiTablesTest {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;

    /**
     * 需求：保存一个用户，一个联系人
     */
    @Test
    @Transactional
    @Rollback(false)
    public void test1(){
        Customer customer = new Customer();
        customer.setCustName("王晨北");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小北");
        LinkMan linkMan1 = new LinkMan();
        linkMan1.setLkmName("小西");
        linkMan1.setCustomer(customer);
        linkMan.setCustomer(customer);
        customerDao.save(customer);
        linkManDao.save(linkMan);
        linkManDao.save(linkMan1);
    }

    /**
     * 删除
     *   2、如果配置了放弃维护关联关系的权利，则不能删除（与外键字段是否允许为null，没有关系）
     *   因为在删除时，它根本不会去更新从表的外键字段了。
     *   因为上述原因，所以下面代码如果不进行级联操作运行会报错
     */
    @Test
    @Transactional
    @Rollback(false)
    public void test2(){
        customerDao.delete(7L);
    }

    /**
     * 级联保存
     */
    @Test
    @Transactional
    @Rollback(false)
    public void test3(){
        Customer customer = new Customer();
        customer.setCustName("王晨北");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小北");
        linkMan.setCustomer(customer);
        LinkMan linkMan1 = new LinkMan();
        linkMan1.setLkmName("小北");
        linkMan1.setCustomer(customer);
        customer.getLinkMen().add(linkMan);
        customer.getLinkMen().add(linkMan1);
        customerDao.save(customer);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void test4(){
        User user = new User();
        user.setUserName("王琛锡");
        Role role = new Role();
        role.setRoleName("程序员");
        user.getRoles().add(role);
        userDao.save(user);

    }

    @Test
    public void test5(){
        LinkMan linkMan = linkManDao.findOne(1L);
    }

}
