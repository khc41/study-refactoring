## 1. 조건문 분해하기
> [예시 코드 (SummerCharge) - before ](/src/main/java/study/refactoring/ch10/decomposeConditional/before/SummerCharge.java) <br>
> [예시 코드 (SummerCharge) - after ](/src/main/java/study/refactoring/ch10/decomposeConditional/after/SummerCharge.java) <br>
- 조건문은 조건을 검사하지만 왜 일어나는지 말해주지 않으므로 복잡성을 가중시킨다.
- 조건문을 분리해서 의도를 살린 이름의 함수 호출로 바꿔주면 전체적인 의도가 확실히 드러난다.

### 절차
1. 조건식과 그 조건식에 딸린 조건절을 각각의 함수로 추출한다.

## 2. 조건식 통합하기
> [예시 코드 (Amount) - before ](/src/main/java/study/refactoring/ch10/consolidateConditionalExpression/before/Amount.java) <br>
> [예시 코드 (Amount) - after ](/src/main/java/study/refactoring/ch10/consolidateConditionalExpression/after/Amount.java) <br>
- 비교하는 조건은 다르지만 그 결과로 수행하는 동작이 같으면 하나로 통합하는 게 낫다.
- 통합하면 하려는 일이 명확해지고, 함수 추출하기까지 이어질 가능성이 높아진다.

### 절차
1. 해당 조건식들 모두에 부수효과가 없는지 확인한다.
    - 부수효과가 있는 조건식들에는 질의 함수와 변경 함수 분리하기를 먼저 적용한다.
2. 조건문 두 개를 선택하여 두 조건문의 조건식들을 논리 연산자로 결합한다.
    - 순차적으로 이뤄지는(레벨이 같은) 조건문은 or로 결합하고, 중첩된 조건문은 and로 결합한다.
3. 테스트한다.
4. 조건이 하나만 남을 때까지 2~3 과정을 반복한다.
5. 하나로 합쳐진 조건식을 함수로 추출할지 고려해본다.

## 3. 중첩 조건문을 보호 구문으로 바꾸기
> [예시 코드 (Salary) - before ](/src/main/java/study/refactoring/ch10/replaceNestedConditionalWithGuardClauses/before/Salary.java) <br>
> [예시 코드 (Salary) - after ](/src/main/java/study/refactoring/ch10/replaceNestedConditionalWithGuardClauses/after/Salary.java) <br>
- 조건문에서 한쪽만 정상인 경우 비정상 조건을 if에서 검사한 다음, 조건이 참이면 함수에서 빠져나온다. 이를 흔히 보호 구문이라고 한다.

### 절차
1. 교체해야 할 조건 중 가장 바깥 것을 선택하여 보호 구문으로 바꾼다.
2. 테스트한다.
3. 1~2 과정을 필요한 만큼 반복한다.
4. 모든 보호 구문이 같은 결과를 반환한다면 보호 구문들의 조건식을 통합한다.

## 4. 조건부 로직을 다형성으로 바꾸기
> [예시 코드1 (BirdType) - before ](/src/main/java/study/refactoring/ch10/replaceConditionalWithPolymorphism/before/BirdType.java) <br>
> [예시 코드1 (BirdType) - after ](/src/main/java/study/refactoring/ch10/replaceConditionalWithPolymorphism/after/BirdType.java) <br>
> [예시 코드1 (Bird) - after ](/src/main/java/study/refactoring/ch10/replaceConditionalWithPolymorphism/after/Bird.java) <br>
> [예시 코드1 (EuropeanSwallow) - after ](/src/main/java/study/refactoring/ch10/replaceConditionalWithPolymorphism/after/EuropeanSwallow.java) <br>
> [예시 코드1 (AfricanSwallow) - after ](/src/main/java/study/refactoring/ch10/replaceConditionalWithPolymorphism/after/AfricanSwallow.java) <br>
> [예시 코드1 (NorwegianBlueParrot) - after ](/src/main/java/study/refactoring/ch10/replaceConditionalWithPolymorphism/after/NorwegianBlueParrot.java) <br>
> ---
> [예시 코드2 (VoyageInvestment) - before ](/src/main/java/study/refactoring/ch10/replaceConditionalWithPolymorphism/before/VoyageInvestment.java) <br>
> [예시 코드2 (VoyageInvestment) - after ](/src/main/java/study/refactoring/ch10/replaceConditionalWithPolymorphism/after/VoyageInvestment.java) <br>
> [예시 코드2 (Rating) - after ](/src/main/java/study/refactoring/ch10/replaceConditionalWithPolymorphism/after/Rating.java) <br>
> [예시 코드2 (ExperiencedChinaRating) - after ](/src/main/java/study/refactoring/ch10/replaceConditionalWithPolymorphism/after/ExperiencedChinaRating.java) <br>
- 조부 로직을 클래스와 다형성을 이용하면 더 확실하게 분리할 수 있다.
- 모든 조건부 로직을 다형성으로 대체해야 하는 것은 아니다.

