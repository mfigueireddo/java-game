package management;

public enum ErrorStatus {
    SUCCESS,
    MIN_SCREEN_SIZE;

    public int GetExitCode() {
        return this.ordinal();
    }
}