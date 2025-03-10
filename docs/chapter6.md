## 1. 함수 추출하기
> [예시 코드](/src/main/java/study/refactoring/ch6/ExtractMethod.java)
- 코드 조각을 찾아 무슨 일을 하는지 파악한 다음, 독립된 함수로 추출하고 목적에 맞는 이름을 붙인다.
- 코드를 보고 무슨 일을 하는지 파악하는 데 한참이 걸린다면 그 부분을 함수로 추출한 뒤 '무슨일'에 걸맞는 이름을 짓는다.

### 절차
1. 함수를 새로 만들고 목적을 잘 드러내는 이름을 붙인다(어떻게가 아닌 무엇을 하는지가 드러나야 한다).
2. 추출할 코드를 원본 함수에서 복사하여 새 함수에 붙여넣는다.
3. 추출한 코드 중 원본 함수의 지역 변수를 참조하거나 추출한 함수의 유효범위를 벗어나는 변수는 없는지 검사한다. 있다면 매개변수로 전달한다.
4. 변수를 다 처리했다면 컴파일한다.
5. 원본 함수에서 추출한 코드 부분을 새로 만든 함수를 호출하는 문장으로 바꾼다(즉, 추출한 함수로 일을 위임한다).
6. 테스트한다.
7. 다른 코드에 방금 추출한 것과 똑같거나 비슷한 코드가 없는지 살핀다. 있다면 방금 추출한 새 함수를 호출하도록 바꿀지 검토한다(인라인 코드를 함수 호출로 바꾸기).

#### 예시: 유효 범위를 벗어나는 변수가 없을 때
- 간단히 추출한다.

#### 예시: 지역 변수를 사용할 때
- 변수를 사용하지만 다른 값을 다시 대입하지는 않을 때 매개변수로 넘긴다.

#### 예시: 지역 변수의 값을 변경할 때
- 변수를 쪼개서 임시 변수를 새로 하나 만들어 그 변수에 대입한다.
  - 변수가 추출된 코드 안에서만 사용될 경우 변수 조작을 모두 한곳에 처리하도록 모아두면 편하다.
  - 변수가 추출한 함수 밖에서 사용될 때는 변수에 대입된 새 값을 반환한다.
- 값을 반환할 변수가 여러 개라면?
  - 각각을 반환하는 함수 여러 개로 만든다.

## 2. 함수 인라인하기
> [예시 코드](/src/main/java/study/refactoring/ch6/InlineMethod.java)
- 함수 본문이 이름만큼 명확하거나 깔끔하게 리팩터링할 경우 함수를 제거한다.
- 리팩터링 과정에서 잘못 추출된 함수들도 다시 인라인한다.
- 간접 호출을 과하게 쓰는 코드도 인라인 대상이다.

### 절차
1. 다형 메서드인지 확인한다.
   - 서브 클래스에서 오버라이드 하는 메서드는 인라인하면 안 된다.
2. 인라인할 함수를 호출하는 곳을 모두 찾는다.
3. 각 호출문을 함수 본문으로 교체한다.
4. 하나씩 교체할 때마다 테스트한다.
   - 인라인 작업을 한 번에 처리할 필요는 없다. 인라인 하기가 까다로운 부분이 있다면 일단 남겨두고 여유가 생길 때마다 틈틈이 처리한다.
5. 함수 정의(원래 함수)를 삭제한다.

## 3. 변수 추출하기
> [예시 코드](/src/main/java/study/refactoring/ch6/ExtractMethod.java)
- 지역변수를 활용하면 복잡한 로직을 구성하는 단계마다 이름을 붙일 수 있어서 코드의 목적을 훨씬 명확하게 드러낼 수 있다.

### 절차
1. 추출하려는 표현식에 부작용은 없는지 확인한다.
2. 불변 변수를 하나 선언하고 이름을 붙일 표현식의 복제본을 대입한다.
3. 원본 표현식을 새로 만든 변수로 교체한다.
4. 테스트한다.
5. 표현식을 여러 고셍서 사용한다면 각각을 새로 만든 변수로 교체한다. 하나 교체할 때마다 테스트한다.

