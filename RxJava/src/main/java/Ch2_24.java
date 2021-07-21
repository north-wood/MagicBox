import io.reactivex.rxjava3.core.Observable;

public class Ch2_24 {
    private static int start = 1;
    private static int count = 3;

    public static void main(String[] args) {
        noDefer();
        defer();
    }

    private static void noDefer() {
        System.out.println("--- no defer ---");
        count = 3;

        Observable<Integer> source = Observable.range(start, count);
        source.subscribe(i -> System.out.println("Observer 1: " + i));
        //modify count
        count = 5;
        source.subscribe(i -> System.out.println("Observer 2: " + i));
    }

    private static void defer() {
        System.out.println("--- defer ---");
        count = 3;

        Observable<Integer> source = Observable.defer(() ->
                Observable.range(start, count));
        source.subscribe(i -> System.out.println("Observer 1: " + i));
        //modify count
        count = 5;
        source.subscribe(i -> System.out.println("Observer 2: " + i));
    }
}