package me.com.mxreports.commands;

import java.util.GregorianCalendar;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.com.mxreports.managers.ReportManager;
import me.com.mxreports.managers.Storage;
import me.com.mxreports.object.Report;
import rcore.command.RCommand;

public class ReportCommand extends RCommand {

	@Override
	public boolean canConsolePerform() {
		return false;
	}

	@Override
	public List<String> getAliases() {
		return null;
	}

	@Override
	public String getCommand() {
		return "reporttt";
	}

	@Override
	public List<Object> getDescription() {
		return null;
	}

	@Override
	public int getHelpPageSize() {
		return 0;
	}

	@Override
	public List<Object> getHelpPages() {
		return null;
	}

	@Override
	public String getNextHelpPageCommand() {
		return null;
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String getPreviousHelpPageCommand() {
		return null;
	}

	@Override
	public void perform() {

		if (this.asPlayer().hasPermission("report.use")) {
			if (argsLength() < 2) {
				this.asPlayer().sendMessage("§cSintaxe incorreta! Utilize: /reportar (nomeDoJogador)");
			} else {
				Player player = Bukkit.getPlayer(getArgs()[0]);
				if (player != null) {
					StringBuilder sb = new StringBuilder();
					for (int i = 1; i < argsLength(); i++) {
						sb.append(getArgs()[i] + " ");
					}
					GregorianCalendar calendar = new GregorianCalendar();
					String reason = sb.toString();
					Report report = new Report(player.getName(), this.asPlayer().getName(), reason, calendar);
					ReportManager.addReport(player.getName(), report);
					Storage.set(report);
					this.asPlayer().sendMessage(
							new String[] { "", "§a§LREPORT", "§fVocê enviou uma nova denúncia com sucesso.", "" });
					Bukkit.getOnlinePlayers().forEach(on -> {
						if (on.hasPermission("report.ver")) {
							on.sendMessage(new String[] { "", "§a§LREPORT", "§FFoi registrada uma nova denuncia!",
									"§fPara visualizar a denuncia digite: §a/reports", "" });
							sendTitle(on, "§e§lREPORT", "§fUma novda denuncia foi registrada!", 10, 10, 10);
						}
					});
				} else {
					this.asPlayer().sendMessage("§cJogador não encontrado.");
				}
			}
		}

	}

	@Override
	public List<String> tabComplete() {
		return null;
	}

}
