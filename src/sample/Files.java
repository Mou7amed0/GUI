package sample;

public class Files {
    private int idDoc;
    private String title;
    private String client;
    private String ipClient;
    private String path;
    private String date;

    public Files(int idDoc, String title, String client, String ipClient, String path, String date) {
        this.idDoc = idDoc;
        this.title = title;
        this.client = client;
        this.ipClient = ipClient;
        this.path = path;
        this.date = date;
    }


    public int getIdDoc() {
        return idDoc;
    }

    public String getTitle() {
        return title;
    }

    public String getClient() {
        return client;
    }

    public String getIpClient() {
        return ipClient;
    }

    public String getPath() {
        return path;
    }

    public String getDate() {
        return date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
