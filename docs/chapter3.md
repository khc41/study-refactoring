### 기이한 이름
- 함수, 모듈, 변수, 클래스 등은 그 이름만 보고도 각각이 무슨 일을 하고 어떻게 사용해야 하는지 명확히 알 수 있도록 해야한다.
#### before
```java
class P {
    int d; // discount
    int p; // price

    P(int d, int p) {
        this.d = d;
        this.p = p;
    }

    int f() {
        return p - d;
    }
}
```
#### after
```java
class Product {
    int discount;
    int price;

    Product(int discount, int price) {
        this.discount = discount;
        this.price = price;
    }

    int finalPrice() {
        return price - discount;
    }
}
```

### 중복 코드
- 똑같은 코드 구조가 여러 곳에서 반복된다면 하나로 통합하여 더 나은 프로그램을 만들 수 있다.
#### before
```java
class Order {
    int quantity;
    double price;

    Order(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    double calculateTotalPriceWithDiscountA() {
        double discount = (quantity > 10) ? 0.1 : 0.0;
        return price * quantity * (1 - discount);
    }

    double calculateTotalPriceWithDiscountB() {
        double discount = (quantity > 10) ? 0.1 : 0.0;
        return price * quantity * (1 - discount);
    }
}
```
#### after
```java
class Order {
    int quantity;
    double price;

    Order(int quantity, double price) {
        this.quantity = quantity;
        this.price = price;
    }

    double calculateDiscount() {
        return (quantity > 10) ? 0.1 : 0.0;
    }

    double calculateTotalPrice() {
        return price * quantity * (1 - calculateDiscount());
    }
}
```

### 긴 함수
- 함수를 짧게 구성하면 코드를 이해하고, 공유하고, 선택하기 쉬워진다.
- 주석을 달아야 할 만한 부분을 무조건 함수로 만든다.
#### before
```java
class ReportGenerator {
    void generateReport(List<Order> orders) {
        double total = 0;

        System.out.println("===== 주문 내역 =====");
        for (Order order : orders) {
            double discount = (order.quantity > 10) ? 0.1 : 0.0;
            double price = order.price * order.quantity * (1 - discount);
            total += price;

            System.out.println("상품: " + order.productName);
            System.out.println("수량: " + order.quantity);
            System.out.println("단가: " + order.price);
            System.out.println("총 가격: " + price);
            System.out.println("--------------------");
        }
        System.out.println("총 주문 금액: " + total);
    }
}
```
#### after
```java
class ReportGenerator {
    void generateReport(List<Order> orders) {
        System.out.println("===== 주문 내역 =====");

        double total = calculateTotal(orders);

        System.out.println("총 주문 금액: " + total);
    }

    private double calculateTotal(List<Order> orders) {
        double total = 0;
        for (Order order : orders) {
            double price = calculatePrice(order);
            total += price;
            printOrderDetails(order, price);
        }
        return total;
    }

    private double calculatePrice(Order order) {
        double discount = (order.quantity > 10) ? 0.1 : 0.0;
        return order.price * order.quantity * (1 - discount);
    }

    private void printOrderDetails(Order order, double price) {
        System.out.println("상품: " + order.productName);
        System.out.println("수량: " + order.quantity);
        System.out.println("단가: " + order.price);
        System.out.println("총 가격: " + price);
        System.out.println("--------------------");
    }
}
```

### 긴 매개변수 목록
- 매개변수 목록이 길어지면 그 자체로 이해하기 어려울 때가 많다.
- 클래스는 매개변수 목록을 줄이는 데 효과적인 수단이다.

