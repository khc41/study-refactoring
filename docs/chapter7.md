## 1. 레코드 캡슐화하기
> [예시 코드 (Main) - before](/src/main/java/study/refactoring/ch7/encapsulateRecord/before/Main.java)<br>
> [예시 코드 (Main) - after](/src/main/java/study/refactoring/ch7/encapsulateRecord/after/Main.java)<br>
> [예시 코드 (Organization) - after](/src/main/java/study/refactoring/ch7/encapsulateRecord/after/Organization.java)
- 객체를 사용하면 사용자는 무엇이 저장된 값이고 무엇이 계산된 값인지 알 필요가 없다.

### 절차
1. 레코드를 담은 변수를 캡슐화한다.
   - 레코드를 캡슐화하는 함수의 이름은 검색하기 쉽게 지어준다.
2. 레코드를 감싼 단순한 클래스로 해당 변수의 내용을 교체한다. 이 클래스에 원본 레코드를 반환하는 접근자도 정의하고, 변수를 캡슐화하는 함수들이 이 접근자를 사용하도록 수정한다.
3. 테스트한다.
4. 원본 레코드 대신 새로 정의한 클래스 타입의 객체를 반환하는 함수들을 새로 만든다.
5. 레코드를 반환하는 예전 함수를 사용하는 코드를 4에서 만든 새 함수를 사용하도록 바꾼다. 필드에 접근할 때는 객체의 접근자를 사용한다. 적절한 접근자가 없다면 추가한다. 한 부분을 바꿀 때마다 테스트한다.
   - 중첩된 구조처럼 복잡한 레코드라면, 먼저 데이터를 갱신하는 클라이언트들에 주의해서 살펴본다. 클라이언트가 데이터를 읽기만 한다면 데이터의 복제본이나 읽기전용 프록시를 반환할지 고려해보자.
6. 클래스에서 원본 데이터를 반환하는 접근자와 (1에서 검색하기 쉬운 이름을 붙여둔) 원본 레코드를 반환하는 함수들을 제거한다.
7. 테스트한다.
8. 레코드의 필드도 데이터 구조인 중첩 구조라면 레코드 캡슐화하기와 컬렉션 캡슐화하기를 재귀적으로 적용한다.

- 데이터 구조가 클수록 복제 비용이 커져서 성능이 느려질 수 있는데, 실제로 측정해보는게 좋다.

## 2. 컬렉션 캡슐화하기
> [예시 코드 (Course) - before](/src/main/java/study/refactoring/ch7/encapsulateCollection/before/Course.java)<br>
> [예시 코드 (Person) - before](/src/main/java/study/refactoring/ch7/encapsulateCollection/before/Person.java)<br>
> [예시 코드 (Course) - after](/src/main/java/study/refactoring/ch7/encapsulateCollection/after/Course.java)<br>
> [예시 코드 (Person) - after](/src/main/java/study/refactoring/ch7/encapsulateCollection/after/Person.java)
- 가변 데이터를 모두 캡슐화하는 편인데 데이터 구조가 언제 어떻게 수정되는지 파악하기 쉬워 필요한 시점에 데이터 구조를 변경하기도 쉬워진다.
- 컬렉션을 캡슐화하면서 게터가 컬렉션 자체를 반환하면 컬렉션 원소들이 바뀔 수 있으므로, add(), remove() 이름의 컬렉션 변경자 메서드를 만든다.
- 컬렉션 게터를 제공하되 복제본을 반환하는 방식이 가장 흔하다.

### 절차
1. 아직 컬렉션을 캡슐화하지 않았다면 변수 캡슐화하기부터 한다.
2. 컬렉션에 원소를 추가/제거하는 함수를 추가한다.
3. 정적 검사를 수행한다.
4. 컬렉션을 참조하는 부분을 모두 찾는다. 컬렉션의 변경자를 호출하는 코드가 모두 앞에서 추가한 추가/제거 함수를 호출하도록 수정한다. 하나씩 수정할 때마다 테스트한다.
5. 컬렉션 게터를 수정해서 원본 내용을 수정할 수 없는 읽기전용 프락시나 복제본을 반환하게 한다.

## 3. 기본형을 객체로 바꾸기
> [예시 코드 (Main) - before](/src/main/java/study/refactoring/ch7/replacePrimitiveWithObject/before/Main.java)<br>
> [예시 코드 (Order) - before](/src/main/java/study/refactoring/ch7/replacePrimitiveWithObject/before/Order.java)<br>
> [예시 코드 (Main) - after](/src/main/java/study/refactoring/ch7/replacePrimitiveWithObject/after/Main.java)<br>
> [예시 코드 (Order) - after](/src/main/java/study/refactoring/ch7/replacePrimitiveWithObject/after/Order.java)<br>
> [예시 코드 (Priority) - after](/src/main/java/study/refactoring/ch7/replacePrimitiveWithObject/after/Priority.java)
- 단순한 출력 이상의 기능이 필요해지는 순간 그 데이터를 표현하는 전용 클래스를 정의하면 좋다.

