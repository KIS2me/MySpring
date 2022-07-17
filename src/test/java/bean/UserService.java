package bean;

import java.util.Random;

public class UserService implements UserServiceInterface {

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "涂爷，成都";
    }

    @Override
    public String register(String name) {
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "注册用户：" + name + " success！";
    }
}