#### before
```java
class OrderProcessor {
    void processOrder(String productName, int quantity, double price, String customerName, String address, String phoneNumber) {
        System.out.println("주문 처리 중...");
        System.out.println("상품: " + productName);
        System.out.println("수량: " + quantity);
        System.out.println("가격: " + price);
        System.out.println("고객: " + customerName);
        System.out.println("주소: " + address);
        System.out.println("전화번호: " + phoneNumber);
    }
}
```
#### after
```java
class Order {
    String productName;
    int quantity;
    double price;
    Customer customer;

    Order(String productName, int quantity, double price, Customer customer) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.customer = customer;
    }
}

class Customer {
    String name;
    String address;
    String phoneNumber;

    Customer(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
}

class OrderProcessor {
    void processOrder(Order order) {
        System.out.println("주문 처리 중...");
        System.out.println("상품: " + order.productName);
        System.out.println("수량: " + order.quantity);
        System.out.println("가격: " + order.price);
        System.out.println("고객: " + order.customer.name);
        System.out.println("주소: " + order.customer.address);
        System.out.println("전화번호: " + order.customer.phoneNumber);
    }
}
```

### 전역 데이터
- 버그가 발생해도 원인을 찾아내기 어렵다.
#### before
```java
class Config {
    static String databaseUrl = "jdbc:mysql://localhost:3306/mydb";
}

class DatabaseConnector {
    void connect() {
        System.out.println("Connecting to: " + Config.databaseUrl);
    }
}
```
#### after
```java
class Config {
    private String databaseUrl;

    Config(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    String getDatabaseUrl() {
        return databaseUrl;
    }
}

class DatabaseConnector {
    private Config config;

    DatabaseConnector(Config config) {
        this.config = config;
    }

    void connect() {
        System.out.println("Connecting to: " + config.getDatabaseUrl());
    }
}
```

### 가변 데이터
- 코드의 다른 곳에서는 다른 값을 기대한다는 사실을 인식하지 못한 채 수정해버리면 프로그램이 오작동한다.
#### before
```java
class Order {
    int quantity;

    Order(int quantity) {
        this.quantity = quantity;
    }

    void applyDiscount() {
        if (quantity > 10) {
            quantity -= 2; // 할인 적용 (잘못된 방식)
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Order order = new Order(12);
        System.out.println("할인 전 수량: " + order.quantity);
        order.applyDiscount();
        System.out.println("할인 후 수량: " + order.quantity);
    }
}
```
#### after
```java
class Order {
    private final int quantity;

    Order(int quantity) {
        this.quantity = quantity;
    }

    Order applyDiscount() {
        int discountedQuantity = (quantity > 10) ? quantity - 2 : quantity;
        return new Order(discountedQuantity);
    }

    int getQuantity() {
        return quantity;
    }
}

public class Main {
    public static void main(String[] args) {
        Order order = new Order(12);
        System.out.println("할인 전 수량: " + order.getQuantity());
        Order discountedOrder = order.applyDiscount();
        System.out.println("할인 후 수량: " + discountedOrder.getQuantity());
    }
}
```
### 뒤엉킨 변경
- 단일 책임 원칙이 제대로 지켜지지 않을 때 나타난다.
- 즉, 하나의 모듈이 서로 다른 이유들로 인해 여러 가지 방식으로 변경되는 일이 많을 때 발생한다.
#### before
```java
class UserProfile {
    String username;
    String email;
    String password;

    UserProfile(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    void updateProfile(String newUsername, String newEmail, String newPassword) {
        this.username = newUsername;
        this.email = newEmail;
        this.password = newPassword;
    }

    void sendVerificationEmail() {
        System.out.println("Sending verification email to: " + email);
    }

    void hashPassword() {
        this.password = "hashed_" + password; // 단순 해시 처리
    }
}
```
#### after
```java
class UserProfile {
    private String username;
    private String email;
    private String password;

    UserProfile(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    void updateProfile(String newUsername, String newEmail) {
        this.username = newUsername;
        this.email = newEmail;
    }

    String getEmail() {
        return email;
    }

    String getPassword() {
        return password;
    }

    void setPassword(String newPassword) {
        this.password = newPassword;
    }
}

class PasswordHasher {
    String hash(String password) {
        return "hashed_" + password;
    }
}

class EmailService {
    void sendVerificationEmail(String email) {
        System.out.println("Sending verification email to: " + email);
    }
}
```

