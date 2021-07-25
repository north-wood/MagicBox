package ch3;

import io.reactivex.rxjava3.core.Observable;

import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Ch3_30 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .toList()
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.range(1, 1000)
                .toList(1000)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("Beta", "Gamma", "Alpha")
                .toList(CopyOnWriteArrayList::new)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("Beta", "Gamma", "Alpha")
                .toSortedList()
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(s -> s.charAt(0))
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(s -> s.charAt(0), String::length)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(s -> s.charAt(0), String::length, ConcurrentHashMap::new)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(String::length)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("Alpha", "Beta", "Gamma")
                .toMultimap(String::length)
                .subscribe(s -> System.out.println("Received: " + s));

        Observable.just("Alpha", "Beta", "Gamma", "Beta")
                .collect(HashSet<String>::new, HashSet::add)
                .subscribe(s -> System.out.println("Received: " + s));
    }
}