package me.com.mxreports.managers;

import java.util.HashMap;

import me.com.mxreports.object.Report;

public class ReportManager {
	
	private static HashMap<String, Report> reports = new HashMap<>();
	
	public static boolean contains(String nome) {
		return reports.containsKey(nome);
	}
	
	public static void addReport(String nome, Report report) {
		reports.put(nome, report);
	}
	public static void removeReport(String nome) {
		reports.remove(nome);
	}
	
	public static HashMap<String, Report> getReport() {
		return reports;
	}

}
