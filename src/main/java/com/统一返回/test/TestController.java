package com.统一返回.test;

import com.统一返回.code.UserCode;
import com.统一返回.exception.CustomException;
import com.统一返回.response.PageResult;
import com.统一返回.response.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@RestController
public class TestController {


    @GetMapping("/user/{id}")
    public ResponseResult findUser(@PathVariable Integer id) {

        if (id == 1) {
            throw new CustomException(UserCode.USER_NOT_EXIST);
        }
        if (id == 2) {
            throw new NullPointerException();
        }

        User user = new User("许强", 18);

        return new ResponseResult(user);

    }




    @GetMapping("/user")
    public ResponseResult listUser(){
        ArrayList<User> users = new ArrayList<>();
        User user = new User("许强", 18);
        users.add(user);
        users.add(user);

        PageResult pageResult = new PageResult();
        pageResult.setTotal(2l);
        pageResult.setRows(users);

        return new ResponseResult(pageResult);


    }


}
