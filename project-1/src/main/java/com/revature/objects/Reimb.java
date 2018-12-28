package com.revature.objects;

public class Reimb {
	private int rid;
	private int eid;
	private int mid;
	private int amount;
	private String reason;
	private int status;
	private String imgURL;
	
	
	
	public Reimb(int rid, int eid, int mid, int amount, String reason, int status, String imgURL) {
		super();
		this.rid = rid;
		this.eid = eid;
		this.mid = mid;
		this.amount = amount;
		this.reason = reason;
		this.status = status;
		this.imgURL = imgURL;
	}
	@Override
	public String toString() {
		return "Reimb [rid=" + rid + ", eid=" + eid + ", mid=" + mid + ", amount=" + amount + ", reason=" + reason
				+ ", status=" + status + ", imgURL=" + imgURL + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + eid;
		result = prime * result + ((imgURL == null) ? 0 : imgURL.hashCode());
		result = prime * result + mid;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		result = prime * result + rid;
		result = prime * result + status;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimb other = (Reimb) obj;
		if (amount != other.amount)
			return false;
		if (eid != other.eid)
			return false;
		if (imgURL == null) {
			if (other.imgURL != null)
				return false;
		} else if (!imgURL.equals(other.imgURL))
			return false;
		if (mid != other.mid)
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (rid != other.rid)
			return false;
		if (status != other.status)
			return false;
		return true;
	}
	public int getRid() {
		return rid;
	}
	public void setRid(int rid) {
		this.rid = rid;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getImgURL() {
		return imgURL;
	}
	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}
	
	
	
}
