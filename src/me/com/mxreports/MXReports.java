package me.com.mxreports;

import me.com.mxreports.commands.ReportCommand;
import me.com.mxreports.commands.ReportsCommand;
import me.com.mxreports.managers.Storage;
import rcore.plugin.RPlugin;

public class MXReports extends RPlugin{
	
	private static MXReports instance;

	public MXReports() {
		super("MXReports", "eMatheux");
	}

	@Override
	public void onPreStart() {
		
	}

	@Override
	public void onStart() {
		instance = this;
		registerCommand(new ReportCommand());
		registerCommand(new ReportsCommand());
		Storage.load();
	}

	@Override
	public void onStop() {
		
	}
	
	public static MXReports getI() { return instance; }
	
	

}
