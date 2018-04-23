package demo.com.data_handler.entities;

import com.squareup.moshi.Json;

import java.util.ArrayList;
import java.util.List;

public class Club {
    @Json(name = "name") private String name;
    @Json(name = "count_member") private int totalPlayers;
    @Json(name = "logo") private String logo;
    @Json(name = "players") private List<Player> players;

    public String getName() {
        return name;
    }

    public int getTotalPlayers() {
        return totalPlayers;
    }

    public String getLogo() {
        return logo;
    }

    public List<Player> getPlayers() {
        return players;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("Club " + name + " has " + totalPlayers + " players include ");
        for (Player player : players) {
            s.append(player.getName()).append(" ");
        }
        return s.toString();
    }
}
