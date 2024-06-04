package com.oatss.mapper;
import com.oatss.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface UserMapper {
    @Insert("INSERT INTO user (id, username, password, name, id_card, telephone, role) VALUES (NULL, #{username}, #{password}, #{name}, #{idCard}, #{telephone}, #{role})")
    int insertUser(User user);
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteUserById(Integer id);
    @Update("UPDATE user SET username = #{username}, password = #{password}, name = #{name}, id_card = #{idCard}, telephone = #{telephone}, role = #{role} WHERE id = #{id}")
    int updateUserById(User user);
    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(column = "id_card", property = "idCard")
    })
    User selectUserById(Integer id);
    @Select("SELECT * FROM user WHERE username = #{username}")
    @Results({
            @Result(column = "id_card", property = "idCard")
    })
    User selectUserByUsername(String username);
    @Select("SELECT * FROM user")
    @Results({
            @Result(property = "idCard", column = "id_card")
    })
    List<User> selectAllUser();
    @Select("SELECT * FROM user WHERE CONCAT (id, username, name, id_card, telephone, role) LIKE CONCAT ('%', #{keyword},'%')")
    @Results({
            @Result(column = "id_card", property = "idCard")
    })
    List<User> selectUserByKeyword(String keyword);
}