### 절차
1. 다형적 동작을 표현하는 클래스들이 아직 없다면 만들어준다. 이왕이면 적합한 인스턴스를 알아서 만들어 반환하는 팩터리 함수도 사용하게 한다.
2. 호출하는 코드에서 팩터리 함수를 사용하게 한다.
3. 조건부 로직 함수를 슈퍼클래스로 옮긴다.
   - 조건부 로직이 온전한 함수로 분리되어 있지 않다면 먼저 함수로 추출한다.
4. 서브클래스 중 하나를 선택한다. 서브클래스에서 슈퍼클래스의 조건부 로직 메서드를 오버라이드한다. 조건부 문장 중 선택된 서브클래스에 해당하는 조건절을 서브클래스 메서드로 복사한 다음 적절히 수정한다.
5. 같은 방식으로 각 조건절을 해당 서브클래스에서 메서드로 구현한다.
6. 슈퍼클래스 메서드에는 기본 동작 부분만 남긴다. 혹은 슈퍼클래스가 추상 클래스여야 한다면, 이 메서드를 추상으로 선언하거나 서브클래스에서 처리해야 함을 알리는 에러를 던진다.

## 5. 특이 케이스 추가하기
- 코드베이스에서 특정 값에 대해 똑같이 반응하는 코드가 여러 곳이라면 그 반응들을 한 데로 모으는 게 효율적이다.

### 절차
1. 컨테이너에 특이 케이스인지를 검사하는 속성을 추가하고, false를 반환하게 한다.
2. 특이 케이스 객체를 만든다. 이 객체는 특이 케이스인지를 검사하는 속성만 포함하며, 이 속성은 true를 반환하게 한다.
3. 클라이언트에서 특이 케이스인지를 검사하는 코드를 함수로 추출한다. 모든 클라이언트가 값을 직접 비교하는 대신 방금 추출한 함수를 사용하도록 고친다.
4. 코드에 새로운 특이 케이스 대상을 추가한다. 함수의 반환 값으로 받거나 변환 함수를 적용하면 된다.
5. 특이 케이스를 검사하는 함수 본문을 수정하여 특이 케이스 객체의 속성을 사용하도록 한다.
6. 테스트한다.
7. 여러 함수를 클래스 묶기나 여러 함수를 변환 함수로 묶기를 적용하여 특이 케이스를 처리하는 공통 동작을 새로운 요소로 옮긴다.
   - 특이 케이스 클래스는 간단한 요청에는 항상 같은 값을 반환하는 게 보통이므로, 해당 특이 케이스의 리터럴 레코드를 만들어 활용할 수 있을 것이다.
8. 아직도 특이 케이스 검사 함수를 이용하는 곳이 남아 있다면 검사 함수를 인라인한다.

## 6. 어서션 추가하기
- 어서션은 프로그램이 어떤 상태임을 가정할 채 실행되는지를 다른 개발자에게 알려주는 훌륭한 소통도구이다.

### 절차
1. 참이라고 가정하는 조건이 보이면 그 조건을 명시하는 어서션을 추가한다.

## 7. 제어 플래그를 탈출문으로 바꾸기
- 제어 플래그란 코드의 동작을 변경하는 데 사용되는 변수를 말하며, 어딘가에서 값을 계산해 제어 플래그에 설정한 후 다른 어딘가의 조건문에서 검사하는 형태로 쓰인다.

### 절차
1. 제어 플래그를 사용하는 코드를 함수로 추출할지 고려한다.
2. 제어 플래그를 갱신하는 코드 각각을 적절한 제어문으로 바꾼다. 하나 바꿀 때마다 테스트한다.
   - 제어문으로는 주로 return, break, continue 가 쓰인다.
3. 모두 수정했다면 제어 플래그를 제거한다.

## 느낀점
- 반환점이 하나여야 한다는 규칙은 유용하지 않다고 나와있는데, 이 말이 특히 공감이 됐다. 실무에서 개발을 하다보면 early return을 안하고 마지막에 반환하는 메서드들을 보면 인덴트가 중첩돼서 가독성이 매우 떨어졌다.
- 모든 조건부 로직을 다형성으로 대체해야 하는 것에 동의하지 않는다고 나와있는데, 실무에서 코딩을 하다보면 깔끔하게 코드를 짜야한다는 강박관념에 의해 종종 오버엔지니어링스러운 코드를 짜기도 했고 그런 코드를 목격하기도 했다. 글쓴이의 말처럼 상황에 따라 적절히 적용하는 것이 가독성에 더 도움이 되는 것을 느꼈다. 


