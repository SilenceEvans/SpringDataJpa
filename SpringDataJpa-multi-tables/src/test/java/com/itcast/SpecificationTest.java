package com.itcast;

import cn.itchenxi.dao.CustomerDao;
import cn.itchenxi.entity.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecificationTest {

    @Autowired
    private CustomerDao customerDao;
    /**
     * 根据单条件查询
     */
    @Test
    public void test1(){
        Specification spec = new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path custName = root.get("custName");
                Predicate predicate = criteriaBuilder.equal(custName,"王琛锡");
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 多条件查询
     */
    @Test
    public void test2(){
        Specification specification = new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Path custAddress = root.get("custAddress");
                Path custName = root.get("custName");
                Predicate p1 = cb.equal(custAddress, "西安");
                Predicate p2 = cb.equal(custName, "王晨东");
                Predicate predicate = cb.and(p1, p2);
                return predicate;
            }
        };
        Customer one = customerDao.findOne(specification);
        System.out.println(one);
    }

    /**
     * 查询王姓Customer
     */
    @Test
    public void test3(){
        Specification spec = new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Path custName = root.get("custName");
                Predicate like = cb.like(custName.as(String.class), "王%");
                return like;
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> all = customerDao.findAll(spec,sort);
        for (Customer customer :all){
            System.out.println(customer);
        }
    }

    /**
     * 进行分页查询
     */
    @Test
    public void test4(){
        Specification spec = new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Path custName = root.get("custName");
                Predicate like = cb.like(custName.as(String.class), custName);
                return like;
            }
        };
        //创建分页对象
        Pageable pageable = new PageRequest(0,2);
        Page all = customerDao.findAll(spec, pageable);
        //总条数
        System.out.println(all.getTotalElements());
        //总页数
        System.out.println(all.getTotalPages());
        //数据集合
        System.out.println(all.getContent());
    }

    /**
     * 根据封装的实体中的属性进行查询
     */
    @Test
    public void test5(){
        //创建实体
        final Customer customer = new Customer();
        customer.setCustAddress("天水");
        customer.setCustName("王琛锡");
        Specification spec = new Specification() {
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Path custName = root.get("custName");
                Path custAddress = root.get("custAddress");
                Predicate p1 = cb.equal(custName, customer.getCustName());
                Predicate p2 = cb.equal(custAddress,
                        customer.getCustAddress());
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(p1);
                list.add(p2);
                Predicate[] predicates = list.toArray(new Predicate[0]);
                return cb.and(predicates);
            }
        };
        List<Customer> customers = customerDao.findAll(spec);
        for (Customer customer1: customers ){
            System.out.println(customer1);
        }
    }



}
