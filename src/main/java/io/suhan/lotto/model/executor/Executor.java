package io.suhan.lotto.model.executor;

/**
 * 실행 가능한 동작을 정의하는 범용 인터페이스
 * <p>
 * 이 인터페이스를 구현한 클래스는 특정 작업을 수행하는 {@link #execute()} 메소드를
 * 통해 공통된 Entrypoint를 제공합니다.
 */
public interface Executor {
    void execute();
}
