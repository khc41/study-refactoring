package study.refactoring.ch6;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SplitPhase1 {

    public static void main(String[] args) {
        try {
            System.out.println(run(args));
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    static long run(String[] args) throws IOException {
        if (args.length == 0) throw new RuntimeException("파일명을 입력하세요.");
        String filename = args[args.length - 1];
        return countOrders(args, filename);
    }

    private static long countOrders(String[] args, String filename) throws IOException {
        File input = Paths.get(filename).toFile();
        ObjectMapper mapper = new ObjectMapper();
        Order[] orders = mapper.readValue(input, Order[].class);
        if (Stream.of(args).anyMatch(arg -> "-r".equals(arg)))
            return Stream.of(orders)
                    .filter(o -> "ready".equals(o.status))
                    .count();
        else
            return orders.length;
    }

    public static class Order {
        public String status;
    }
}
