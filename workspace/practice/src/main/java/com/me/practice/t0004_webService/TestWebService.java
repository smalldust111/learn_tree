package com.me.practice.t0004_webService;

/**
 * 
 * @author syj
 * @date 2019年5月30日 下午7:12:57
 */
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class TestWebService {
	@WebMethod(operationName = "sayHello")
	public String sayHello(String ss) {
		return "Hello world!    hello  " + ss;
	}

	@WebMethod(operationName = "getSum")
	public int getSum(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8083/HelloWorld", new TestWebService());
		System.out.println("发布成功！");
	}

}