### 산탄총 수술
- 코드를 변경할 때마다 자잘하게 수정해야 하는 클래스가 많을 때 나타난다.
- 변경할 부분이 코드 전반에 퍼져 있다면 찾기도 어렵고 꼭 수정해야 할 곳을 지나치기 쉽다.

#### before
```java
class User {
    String username;
    String email;
    String address;

    User(String username, String email, String address) {
        this.username = username;
        this.email = email;
        this.address = address;
    }
}

class Order {
    User user;
    String product;
    double price;

    Order(User user, String product, double price) {
        this.user = user;
        this.product = product;
        this.price = price;
    }

    void printReceipt() {
        System.out.println("Receipt:");
        System.out.println("User: " + user.username);
        System.out.println("Email: " + user.email);
        System.out.println("Shipping to: " + user.address);
        System.out.println("Product: " + product);
        System.out.println("Price: " + price);
    }
}

class NotificationService {
    void sendOrderConfirmation(User user) {
        System.out.println("Sending order confirmation to " + user.email);
    }
}

class AddressService {
    void updateAddress(User user, String newAddress) {
        user.address = newAddress;
        System.out.println("Updated address to: " + newAddress);
    }
}
```
#### after
```java
class User {
    private String username;
    private ContactInfo contactInfo;

    User(String username, String email, String address) {
        this.username = username;
        this.contactInfo = new ContactInfo(email, address);
    }

    String getUsername() {
        return username;
    }

    ContactInfo getContactInfo() {
        return contactInfo;
    }

    void updateAddress(String newAddress) {
        contactInfo.setAddress(newAddress);
    }
}

class ContactInfo {
    private String email;
    private String address;

    ContactInfo(String email, String address) {
        this.email = email;
        this.address = address;
    }

    String getEmail() {
        return email;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String newAddress) {
        this.address = newAddress;
    }
}

class Order {
    private User user;
    private String product;
    private double price;

    Order(User user, String product, double price) {
        this.user = user;
        this.product = product;
        this.price = price;
    }

    void printReceipt() {
        System.out.println("Receipt:");
        System.out.println("User: " + user.getUsername());
        System.out.println("Email: " + user.getContactInfo().getEmail());
        System.out.println("Shipping to: " + user.getContactInfo().getAddress());
        System.out.println("Product: " + product);
        System.out.println("Price: " + price);
    }
}

class NotificationService {
    void sendOrderConfirmation(User user) {
        System.out.println("Sending order confirmation to " + user.getContactInfo().getEmail());
    }
}
```

### 기능 편애
- 프로그램을 모듈화할 때는 코드를 여러 영역으로 나눈 뒤 영역 안에서 이뤄지는 상호작용은 최대한 늘리고 영역 사이에서 이뤄지는 상호작용은 최소로 줄이는 데 주력한다.
#### before
```java
class Address {
    String street;
    String city;
    String zipCode;

    Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    String getFullAddress() {
        return street + ", " + city + " " + zipCode;
    }
}

class Order {
    Address shippingAddress;

    Order(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    void printShippingLabel() {
        System.out.println("Shipping to: " + shippingAddress.street + ", " + shippingAddress.city + " " + shippingAddress.zipCode);
    }
}
```
#### after
```java
class Address {
    private String street;
    private String city;
    private String zipCode;

    Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    String getFullAddress() {
        return street + ", " + city + " " + zipCode;
    }
}

class Order {
    private Address shippingAddress;

    Order(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    void printShippingLabel() {
        System.out.println("Shipping to: " + shippingAddress.getFullAddress());
    }
}
```

