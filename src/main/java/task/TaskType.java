package task;

public enum TaskType {
    TODO,
    DEADLINE,
    EVENT;

    @Override
    public String toString() {
        return this.name().toLowerCase(); // Formats as "todo", "deadline", "event"
    }
}
