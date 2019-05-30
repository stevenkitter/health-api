package org.spring.springboot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.Plan;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PlanDao {
	
	public List<Plan> findByUserId(@Param("userId")String userId);

	public void insertPlan(@Param("plan")Plan plan);

	public void updateState();

	public void update(@Param("id")String id, @Param("state")String state);

	public void delete(@Param("id")String id);
	
}
