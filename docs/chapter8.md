## 1. 함수 옮기기
> ### 중첩 함수를 최상위로 옮기기
> [예시 코드 (Gps) - before ](/src/main/java/study/refactoring/ch8/moveFunction/before/Gps.java) <br>
> [예시 코드 (Point) - before ](/src/main/java/study/refactoring/ch8/moveFunction/before/Point.java) <br>
> [예시 코드 (Gps) - after ](/src/main/java/study/refactoring/ch8/moveFunction/after/Gps.java) <br>
> [예시 코드 (Point) - after ](/src/main/java/study/refactoring/ch8/moveFunction/after/Point.java) <br>
> ### 다른 클래스로 옮기기
> [예시 코드 (Account) - before](/src/main/java/study/refactoring/ch8/moveFunction/before/Account.java) <br>
> [예시 코드 (AccountType) - before](/src/main/java/study/refactoring/ch8/moveFunction/before/AccountType.java) <br>
> [예시 코드 (Account) - after](/src/main/java/study/refactoring/ch8/moveFunction/after/Account.java) <br>
> [예시 코드 (AccountType) - after](/src/main/java/study/refactoring/ch8/moveFunction/after/AccountType.java) <br>
- 좋은 소프트웨어 설계의 핵심은 모듈화가 얼마나 잘 되어 있느냐를 뜻하는 모듈성이다.
- 이는 프로그램의 어딘가를 수정하려 할 때 해당 기능과 깊이 관련된 작은 일부만 이해해도 가능하게 해주는 능력이다.
- 객체 지향 프로그래밍의 핵심 모듈화 컨텍스트는 클래스다.
- 함수를 옮길지 말지 정하기 쉽지 않을 땐 대상 함수의 현재 컨텍스트와 후보 컨텍스트를 둘러보면 도움이 된다.

### 절차
1. 선택한 함수가 현재 컨택스트에서 사용중인 모든 프로그램 요소를 살펴본다. 이 요소들 중에도 함께 옮겨야 할 게 있는지 고민해본다.
   - 호출되는 함수 중 함께 옮길 게 있다면 대체로 그 함수를 먼저 옮기는 게 낫다. 얽혀 있는 함수가 여러 개라면 다른 곳에 미치는 영향이 적은 함수부터 옮기도록 하자.
   - 하위 함수들의 호출자가 고수준 함수 하나뿐이면 먼저 하위 함수들을 고수준 함수에 인라인한 다음, 고수준 함수를 옮기고, 옮긴 위치에서 개별 함수들로 다시 추출하자.
2. 선택한 함수가 다형 메서드인지 확인한다.
    - 객체 지향 언어에서는 같은 메서드가 슈퍼클래스나 서브클래스에도 선언되어 있는지까지 고려해야 한다.
3. 선택한 함수를 타깃 컨텍스트로 복사한다(이때 원래의 함수를 소스 함수라 하고 복사해서 만든 새로운 함수를 타깃 함수라 한다). 타깃 함수가 새로운 터전에 잘 자리 잡도록 다듬는다.
    - 함수 분몬에서 소스 컨텍스트의 요소를 사용한다면 해당 요소들을 매개변수로 넘기거나 소스 컨텍스트 자체를 참조로 넘겨준다.
    - 함수를 옮기게 되면 새로운 컨텍스트에 어울리는 새로운 이름으로 바꿔줘야 할 경우가 많다. 필요하면 바꿔준다.
4. 정적 분석을 수행한다.
5. 소스 컨텍스트에서 타깃 함수를 참조할 방법을 찾아 반영한다.
6. 소스 함수를 타깃 함수의 위임 함수가 되도록 수정한다.
7. 테스트한다.
8. 소스 함수를 인라인할지 고민해본다.
    - 소스 함수는 언제까지라도 위임 함수로 남겨둘 수 있다. 하지만 소스 함수를 호출하는 곳에서 타깃 함수를 직접 호출하는 데 무리가 없다면 중간 단계(소스 함수)는 제거하는 편이 낫다.

## 2. 필드 옮기기
> [예시 코드 (Customer) - before ](/src/main/java/study/refactoring/ch8/moveField/before/Customer.java) <br>
> [예시 코드 (CustomerContract) - before ](/src/main/java/study/refactoring/ch8/moveField/before/CustomerContract.java) <br>
> [예시 코드 (Customer) - after ](/src/main/java/study/refactoring/ch8/moveField/after/Customer.java) <br>
> [예시 코드 (CustomerContract) - after ](/src/main/java/study/refactoring/ch8/moveField/after/CustomerContract.java) <br>
- 주어진 문제에 적합한 데이터구조를 활용하면 동작 코드는 자연스럽게 단순하고 직관적으로 짜여진다.
- 현재 데이터 구조가 적절치 않음을 깨닫게 되면 곧바로 수정해야 한다.