### 데이터 뭉치
- 데이터 항목 서너 개가 여러곳에 함께 뭉쳐 다니면 클래스를 새로 만들어줘야한다.
#### before
```java
class Order {
    String customerName;
    String customerEmail;
    String customerAddress;
    String product;
    double price;

    Order(String customerName, String customerEmail, String customerAddress, String product, double price) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAddress = customerAddress;
        this.product = product;
        this.price = price;
    }

    void printOrderDetails() {
        System.out.println("Customer: " + customerName);
        System.out.println("Email: " + customerEmail);
        System.out.println("Address: " + customerAddress);
        System.out.println("Product: " + product);
        System.out.println("Price: " + price);
    }
}
```

#### after
```java
class Customer {
    private String name;
    private String email;
    private String address;

    Customer(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    String getAddress() {
        return address;
    }
}

class Order {
    private Customer customer;
    private String product;
    private double price;

    Order(Customer customer, String product, double price) {
        this.customer = customer;
        this.product = product;
        this.price = price;
    }

    void printOrderDetails() {
        System.out.println("Customer: " + customer.getName());
        System.out.println("Email: " + customer.getEmail());
        System.out.println("Address: " + customer.getAddress());
        System.out.println("Product: " + product);
        System.out.println("Price: " + price);
    }
}
```

### 기본형 집착
- 자신에게 주어진 문제에 딱 맞는 기초 타입을 직접 정의하지 않아 나타난다.
#### before
```java
class User {
    String name;
    String email;
    String phoneNumber;

    User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    boolean isValidEmail() {
        return email.contains("@");
    }

    boolean isValidPhoneNumber() {
        return phoneNumber.matches("\\d{10,11}");
    }
}
```
#### after
```java
class Email {
    private final String value;

    Email(String value) {
        if (!value.contains("@")) {
            throw new IllegalArgumentException("Invalid email format: " + value);
        }
        this.value = value;
    }

    String getValue() {
        return value;
    }
}

class PhoneNumber {
    private final String value;

    PhoneNumber(String value) {
        if (!value.matches("\\d{10,11}")) {
            throw new IllegalArgumentException("Invalid phone number: " + value);
        }
        this.value = value;
    }

    String getValue() {
        return value;
    }
}

class User {
    private String name;
    private Email email;
    private PhoneNumber phoneNumber;

    User(String name, Email email, PhoneNumber phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    void printUserInfo() {
        System.out.println("Name: " + name);
        System.out.println("Email: " + email.getValue());
        System.out.println("Phone: " + phoneNumber.getValue());
    }
}
```
### 반복되는 switch 문
- 중복된 switch 문이 문제가 되는 이유는 조건절을 하나 추가할 때마다 다른 switch 문들도 모두 찾아서 함께 수정해야 하기 때문이다.
- 다형성은 반복된 switch 문을 해결해준다.
#### before
```java
class Employee {
    String type;

    Employee(String type) {
        this.type = type;
    }

    double calculateBonus(double salary) {
        switch (type) {
            case "Manager":
                return salary * 0.2;
            case "Developer":
                return salary * 0.15;
            case "Intern":
                return salary * 0.05;
            default:
                throw new IllegalArgumentException("Unknown employee type: " + type);
        }
    }

    String getJobDescription() {
        switch (type) {
            case "Manager":
                return "Manages the team and projects.";
            case "Developer":
                return "Writes and maintains software code.";
            case "Intern":
                return "Assists in development tasks and learns.";
            default:
                throw new IllegalArgumentException("Unknown employee type: " + type);
        }
    }
}
```
#### after
```java
abstract class Employee {
    abstract double calculateBonus(double salary);
    abstract String getJobDescription();
}

class Manager extends Employee {
    @Override
    double calculateBonus(double salary) {
        return salary * 0.2;
    }

    @Override
    String getJobDescription() {
        return "Manages the team and projects.";
    }
}

class Developer extends Employee {
    @Override
    double calculateBonus(double salary) {
        return salary * 0.15;
    }

    @Override
    String getJobDescription() {
        return "Writes and maintains software code.";
    }
}

class Intern extends Employee {
    @Override
    double calculateBonus(double salary) {
        return salary * 0.05;
    }

    @Override
    String getJobDescription() {
        return "Assists in development tasks and learns.";
    }
}
```
### 반복문
- 일급 함수를 지원하는 언어가 많아져서 반복문을 제거할 수 있게 됐다.
#### before
```java
import java.util.List;

class Order {
    double price;

    Order(double price) {
        this.price = price;
    }

    double getPrice() {
        return price;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(new Order(100), new Order(200), new Order(300));

        // 기존 반복문 사용
        double total = 0;
        for (Order order : orders) {
            total += order.getPrice();
        }

        System.out.println("총 주문 금액: " + total);
    }
}
```
#### after
```java
import java.util.List;

class Order {
    double price;

    Order(double price) {
        this.price = price;
    }

    double getPrice() {
        return price;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Order> orders = List.of(new Order(100), new Order(200), new Order(300));

        // 스트림 API 사용
        double total = orders.stream()
                             .mapToDouble(Order::getPrice)
                             .sum();

        System.out.println("총 주문 금액: " + total);
    }
}
```
### 성의 없는 요소
- 본문 코드를 그대로 쓰는 것과 진배없는 함수도 있고, 실질적으로 메서드가 하나뿐인 클래스도 있다.
#### before
```java
class User {
    private String name;
    private String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }
}

class UserService {
    String getUserEmail(User user) {
        return user.getEmail();
    }
}
```
#### after
```java
class User {
    private String name;
    private String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }
}
```

