package cn.kane.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "wanba_activity")
public class Activity implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4959430723934840739L;

	@Id
	@GeneratedValue
	private Long id;

	/**
	 * 游戏id
	 */
	private Long gameId;

	/**
	 * 活动说明
	 */
	private String intro;

	private Date startTime;

	private Date endTime;
	
	private String plat;

	/**
	 * 关联开发者信息类
	 */
	@OneToMany(mappedBy = "activity", cascade = CascadeType.ALL ,fetch=FetchType.EAGER)
	@OrderBy(value = "id desc")
	private List<ActivityItem> items;
	//活动游戏对应的服务器信息
	@Transient
	private String serverName;
	//已领取数量
	@Transient
	private Integer alreadyGet;
	//剩余数量
	@Transient
	private Integer remainCount;
	
	
	public Activity(Long id, Long gameId, String intro, Date startTime,
			Date endTime, String plat) {
		super();
		this.id = id;
		this.gameId = gameId;
		this.intro = intro;
		this.startTime = startTime;
		this.endTime = endTime;
		this.plat = plat;
	}
	public Activity(){
		
	}
	public Integer getAlreadyGet() {
		return alreadyGet;
	}

	public void setAlreadyGet(Integer alreadyGet) {
		this.alreadyGet = alreadyGet;
	}


	public Integer getRemainCount() {
		return remainCount;
	}

	public void setRemainCount(Integer remainCount) {
		this.remainCount = remainCount;
	}


	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getIntro() {
		return intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<ActivityItem> getItems() {
		return items;
	}

	public void setItems(List<ActivityItem> items) {
		this.items = items;
	}

	public String getPlat() {
		return plat;
	}

	public void setPlat(String plat) {
		this.plat = plat;
	}

}
