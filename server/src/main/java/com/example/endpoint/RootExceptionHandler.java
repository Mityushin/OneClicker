/*
 * Copyright 2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.ConnectException;

/**
 * @author Dmitry Mityushin
 * @since 1.0.0
 */
@Slf4j
@ControllerAdvice
public class RootExceptionHandler {
    private static final String MESSAGE_CANNOT_CREATE_TRANSACTION_EXCEPTION = "Ошибка создания транзакции";
    private static final String MESSAGE_CONNECT_EXCEPTION = "Ошибка доступа к целевой подсистеме";
    private static final String MESSAGE_EXCEPTION = "Неизвестная внутренняя ошибка";

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = MESSAGE_CANNOT_CREATE_TRANSACTION_EXCEPTION)
    @ExceptionHandler(value = CannotCreateTransactionException.class)
    public void handleCannotCreateTransactionException(CannotCreateTransactionException ex) {
        log.warn(MESSAGE_CANNOT_CREATE_TRANSACTION_EXCEPTION, ex);
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = MESSAGE_CONNECT_EXCEPTION)
    @ExceptionHandler(value = ConnectException.class)
    public void handleConnectException(ConnectException ex) {
        log.warn(MESSAGE_CONNECT_EXCEPTION, ex);
    }

    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR, reason = MESSAGE_EXCEPTION)
    @ExceptionHandler(value = Exception.class)
    public void handleAnyException(Exception ex) {
        log.error(MESSAGE_EXCEPTION, ex);
    }
}
