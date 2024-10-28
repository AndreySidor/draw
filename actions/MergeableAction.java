package actions;

public interface MergeableAction {

    Boolean merge(MergeableAction action);

    void stopMerge();

    Boolean canMerge();

}