- 객체는 특정 로직과 데이터를 외부와 공유할 정보를 설명해주는 적당한 크기의 문맥이 되어주는 기능이 있어 공통 동작을 추상화해두면 매우 유용하다.

## 4. 변수 인라인하기
- 변수의 이름이 원래 표현식과 다를 바 없거나, 주변 코드를 리팩터링하는 데 방해가 되면 인라인한다.

### 절차
1. 대입문의 우변(표현식)에서 부작용이 생기지는 않는지 확인한다.
2. 변수가 불변으로 선언되지 않았다면 불변으로 만든 후 테스트한다.
    - 이렇게 하면 변수에 값이 단 한번만 대입되는지 확인할 수 있다.
3. 이 변수를 가장 처음 사용하는 코드를 찾아서 대입문 우변의 코드로 바꾼다.
4. 테스트한다.
5. 변수를 사용하는 부분을 모두 교체할 때까지 이 과정을 반복한다.
6. 변수 선언문과 대입문을 지운다.
7. 테스트한다.

## 5. 함수 선언 바꾸기
> [예시 코드](/src/main/java/study/refactoring/ch6/ChangeFunctionDeclaration.java)
- 함수는 소프트웨어 시스템의 구성 요소를 조립하는 연결부 역할을 하며, 잘 정의하면 새로운 부분을 추가하기가 쉬워지는 반면, <br>잘못 정의하면 지속적인 방해요인으로 작용하여 소프트웨어 동작을 파악하기 어려워지고 요구사항이 바뀔때 적절히 수정하기 어렵게 한다.
- 가장 중요한 요소는 함수의 이름이다. 함수가 무엇을 하는지 이름에서 명확하게 드러나게 해야한다.
- 주석을 이용해 함수의 목적을 설명해보면 주석이 함수의 이름으로 바뀌어 되돌아온다.
- 매개변수를 잘 정의하면 다른 모듈과의 결합을 제거할 수 있어 함수의 활용범위를 넓힐 수 있다.

### 간단한 절차 
1. 매개변수를 제거하려거든 먼저 함수 본문에서 제거 대상 매개변수를 참조하는 곳은 없는지 확인한다.
2. 메서드 선언을 원하는 형태로 바꾼다.
3. 기존 메서드 선언을 참조하는 부분을 모두 찾아서 바뀐 형태로 수정한다.
4. 테스트한다.

### 마이그레이션 절차
1. 이어지는 추출 단계를 수월하게 만들어야 한다면 함수의 본문을 적절히 리팩터링한다.
2. 함수 본문을 새로운 함수로 추출한다.
    - 새로 만들 함수 이름이 기존 함수와 같다면 일단 검색하기 쉬운 이름을 임시로 붙여둔다.
3. 추출한 함수에 매개변수를 추가해야 한다면 '간단한 절차'를 따라 추가한다.
4. 테스트한다.
5. 기존 함수를 인라인한다.
6. 이름을 임시로 붙여뒀다면 함수 선언 바꾸기를 한 번 더 적용해서 원래 이름으로 되돌린다.
7. 테스트한다.

- 다형성을 구현한 경우 원하는 형태의 메서드를 새로 만들어서 원래 함수를 호출하는 전달 메서드로 활용한다.
- 공개된 API를 리팩터링할 때는 새 함수를 추가한 뒤, 기존 함수를 deprecated 처리하고 클라이언트가 모두 이전하면 기존 함수를 삭제한다.

## 6. 변수 캡슐화하기
> [예시 코드](/src/main/java/study/refactoring/ch6/EncapsulateVariable.java)
- 접근할 수 있는 범위가 넓은 데이터를 옮길 때는 먼저 그 데이터로의 접근을 독점하려는 함수를 만드는 식으로 캡슐화하는 것이 가장 좋은 방법일 때가 많다.
- 캡슐화하면 데이터 변경 전 검증이나 변경 후 추가 로직을 쉽게 끼워 넣을 수 있다.
- 불변 데이터는 가변 데이터보다 캡슐화할 이유가 적다.

