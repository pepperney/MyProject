package cn.com.pepper.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.pepper.common.model.User;

public interface UserDao {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	User selectByUsernameAndPassword(@Param("username")String username,@Param("password")String passord,@Param("openid")String openid);

	User selectUserByOpenId(String openId);

	int weChatUnBind(User user);

	int updateBatch(List<User> userList);
	
	int insertBatch(List<User> userList);
}