### 추측성 일반화
- '나중에 필요할 거야' 라는 생각으로 당장은 필요 없는 모든 종류의 후킹 포인트와 특이 케이스 처리 로직을 작성해둔 코드에서 나타난다.
- 테스트 코드 말고는 사용하는 곳이 없는 함수나 클래스에서 흔히 볼 수 있다.
#### before
```java
// 나중에 다양한 타입을 지원할 거라며 만든 Generic Repository (하지만 현재 User만 사용)
class Repository<T> {
    void save(T entity) {
        System.out.println("Saving entity: " + entity.toString());
    }

    void delete(T entity) {
        System.out.println("Deleting entity: " + entity.toString());
    }
}

// 현재 User만 사용함, 하지만 일반화를 시도함
class User {
    private String name;
    private String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "'}";
    }
}

public class Main {
    public static void main(String[] args) {
        Repository<User> userRepository = new Repository<>();
        User user = new User("Alice", "alice@example.com");
        userRepository.save(user);
        userRepository.delete(user);
    }
}
```
#### after
```java
class UserRepository {
    void save(User user) {
        System.out.println("Saving user: " + user);
    }

    void delete(User user) {
        System.out.println("Deleting user: " + user);
    }
}

class User {
    private String name;
    private String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{name='" + name + "', email='" + email + "'}";
    }
}

public class Main {
    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();
        User user = new User("Alice", "alice@example.com");
        userRepository.save(user);
        userRepository.delete(user);
    }
}
```

