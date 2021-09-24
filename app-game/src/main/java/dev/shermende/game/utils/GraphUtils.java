package dev.shermende.game.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class GraphUtils {
    private final Random random = new Random();

    public boolean isMatch(
            int edgeChance
    ) {
        if (edgeChance < 0) throw new IllegalArgumentException("'edgeChance' cannot be less than zero");
        return random.nextInt(100) < edgeChance;
    }

    public int nextPeakPosition(
            int source,
            int peaksCount
    ) {
        if (source < 0) throw new IllegalArgumentException("'source' cannot be less than zero");
        if (peaksCount < 0) throw new IllegalArgumentException("'source' cannot be less than zero");
        if (source >= peaksCount) throw new IllegalArgumentException("'source' cannot be large than 'peaksCount'");
        int target;
        do {
            target = random.nextInt(peaksCount);
        } while (source == target);
        return target;
    }
}
