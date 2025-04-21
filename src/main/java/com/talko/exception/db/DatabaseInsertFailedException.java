package com.talko.exception.db;

import com.talko.exception.DatabaseException;
import com.talko.exception.ErrorCode;

public class DatabaseInsertFailedException extends DatabaseException {

    public DatabaseInsertFailedException() {
        super(ErrorCode.DATABASE_INSERT_FAILED);
    }

    public DatabaseInsertFailedException(String message) {
        super(ErrorCode.DATABASE_INSERT_FAILED, message);
    }

    public DatabaseInsertFailedException(String message, Throwable cause) {
        super(ErrorCode.DATABASE_INSERT_FAILED, message, cause);
    }

}
