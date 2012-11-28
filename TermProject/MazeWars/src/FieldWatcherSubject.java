public interface FieldWatcherSubject {
    public void watchForFieldChange(FieldObserver observer);
    public void signalFieldChange(Field field);
}