### 임시 필드
- 특정 상황에서만 값이 설정되는 필드를 가진 클래스도 있다.
- 코드를 이해하기 어려워진다.
#### before
```java
class Order {
    private String product;
    private double price;
    private Double discount; // 일부 주문만 할인 적용

    Order(String product, double price) {
        this.product = product;
        this.price = price;
        this.discount = null; // 기본적으로 할인 없음
    }

    void applyDiscount(double discount) {
        this.discount = discount;
    }

    double calculateTotal() {
        if (discount != null) {
            return price * (1 - discount);
        }
        return price;
    }

    void printOrderDetails() {
        System.out.println("Product: " + product);
        System.out.println("Price: " + price);
        if (discount != null) {
            System.out.println("Discount Applied: " + (discount * 100) + "%");
            System.out.println("Final Price: " + calculateTotal());
        } else {
            System.out.println("No Discount Applied.");
        }
    }
}
```
#### after
```java
class Order {
    protected String product;
    protected double price;

    Order(String product, double price) {
        this.product = product;
        this.price = price;
    }

    double calculateTotal() {
        return price;
    }

    void printOrderDetails() {
        System.out.println("Product: " + product);
        System.out.println("Price: " + price);
        System.out.println("No Discount Applied.");
    }
}

class DiscountedOrder extends Order {
    private double discount;

    DiscountedOrder(String product, double price, double discount) {
        super(product, price);
        this.discount = discount;
    }

    @Override
    double calculateTotal() {
        return price * (1 - discount);
    }

    @Override
    void printOrderDetails() {
        System.out.println("Product: " + product);
        System.out.println("Original Price: " + price);
        System.out.println("Discount Applied: " + (discount * 100) + "%");
        System.out.println("Final Price: " + calculateTotal());
    }
}
```

### 메시지 체인
- 클라이언트가 한 객체를 통해 다른 객체를 얻은 뒤 방금 얻은 객체에 또 다른 객체를 요청하는 식으로, 다른 객체를 요청하는 작업이 연쇄적으로 이어지는 코드를 말한다.
#### before
```java
class Address {
    private String city;

    Address(String city) {
        this.city = city;
    }

    String getCity() {
        return city;
    }
}

class Customer {
    private Address address;

    Customer(Address address) {
        this.address = address;
    }

    Address getAddress() {
        return address;
    }
}

class Order {
    private Customer customer;

    Order(Customer customer) {
        this.customer = customer;
    }

    Customer getCustomer() {
        return customer;
    }
}

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Seoul");
        Customer customer = new Customer(address);
        Order order = new Order(customer);

        // 메시지 체인 발생
        System.out.println("Shipping City: " + order.getCustomer().getAddress().getCity());
    }
}
```
#### after
```java
class Address {
    private String city;

    Address(String city) {
        this.city = city;
    }

    String getCity() {
        return city;
    }
}

class Customer {
    private Address address;

    Customer(Address address) {
        this.address = address;
    }

    Address getAddress() {
        return address;
    }
}

class Order {
    private Customer customer;

    Order(Customer customer) {
        this.customer = customer;
    }

    // 메시지 체인을 숨기기 위해 메서드 추가
    String getShippingCity() {
        return customer.getAddress().getCity();
    }
}

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Seoul");
        Customer customer = new Customer(address);
        Order order = new Order(customer);

        // 메시지 체인이 사라지고 단순해짐
        System.out.println("Shipping City: " + order.getShippingCity());
    }
}
```
### 중개자
- 클래스가 제공하는 메서드 중 일정 부분 이상이 다른 클래스에 구현을 위임하고 있다.
#### before
```java
class Address {
    private String city;

    Address(String city) {
        this.city = city;
    }

    String getCity() {
        return city;
    }
}

class Customer {
    private Address address;

    Customer(Address address) {
        this.address = address;
    }

    Address getAddress() {
        return address;
    }
}

class Order {
    private Customer customer;

    Order(Customer customer) {
        this.customer = customer;
    }

    // 중개자 역할만 하는 메서드
    Address getAddress() {
        return customer.getAddress();
    }

    String getCity() {
        return customer.getAddress().getCity();
    }
}

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Seoul");
        Customer customer = new Customer(address);
        Order order = new Order(customer);

        // 불필요한 중개자 메서드를 통해 데이터 접근
        System.out.println("Shipping City: " + order.getCity());
    }
}
```
#### after
```java
class Address {
    private String city;

    Address(String city) {
        this.city = city;
    }

    String getCity() {
        return city;
    }
}

class Customer {
    private Address address;

    Customer(Address address) {
        this.address = address;
    }

    String getCity() {
        return address.getCity();
    }
}

class Order {
    private Customer customer;

    Order(Customer customer) {
        this.customer = customer;
    }

    Customer getCustomer() {
        return customer;
    }
}

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Seoul");
        Customer customer = new Customer(address);
        Order order = new Order(customer);

        // 직접 Customer를 통해 정보에 접근
        System.out.println("Shipping City: " + order.getCustomer().getCity());
    }
}
```

