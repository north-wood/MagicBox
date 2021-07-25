package ch3;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDate;

public class Ch3_25 {
    public static void main(String[] args) {
        Observable.just(5, 3, 7, 11, 2, 14)
                .all(i -> i < 10)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("2016-01-01", "2016-05-02",
                "2016-09-12", "2016-04-03")
                .map(LocalDate::parse)
                .any(dt -> dt.getMonthValue() >= 6)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("One", "Two", "Three")
                .filter(s -> s.contains("z"))
                .isEmpty()
                .subscribe(s -> System.out.println("Received1: " + s));

        Observable.just("One", "Twoz", "Three")
                .filter(s -> s.contains("z"))
                .isEmpty()
                .subscribe(s -> System.out.println("Received2: " + s));

        Observable.range(1, 10000)
                .contains(9563)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable<String> obs1 = Observable.just("One","Two","Three");
        Observable<String> obs2 = Observable.just("One","Two","Three");
        Observable<String> obs3 = Observable.just("Two","One","Three");
        Observable<String> obs4 = Observable.just("One","Two");
        Observable.sequenceEqual(obs1, obs2)
                .subscribe(s -> System.out.println("Received: " + s));
        Observable.sequenceEqual(obs1, obs3)
                .subscribe(s -> System.out.println("Received: " + s));
        Observable.sequenceEqual(obs1, obs4)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}