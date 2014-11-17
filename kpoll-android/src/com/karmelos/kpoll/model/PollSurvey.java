package com.karmelos.kpoll.model;

import android.os.Parcel;
import android.os.Parcelable;

public class PollSurvey  implements Parcelable{

	String pollname;
	String pollId;
	/**
	 * @return the pollname
	 */
	public String getPollname() {
		return pollname;
	}
	/**
	 * @param pollname the pollname to set
	 */
	public void setPollname(String pollname) {
		this.pollname = pollname;
	}
	/**
	 * @return the pollId
	 */
	public String getPollId() {
		return pollId;
	}
	/**
	 * @param pollId the pollId to set
	 */
	public void setPollId(String pollId) {
		this.pollId = pollId;
	}
	
	public static final Parcelable.Creator<PollSurvey> CREATOR  = new Creator<PollSurvey>(){
		
		public PollSurvey createFromParcel(Parcel source){
			PollSurvey poll = new PollSurvey();
			poll.pollId = source.readString();
			poll.pollname = source.readString();
			return poll;
		}
		public PollSurvey[] newArray(int size){
			return new PollSurvey[size];
		}
		
		
	};

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
		//
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(pollId);
		dest.writeString(pollname);
		
	}}
