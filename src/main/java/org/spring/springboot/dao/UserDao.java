package org.spring.springboot.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.User;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserDao {
	public void insert(@Param("userName")String userName,@Param("passWord")String passWord);
	
	public User findByNameAndWord(@Param("userName")String userName,@Param("passWord")String passWord);

	public User findByName(@Param("userName")String userName);

	public User findById(@Param("userId")String userId);
	
	public void updatePassword(@Param("userId")String userId, @Param("passWord")String passWord);
	
	public void updateHW(@Param("user")User user);
}
