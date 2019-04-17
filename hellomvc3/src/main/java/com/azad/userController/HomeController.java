package com.azad.userController;

import com.azad.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    static List<User> list;

    static {
        list = new ArrayList<>();
        list.add(new User(1L, "AZAD", "012000001"));
        list.add(new User(2L, "WADUD", "012000002"));
        list.add(new User(2L, "ANAS", "012000003"));
    }

//    //get list
//    @GetMapping(value = "/list")
//    public List<User> getList() {
////    List<User> list=new ArrayList<>();
//        this.list = new ArrayList<>();
//        list.add(new User(1L, "AZAD", "0123456789"));
//        list.add(new User(2L, "WADUD", "012546456789"));
//        list.add(new User(2L, "ANAS", "012354546789"));
//        return list;
//    }
//

    @GetMapping(value = "/")
    public String home() {
        return "Hello mvc";
    }

    @GetMapping(value = "/test")
    public String test() {
        return "Hello test";
    }


    @GetMapping(value = "/user")
    public User getUser() {
        return new User(1L, "AZAD", "0123456789");
    }

//
//    //Get user by ID
//    @GetMapping(value = "/user/{id}")
//    public User getUserById(@PathVariable("id") Long userid, @PathVariable("mobile") String mo) {
//        User user =null;
//        for(User u:this.list){
//         if(userid==u.getId() && mo.equalsIgnoreCase(u.getMobile())) {
//             user=new User();
//             user=new User(u.getId(),u.getUserName(),u.getMobile());
//             break;;
//         }
//        }
//        return user;
//    }

    @GetMapping(value = "/adduser")
    public List<User> addUserToList() {
        list.add(new User(4L, "AHMAD", "012000000"));
        return list;
    }

//    @GetMapping(value = "/del/{id}")
//    public List<User> romoveUserFromList(@PathVariable{"id"} Long id){
//        list.remove(getUserBy(id));
//        return list;
//    }
//
//    long id=list.size();
//    @GetMapping(value = "/add/{userName}/{mobile}")
//    public List<User> addUserToListDinamically(@PathVariable("userName") String username,
//                                               @PathVariable("mobile") String mobile){
//        id++;
//        list.add(new User(id, username,m));
//        return list;
//    }
}
