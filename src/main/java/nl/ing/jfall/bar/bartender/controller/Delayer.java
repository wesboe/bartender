package nl.ing.jfall.bar.bartender.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Delayer {

    public static void for10MillisWithProbability(int probability) {
        delayInMilliseconds(probability, 10);
    }

    public static void for30MillisWithProbability(int probability) {
        delayInMilliseconds(probability, 30);
    }

    private static void delayInMilliseconds(int probability, long millis) {
        if ((int)(Math.random()*100) % probability == 0) {
            try {
                Thread.sleep(millis);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

}
