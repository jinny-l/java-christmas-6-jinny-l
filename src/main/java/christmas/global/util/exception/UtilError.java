package christmas.global.util.exception;

import christmas.global.exception.Error;

public enum UtilError implements Error {

    FILE_IO("파일 입출력 관련 오류입니다."),
    PARSING("파싱 관련 오류입니다.");

    private final String message;

    UtilError(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
