//package com.springrest.springrest.UtilClass;
//
//import java.util.Timer;
//import java.util.TimerTask;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.springrest.springrest.ServiceImpl.CryptoCoinDAOServiceImpl;
//
//public class SchedulerClass {
//	@Autowired
//	static CryptoCoinDAOServiceImpl cryptoCoinDAOServiceImpl;
//	private static Timer timer = new Timer();
//	
//	static TimerTask task = new TimerTask() {
//        public void run() {
//        	cryptoCoinDAOServiceImpl.getVotes();
//        }
//    };
//    
//    static {
//    	timer.schedule(task, 15000, 15000);
//    }
//}
