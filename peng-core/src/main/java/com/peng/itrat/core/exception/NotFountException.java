package com.peng.itrat.core.exception;

import com.peng.itrat.core.enums.Messages;

public class NotFountException extends JeeException {
    public NotFountException(Messages messages) {
        super(messages);
    }

    public NotFountException(String msg) {
        super(msg + "不存在");
    }
}
