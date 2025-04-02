package MyDesign;

/**
 *
 * @author QUANG DIEN
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

/**
 *
 * @author QUANG DIEN
 */
public class List_Menu<E extends Object> extends JList<E> {

    private final DefaultListModel<E> model;
    private final List<E> allItems;
    private final List<Boolean> visibilityFlags;
    private int selectedIndex = -1;
    private int overIndex = -1;
    private EventMenuSelected event;

    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
    }

    public List_Menu() {
        model = new DefaultListModel<>();
        allItems = new ArrayList<>();
        visibilityFlags = new ArrayList<>();
        setModel(model);
        setupMouseEvents();
    }

    private void setupMouseEvents() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    int index = locationToIndex(e.getPoint());
                    if (index >= 0 && index < model.getSize()) {
                        selectedIndex = index;
                        if (event != null) {
                            int actualIndex = getActualIndex(index);
                            event.selected(actualIndex);  // Use the mapped index
                        }
                    }
                    repaint();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                overIndex = -1;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index != overIndex) {
                    overIndex = index;
                    repaint();
                }
            }
        });
    }

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object o, int index, boolean selected, boolean focus) {
                Model_Menu data = o instanceof Model_Menu ? (Model_Menu) o : new Model_Menu("", o + "", Model_Menu.MenuType.EMPTY);
                Item_Menu item = new Item_Menu(data);
                item.setSelected(selectedIndex == index);
                item.setOver(overIndex == index);
                return item;
            }
        };
    }

    public void addItem(Model_Menu data) {
        allItems.add((E) data);
        visibilityFlags.add(true);
        model.addElement((E) data);
        System.out.println("All items: " + allItems);  // Log all items
    }

    public void setMenuItemVisible(int index, boolean visible) {
        if (index < 0 || index >= allItems.size()) {
            System.out.println("Invalid menu index: " + index);
            return;
        }

        // Log the current visibility state before updating
        System.out.println("Before update: " + visibilityFlags);

        visibilityFlags.set(index, visible);

        // Log the updated visibility state
        System.out.println("After update: " + visibilityFlags);

        model.clear();
        for (int i = 0; i < allItems.size(); i++) {
            if (visibilityFlags.get(i)) {
                model.addElement(allItems.get(i));
            }
        }

        revalidate();
        repaint();
    }   

    private int getActualIndex(int visibleIndex) {
        int count = -1;
        for (int i = 0; i < allItems.size(); i++) {
            if (visibilityFlags.get(i)) {
                count++;
                if (count == visibleIndex) {
                    return i;  // Return actual index in allItems
                }
            }
        }
        return -1; // This should not happen if indexes are valid
    }
}

