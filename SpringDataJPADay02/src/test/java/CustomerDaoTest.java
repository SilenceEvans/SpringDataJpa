import cn.itheima.dao.CustomerDao;
import cn.itheima.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {
    @Autowired
    private CustomerDao customerDao;
    @Test
    public void save(){
        Customer customer = new Customer();
        customer.setCustName("王晨东");
        customerDao.save(customer);
    }
    @Test
    public void update(){
        //先查询再修改
        Customer one = customerDao.findOne(1L);
        one.setCustName("王琛锡");
        customerDao.save(one);
    }
    @Test
    public void delete(){
        customerDao.delete(1L);
    }
    @Test
    public void testJpql(){
        Customer customer = customerDao.findByJpql("王琛锡", 1L);
        System.out.println(customer.getCustName());
    }
    @Test
    public void test1(){
        Boolean aBoolean = new Boolean(true);
        Boolean bBollean = new Boolean(true);
        System.out.println(aBoolean == bBollean); //false
        System.out.println(aBoolean.equals(bBollean));//true
        System.out.println(aBoolean|bBollean);//true
        System.out.println(aBoolean&bBollean);//true
        System.out.println(aBoolean||bBollean);//true
        System.out.println(aBoolean&&bBollean);//true

    }
}
