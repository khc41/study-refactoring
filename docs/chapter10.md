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

