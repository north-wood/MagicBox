package ch3;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Ch3_50 {
    public static void main(String[] args) throws InterruptedException {
        Observable.just("Alpha", "Beta", "Gamma")
                .doOnNext(s -> System.out.println("Processing: " + s))
                .map(String::length)
                .subscribe(i -> System.out.println("Received: " + i));

        Observable.just("Alpha", "Beta", "Gamma")
                .doAfterNext(s -> System.out.println("After: " + s))
                .map(String::length)
                .subscribe(i -> System.out.println("Received: " + i));

        Observable.just("Alpha", "Beta", "Gamma")
                .doOnComplete(() ->
                        System.out.println("Source is done emitting!"))
                .map(String::length)
                .subscribe(i -> System.out.println("Received: " + i));

        Observable.just(5, 2, 4, 0, 3, 2, 8)
                .doOnError(e -> System.out.println("Source failed!"))
                .map(i -> 10 / i)
                .doOnError(e -> System.out.println("Division failed!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i),
                        e -> System.out.println("RECEIVED ERROR: " + e));

            Observable.just("One", "Two", "Three")
                    .doOnEach(s -> System.out.println("doOnEach: " + s))
                    .subscribe(i -> System.out.println("Received: " + i));

        Observable.just("One", "Two", "Three")
                .doOnEach(s -> System.out.println("doOnEach: " +
                        s.isOnNext() + ", " + s.isOnError() +
                        ", " + s.isOnComplete()))
                .subscribe(i -> System.out.println("Received: " + i));

        Observable.just("One", "Two", "Three")
                .doOnEach(s -> System.out.println("doOnEach: " +
                        s.getError() + ", " + s.getValue()))
                .subscribe(i -> System.out.println("Received: " + i));

        Observable.just("Alpha", "Beta", "Gamma")
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));

        Disposable disp = Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));
        sleep(3000);
        disp.dispose();
        sleep(3000);

        Observable.just(5, 3, 7)
                .reduce((total, next) -> total + next)
                .doOnSuccess(i -> System.out.println("Emitting: " + i))
                .subscribe(i -> System.out.println("Received: " + i));

        Observable.just("One", "Two", "Three")
                .doFinally(() -> System.out.println("doFinally!"))
                .doAfterTerminate(() ->
                        System.out.println("doAfterTerminate!"))
                .subscribe(i -> System.out.println("Received: " + i));

        Disposable disp1 = Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .doFinally(() -> System.out.println("doFinally!"))
                .doAfterTerminate(() ->
                        System.out.println("doAfterTerminate!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));
        sleep(3000);
        disp1.dispose();
        sleep(3000);

        Disposable disp2 = Observable.interval(1, TimeUnit.SECONDS)
                .doAfterTerminate(() ->
                        System.out.println("doAfterTerminate!"))
                .doFinally(() -> System.out.println("doFinally!"))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));
        sleep(3000);
        disp2.dispose();
        sleep(3000);
    }
}