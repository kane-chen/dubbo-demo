package cn.kane.rpc.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Queue;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

public class ConcurrentPoolUtils<T> {

	public static final int PARAM_ERROR = -1 ;
	public static final int SUCCESS = 0 ;
	public static final int DUPLICATE_REQUEST = 1 ; 
	public static final int POOL_FULL = 2 ; 
	
	private int capacity = 200 ;
	private boolean isFair = true ;
	private Date execTime ;
	private long period  ;
	private Queue<Object> pool = null ;

	public ConcurrentPoolUtils(int capacity,boolean isFair,Date execTime,long period){
		if(capacity<0 || period<0 || execTime == null){
			throw new RuntimeException("illegal-params");
		}
		this.capacity = capacity ;
		this.isFair = isFair ;
		this.execTime = execTime ;
		this.period = period ;
		pool = new ArrayBlockingQueue<Object>(capacity,isFair) ;
		this.cleanDatas();
	}
	
	private void cleanDatas(){
		new Timer().scheduleAtFixedRate(new TimerTask(){
			@Override
			public void run() {
				if(null!=pool){
					pool.clear();
				}
			}
		}, execTime, period);  
	}
	
	public Date getSchdulerStartTimer(int hour,int minute,int seconds,long period){
		Calendar calendar = Calendar.getInstance() ;
		long currentMills = calendar.getTimeInMillis() ;
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, seconds);
		long execMills = calendar.getTimeInMillis() ;
		int step = (int)(currentMills-execMills)/10000 ;
		calendar.add(Calendar.MILLISECOND, (step+1)*10000);
		Date today = calendar.getTime();
		return today ;
	}
	
	public int checkConcurrencyCode(T entity){
		long startMills = System.currentTimeMillis() ;
		int retCode = 0 ;
		if(null==entity){
			retCode = PARAM_ERROR ;
		}else{
			//duplicate
			if(pool.contains(entity)){
				retCode = DUPLICATE_REQUEST ;
			}else{
				//is Full 
				boolean isPoolFull = true ;
				//add
				try{
					isPoolFull = pool.add(entity) ;
				}catch(Exception e){
					isPoolFull = true ;
				}
				if(isPoolFull){
					retCode = POOL_FULL ;
				}
				retCode = SUCCESS ;
			}
		}
		System.out.println(Thread.currentThread().getName()+" "+retCode+" "+(System.currentTimeMillis()-startMills));
		return retCode ;
	}
	
	public static void main(String[] args){
		Calendar calendar = Calendar.getInstance() ;
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date today = calendar.getTime();
		System.out.println(today);
		long period = 24*60*60*1000L ;
		final ConcurrentPoolUtils<Long> poolUtils = new ConcurrentPoolUtils<Long>(100,true,today,period);
		for(int i=0 ; i<100;i++){
			new Thread(new Runnable() {
				Random random = new Random() ;
				@Override
				public void run() {
					while(true){
						Long entity = random.nextLong() ;
						poolUtils.checkConcurrencyCode(entity) ;
					}
				}
			},"TRD-"+i).start();
		}
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public int getCapacity() {
		return capacity;
	}

	public boolean isFair() {
		return isFair;
	}

	public Date getExecTime() {
		return execTime;
	}

	public long getPeriod() {
		return period;
	}

	public Queue<Object> getPool() {
		return pool;
	}
}