### 절차 
1. 변수로의 접근과 갱신을 전담하는 캡슐화 함수들을 만든다.
2. 정적 검사를 수행한다.
3. 변수를 직접 참조하던 부분을 모두 적절한 캡슐화 함수 호출로 바꾼다. 하나씩 바꿀 때마다 테스트한다.
4. 변수의 접근 범위를 제한한다.
   - 변수로의 직접 접근을 막을 수 없을 때도 잇다. 그럴 때는 변수 이름을 바꿔서 테스트해보면 해당 변수를 참조하는 곳을 쉽게 찾아낼 수 있다.
5. 테스트한다.
6. 변수 값이 레코드라면 레코드 캡슐화하기를 적용할지 고려해본다.

- 복제본 만들기와 클래스로 감싸는 방식은 레코드 구조에서 깊이가 1인 속성들까지만 효과가 있다.

## 7. 변수 이름 바꾸기
- 변수는 프로그래머가 하려는 일에 관해 많은 것을 설명해준다.

### 절차
1. 폭넓게 쓰이는 변수라면 변수 캡슐화하기를 고려한다.
2. 이름을 바꿀 변수를 참조하는 곳을 모두 찾아서, 하나씩 변경한다.
    - 다른 코드베이스에서 참조하는 변수는 외부에 공개된 변수이므로 이 리팩터링을 적용할 수 없다.
    - 변수 값이 변하지 않는다면 다른 이름으로 복제본을 만들어서 하나씩 점진적으로 변경한다. 하나씩 바꿀 때마다 테스트한다.
3. 테스트한다.

## 8. 매개변수 객체 만들기
> [예시 코드](/src/main/java/study/refactoring/ch6/IntroduceParameterObject.java)
- 데이터 항목 여러 개가 몰려다니면 데이터 구조 하나로 모아준다.
- 데이터 사이의 관계가 명확해지고, 매개변수 수가 줄어든다. 또한, 모든 함수가 원소를 참조할 때 항상 똑같은 이름을 사용하기 때문에 일관성도 높여준다.
- 데이터 구조에 공통으로 적용되는 동작을 추출해서 함수로 만들면, 문제 영역을 훨씬 간결하게 표현하는 새로운 추상개념으로 격상되면서 코드의 개념적인 그림을 다시 그릴 수 있다.

### 절차
1. 적당한 데이터 구조가 아직 마련되어 있지 않다면 새로 만든다.  
    - 개인적으로 클래스로 만드는 걸 선호한다. 나중에 동작까지 함께 묶기 좋기 때문이다. 주로 데이터 구조를 값 객체로 만든다.
2. 테스트한다.
3. 함수 선언 바꾸기로 새 데이터 구조를 매개변수로 추가한다.
4. 테스트한다.
5. 함수 호출 시 새로운 데이터 구조 인스턴스를 넘기도록 수정한다. 하나씩 수정할 때마다 테스트한다.
6. 기존 매개변수를 사용하던 코드를 새 데이터 구조의 원소를 사용하도록 바꾼다.
7. 다 바꿨다면 기존 매개변수를 제거하고 테스트한다.

## 9. 여러 함수를 클래스로 묶기
> [예시 코드](/src/main/java/study/refactoring/ch6/CombineFunctionsIntoClass.java)
- 공통 데이터를 중심으로 긴밀하게 엮여 작동하는 함수 무리를 클래스로 묶으면, 공유하는 공통 환경을 더 명확하게 표현할 수 있고 각 함수에 전달되는 인수를 줄여서 객체 안에서의 함수 호출을 간결하게 만들 수 있다.
- 클래스로 묶을 때의 두드러진 장점은 클라이언트가 객체의 핵심 데이터를 변경할 수 있고, 파생 객체들을 일관되게 관리할 수 있다는 것이다.

### 절차
1. 함수들이 공유하는 공통 데이터 레코드를 캡슐화한다.
   - 공통 데이터가 레코드 구조로 묶여있지 않다면 먼저 매개변수 객체 만들기 로 데이터를 하나로 묶는 레코드를 만든다.
