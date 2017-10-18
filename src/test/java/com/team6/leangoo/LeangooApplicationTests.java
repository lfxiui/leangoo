package com.team6.leangoo;

import com.team6.leangoo.util.AvatarUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeangooApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println(AvatarUtil.getAvatar());
	}

}
