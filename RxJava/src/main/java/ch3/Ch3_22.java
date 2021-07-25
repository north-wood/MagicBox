package ch3;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_22 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .count()
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just(5, 3, 7)
                .reduce((total, i) -> total + i)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just(5, 3, 7)
                .reduce("", (total, i) ->
                        total + (total.equals("") ? "" : ",") + i)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}