### 절차
1. 아직 변수를 캡슐화하지 않았다면 캡슐화한다.
2. 단순한 값 클래스를 만든다. 생성자는 기존 값을 인수로 받아서 저장하고, 이 값을 반환하는 게터를 추가한다.
3. 정적 검사를 수행한다.
4. 값 클래스의 인스턴스를 새로 만들어서 필드에 저장하도록 세터를 수정한다. 이미 있다면 필드의 타입을 적절히 변경한다.
5. 새로 만든 클래스의 게터를 호출한 결과를 반환하도록 게터를 수정한다.
6. 테스트한다.
7. 함수 이름을 바꾸면 원본 접근자의 동작을 더 잘 드러낼 수 있는지 검토한다.

## 4. 임시 변수를 질의 함수로 바꾸기
> [예시 코드 (Item) - before](/src/main/java/study/refactoring/ch7/replaceTempWithQuery/before/Item.java)<br>
> [예시 코드 (Order) - before](/src/main/java/study/refactoring/ch7/replaceTempWithQuery/before/Order.java)<br>
> [예시 코드 (Item) - after](/src/main/java/study/refactoring/ch7/replaceTempWithQuery/after/Item.java)<br>
> [예시 코드 (Order) - after](/src/main/java/study/refactoring/ch7/replaceTempWithQuery/after/Order.java)<br>
- 임시 변수를 사용하는 것보다 함수를 만들어 사용하는 편이 나을 때가 많다.
- 변수를 각각의 함수로 만들면 추출한 함수에 변수를 따로 전달할 필요가 없고, 원래 함수의 경계가 분명해져 부자연스러운 의존 관계나 부수효과를 찾고 제거하는 데 도움이 된다.
- 클래스 안에서 적용할 때 효과가 가장 크다.

### 절차
1. 변수가 사용되기 전에 값이 확실히 결정되는지, 변수를 사용할 때마다 계산 로직이 매번 다른 결과를 내지는 않는지 확인한다.
2. 읽기전용으로 만들 수 있는 변수는 읽기전용으로 만든다.
3. 테스트한다.
4. 변수 대입문을 함수로 추출한다.
   - 변수와 함수가 같은 이름을 가질 수 없다면 함수 이름을 임시로 짓는다. 또한, 추출한 함수가 부수효과를 일으키지는 않는지 확인한다. 부수효과가 있다면 질의 함수와 변경 함수 분리하기로 대처한다.
5. 테스트한다.
6. 변수 인라인하기로 임시변수를 제거한다.

## 5. 클래스 추출하기
> [예시 코드 (Person) - before](/src/main/java/study/refactoring/ch7/extractClass/before/Person.java)<br>
> [예시 코드 (Person) - after](/src/main/java/study/refactoring/ch7/extractClass/after/Person.java)<br>
> [예시 코드 (TelephoneNumber) - after](/src/main/java/study/refactoring/ch7/extractClass/after/TelephoneNumber.java)<br>
- 일부 데이터와 메서드를 따로 묶을 수 잇다면 어서 분리하라는 신호다.
- 함께 변경되는 일이 많거나 서로 의존하는 데이터들도 분리한다.
- 작은 일부의 기능만을 위해 서브클래스를 만들거나, 확장해야 할 기능이 무엇이냐에 따라 서브클래스를 만드는 방식도 달라진다면 클래스를 나눠야 한다는 신호다.

### 절차
1. 클래스의 역할을 분리할 방법을 정한다.
2. 분리될 역할을 담당할 클래스를 새로 만든다.
   - 원래 클래스에 남은 역할과 클래스 이름이 어울리지 않는다면 적절히 바꾼다.
3. 원래 클래스의 생성자에서 새로운 클래스의 인스턴스를 생성하여 필드에 저장해둔다.
4. 분리될 역할에 필요한 필드들을 새 클래스로 옮긴다.(필드 옮기기) 하나씩 옮길 때마다 테스트한다.
5. 메서드들도 새 클래스로 옮긴다.(함수 옮기기) 이때 저수준 메서드, 즉 다른 메서드를 호출하기보다는 호출을 당하는 일이 많은 메서드부터 옮긴다. 하나씩 옮길 때마다 테스트한다.
6. 양쪽 클래스의 인터페이스를 살펴보면서 불필요한 메서드를 제거하고, 이름도 새로운 환경에 맞게 바꾼다.
7. 새 클래스를 외부로 노출할지 정한다. 노출하려거든  새 클래스에 참조를 값으로 바꾸기를 적용할 지 고민해본다.

## 6. 클래스 인라인하기
> [예시 코드 (Shipment) - before](/src/main/java/study/refactoring/ch7/inlineClass/before/Shipment.java)<br>
> [예시 코드 (TrackingInformation) - before](/src/main/java/study/refactoring/ch7/inlineClass/before/TrackingInformation.java)<br>
> [예시 코드 (Shipment) - after](/src/main/java/study/refactoring/ch7/inlineClass/after/Shipment.java)<br>
- 클래스 추출하기를 거꾸로 돌리는 리팩터링
- 역할을 옮기는 리팩터링을 하고나니 특정 클래스에 남은 역할이 거의 없을 때 자주 생긴다.
- 두 클래스의 기능을 지금과 다르게 배분하고 싶을 때도 클래스를 인라인한다.

