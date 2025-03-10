## 1. 함수 옮기기
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
