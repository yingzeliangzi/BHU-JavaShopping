
import java.util.*;

/**
 * @author SakurazawaRyoko
 * @version 1.0
 * @date 1/6/2023 1:49 PM
 * @description TODO 不写注释眼睛要瞎了
 */
public class Main {
    public static void main(String[] args) {
        //定义所有商品 用户
        List<Commodity> game = new ArrayList<>();
        List<Commodity> software = new ArrayList<>();
        List<Commodity> hardware = new ArrayList<>();

        game.add(new Commodity("CS: GO", 98, 100));
        game.add(new Commodity("God of War", 238, 100));
        game.add(new Commodity("Outlast 2", 88, 100));

        software.add(new Commodity("Intellij IDEA", 298, 100));
        software.add(new Commodity("WebStorm", 298, 100));
        software.add(new Commodity("CLion", 298, 100));

        hardware.add(new Commodity("RTX5090", 15800, 100));
        hardware.add(new Commodity("i7-12900F", 4800, 100));
        hardware.add(new Commodity("MSI B460M", 1980, 100));

        Map<String, List<Commodity>> commodityMap = new HashMap<>();
        commodityMap.put("game", game);
        commodityMap.put("software", software);
        commodityMap.put("hardware", hardware);

        List<User> userList = new ArrayList<>();
        userList.add(new User("SakurazawaRyoko", "admin", 1));
        userList.add(new User("vip1", "vip1", 2));
        userList.add(new User("vip2", "vip2", 2));
        userList.add(new User("user1", "user1", 3));
        userList.add(new User("user2", "user2", 3));

        //主函数
        //登录环节
        login:
        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Welcome to shop! Please login first. ");
            System.out.print("Username: ");
            String username = scan.next();
            System.out.print("Password: ");
            String password = scan.next();
            for (User user : userList) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    switch (user.getLevel()) {
                        case 1:
                            //管理员登录
                            System.out.println("Welcome, admin. You can add commodity below.");
                            List<Commodity> newCommodity = new ArrayList<>();
                            //管理员添加商品
                            input:
                            while (true) {
                                System.out.print("Input the commodity: ");
                                Commodity newCom = new Commodity();
                                newCom.setName(scan.next());
                                System.out.print("Input the prize: ");
                                newCom.setPrize(scan.nextDouble());
                                System.out.print("Input the stock: ");
                                newCom.setStock(scan.nextInt());
                                newCommodity.add(newCom);
                                //确认yn循环
                                while (true) {
                                    System.out.print("Would you like to add more commodity? (y/n)");
                                    String choice = scan.next();
                                    if (choice.equals("y")) {
                                        break;
                                    } else if (choice.equals("n")) {
                                        break input;
                                    } else {
                                        System.out.println("Input error, please re-enter");
                                    }
                                }
                            }
                            //列出所有商品
                            System.out.println("Here are all the commodities: ");
                            //新增商品
                            for (Commodity com : newCommodity) {
                                System.out.println("Commodity: " + com.getName() + "\tPrize: " + com.getPrize() + "\tStock: " + com.getStock());
                            }
                            //原始商品
                            for (String s : commodityMap.keySet()) {
                                List<Commodity> OldCommodity = commodityMap.get(s);
                                for (Commodity com : OldCommodity) {
                                    System.out.println("Commodity: " + com.getName() + "\tPrize: " + com.getPrize() + "\tStock: " + com.getStock());
                                }
                            }
                            break login;
                        case 2:
                            //vip登录 并显示折扣
                            Random random = new Random();
                            int discount = random.nextInt(9 - 1) + 1;
                            System.out.println("Welcome, vip user " + username + ". You have discount " + (10 - discount) + "0% off today!");
                            //定义大类 并创建购物车
                            String classification;
                            List<Cart> cart = new ArrayList<>();
                            //展示所有大类
                            System.out.println("This shop has classifications: ");
                            for (String s : commodityMap.keySet()) {
                                System.out.print(s + " ");
                            }
                            //开始购买
                            vipbuy:
                            while (true) {
                                System.out.print("\nWhich classification would you want to buy? ('666' to end) ");
                                classification = scan.next();
                                if (Objects.equals(classification, "666")) {
                                    break;
                                }
                                //展示所有小类
                                for (String s : commodityMap.keySet()) {
                                    if (Objects.equals(classification, s)) {
                                        System.out.println(s + " has these commodities below: ");
                                        List<Commodity> commodities = commodityMap.get(s);
                                        int count = 1;
                                        for (Commodity commodity : commodities) {
                                            System.out.println(count + ".Commodity: " + commodity.getName() + " prize: " + commodity.getPrize() + " stock: " + commodity.getStock());
                                            count++;
                                        }
                                        //输入小类序号 666退出
                                        System.out.println("Which commodity would you want to buy? (Number)('666' to end)");
                                        int num = scan.nextInt();
                                        if (num >= 1 && num <= count) {
                                            Commodity commodity = commodityMap.get(classification).get(num - 1);
                                            //输入购买数量 验证 加入购物车
                                            System.out.print("You chose: " + commodity.getName() + ". Please input count: ");
                                            int stock = scan.nextInt();
                                            if (stock >= 1 && stock <= commodity.getStock()) {
                                                Cart c = new Cart(commodity.getName(), commodity.getPrize(), stock);
                                                cart.add(c);
                                            } else {
                                                System.out.println("Sorry. We don't have much stock.");
                                            }
                                        } else if (num == 666) {
                                            break vipbuy;
                                        }
                                    }
                                }
                            }
                            //结算
                            double total = 0;
                            System.out.println("You have bought:");
                            for (Cart c : cart) {
                                System.out.println("Commodity: " + c.getName() + " prize: " + c.getPrize() + " stock: " + c.getStock());
                                total += c.getPrize() * c.getStock();
                            }
                            System.out.println("The origin total prize is: $" + total);
                            System.out.println("The discounted total prize is: $" + total * discount / 10);
                            break login;
                        case 3:
                            //普通用户购买 并创建购物车
                            String classification2;
                            List<Cart> cart2 = new ArrayList<>();
                            //展示所有大类
                            System.out.println("This shop has classifications: ");
                            for (String s : commodityMap.keySet()) {
                                System.out.print(s + " ");
                            }
                            buy:
                            while (true) {
                                System.out.print("\nWhich classification would you want to buy? ('666' to end) ");
                                classification2 = scan.next();
                                if (Objects.equals(classification2, "666")) {
                                    break;
                                }
                                //展示所有小类
                                for (String s2 : commodityMap.keySet()) {
                                    if (Objects.equals(classification2, s2)) {
                                        System.out.println(s2 + " has these commodities below: ");
                                        List<Commodity> commodities = commodityMap.get(s2);
                                        int count = 1;
                                        for (Commodity commodity : commodities) {
                                            System.out.println(count + ".Commodity: " + commodity.getName() + " prize: " + commodity.getPrize() + " stock: " + commodity.getStock());
                                            count++;
                                        }
                                        //输入小类序号 666退出
                                        System.out.println("Which commodity would you want to buy? (Number)('666' to end)");
                                        int num = scan.nextInt();
                                        if (num >= 1 && num <= count) {
                                            Commodity commodity = commodityMap.get(classification2).get(num - 1);
                                            //输入购买数量 验证 加入购物车
                                            System.out.print("You chose: " + commodity.getName() + ". Please input count: ");
                                            int stock = scan.nextInt();
                                            if (stock >= 1 && stock <= commodity.getStock()) {
                                                Cart c = new Cart(commodity.getName(), commodity.getPrize(), stock);
                                                cart2.add(c);
                                            } else {
                                                System.out.println("Sorry. We don't have much stock.");
                                            }
                                        } else if (num == 666) {
                                            break buy;
                                        }
                                    }
                                }
                            }
                            double total2 = 0;
                            System.out.println("You have bought:");
                            for (Cart c2 : cart2) {
                                System.out.println("Commodity: " + c2.getName() + " prize: " + c2.getPrize() + " stock: " + c2.getStock());
                                total2 += c2.getPrize() * c2.getStock();
                            }
                            System.out.println("The origin total prize is: $" + total2);
                            double discount2 = 0;
                            double total2dis = total2;
                            for(;total2dis>=200;total2dis-=200){
                                discount2+=10;
                            }
                            System.out.println("The discounted total prize is: $" + (total2-discount2));
                            break login;
                    }
                }
            }
            System.out.println("Username or password wrong. Please retry.");
        }
    }
}
