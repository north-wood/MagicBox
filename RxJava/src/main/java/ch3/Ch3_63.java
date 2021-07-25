package ch3;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Ch3_63 {
    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM:ss");
        System.out.println(LocalDateTime.now().format(f));
        Observable.just("Alpha", "Beta", "Gamma")
                .delay(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(LocalDateTime.now()
                        .format(f) + " Received: " + s));
        sleep(5000);

        Observable.just("Alpha", "Beta", "Gamma")
                .repeat(2)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("One")
                .single("Four")
                .subscribe(i -> System.out.println("Received: " + i));

        Observable.just("One", "Two", "Three")
                .filter(s -> s.contains("z"))
                .single("Four")
                .subscribe(i -> System.out.println("Received: " + i));

        Observable.just("One", "Two", "Three")
                .timestamp(TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("Received: " + i));

        Observable.just("One", "Two", "Three")
                .timestamp(TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("Received: " +
                        i.time() + " " + i.unit() + " " + i.value()));

        Observable.interval(2, TimeUnit.SECONDS)
                .doOnNext(i -> System.out.println("Emitted: " + i))
                .take(3)
                .timeInterval(TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("Received: " + i));
        sleep(7000);

        Observable.interval(2, TimeUnit.SECONDS)
                .doOnNext(i -> System.out.println("Emitted: " + i))
                .take(3)
                .timeInterval(TimeUnit.SECONDS)
                .subscribe(i -> System.out.println("Received: " +
                        i.time() + " " + i.unit() + " " + i.value()));
        sleep(7000);
    }
}