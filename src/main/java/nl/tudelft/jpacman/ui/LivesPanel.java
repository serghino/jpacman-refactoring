package nl.tudelft.jpacman.ui;

import nl.tudelft.jpacman.level.Player;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LivesPanel extends JPanel {
    /**
     * The map of players and the labels their lives are on.
     */
    private final Map<Player, JLabel> livesLabels;

    /**
     * The default way in which the lives are shown.
     */
    public static final LivesFormatter DEFAULT_LIVES_FORMATTER =
        (Player player) -> String.format("Lives: %3d", player.getLives());

    /**
     * The way to format the lives' information.
     */
    private LivesFormatter livesFormatter = DEFAULT_LIVES_FORMATTER;

    public LivesPanel(List<Player> players) {
        super();
        assert players != null;
        setLayout(new GridLayout(1, players.size()));
        livesLabels = new LinkedHashMap<>();
        for (Player player : players) {
            JLabel liveLabel = new JLabel("0", JLabel.CENTER);
            livesLabels.put(player, liveLabel);
            add(liveLabel);
        }
    }

    /**
     * Refresh the lives of the players.
     */
    protected void refresh() {
        for (Map.Entry<Player, JLabel> entry : livesLabels.entrySet()) {
            Player player = entry.getKey();
            entry.getValue().setText(livesFormatter.format(player));
        }
    }

    /**
     * Provide means to format the lives for a given player.
     */
    public interface LivesFormatter {

        /**
         * Format the lives of a given player.
         * @param player The player and its lives
         * @return Formatted lives.
         */
        String format(Player player);
    }

    /**
     * Let the live panel use a dedicated formatter to format the lives.
     * @param livesFormatter The formatter to use.
     */
    public void setLivesFormatter(LivesFormatter livesFormatter) {
        assert livesFormatter != null;
        this.livesFormatter = livesFormatter;
    }
}