2. 공통 레코드를 사용하는 함수 각각을 새 클래스로 옮긴다(함수 옮기기)
   - 공통 레코드의 맴버는 함수 호출문의 인수 목록에서 제거한다.
3. 데이터를 조작하는 로직들은 함수로 추출해서 새 클래스로 옮긴다.

## 10. 여러 함수를 변환 함수로 묶기
> [예시 코드](/src/main/java/study/refactoring/ch6/CombineFunctionsIntoTransform.java)
- 도출 로직이 여러군데서 반복되면 모아서 검색과 갱신을 일관된 장소에서 처리하고 로직의 중복도 막을 수 있다.
- 변환함수를 사용하거나 클래스로 묶는 방법이 있는데, 원본 데이터가 코드 안에서 갱신될 때는 클래스로 묶는 편이 훨씬 낫다.

### 절차 
1. 변환할 레코드를 입력받아서 값을 그대로 반환하는 변환 함수를 만든다.
   - 이 작업은 대체로 깊은 복사로 처리해야 한다. 변환 함수가 원본 레코드를 바꾸지 않는지 검사하는 테스트를 마련해두면 도움될 때가 많다.
2. 묶을 함수 중 함수 하나를 골라서 본문 코드를 변환 함수로 옮기고, 처리 결과를 레코드에 새 필드로 기록한다. 그런 다음 클라이언트 코드가 이 필드를 사용하도록 수정한다.
   - 로직이 복잡하면 함수 추출하기부터 한다.
3. 테스트한다.
4. 나머지 관련 함수도 위 과정에 따라 처리한다.

- 클라이언트가 데이터를 변경하면 일관성이 깨지므로 클래스로 묶어 불변성을 확보한다.

## 11. 단계 쪼개기
> [예시 코드1](/src/main/java/study/refactoring/ch6/SplitPhase.java)<br>
> [예시 코드2](/src/main/java/study/refactoring/ch6/SplitPhaseCommandLine.java)<br>
> [예시 코드3](/src/main/java/study/refactoring/ch6/SplitPhaseTranslator.java)<br>
- 서도 다른 두 대상을 한꺼번에 다루는 코드를 발견하면 각각을 별개 모듈로 나누는 방법을 모색한다.
- 가장 간편한 방법은 동작을 연이은 두 단계로 쪼개는 것이다.

### 절차
1. 두 번째 단계에 해당하는 코드를 독립 함수로 추출한다.
2. 테스트한다.
3. 중간 데이터 구조를 만들어서 앞에서 추출한 함수의 인수로 추가한다.
4. 테스트한다.
5. 추출한 두 번째 단계 함수의 매개변수를 하나씩 검토한다. 그중 첫 번째 단계에서 사용되는 것은 중간 데이터 구조로 옮긴다. 하나씩 옮길 때마다 테스트한다.
   - 간혹 두 번째 단계에서 사용하면 안 되는 매개변수가 있다. 이럴 때는 각 매개변수를 사용한 결과를 중간 데이터 구조의 필드로 추출하고, 이 필드의 값을 설정하는 문장을 호출한 곳으로 옮긴다.
6. 첫 번째 단계 코드를 함수로 추출하면서 중간 데이터 구조를 반환하도록 만든다.
   - 이때 첫 번째 단계를 변환기객체로 추출해도 좋다.


## 느낀점
- 변수의 사용처를 파악하기 위해 불변으로 만들고 테스트하는 부분이 있는데 불변성을 이렇게 활용할 수 있다는 것이 놀라웠다.
- 예시가 자바스크립트로 되어있는데, 동적타입 언어라 자바로 예시를 작성하기 어려운 부분들이 많이 있었다.
- 상황에 따라 쪼개고 합치는 과정을 진행하는데 상황에 맞는 리팩토링이 중요하다는 것을 깨달았고, 이것은 많은 연습과 경험이 뒷받침돼야 한다는 것을 느꼈다. <br>평소에 테스트 코드를 짜고 리팩토링을 진행하면서 상황에 맞는 리팩토링 전략을 구사할 수 있도록 실력을 키워볼 것이다.