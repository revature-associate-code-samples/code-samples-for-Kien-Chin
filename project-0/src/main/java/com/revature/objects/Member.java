package com.revature.objects;

public class Member {
	private int mID;
	private String fName;
	private String lName;
	private String uName;
	private String hPass;
	private int auth;
	
	public Member(int memberID, String firstName, String lastName, String userName, String hashPass, int authLVL) {
		mID = memberID;
		fName = firstName;
		lName = lastName;
		uName = userName;
		hPass = hashPass;
		auth = authLVL;
	}

	@Override
	public String toString() {
		return "Member [mID=" + mID + ", fName=" + fName + ", lName=" + lName + ", uName=" + uName + ", hPass=" + hPass
				+ ", auth=" + auth + "]";
	}

	public int getmID() {
		return mID;
	}

	public void setmID(int mID) {
		this.mID = mID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public String gethPass() {
		return hPass;
	}

	public void sethPass(String hPass) {
		this.hPass = hPass;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}
	
	
	
}
