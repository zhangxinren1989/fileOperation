package gui.bean;

public class Message {
    private int x;
    private int y;
    private String msg;

    public Message() {
    }

    
    public Message(int x, int y, String msg) {
        this.x = x;
        this.y = y;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    
    
    
}
