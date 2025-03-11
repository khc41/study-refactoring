package study.refactoring.ch8.moveStatementsIntoFunction.before;

import java.io.OutputStream;
import java.time.LocalDateTime;

public class Render {
    public String renderPerson(OutputStream outStream, Person person) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("<p>%s</p>", person.getName()));
        result.append(renderPhoto(person.getPhoto()));
        result.append(String.format("<p>제목: %s</p>", person.getPhoto().getTitle()));
        result.append(emitPhotoData(person.getPhoto()));

        return result.toString();
    }

    public String photoDiv(Photo p){
        StringBuilder result = new StringBuilder();
        result.append("<div>");
        result.append(String.format("<p>제목: %s</p>", p.getTitle()));
        result.append(emitPhotoData(p));
        result.append("</div>");
        return result.toString();
    }

    public String emitPhotoData(Photo aPhoto){
        StringBuilder result = new StringBuilder();
        result.append(String.format("<p>위치: %s</p>", aPhoto.getLocation()));
        result.append(String.format("<p>날짜: %s</p>", aPhoto.getDate().toString()));
        return result.toString();
    }

    public String renderPhoto(Photo photo){
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
