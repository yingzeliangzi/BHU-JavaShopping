
/**
 * @author SakurazawaRyoko
 * @version 1.0
 * @date 1/6/2023 3:21 PM
 * @description TODO
 */
public class Cart {
    private String name;
    private double prize;
    private int stock;

    public Cart() {
    }

    public Cart(String name, double prize, int stock) {
        this.name = name;
        this.prize = prize;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
