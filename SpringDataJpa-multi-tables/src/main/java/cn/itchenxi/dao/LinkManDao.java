package cn.itchenxi.dao;

import cn.itchenxi.entity.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LinkManDao extends JpaRepository<LinkMan,Long>,
        JpaSpecificationExecutor<LinkMan> {
}