### 절차
1. 소스 필드가 캡슐화되어 있지 않다면 캡슐화한다.
2. 테스트한다.
3. 타깃 객체에 필드 (와 접근자 메서드들)를 생성한다.
4. 정적 검사를 수행한다.
5. 소스 객체에서 타깃 객체를 참조할 수 있는지 확인한다.
   - 기존 필드나 메서드 중 타깃 객체를 넘겨주는 게 있을지 모른다. 없다면 이런 기능의 메서드를 쉽게 만들 수 있는지 살펴본다. 간단치 않다면 타깃 객체를 저장할 새 필드를 소스 객체에 생성하자. 
   이는 영구적인 변경이 되겠지만, 더 넓은 맥락에서 리팩터링을 충분히 하고 나면 다시 없앨 수 있을 때도 있다.
6. 접근자들이 타깃 필드를 사용하도록 수정한다.
   - 여러 소스에서 같은 타깃을 공유한다면, 먼저 세터를 수정하여 타깃 필드와 소스 필드 모두를 갱신하게 하고, 이어서 일관성을 깨뜨리는 갱신을 검출할 수 있도록 어서션을 추가하자. 모든 게 잘 마무리되었다면 접근자들이 타깃 필드를 사용하도록 수정한다.
7. 테스트한다.
8. 소스 필드를 제거한다.
9. 테스트한다.

## 3. 문장을 함수로 옮기기
> [예시 코드 (Render) - before ](/src/main/java/study/refactoring/ch8/moveStatementsIntoFunction/before/Render.java) <br>
> [예시 코드 (Render) - after ](/src/main/java/study/refactoring/ch8/moveStatementsIntoFunction/after/Render.java) <br>
- 중복 제거는 코드를 건강하게 관리하는 가장 효과적인 방법 중 하나다.

### 절차
1. 반복 코드가 함수 호출 부분과 멀리 떨어져 있다면 문장 슬라이드하기를 적용해 근처로 옮긴다.
2. 타깃 함수를 호출하는 곳이 한 곳뿐이면, 단순히 소스 위치에서 해당 코드를 잘라내어 피호출 함수로 복사하고 테스트한다. 이 경우라면 나머지 단계는 무시한다.
3. 호출자가 둘 이상이면 호출자 중 하나에서 '타깃 함수 호출 부분과 그 함수로 옮기려는 문장들을 함께' 다른 함수로 추출한다. 추출한 함수에 기억하기 쉬운 임시 이름을 지어준다.
4. 다른 호출자 모두가 방금 추출한 함수를 사용하도록 수정한다. 하나씩 수정할 때마다 테스트한다.
5. 모든 호출자가 새로운 함수를 사용하게 되면 원래 함수를 새로운 함수 안으로 인라인한 후 원래 함수를 제거한다.
6. 새로운 함수의 이름을 원래 함수의 이름으로 바꿔준다(함수 이름 바꾸기).
   - 더 나은 이름이 있다면 그 이름을 쓴다.

## 4. 문장을 호출한 곳으로 옮기기
> [예시 코드 (Render) - before ](/src/main/java/study/refactoring/ch8/movestatementsToCallers/before/Render.java) <br>
> [예시 코드 (Render) - after ](/src/main/java/study/refactoring/ch8/movestatementsToCallers/after/Render.java) <br>
- 여러 곳에서 사용하던 기능이 일부 호출자에게는 다르게 동작하도록 바뀌어야 한다면 달라진 동작을 함수에서 꺼내 해당 호출자로 옮겨야한다.

### 절차
1. 호출자가 한두 개뿐이고 피호출 함수도 간단한 단순한 상황이면, 피호출 함수의 처음(혹은 마지막) 줄(들)을 잘라내어 호출자(들)로 복사해 넣는다(필요하면 적당히 수정한다). 테스트만 통과하면 이번 리팩터링은 여기서 끝이다.
2. 더 복잡한 상황에서는, 이동하지 '않길' 원하는 모든 문장을 함수로 추출한 다음 검색하기 쉬운 임시 이름을 지어준다.
   - 대상 함수가 서브클래스에서 오버라이드됏따면 오버라이드한 서브클래스들의 메서드 모두에서 동일하게, 남길 부분을 메서드로 추출한다. 이때 남겨질 메서드의 본문은 모든 클래스에서 똑같아야 한다. 그런 다음 (슈퍼클래스의 메서드만 남기고) 서브클래스들의 메서드를 제거한다.
3. 원래 함수를 인라인한다.
4. 추출된 함수의 이름을 원래 함수의 이름으로 변경한다(함수 이름 바꾸기)
   - 더 나은 이름이 떠오르면 그 이름을 사용하자.

## 5. 인라인 코드를 함수 호출로 바꾸기
- 함수는 여러 동작을 하나로 묶고 이름이 코드의 목적을 말해주기 때문에 코드를 이해하기 쉽게 해준다.

### 절차
1. 인라인 코드를 함수 호출로 대체한다.
2. 테스트한다.


## 느낀점
- 중첩 함수를 사용하는 javascript와는 문법 자체가 달라 신경 쓸 포인트가 줄었다는 것에 대해 기분이 좋았다.
