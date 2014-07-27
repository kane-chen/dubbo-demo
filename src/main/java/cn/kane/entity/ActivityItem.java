package cn.kane.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wanba_activity_item")
public class ActivityItem implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7223444015458791554L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 对应活动
	 */
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "activityId")
	private Activity activity;

	/**
	 * 触发时间
	 */
	private Date triggerTime;

	/**
	 * 发放帐号数
	 */
	private int accountNumber;

	/**
	 * 可用帐号数
	 */
	private int availableNumber;

	public String getTriggerTimeStr() {
		if (triggerTime != null)
			return new SimpleDateFormat("HH:mm:ss").format(triggerTime);
		else
			return "00:00:00";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTriggerTime() {
		return triggerTime;
	}

	public void setTriggerTime(Date triggerTime) {
		this.triggerTime = triggerTime;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAvailableNumber() {
		return availableNumber;
	}

	public void setAvailableNumber(int availableNumber) {
		this.availableNumber = availableNumber;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

}