### 내부자 거래
- 어려 모듈이 같은 관심사를 공유한다면 공통 부분을 정식으로 처리하는 제3의 모듈을 새로 만들거나 위임 숨기기를 이용하여 다른 모듈이 중간자 역할을 하게 만든다.
#### before
```java
class Address {
    private String city;
    private String street;

    Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    String getCity() {
        return city;
    }

    String getStreet() {
        return street;
    }
}

class Customer {
    private String name;
    private Address address;

    Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    Address getAddress() {
        return address;
    }
}

class Order {
    private Customer customer;

    Order(Customer customer) {
        this.customer = customer;
    }

    void printShippingLabel() {
        // Order가 Customer의 내부 구조(Address)를 직접 참조 → 내부자 거래 발생
        Address address = customer.getAddress();
        System.out.println("Shipping to: " + address.getStreet() + ", " + address.getCity());
    }
}

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Seoul", "Gangnam-daero");
        Customer customer = new Customer("Alice", address);
        Order order = new Order(customer);

        order.printShippingLabel();
    }
}
```
#### after
```java
class Address {
    private String city;
    private String street;

    Address(String city, String street) {
        this.city = city;
        this.street = street;
    }

    String getFullAddress() {
        return street + ", " + city;
    }
}

class Customer {
    private String name;
    private Address address;

    Customer(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    String getShippingAddress() {
        return address.getFullAddress();
    }
}

class Order {
    private Customer customer;

    Order(Customer customer) {
        this.customer = customer;
    }

    void printShippingLabel() {
        // Customer에게 위임 → Order는 Address의 내부 구조를 알 필요가 없음
        System.out.println("Shipping to: " + customer.getShippingAddress());
    }
}

public class Main {
    public static void main(String[] args) {
        Address address = new Address("Seoul", "Gangnam-daero");
        Customer customer = new Customer("Alice", address);
        Order order = new Order(customer);

        order.printShippingLabel();
    }
}
```
### 거대한 클래스
- 한 클래스가 너무 많은 일을 하려다 보면 필드 수가 상당히 늘어난다.
- 클래스에 필드가 너무 많으면 중복 코드가 생기기 쉽다.
#### before
```java
class Employee {
    private String name;
    private double salary;
    private String department;
    private String address;
    private String phoneNumber;

    Employee(String name, double salary, String department, String address, String phoneNumber) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    void printContactInfo() {
        System.out.println(name + ": " + phoneNumber + ", " + address);
    }

    void calculateBonus() {
        System.out.println("Bonus: " + salary * 0.1);
    }
}
```
#### after
```java
class ContactInfo {
    private String address;
    private String phoneNumber;

    ContactInfo(String address, String phoneNumber) {
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    void print() {
        System.out.println(phoneNumber + ", " + address);
    }
}

class Employee {
    private String name;
    private double salary;
    private String department;
    private ContactInfo contactInfo;

    Employee(String name, double salary, String department, ContactInfo contactInfo) {
        this.name = name;
        this.salary = salary;
        this.department = department;
        this.contactInfo = contactInfo;
    }

    void calculateBonus() {
        System.out.println("Bonus: " + salary * 0.1);
    }

    void printContactInfo() {
        System.out.print(name + ": ");
        contactInfo.print();
    }
}
```

