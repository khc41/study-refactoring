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
        return countOrders(parseCommandLine(args));
    }

    private static CommandLine parseCommandLine(String[] args) {
        if (args.length == 0) throw new RuntimeException("파일명을 입력하세요.");
        CommandLine result = new CommandLine();
        result.filename = args[args.length - 1];
        result.onlyCountReady = Stream.of(args).anyMatch(arg -> "-r".equals(arg));
        return result;
    }

    private static long countOrders(CommandLine commandLine) throws IOException {
        File input = Paths.get(commandLine.filename).toFile();
        ObjectMapper mapper = new ObjectMapper();
        Order[] orders = mapper.readValue(input, Order[].class);
        if (commandLine.onlyCountReady)
            return Stream.of(orders)
                    .filter(o -> "ready".equals(o.status))
                    .count();
        else
            return orders.length;
    }

    public static class CommandLine {
        boolean onlyCountReady;
        String filename;
    }

    public static class Order {
        public String status;
    }
}
