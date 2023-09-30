package com.tsb.lbl;

import com.tsb.lbl.service.AppService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.web3j.protocol.Web3j;

import static org.junit.Assert.assertNotNull;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LblApplicationTests {
	@Autowired
	private Web3j web3j;
	@MockBean
	private AppService appService;
	@Test
	public void testWeb3jBeanInitialized() {
		//given web3j bean in spring-boot context

		//when app finished deploy

		//then
		assertNotNull(web3j);
	}

	//AppService testing logic is tight to the Infura API key

}
