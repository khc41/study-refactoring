package study.refactoring.ch8.movestatementsToCallers.after;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;

public class Render {
    public String renderPerson(OutputStream outStream, Person person) throws IOException {
        outStream.write(String.format("<p>%s</p>", person.getName()).getBytes());
        StringBuilder result = new StringBuilder();
        renderPhoto(outStream, person.getPhoto());
        emitPhotoData(outStream, person.getPhoto());
        outStream.write(String.format("<p>위치: %s</p>", person.getPhoto().getLocation()).getBytes());
        return result.toString();
    }

    public void listRecentPhotos(OutputStream outStream, List<Photo> photos) {
        photos.stream()
                .filter(p -> p.getDate().isAfter(recentDateCutoff()))
                .forEach(p -> {
                    try {
                        outStream.write("<div>".getBytes());
                        emitPhotoData(outStream, p);
                        outStream.write(String.format("<p>위치: %s</p>", p.getLocation()).getBytes());
                        outStream.write("</div>".getBytes());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private static void emitPhotoData(OutputStream outputStream, Photo p) throws IOException {
        outputStream.write(String.format("<p>제목: %s</p>", p.getTitle()).getBytes());
        outputStream.write(String.format("<p>날짜: %s</p>", p.getDate().toString()).getBytes());
    }

    private LocalDateTime recentDateCutoff() {
        return null;
    }

    public String renderPhoto(OutputStream outputStream, Photo photo) {
        return photo.toString();
    }

    public static class Person {
        private String name;
        private Photo photo;

        public String getName() {
            return name;
        }

        public Photo getPhoto() {
            return photo;
        }
    }

    public static class Photo {
        private String title;
        private String location;
        private LocalDateTime date;

        public String getTitle() {
            return title;
        }

        public String getLocation() {
            return location;
        }

        public LocalDateTime getDate() {
            return date;
        }
    }
}
