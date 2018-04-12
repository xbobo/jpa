package com.xiaobo.test;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xiaobo.Application;
import com.xiaobo.bean.ManyToManyThird;
import com.xiaobo.bean.OneFirst;
import com.xiaobo.bean.OneToManyFirst;
import com.xiaobo.bean.OneToManySecond;
import com.xiaobo.service.ManyToManyThirdService;
import com.xiaobo.service.OneFirstService;
import com.xiaobo.service.OneToManyFirstService;
import com.xiaobo.service.OneToManySecondService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ServiceTest {

	@Autowired
	OneFirstService oneFirstService;
	
	@Autowired
	OneToManyFirstService oneToManyFirstService;
	
	@Autowired
	OneToManySecondService oneToManySecondService;
	
	@Autowired
	ManyToManyThirdService manyToManyThirdService;
	
	/*@Test
	public void oneFirstTest(){
		List<OneFirst> lists = oneFirstService.findAll();
		System.out.println(lists.size());
	}*/
	
	@Test
	public void oneToManyFirstTest(){
		/*try {
			List<OneToManyFirst> lists = oneToManyFirstService.findAll();
			System.out.println(lists.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		List<OneToManySecond> lists = oneToManySecondService.findAll();
		for(OneToManySecond single:lists){
			System.out.println(single.getId()+","+single.getFirst());
			OneToManyFirst first = single.getFirst();
			System.out.println(first.getName());
			Set<ManyToManyThird> thirds = single.getThirds();
			for(ManyToManyThird third:thirds){
				System.out.println(third.getThirdName());
			}
			System.out.println(thirds.size());
		}
		/*List<ManyToManyThird> lists = manyToManyThirdService.findAll();
		for(ManyToManyThird third:lists){
			System.out.println(third.getId()+","+third.getThirdName());
			Set<OneToManySecond> seconds = third.getSeconds();
			for(OneToManySecond second:seconds){
				System.out.println(second.getFirst().getName());
			}
			System.out.println(seconds.size());
		}*/
		/*ManyToManyThird single = manyToManyThirdService.findById(1l);
		System.out.println(single.getThirdName());
		Set<OneToManySecond> seconds = single.getSeconds();
		for(OneToManySecond second:seconds){
			System.out.println(second.getFirst().getName());
		}
		System.out.println(seconds.size());*/
		
	}
}
