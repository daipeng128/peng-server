package com.peng.itrat.core.exception;

import com.peng.itrat.core.enums.Messages;

public class NotLoginException extends JeeException {
    public NotLoginException() {
        super(Messages.UN_LOGIN);
    }
}