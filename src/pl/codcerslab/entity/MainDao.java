package pl.codcerslab.entity;

import java.util.Arrays;

public class MainDao {

    public static void main(String[] args) {
//        User user = new User();
//        user.setEmail("wer@gmail.com");
//        user.setUserName("We");
//        user.setPassword("222222");

        UserDao userDao = new UserDao();
        User[] array = userDao.findAll();
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
//        userDao.create(user);
//        user.setEmail("wera@kdjd.pl");
//        user.setUserName("Machniom");
//        user.setPassword("222222");
//        userDao.update(user);
//        System.out.println(userDao.read(1));
//        User user = userDao.read(1);
//        user.setUserName("Machnio");
//        user.setEmail("madjd@ddd.pl");
//        user.setPassword("1111111");
//        userDao.update(user);
//        userDao.delete(1);

//        userDao.update(user);
//        System.out.println(userDao.read(5));


    }
}
