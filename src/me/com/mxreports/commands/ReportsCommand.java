package me.com.mxreports.commands;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.com.mxreports.managers.ReportManager;
import me.com.mxreports.managers.Storage;
import me.com.mxreports.object.Report;
import rcore.command.RCommand;
import rcore.inventory.Inv;
import rcore.util.MakeItem;
import rcore.util.Sound;

public class ReportsCommand extends RCommand {


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
		return "reports";
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

		if (this.asPlayer().hasPermission("report.admin")) {
			switch (argsLength()) {
			case 0:
				if (ReportManager.getReport().isEmpty()) {
					this.asPlayer().sendMessage("§cO servidor não possui reports para serem visualizados.");
					break;
				}
				Locale locale = new Locale("pt","BR");
				SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm:ss", locale);
				Inv inv = new Inv(6 * 9, "Reports do servidor");
				for (String report : ReportManager.getReport().keySet()) {
					Report reportValue = ReportManager.getReport().get(report);
					inv.addItem(new MakeItem(reportValue.getReported()).setName("§aJogador: §7" + reportValue.getReported())
							.setLore(Arrays.asList("§fAutor: §7" + reportValue.getAuthor(),
									"§fMotivo do report: §7" + reportValue.getReason(),
									"§fData de envio: §7" + formatador.format(reportValue.getCalendar().getTime()), "",
									"§aClique direito para banir o jogador.",
									"§aClique esquerdo para teleportar-se até o jogador."))
							.build(), (e) -> {
								if (e.isRightClick()) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
											"ban " + reportValue.getReported() + " " + reportValue.getReason());
									this.asPlayer().closeInventory();
									this.asPlayer().sendMessage(new String[] {"", "§aGG! Você puniu o jogador "+ reportValue.getReported() + " §acom sucesso.", "" });
									ReportManager.removeReport(reportValue.getReported());
									Storage.delete(reportValue);
								}
								if (e.isLeftClick()) {
									Player player = Bukkit.getPlayer(reportValue.getReported());
									if (player != null) {
										this.asPlayer().teleport(player);
										playSound(Sound.ENTITY_ENDERMAN_TELEPORT, 10.0f, 10.0f);
									} else {
										this.asPlayer().sendMessage("§cO jogador não foi encontrado.");
										this.asPlayer().closeInventory();
									}
								}
							});
					inv.open(this.asPlayer());
					break;
				}
				break;
			default:
				this.asPlayer().sendMessage("§cSem argumentos.");
				break;
			}
		}

	}

	@Override
	public List<String> tabComplete() {
		return null;
	}

}
