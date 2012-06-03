package wecui.util;

/**
 * Stores data for a rendering tile for previewing
 * 
 * @author yetanotherx
 */
public class BaseBlock {

    private Vector3 point;
    private int type;
    private int data;

    public BaseBlock(Vector3 point, int type, int data) {
        this.point = point;
        this.type = type;
        this.data = data;
    }

    public BaseBlock(int x, int y, int z, int type, int data) {
        this.type = type;
        this.data = data;
        this.point = new Vector3(x, y, z);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseBlock other = (BaseBlock) obj;
        if (this.point != other.point && (this.point == null || !this.point.equals(other.point))) {
            return false;
        }
        if (this.type != other.type) {
            return false;
        }
        if (this.data != other.data) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + (this.point != null ? this.point.hashCode() : 0);
        hash = 59 * hash + this.type;
        hash = 59 * hash + this.data;
        return hash;
    }

    public Vector3 getPoint() {
        return point;
    }

    public int getData() {
        return data;
    }

    public int getType() {
        return type;
    }
    
}
