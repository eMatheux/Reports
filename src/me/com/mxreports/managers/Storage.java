package me.com.mxreports.managers;


import java.util.GregorianCalendar;


import me.com.mxreports.MXReports;
import me.com.mxreports.object.Report;
import rcore.util.GsonManager;

public class Storage {
	
	public static void set(Report report) {
		GsonManager gson = new GsonManager("RPlugins/Report", "reports").prepareGson();
		gson.put("Reports." + report.getReported() + ".reason", report.getReason());
		gson.put("Reports." + report.getReported() + ".author", report.getAuthor());
		gson.put("Reports." + report.getReported() + ".dia" ,  report.getCalendar().get(GregorianCalendar.DATE));
		gson.put("Reports." + report.getReported() + ".ano" ,  report.getCalendar().get(GregorianCalendar.YEAR));
		gson.put("Reports." + report.getReported() + ".mes" ,  report.getCalendar().get(GregorianCalendar.MONTH));
		gson.put("Reports." + report.getReported() + ".hora" ,  report.getCalendar().get(GregorianCalendar.HOUR));
		gson.put("Reports." + report.getReported() + ".minuto" ,  report.getCalendar().get(GregorianCalendar.MINUTE));
		gson.put("Reports." + report.getReported() + ".segundo" ,  report.getCalendar().get(GregorianCalendar.SECOND));
		gson.save();
	}
	
	public static void load() {
		GsonManager gson = new GsonManager("RPlugins/Report", "reports").prepareGson();
		
		for(String reported : gson.getSection("Reports")) {
			String path = "Reports." + reported;
			String reason = gson.get(path + ".reason").asString();
			String author = gson.get(path + ".author").asString();
			int year = gson.getIntOr(path + ".ano", 0);
			int month = gson.getIntOr(path + ".mes", 0);
			int date = gson.getIntOr(path + ".dia", 0);
			int hourOfDay = gson.getIntOr(path + ".hora", 0);
			int minute = gson.getIntOr(path + ".minuto", 0);
			int second = gson.getIntOr(path + ".segundo", 0);
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.set(year, month, date, hourOfDay, minute, second);
			Report report = new Report(reported, author, reason, calendar);
			ReportManager.addReport(reported, report);
		}
		MXReports.getI().getLogger().info("Foram carregados " + ReportManager.getReport().size() + " reports ao total.");
	}
	public static void delete(Report report) {
		GsonManager gson = new GsonManager("RPlugins/Report", "reports").prepareGson();
		gson.remove("Reports." + report.getReported() + ".reason");
		gson.remove("Reports." + report.getReported() + ".author");
		gson.remove("Reports." + report.getReported() + ".dia");
		gson.remove("Reports." + report.getReported() + ".ano");
		gson.remove("Reports." + report.getReported() + ".mes");
		gson.remove("Reports." + report.getReported() + ".hora");
		gson.remove("Reports." + report.getReported() + ".minuto");
		gson.remove("Reports." + report.getReported() + ".segundo");
		gson.save();

	}

}
