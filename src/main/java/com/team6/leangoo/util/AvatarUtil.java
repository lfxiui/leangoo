package com.team6.leangoo.util;

import java.util.Random;

/**
 * Created by AgZou on 2017/10/13.
 */
public final class AvatarUtil {
    private static final String[] avatars={"./img/one.jpg","./img/two.jpg","./img/three.jpg","./img/four.jpg","./img/five.jpg"
    ,"./img/six.jpg","./img/seven.jpg"};
    public static String getAvatar(){
        int max=6;
        int min=0;
        Random random=new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return avatars[s];
    }
}
