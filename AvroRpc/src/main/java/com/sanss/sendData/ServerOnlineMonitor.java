package com.sanss.sendData;

import java.io.IOException;
import java.util.Calendar;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;


public class ServerOnlineMonitor
{
	
	private static Logger log = Logger.getLogger(ServerOnlineMonitor.class);
	
	public static void main(String args[])
	{
		Monitor  m = new Monitor("tes");
		m.run();
	}
	
	public static boolean testCheck()
	{   
		boolean result = false;
//		log.info("broadCastToService start");
//		String url = "http://223.202.47.143:8080/TRACEProbeService/accept";
//		String url = "http://10.2.56.46:8080/TRACEProbeService/accept";
		//String url = "http://localhost:8080/TRACEProbeService/accept";//设置成公网URL目的地址
		String url = "http://192.168.13.74/TRACEProbeService/accept";
		//String url = "http://192.168.13.74/log/startLog";
//		String url = "http://10.2.56.47:8080/TRACEProbeServiceLogSlave/accept";
//		String url = "http://10.2.56.47:8080/TRACEProbeServiceSlaveActivity/accept";
		HttpClient client = new HttpClient();   
	    
	   //  client.getCredentialsProvider().setCredentials(new AuthScope("proxy.cmcc",8080), new UsernamePasswordCredentials("", ""));
		//设置代理开始。如果代理服务器需要验证的话，可以修改用户名和密码   
		//client.getHostConfiguration().setProxy("proxy.cmcc", 8080);
		//HttpHost proxy = new HttpHost("proxy.cmcc", 8080);

		//HttpMethod method = new GetMethod(url);
		
		PostMethod postRequest = new PostMethod(url);
		//HttpMethod postRequest = new PostMethod(url);
		//postRequest.setParams("{\"21\":{\"8\":[{\"6\":\"BCE53DA4-0AB8-4779-BB1C-A"+Math.random()+"EC0D0B7F0521392194406419\",\"9\":\"20141222164006419\"}],\"11\":[{\"1\":\"11780\",\"6\":\"BCE53DA4-0AB8"+Math.random()+"-4779-BB1C-AEC0D0B7F0521392194406419\",\"4\":[[\"activitiesView\",\"1544\"]],\"9\":\"20141222164018199\"}],\"15\":[{\"17\":\"自定义lable\",\"16\":\"tag=100\",\"50\":\"attribute\",\"9\":\"20140212164014216\",\"18\":\"1\",\"6\":\"BCE53DA4-0AB8-4779-BB1C-AEC0D0B7F0521392194406419\"}]},\"19\":{\"32\":\"iphone\",\"25\":\"BCE53DA4-0AB8-4779-BB1C-AEC0D0B7F052\",\"33\":1,\"26\":\"unknown\",\"41\":\"WIFI\",\"34\":\"iphone\",\"27\":\"iPhone Simulator\",\"42\":\"unknown\",\"35\":\"6.1\",\"28\":\"apple\",\"43\":\"unknown\",\"36\":\"unknown\",\"29\":\"1.0\",\"51\":\"iosTest\",\"44\":\"37.785834\",\"37\":\"en\",\"5\":\"139320984651624c7385dd004dad5bd4\",\"45\":\"-122.406417\",\"38\":8,\"46\":\"unknown\",\"39\":\"480*320\",\"31\":0,\"24\":\"BCE53DA4-0AB8-4779-BB1C-AEC0D0B7F052\"}}");
		//\"52\":\"appStore\",
		 java.util.Random r=new java.util.Random(); 
		 String m = String.valueOf(r.nextInt(13));
		 String month = (m.length() != 2)? "0" + m : m;
		 month = month.equals("00") ? "01" : month;
		 
		 
		 java.util.Random rd=new java.util.Random(); 
		 String md = String.valueOf(rd.nextInt(31));
		 String day = (m.length() != 2)? "0" + md : md;
		 day = day.equals("00") ? "01" : day;
		 
		//13481095503691a045efabbbba5fff54
		postRequest.setParameter("content", "{\"21\":{\"8\":[{\"6\":\"BCE53DA4-0AB8-4779-BB1C-A"+Math.random()+"EC0D0B7F0521392194406419\",\"9\":\"2014" + month + day + "164006419\"}],\"11\":[{\"1\":\"11780\",\"6\":\"BCE53DA4-0AB8"+Math.random()+"-4779-BB1C-AEC0D0B7F0521392194406419\",\"4\":[[\"activitiesView\",\"1544\"]],\"9\":\"2014" + month + day + "164018199\"}],\"15\":[{\"17\":\"自定义lable\",\"16\":\"tag=100\",\"50\":\"attribute\",\"9\":\"2014" + month + "12164014216\",\"18\":\"1\",\"6\":\"BCE53DA4-0AB8-4779-BB1C-AEC0D0B7F0521392194406419\"}]},\"19\":{\"32\":\"iphone\",\"25\":\"BCE53DA4-0AB8-4779-BB1C-AEC0D0B7F052\",\"33\":1,\"26\":\"unknown\",\"41\":\"WIFI\",\"34\":\"iphone\",\"27\":\"iPhone Simulator\",\"42\":\"unknown\",\"35\":\"6.1\",\"28\":\"apple\",\"43\":\"unknown\",\"36\":\"unknown\",\"29\":\"1.0\",\"51\":\"iosTest\",\"44\":\"37.785834\",\"37\":\"en\",\"5\":\"13481095503691a045efabbbba5fff54\",\"45\":\"-122.406417\",\"38\":8,\"46\":\"unknown\",\"39\":\"480*320\",\"31\":0,\"24\":\"BCE53DA4-0AB8-4779-BB1C-AEC0D0B7F052\"}} ");
		try   
		{
			log.info("executeMethod start");
			client.executeMethod(postRequest);
			log.info("executeMethod end");
		} 
		catch (HttpException e) 
		{
			log.error("broadCastToService http execetion ");
			e.printStackTrace();
		}  
		catch (IOException e)
		{
			log.error("broadCastToService io execetion ");
			e.printStackTrace();
		}
		// 200 is ok
		if (postRequest.getStatusCode() == 200) 
		{
			log.info("connected");
			result = true;
			postRequest.releaseConnection(); 
		}
		else 
		{
			log.info("fail");
			log.info("Status code is : " + postRequest.getStatusCode());
			try 
			{
				log.info(postRequest.getResponseBodyAsString());
			}
			catch (Exception e) 
			{
				log.info("Get response code error");
				//e.printStackTrace();
			}
			result = false;
			postRequest.releaseConnection();
		}
		
		return result;
	}
	
	public static class Monitor extends Thread 
	{
		Monitor(String name)
		{
			super(name);//调用父类带参数的构造方法
	    }
		
		public void run() 
		{	
			boolean lastTest =false;
			boolean connected = false;
			int conLostTick = 0;
			String tip ="连接消失";
			while(true)
			{
//				File his_file = new File(WINDOWS_ONLINE_TEST_LOG);
				try 
				{
//					if(!his_file.exists())
//					{
//						his_file.createNewFile();
//					}
					
//					FileWriter fw  = new FileWriter(WINDOWS_ONLINE_TEST_LOG , true);
					long time = DateUtilities.Calendar2timeString( Calendar.getInstance());
					time = time/100000;
					connected = testCheck();
					conLostTick++;
					System.out.println(conLostTick);
//					fw.write(time+ " : "+ connected  + " lost conn : " +conLostTick +"m");
//					fw.write("\r\n");
//					fw.close();
				} catch (Exception e)
				{
					e.printStackTrace();
					System.out.println("the method Monitor.run() is error");
				}
				//两次发送设定间隔
				try 
				{
					this.sleep(100);
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}