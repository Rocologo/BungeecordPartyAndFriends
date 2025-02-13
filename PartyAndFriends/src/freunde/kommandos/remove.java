package freunde.kommandos;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.StringTokenizer;

import mySql.mySql;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class remove {

	public static void entfernen(ProxiedPlayer player, String[] args,
			mySql verbindung) throws SQLException {
		if (args.length == 2) {
			int idAbfrage = verbindung.getIDByPlayerName(args[1]);
			String senderName = player.getName();
			int idSender = verbindung.getIDByPlayerName(senderName);
			String zuTrennen = verbindung.freundeAusgeben(idSender);
			int[] anfragenID = new int[0];
			StringTokenizer st = new StringTokenizer(zuTrennen, "|");
			while (st.hasMoreTokens()) {
				Object newArray = Array.newInstance(anfragenID.getClass()
						.getComponentType(), Array.getLength(anfragenID) + 1);
				System.arraycopy(anfragenID, 0, newArray, 0,
						Array.getLength(anfragenID));
				anfragenID = (int[]) newArray;
				anfragenID[Array.getLength(anfragenID) - 1] = Integer
						.parseInt(st.nextToken());
			}
			boolean gefunden = false;
			int i = 0;
			while (anfragenID.length > i && gefunden == false) {
				if (anfragenID[i] == idAbfrage) {
					gefunden = true;
				}
				i++;
			}
			if (gefunden == true) {
				verbindung.freundLoeschen(idSender, idAbfrage, 0);
				player.sendMessage(new TextComponent("�8[�5�lFriends�8]"
						+ ChatColor.RESET + "�7 Du hast den Freund �e"
						+ args[1] + " �7entfernt"));
			} else {
				player.sendMessage(new TextComponent("�8[�5�lFriends�8]"
						+ ChatColor.RESET + " �7Du �7bist �7nicht �7mit �e"
						+ args[1] + " �7befreundet"));
			}
		} else {
			if (args.length < 2) {
				player.sendMessage(new TextComponent("�8[�5�lFriends�8]"
						+ ChatColor.RESET
						+ "�7 Sie m�ssen einen Spieler angeben"));
				player.sendMessage(new TextComponent(
						"�8/�5friends �5remove �5[Name �5des �5Spielers]"
								+ ChatColor.RESET
								+ " �8- �7entfernt �7einen �7Freund"));
			} else {
				player.sendMessage(new TextComponent("�8[�5�lFriends�8]"
						+ ChatColor.RESET + "�7 Zu viele Argumente"));
				player.sendMessage(new TextComponent(
						"�8/�5friends �5remove �5[Name �5des �5Spielers]"
								+ ChatColor.RESET
								+ " �8- �7entfernt �7einen �7Freund"));
			}
		}
	}

}
