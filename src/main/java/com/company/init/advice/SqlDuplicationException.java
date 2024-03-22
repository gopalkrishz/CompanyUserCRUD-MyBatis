package com.company.init.advice;

import java.sql.SQLIntegrityConstraintViolationException;

public class SqlDuplicationException extends SQLIntegrityConstraintViolationException {
    public SqlDuplicationException(String reason) {
        super(reason);
    }

    public SqlDuplicationException(String reason, Throwable cause) {
        super(reason, cause);
    }
}
