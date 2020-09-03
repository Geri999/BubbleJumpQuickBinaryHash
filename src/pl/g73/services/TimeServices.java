package pl.g73.services;

public class TimeServices {

    public static String timeConverter(long time) {
        return String.format("%d min. %d sec. %d ms.",
                (time - ((time - time % 1000) / 1000) % 60) / 60000,
                ((time - time % 1000) / 1000) % 60,
                time % 1000);
    }
}