### 서로 다른 인터페이스의 대안 클래스들
- 클래스를 사용할 때의 큰 장점은 필요에 따라 언제든 다른 클래스로 교체할 수 있다는 것이다.
- 단, 교체하려면 인터페이스가 같아야 한다.
#### before
```java
class PayPalPayment {
    void sendPayment(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

class CreditCardPayment {
    void processPayment(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PaymentProcessor {
    private PayPalPayment paypalPayment;
    private CreditCardPayment creditCardPayment;

    PaymentProcessor(PayPalPayment paypalPayment, CreditCardPayment creditCardPayment) {
        this.paypalPayment = paypalPayment;
        this.creditCardPayment = creditCardPayment;
    }

    void payWithPayPal(double amount) {
        paypalPayment.sendPayment(amount);
    }

    void payWithCreditCard(double amount) {
        creditCardPayment.processPayment(amount);
    }
}
```
#### after
```java
interface PaymentMethod {
    void pay(double amount);
}

class PayPalPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}

class CreditCardPayment implements PaymentMethod {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

class PaymentProcessor {
    private PaymentMethod paymentMethod;

    PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    void process(double amount) {
        paymentMethod.pay(amount);
    }
}
```

### 데이터 클래스
- 데이터 클래스란 데이터 필드와 게터/세터 메서드로만 구성된 클래스를 말한다.
- 필요한 동작이 엉뚱한 곳에 정의돼 있다는 신호일 수 있다.
#### before
```java
class User {
    private String name;
    private String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }
}
```
#### after
```java
class User {
    private String name;
    private String email;

    User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    void updateEmail(String newEmail) {
        if (!newEmail.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        this.email = newEmail;
    }

    void printUserInfo() {
        System.out.println("User: " + name + ", Email: " + email);
    }
}
```

### 상속 포기
- 서브클래스 중 부모의 메서드를 원치 않거나 필요 없을 때 나타난다.
- 같은 계충에 서브클래스를 하나 새로 만들고, 메서드 내리기와 필드 내리기를 활용해서 물려받지 않을 부모 코드를 모조리 새로 만든 서브클래스로 넘긴다.
#### before
```java
class Employee {
    String name;
    double salary;

    Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    void calculateBonus() {
        System.out.println("Bonus: " + salary * 0.1);
    }

    void assignProject(String project) {
        System.out.println(name + " is assigned to project: " + project);
    }
}

class Intern extends Employee {
    Intern(String name, double salary) {
        super(name, salary);
    }

    @Override
    void calculateBonus() {
        throw new UnsupportedOperationException("Interns do not receive bonuses.");
    }
}
```
#### after
```java
class Worker {
    String name;
    double salary;

    Worker(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
}

class RegularEmployee extends Worker {
    RegularEmployee(String name, double salary) {
        super(name, salary);
    }

    void calculateBonus() {
        System.out.println("Bonus: " + salary * 0.1);
    }

    void assignProject(String project) {
        System.out.println(name + " is assigned to project: " + project);
    }
}

class Intern extends Worker {
    Intern(String name, double salary) {
        super(name, salary);
    }
}
```

### 주석
- 주석이 장황하게 달린 원인이 코드를 잘못 작성했기 때문인 경우가 의외로 많다.
- 주석을 남겨야겠다는 생각이 들면, 가장 먼저 주석이 필요 없는 코드로 리팩터링해본다.
#### before
```java
class Order {
    private double price;
    private int quantity;

    Order(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    // 총 가격을 계산하는 메서드
    double calculateTotalPrice() {
        // 할인율 설정 (10개 이상 구매 시 10% 할인)
        double discount = (quantity >= 10) ? 0.1 : 0.0;

        // 할인된 가격 계산
        return price * quantity * (1 - discount);
    }
}
```
#### after
```java
class Order {
    private double price;
    private int quantity;

    Order(double price, int quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    double calculateTotalPrice() {
        return price * quantity * (1 - getDiscountRate());
    }

    private double getDiscountRate() {
        return (quantity >= 10) ? 0.1 : 0.0;
    }
}
```