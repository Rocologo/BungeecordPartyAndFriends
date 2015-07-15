package freunde.kommandos;

import java.sql.SQLException;
import freunde.nachricht;
import mySql.mySql;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class msg extends Command {
	mySql verbindung;

	public msg(mySql pVerbindung) {
		super("msg", null, new String[] { "message", "chat" });
		verbindung = pVerbindung;
	}

	@Override
	public void execute(CommandSender commandSender, String[] args) {
		if (!(commandSender instanceof ProxiedPlayer)) {
			commandSender.sendMessage(new TextComponent("�8[�5�lFriends�8]"
					+ " Du must ein Spieler sein!"));
			return;
		}
		ProxiedPlayer player = (ProxiedPlayer) commandSender;
		try {
			nachricht.senden(player, args, verbindung, 1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
