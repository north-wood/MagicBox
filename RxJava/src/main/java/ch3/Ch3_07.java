package ch3;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Ch3_07 {
    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("ss:SSS");
        System.out.println(LocalDateTime.now().format(f));
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe(i -> System.out.println(LocalDateTime.now()
                        .format(f) + " RECEIVED: " + i));
        sleep(5000);
    }
}
