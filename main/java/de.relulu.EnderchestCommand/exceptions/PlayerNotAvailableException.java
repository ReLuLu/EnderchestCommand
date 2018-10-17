package de.web.relulu.EnderchestCommand.exceptions;

/**
 * Behandelt den Fehlerfall, dass versucht wird, die Inhalte der Kiste 
 * eines Spielers zu �ffnen, der nicht bekannt oder offline ist.
 * 
 * @author Ren� Lundi aka. ReLuLu
 *
 */
public class PlayerNotAvailableException extends Exception {

	private static final long serialVersionUID = -7530484768075871118L;

	public PlayerNotAvailableException(String playername) {
		super("Player " + playername + " is not available.");
	}
}
