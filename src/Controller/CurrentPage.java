package Controller;

import java.util.HashMap;

public class CurrentPage {
    private int pageId;
    private HashMap<String, Object> context;

    public CurrentPage(int pageId) {
        this.pageId = pageId;
        this.context = new HashMap<>();
    }

    public int getPageId() {
        return pageId;
    }

    public void setContext(String key, Object value) {
        context.put(key, value);
    }

    public Object getContext(String key) {
        return context.get(key);
    }

    public HashMap<String, Object> getAllContext() {
        return context;
    }
}
