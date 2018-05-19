package by.epam.view;

public class View {

    private String pagePath;
    private ViewType view;

    public enum ViewType {
        FORWARD, REDIRECT
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public void setViewType(ViewType view) {
        this.view = view;
    }

    public String getPagePath() {
        return pagePath;
    }

    public ViewType getViewType() {
        return view;
    }
}
