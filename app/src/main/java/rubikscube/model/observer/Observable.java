package rubikscube.model.observer;

public interface Observable 
{
	public void addObserver(Observer newObserver);
	public void notifyObservers();
}
