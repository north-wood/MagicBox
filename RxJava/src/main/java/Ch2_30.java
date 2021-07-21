import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;

public class Ch2_30 {
    public static void main(String[] args) {
        maybe();
        observable();
    }

    private static void maybe() {
        // has emission
        Maybe<Integer> source = Maybe.just(100);
        source.subscribe(s -> System.out.println("Process 1: " + s),
                e -> System.out.println("Error captured: " + e),
                () -> System.out.println("Process 1 done!"));
        //no emission
        Maybe<Integer> empty = Maybe.empty();
        empty.subscribe(s -> System.out.println("Process 2: " + s),
                e -> System.out.println("Error captured: " + e),
                () -> System.out.println("Process 2 done!"));
    }

    private static void observable() {
        // has emission
        Observable<Integer> source = Observable.just(100);
        source.subscribe(s -> System.out.println("Process 1:" + s),
                e -> System.out.println("Error captured: " + e),
                () -> System.out.println("Process 1 done!"));
        //no emission
        Observable<Integer> empty = Observable.empty();
        empty.subscribe(s -> System.out.println("Process 2:" + s),
                e -> System.out.println("Error captured: " + e),
                () -> System.out.println("Process 2 done!"));
    }
}