import de.ur.mi.oop.events.MousePressedEvent;

public interface InputEventListener {
    void handleMouseClick(int x, int y);

    void handleMouseClick(MousePressedEvent event);
}
