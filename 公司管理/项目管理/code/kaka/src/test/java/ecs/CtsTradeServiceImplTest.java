/**   
 * @Project: tfs-plm-biz 
 * @Title: CtsTradeServiceImplTest.java 
 * @Package com.tfstec.plm.biz.service.ext 
 * @Description: TODO 
 * @author A8509 
 * @date 2016年11月9日 下午4:52:59 
 * @Copyright: 2016 年 前海阿拉海钜科技. All rights reserved  
 * @version V1.0   
 *//*
package ecs;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;


*//** 
 * @ClassName CtsTradeServiceImplTest  
 * @Description TODO 
 * @author A8509 
 * @date 2016年11月9日  
 *   
 *//*
public class CtsTradeServiceImplTest {

	*//** 
	 * @Title: setUpBeforeClass 
	 * @Description: TODO(这里用一句话描述这个方法的作用) 
	 * @throws java.lang.Exception 参数说明
	 * @return void    返回类型
	 *//*
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	*//**
	 * Test method for {@link com.tfstec.plm.biz.service.ext.CtsTradeServiceImpl#updateQuotaInfo(java.lang.String, java.math.BigDecimal)}.
	 *//*
	@Test
	public void testUpdateQuotaInfo() {
			Map<String,Object> map = new HashMap<String,Object>();
//			map.put("userId", "81d6c143-f2de-4618-bab6-2269848a52b1");
//			map.put("tradeId", "148058347363856628");
//			map.put("repayId", "201612010000013");
//			map.put("repayStatus", "3");
//			map.put("isSettled", "0");
//			map.put("isOverdue", "0");
//			map.put("repayTerm", "1");
//			map.put("currentIsettle", "0");
//			map.put("currentIsOverdue", "0");
//			map.put("repayDate", TimeUtil.getDate());
//			map.put("payTotalTerm", "1");
//			map.put("dataSource", "CTS");
//			map.put("reasonRemark", "还款反操作测试");
//			map.put("modifyTime", TimeUtil.getDate());
//			String rst = service.repayBusinessRollBack(GsonUtils.toJson(map));
			System.out.println(map.get("").toString());
	}
	
//	private CtsRepayService getCtsTradeService() {
//		if(null==context) init();
//		CtsRepayService ctsRepayService = (CtsRepayService) context.getBean("ctsRepayService");  
//		return ctsRepayService;
//	}

	ClassPathXmlApplicationContext context = null;
	
	public void init(){
		//putoutId = "107811150977446099";
		context = new ClassPathXmlApplicationContext(new String[] { "applicationContext.xml" }); 
		context.start();
	}

}
*/