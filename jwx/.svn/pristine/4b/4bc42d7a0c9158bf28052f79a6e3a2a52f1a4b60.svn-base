package sdk.weishenghuo;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * @Auth popl
 * @Date 2016年8月16日 上午11:22:25
 * @authEmail popl_lu@sian.cn
 * @CalssName utils.LongIdWorker
 * @dec 
 */
public class LongIdWorker {
	private final long workerId;
	private long sequence = 0L;
	private final static long workerIdBits = 7L;
	public final static long maxWorkerId = -1L ^ -1L << workerIdBits;
	
	private final static long sequenceBits = 15L;	// 15位
	public final static long sequenceMask = -1L ^ -1L << sequenceBits;
	private static LongIdWorker longIdWorker = null;
	private long lastTimestamp = -1L;

	private Logger logger = Logger.getLogger(LongIdWorker.class);
	public LongIdWorker(final long workerId) {
		super();
		if (workerId > maxWorkerId || workerId < 0) {
			throw new IllegalArgumentException(String.format("业务编码必须在%d和0之间", maxWorkerId));
		}
		this.workerId = workerId;
	}

	public synchronized long nextId() {
		long timestamp = this.timeGen();
		if (this.lastTimestamp == timestamp) {
			this.sequence = (this.sequence + 1) & sequenceMask;
			if (this.sequence == 0) {
				// 已经到达毫秒最大数，等待下一次生成
				timestamp = this.tilNextMillis(this.lastTimestamp);
			}
		} else {
			this.sequence = 0;
		}
		if (timestamp < this.lastTimestamp) {
			try {
				long sleepTime = Math.abs(this.lastTimestamp-timestamp);
				logger.info("由于系统时间向后调整，故睡眠"+sleepTime+"ms来进行时间调整");
				Thread.sleep(sleepTime);
				logger.info("睡眠时间结束...");
			} catch (Exception e) {
				logger.warn("睡眠时间被终止...", e);
			}
		}

		this.lastTimestamp = timestamp;
		long nextId = (this.workerId << (64L - workerIdBits)) | ((timestamp << (sequenceBits))) | (this.sequence);
		// workerid << 57 | temstamp << 15 | sequence
//		System.out.println("workerid << " + (64L - workerIdBits) + " | temstamp << "+(sequenceBits)+ " | sequence");
		return nextId;
	}

	private long tilNextMillis(final long lastTimestamp) {
		long timestamp = this.timeGen();
		while (timestamp <= lastTimestamp) {
			timestamp = this.timeGen();
		}
		return timestamp;
	}

	private long timeGen() {
		return System.currentTimeMillis();
	}

	public static  long getWeishenhuoId(){
		if(longIdWorker == null){
			longIdWorker = new LongIdWorker(2L);
		}
		return longIdWorker.nextId();
	}
	public static void main(String[] args) throws IOException {
		LongIdWorker worker2 = new LongIdWorker(1);
//		LongIdWorker worker3 = new LongIdWorker(11);
		 
//		 long start = System.currentTimeMillis();
//		 for(long i = 0; i < Integer.MAX_VALUE; i++){


//		System.out.println(worker2.nextId());
//		System.out.println(worker2.nextId());
//		System.out.println(worker3.nextId());
//		System.out.println(System.currentTimeMillis());
//		System.out.println("10100100111100110000011110101010101001111".length());
//		System.out.println(new Date(1361753741828L));
//		 }
//		 System.out.println("total_use_time : "+(System.currentTimeMillis()-start)+"ms");
	}

}
