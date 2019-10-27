package nl.ing.jfall.bar.bartender.controller;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Delayer {

    public static void for20MillisWithProbability(int probability) {
        delayInMilliseconds(probability, 20);
    }

    public static void for40MillisWithProbability(int probability) {
        delayInMilliseconds(probability, 40);
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
