package com.ml.test.xxx.mapper.complex;

import java.util.List;
import java.util.Map;

import com.ml.test.xxx.model.complex.UserInClass;
import com.ml.test.xxx.model.simple.CategoryDictionary;
import com.ml.test.xxx.model.simple.User;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * Created by mal on 2019/5/23.
 */
public interface UserComplexMapper {

    @Select({
            "<script>                               ",
            "    select                             ",
            "        u.name `user.name`,            ",
            "        c.class_name `clazz.className` ",
            "    from                               ",
            "        t_user u,                      ",
            "        t_class c,                     ",
            "        t_user_class_mappiing m        ",
            "    where                              ",
            "        u.id = m.user_id               ",
            "        and m.class_id = c.id          ",
            "        <if test=\"id != null\">       ",
            "            and u.id = ${id}           ",
            "        </if>                          ",
            "</script>                              "
    })
    // @SelectProvider(type = UserComplexSqlProvider.class, method = "queryAllUserInClassSQL")
//    @Results(
//            id = "allUserInClassResult",
//            value = {@Result(column = "name", property = "user.name", jdbcType = JdbcType.VARCHAR),
//                     @Result(column = "class_name", property = "clazz.className", jdbcType = JdbcType.VARCHAR)})
    List<UserInClass> queryAllUserInClass(User user);

//    @Select({
//            "<script>                         ",
//            "    select                       ",
//            "        u.name `name`,           ",
//            "        c.class_name `className` ",
//            "    from                         ",
//            "        t_user u,                ",
//            "        t_class c,               ",
//            "        t_user_class_mappiing m  ",
//            "    where                        ",
//            "        u.id = m.user_id         ",
//            "        and m.class_id = c.id    ",
//            "        <if test=\"id != null\"> ",
//            "            and u.id = ${id}     ",
//            "        </if>",
//            "</script>"
//    })
//    @Results(
//            id = "allUserInClassResult",
//            value = {@Result(column = "name", property = "user.name", jdbcType = JdbcType.VARCHAR),
//                     @Result(column = "class_name", property = "clazz.className", jdbcType = JdbcType.VARCHAR)})
//    List<UserInClass> queryAllUserInClass1(User user);
//
//    @Select({
//            "<script>                         ",
//            "    select                       ",
//            "        u.name,                  ",
//            "        c.class_name             ",
//            "    from                         ",
//            "        t_user u,                ",
//            "        t_class c,               ",
//            "        t_user_class_mappiing m  ",
//            "    where                        ",
//            "        u.id = m.user_id         ",
//            "        and m.class_id = c.id    ",
//            "        <if test=\"id != null\"> ",
//            "            and u.id = ${id}     ",
//            "        </if>                    ",
//            "</script>                        "
//    })
//    Map queryMap(User user);

    @Select({
            "<script>                         ",
            "    select                       ",
            "        count(u.id) cnt          ",
            "    from                         ",
            "        t_user u,                ",
            "        t_class c,               ",
            "        t_user_class_mappiing m  ",
            "    where                        ",
            "        u.id = m.user_id         ",
            "        and m.class_id = c.id    ",
            "        <if test=\"id != null\"> ",
            "            and u.id = ${id}     ",
            "        </if>                    ",
            "</script>                        "
    })
    @ResultType(Long.class)
    void queryCnt(User user);


    @Select({
            "<script>                      ",
            "    select                    ",
            "        *                     ",
            "    from                      ",
            "        t_category_dictionary ",
            "</script>                     "
    })
    @MapKey("category")
    Map<String, CategoryDictionary> queryByCategory();

}
