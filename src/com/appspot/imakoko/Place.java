package com.appspot.imakoko;

import java.util.Calendar;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Place {
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@PrimaryKey() private Long id;

	@Persistent private String nickname;
	@Persistent private String tag;
	@Persistent private String message;
	@Persistent private double lat;
	@Persistent private double lng;
	@Persistent private Date registDate;

	public Place(String nickname,String tag,String message,double lat,double lng) {
		this.nickname = nickname;
		this.tag = tag;
		this.message = message;
		this.lat = lat;
		this.lng = lng;
		this.registDate = Calendar.getInstance().getTime();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public Date getRegistDate() {
		return registDate;
	}

	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}

	public Long getId() {
		return id;
	}
    /* 登録されたおおよその時期を返す */
    public String getElapseTime() {
        long delta = Calendar.getInstance().getTimeInMillis()
                - this.registDate.getTime();
        if (delta < 1000) {
            return "1秒未満";
        } else if (delta < 60000) {
            return (int) (delta / 1000) + "秒前";
        } else if (delta < 3600000) {
            return (int) (delta / 60000) + "分前";
        } else if (delta < 86400000) {
            return (int) (delta / 3600000) + "時間前";
        } else if (delta < 864000000) {
            return (int) (delta / 86400000) + "日前";
        } else {
            return "だいぶ前";
        }
    }
}
