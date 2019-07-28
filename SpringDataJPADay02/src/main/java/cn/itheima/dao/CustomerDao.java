package cn.itheima.dao;

import cn.itheima.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerDao extends JpaRepository<Customer, Long> {
    @Query(value = "from Customer where custName = ?1 and custId = ?2")
    public Customer findByJpql(String name,Long id);
}
