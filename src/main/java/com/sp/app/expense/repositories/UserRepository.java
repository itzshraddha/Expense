package com.sp.app.expense.repositories;

import com.sp.app.expense.entity.UserEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{

	List<UserEntity> findByName(String name);

}
