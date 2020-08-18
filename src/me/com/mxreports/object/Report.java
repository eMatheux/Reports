package me.com.mxreports.object;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Report {

	private String reported;
	private String author;
	private String reason;
	private GregorianCalendar calendar;

	public Report(String reported, String author, String reason, GregorianCalendar calendar) {
		this.reason = reason;
		this.reported = reported;
		this.author = author;
		this.calendar = calendar;
	}

	public String getReported() {
		return reported;
	}

	public String getAuthor() {
		return author;
	}


	public String getReason() {
		return reason;
	}

	public Calendar getCalendar() {
		return calendar;
	}

}
