package pl.codcerslab.entity;

public class MainDao {

    public static void main(String[] args) {
        User user = new User();
        user.setEmail("machniom@gmail.com");
        user.setUserName("Mach");
        user.setPassword("1qaz");
        UserDao userDao = new UserDao();
        userDao.create(user);


    }
}