### 절차
1. 소스 클래스의 각 public 메서드에 대응하는 메서드들을 타깃 클래스에 생성한다. 이 메서드들은 단순히 작업을 소스 클래스로 위임해야 한다.
2. 소스 클래스의 메서드를 사용하는 코드를 모두 타깃 클래스의 위임 메서드를 사용하도록 바꾼다. 하나씩 바꿀 때마다 테스트한다.
3. 소스 클래스의 메서드와 필드를 모두 타깃 클래스로 옮긴다. 하나씩 옮길 때마다 테스트한다.
4. 소스 클래스를 삭제하고 조의를 표한다.

## 7. 위임 숨기기
> [예시 코드 (Person) - before](/src/main/java/study/refactoring/ch7/hideDelegate/before/Person.java)<br>
> [예시 코드 (Department) - before](/src/main/java/study/refactoring/ch7/hideDelegate/before/Department.java)<br>
> [예시 코드 (Person) - after](/src/main/java/study/refactoring/ch7/hideDelegate/after/Person.java)<br>
> [예시 코드 (Department) - after](/src/main/java/study/refactoring/ch7/hideDelegate/after/Department.java)<br>
- 모듈화 설계를 제대로 하는 핵심은 캡슐화다.
- 캡슐화가 잘 되어 있다면 무언가를 변경해야 할 때 함께 고려해야 할 모듈 수가 적어져서 코드를 변경하기가 훨씬 쉬워진다.

### 절차
1. 위임 객체의 각 메서드에 해당하는 위임 메서드를 서버에 생성한다.
2. 클라이언트가 위임 객체 대신 서버를 호출하도록 수정한다. 하나씩 바꿀 때마다 테스트한다.
3. 모두 수정했다면, 서버로부터 위임 객체를 얻는 접근자를 제거한다.
4. 테스트한다.

## 8. 중개자 제거하기
- 클라이언트가 위임 객체의 또 다른 기능을 사용하고 싶을 때마다 위임 메서드들이 점점 많아지면 차라리 클라이언트가 위임 객체를 직접 호출하는 게 나을 수 있다.
- 숨겨야 판단하기는 힘들지만, 필요하면 언제든 균형점을 옮길 수 있으니 시도하면 된다.

### 절차
1. 위임 객체를 얻는 게터를 만든다.
2. 위임 메서드를 호출하는 클라이언트가 모두 이 게터를 거치도록 수정한다. 하나씩 바꿀 때마다 테스트한다.
3. 모두 수정했다면 위임 메서드를 삭제한다.
   - 자동 리팩터링 도구를 사용할 때는 위임 필드를 캡슐화한 다음, 이를 사용하는 모든 메서드를 인라인한다.

## 9. 알고리즘 교체하기
- 문제를 더 확실히 이해하고 훨씬 쉽게 해결하는 방법을 발견하면 리팩터링 한다.
- 거대하고 복잡한 알고리즘을 교체하기란 상당히 어려우니 착수하기 전에 반드시 메서드를 가능한 잘게 나눴는지 확인해야 한다.

### 절차
1. 교체할 코드를 함수 하나에 모은다.
2. 이 함수만을 이용해 동작을 검증하는 테스트를 마련한다.
3. 대체할 알고리즘을 준비한다.
4. 정적 검사를 수행한다.
5. 기존 알고리즘과 새 알고리즘의 결과를 비교하는 테스트를 수행한다. 두 결과가 같다면 리팩터링이 끝난다. 그렇지 않다면 기존 알고리즘을 참고해서 새 알고리즘을 테스트하고 디버깅한다.

## 느낀점
- ~~클라이언트가 데이터를 요청할 때 실제 데이터를 제공해도 되지만, 수정을 막기 위해 복사본을 제공한다. 이때 데이터 구조가 클수록 성능에 영향이 끼칠 수도 있는데 이부분을 걱정만 하지 말고 실제로 측정해본다는 점이 인상적이였고, 실무에 이런 상황이 생기면 성능 테스트를 해보는 게 좋을 것 같단 생각이 들었다.~~
- 디미터의 법칙 (내부 정보를 가능한 한 숨기고 밀접한 모듈과만 상호작용해 결합도를 낮추자는 원칙)이 과도하면 오히려 위임 혹은 래퍼 메서드가 너무 늘어나서 상황에 맞게 조절해야 한다는 부분에서 생각해볼게 많다고 느꼈다. 
- 캡슐화가 여러군데서 수정할 수 있는 로직들을 한 곳에 모아 변화에 유용하게 대처하는 느낌이 들었다.

## 스터디에서 나온 내용 정리
- 객체를 조회할 때 unmodifiable을 사용하지 않고 iterator 형태로 반환하기도 한다.
- 요새는 하드웨어가 좋아져서 데이터를 복사할 때 성능에 대한 우려를 많이 안해도 된다.!