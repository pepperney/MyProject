package cn.com.pepper.secondkill.model;

/**
 * 封装执行秒杀后的结果:是否秒杀成功 Created by codingBoy on 16/11/27.
 */
public class SeckillExecution {

	private long seckillId;

	// 秒杀执行结果的状态
	private int state;

	// 状态的明文标识
	private String stateInfo;

	// 当秒杀成功时，需要传递秒杀成功的对象回去
	private SeckillRecord successKilled;

	// 秒杀成功返回所有信息
	public SeckillExecution(long seckillId, Integer state, SeckillRecord successKilled) {
		this(seckillId, state);
		this.successKilled = successKilled;
		this.stateInfo = "success";
	}

	// 秒杀失败
	public SeckillExecution(long seckillId, Integer state) {
		this.seckillId = seckillId;
		this.state = state;
		this.stateInfo = "fail";
	}

	public long getSeckillId() {
		return seckillId;
	}

	public void setSeckillId(long seckillId) {
		this.seckillId = seckillId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SeckillRecord getSuccessKilled() {
		return successKilled;
	}

	public void setSuccessKilled(SeckillRecord successKilled) {

		this.successKilled = successKilled;
	}

}
