package me.nglow.java8to11.optional;

import org.springframework.expression.spel.ast.OpInc;

import java.util.Optional;

public class OnlineClass {

    private Integer id;

    private String title;

    private boolean closed;

    public Progress progress;

    /**
     * 아래와 같이 사용하는것도 권장하지 않음
     */
//    public Optional<Progress> progress;

    public OnlineClass(Integer id, String title, boolean closed) {
        this.id = id;
        this.title = title;
        this.closed = closed;
    }

    /**
     * 파라미터로 쓰지말고 Return값으로 쓰는것을 권장한다.
     * @return
     */
    public Optional<Progress> getProgress() {
        return Optional.ofNullable(progress);
    }

    /**
     * 아래와 같이 사용하면 얼마든지 Optional객체 자체값에 null을 넣을 수 있어 NullPointerException이 발생 -> Option을 쓰는 의미가 없어짐
     * @param progress
     */
    public void setProgress(Optional<Progress> progress) {
        progress.ifPresent(p -> this.progress = p);
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }



    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}

