package service;

import java.util.List;

public interface Actions {
    void testConnection();

    List<Integer> checkBoundOfAmount(int selectedIndex);

    int[] searchWithAudi();
}
